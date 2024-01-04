package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.ApiModelDto;
import io.malachai.finance.application.dto.ScheduleDto;
import io.malachai.finance.application.scheduler.ApiCallScheduler;
import io.malachai.finance.common.exception.NoApiModelFoundException;
import io.malachai.finance.common.exception.NoScheduleFoundException;
import io.malachai.finance.domain.ApiModel;
import io.malachai.finance.domain.Schedule;
import io.malachai.finance.domain.repository.ApiModelRepository;
import io.malachai.finance.domain.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final ApiModelRepository apiModelRepository;

  public ScheduleService(ScheduleRepository scheduleRepository, ApiModelRepository apiModelRepository) {
    this.scheduleRepository = scheduleRepository;
    this.apiModelRepository = apiModelRepository;
  }

  public List<ScheduleDto> getSchedules() {
    return scheduleRepository.findAll()
        .orElse(new ArrayList<>())
        .stream().map(ScheduleDto::of)
        .collect(Collectors.toList());
  }

  public ScheduleDto getSchedule(Long scheduleId) {
    Schedule schedule = scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new NoScheduleFoundException("No Schedule found: id="+scheduleId));
    return ScheduleDto.of(schedule);
  }

  public List<ScheduleDto> getMultipleSchedules(List<Long> scheduleIds) {
    return scheduleRepository.findAllByIdIn(scheduleIds)
        .orElse(new ArrayList<>())
        .stream().map(ScheduleDto::of)
        .collect(Collectors.toList());
  }

  public List<ScheduleDto> getSchedulesByApiModelId(Long apiModelId) {
    return scheduleRepository.findAllByApiModel(
        apiModelRepository.findById(apiModelId)
            .orElseThrow(() -> new NoApiModelFoundException("no api found: id="+apiModelId))
        ).orElse(new ArrayList<>())
        .stream().map(ScheduleDto::of)
        .collect(Collectors.toList());
  }

  public void updateSchedule(ScheduleDto scheduleDto) {
    Schedule schedule = new Schedule(
        scheduleDto.id,
        new ApiModel(
            scheduleDto.apiModelDto.id,
            scheduleDto.apiModelDto.name,
            scheduleDto.apiModelDto.apiGroup,
            scheduleDto.apiModelDto.url,
            scheduleDto.apiModelDto.header
        ),
        scheduleDto.reference,
        scheduleDto.cronExpression
    );
    scheduleRepository.save(schedule);
  }

  public void updateSchedule(ScheduleDto scheduleDto, String apiGroup) {
    apiModelRepository.findAllByApiGroup(apiGroup)
        .orElse(new ArrayList<>())
        .stream().map(ApiModelDto::of)
        .forEach(apiModelDto -> {
          Schedule schedule = new Schedule(
              scheduleDto.id,
              new ApiModel(
                  apiModelDto.id,
                  apiModelDto.name,
                  apiModelDto.apiGroup,
                  apiModelDto.url,
                  apiModelDto.header
              ),
              scheduleDto.reference,
              scheduleDto.cronExpression
          );
          scheduleRepository.save(schedule);
        });
  }

  public void deleteSchedulesByApiId(Long apiModelId){
    scheduleRepository.deleteAllByApiModel(
        apiModelRepository.findById(apiModelId)
            .orElseThrow(() -> new NoApiModelFoundException("no api found: id="+apiModelId))
    );
  }
}
