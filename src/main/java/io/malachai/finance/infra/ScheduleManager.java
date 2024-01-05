package io.malachai.finance.infra;

import io.malachai.finance.common.exception.TaskNotCanceledException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduleManager {

  private final Map<Long, ScheduledFuture<?>> schedules;

  public ScheduleManager() {
    this.schedules = new HashMap<>();
  }

  public List<Long> getRegisteredScheduleIds() {
    return schedules.keySet().stream().toList();
  }

  public ScheduledFuture<?> get(Long scheduleId) {
    return schedules.get(scheduleId);
  }

  public void register(Long scheduleId, ScheduledFuture<?> apiCall) {
    schedules.put(scheduleId, apiCall);
  }

  public void cancel(Long scheduleId) {
    ScheduledFuture<?> apiCall = schedules.get(scheduleId);
    if(apiCall.cancel(true)) {
      schedules.remove(scheduleId);
    } else throw new TaskNotCanceledException("task not canceled properly: id=" + scheduleId);
  }

  public boolean isIdRegistered(Long scheduleId) {
    return schedules.containsKey(scheduleId);
  }
}
