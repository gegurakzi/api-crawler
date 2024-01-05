package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.ScheduleHistoryDto;
import io.malachai.finance.domain.ScheduleHistory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ScheduleHistoryService {
  List<ScheduleHistoryDto> getHistories();
  void updateHistory(ScheduleHistoryDto historyDto);
}
