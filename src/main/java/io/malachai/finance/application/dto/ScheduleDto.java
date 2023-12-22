package io.malachai.finance.application.dto;

import io.malachai.finance.domain.Schedule;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ScheduleDto {

  public Long id;
  public Boolean isActive;
  public ApiModelDto apiModelDto;
  public String cronExpression;

  public static ScheduleDto of(Schedule schedule) {
    return ScheduleDto.builder()
        .id(schedule.getId())
        .apiModelDto(ApiModelDto.of(schedule.getApiModel()))
        .cronExpression(schedule.getCronExpression())
        .build();
  }
}
