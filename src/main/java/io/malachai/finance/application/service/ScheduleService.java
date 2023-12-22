package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.ScheduleDto;
import io.malachai.finance.common.exception.NoScheduleFoundException;
import io.malachai.finance.domain.ApiModel;
import io.malachai.finance.domain.Schedule;
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

  public ScheduleService(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
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

  public List<ScheduleDto> getMultipleSchedules(List<Long> ids) {
    return scheduleRepository.findAllByIdIn(ids)
        .orElse(new ArrayList<>())
        .stream().map(ScheduleDto::of)
        .collect(Collectors.toList());
  }

  public void updateSchedule(ScheduleDto scheduleDto) {
    Schedule schedule = new Schedule(
        scheduleDto.id,
        new ApiModel(
            scheduleDto.apiModelDto.id,
            scheduleDto.apiModelDto.url,
            scheduleDto.apiModelDto.header
        ),
        scheduleDto.reference,
        scheduleDto.cronExpression
    );
    scheduleRepository.save(schedule);
  }

}
