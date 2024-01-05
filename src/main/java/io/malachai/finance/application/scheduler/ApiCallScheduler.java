package io.malachai.finance.application.scheduler;

import io.malachai.finance.application.dto.ScheduleDto;
import io.malachai.finance.application.service.DocumentService;
import io.malachai.finance.application.service.ScheduleHistoryService;
import io.malachai.finance.application.service.ScheduleService;
import io.malachai.finance.common.exception.AlreadyScheduledException;
import io.malachai.finance.common.exception.NotScheduledException;
import io.malachai.finance.common.exception.TaskNotCanceledException;
import io.malachai.finance.infra.ScheduleManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class ApiCallScheduler {

  private static final Logger LOG = Logger.getLogger(ApiCallScheduler.class.getName());
  private final ScheduleService scheduleService;
  private final DocumentService documentService;
  private final ScheduleHistoryService scheduleHistoryService;
  private final ThreadPoolTaskScheduler scheduler;
  private final ScheduleManager scheduleManager;

  public ApiCallScheduler(ScheduleService scheduleService, DocumentService documentService, ScheduleHistoryService scheduleHistoryService, ThreadPoolTaskScheduler scheduler, ScheduleManager scheduleManager) {
    this.scheduleService = scheduleService;
    this.documentService = documentService;
    this.scheduleHistoryService = scheduleHistoryService;
    this.scheduler = scheduler;
    this.scheduleManager = scheduleManager;
  }

  public List<ScheduleDto> getActiveSchedules() {
    List<ScheduleDto> activeSchedules = scheduleService.getMultipleSchedules(scheduleManager.getRegisteredScheduleIds());
    activeSchedules.forEach(schedule -> {
      schedule.isActive = true;
    });
    return activeSchedules;
  }

  public List<ScheduleDto> getInactiveSchedules() {
    List<ScheduleDto> allSchedules = scheduleService.getSchedules();
    return allSchedules
        .stream().filter(schedule -> !scheduleManager.isIdRegistered(schedule.id))
        .collect(Collectors.toList());
  }

  public void addSchedule(Long scheduleId) throws AlreadyScheduledException{
    if(scheduleManager.isIdRegistered(scheduleId)) {
      throw new AlreadyScheduledException("schedule id "+scheduleId+" is already scheduled");
    }
    ScheduleDto schedule = scheduleService.getSchedule(scheduleId);
    ApiCallTask apiCallTask = new ApiCallTask(
        schedule,
        documentService,
        scheduleHistoryService
    );
    ScheduledFuture<?> apiCall = scheduler.schedule(apiCallTask, new CronTrigger(schedule.cronExpression));
    scheduleManager.register(scheduleId, apiCall);
    LOG.info("schedule added: "+scheduleId+" "+schedule.cronExpression);
  }

  public void removeSchedule(Long scheduleId) {
    if(!scheduleManager.isIdRegistered(scheduleId)) {
      throw new NotScheduledException("schedule id "+scheduleId+" is not scheduled");
    }
    scheduleManager.cancel(scheduleId);
    LOG.info("schedule removed: "+scheduleId);
  }

  public void removeSchedulesByApiId(Long apiModelId) {
    List<Long> activeScheduleIds = getActiveSchedules()
        .stream().map(ScheduleDto::getId)
        .toList();
    scheduleService.getSchedulesByApiModelId(apiModelId)
        .stream().filter(scheduleDto -> activeScheduleIds.contains(scheduleDto.id))
        .forEach(scheduleDto -> removeSchedule(scheduleDto.id));
  }

}
