package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.ScheduleHistoryDto;
import io.malachai.finance.domain.ScheduleHistory;
import io.malachai.finance.domain.repository.ScheduleHistoryRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleHistoryServiceImpl implements ScheduleHistoryService {

  private final ScheduleHistoryRepository scheduleHistoryRepository;

  public ScheduleHistoryServiceImpl(ScheduleHistoryRepository scheduleHistoryRepository) {
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
        historyDto.getApiName(),
        historyDto.getApiUrl(),
        historyDto.getApiHeader(),
        historyDto.getReference(),
        historyDto.getCronExpression(),
        historyDto.getState(),
        historyDto.getMessage(),
        historyDto.getTimestamp()
    );
    scheduleHistoryRepository.save(history);
  }
}
