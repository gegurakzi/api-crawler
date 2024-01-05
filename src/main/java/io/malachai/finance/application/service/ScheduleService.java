package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {
  public List<ScheduleDto> getSchedules();
  public ScheduleDto getSchedule(Long scheduleId);
  public List<ScheduleDto> getMultipleSchedules(List<Long> scheduleIds);
  public List<ScheduleDto> getSchedulesByApiModelId(Long apiModelId);
  public void updateSchedule(ScheduleDto scheduleDto);
  public void updateSchedule(ScheduleDto scheduleDto, String apiGroup);
  public void deleteSchedulesByApiId(Long apiModelId);
}
