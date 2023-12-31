package io.malachai.finance.application.dto;

import io.malachai.finance.domain.Schedule;
import io.malachai.finance.domain.ScheduleHistory;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class ScheduleHistoryDto {

  public Long id;
  public String apiName;
  public String apiUrl;
  public String apiHeader;
  public String reference;
  public String cronExpression;
  public String state;
  public String message;
  public Timestamp timestamp;

  public static ScheduleHistoryDto of(ScheduleHistory scheduleHistory) {
    return ScheduleHistoryDto.builder()
        .id(scheduleHistory.getId())
        .apiName(scheduleHistory.getApiName())
        .apiUrl(scheduleHistory.getApiUrl())
        .apiHeader(scheduleHistory.getApiHeader())
        .reference(scheduleHistory.getReference())
        .cronExpression(scheduleHistory.getCronExpression())
        .state(scheduleHistory.getState())
        .message(scheduleHistory.getMessage())
        .timestamp(scheduleHistory.getTimestamp())
        .build();
  }

  public ScheduleHistoryDto setState(String state) {
    this.state = state;
    return this;
  }
  public ScheduleHistoryDto setMessage(String message) {
    this.message = message;
    return this;
  }
}
