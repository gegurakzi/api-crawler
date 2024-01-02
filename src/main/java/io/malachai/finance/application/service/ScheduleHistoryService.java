package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.ScheduleHistoryDto;
import io.malachai.finance.domain.ApiModel;
import io.malachai.finance.domain.Schedule;
import io.malachai.finance.domain.ScheduleHistory;
import io.malachai.finance.domain.repository.ScheduleHistoryRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleHistoryService {

  private final ScheduleHistoryRepository scheduleHistoryRepository;

  public ScheduleHistoryService(ScheduleHistoryRepository scheduleHistoryRepository) {
    this.scheduleHistoryRepository = scheduleHistoryRepository;
  }

  public List<ScheduleHistoryDto> getHistories() {
    return scheduleHistoryRepository.findAll()
        .orElse(new ArrayList<>())
        .stream().map(ScheduleHistoryDto::of)
        .collect(Collectors.toList());
  }

  public void updateHistory(ScheduleHistoryDto historyDto) {
    historyDto.timestamp = new Timestamp(System.currentTimeMillis());
    ScheduleHistory history = new ScheduleHistory(
        historyDto.getId(),
        new Schedule(
            historyDto.scheduleDto.getId(),
            new ApiModel(
                historyDto.scheduleDto.apiModelDto.getId(),
                historyDto.scheduleDto.apiModelDto.getUrl(),
                historyDto.scheduleDto.apiModelDto.getHeader()
            ),
            historyDto.scheduleDto.getReference(),
            historyDto.scheduleDto.getCronExpression()
        ),
        historyDto.getState(),
        historyDto.getMessage(),
        historyDto.getTimestamp()
    );
    scheduleHistoryRepository.save(history);
  }
}
