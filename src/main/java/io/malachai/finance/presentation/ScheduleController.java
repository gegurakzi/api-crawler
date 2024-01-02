package io.malachai.finance.presentation;

import io.malachai.finance.application.dto.ApiModelDto;
import io.malachai.finance.application.dto.ScheduleDto;
import io.malachai.finance.application.request.ScheduleCreateRequest;
import io.malachai.finance.application.scheduler.ApiCallScheduler;
import io.malachai.finance.application.service.ApiModelService;
import io.malachai.finance.application.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
  private final ApiCallScheduler apiCallScheduler;
  private final ScheduleService scheduleService;
  private final ApiModelService apiModelService;

  public ScheduleController(ApiCallScheduler apiCallScheduler, ScheduleService scheduleService, ApiModelService apiModelService) {
    this.apiCallScheduler = apiCallScheduler;
    this.scheduleService = scheduleService;
    this.apiModelService = apiModelService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String home(Model model) {
    model.addAttribute("activeSchedules", apiCallScheduler.getActiveSchedules());
    model.addAttribute("inactiveSchedules", apiCallScheduler.getInactiveSchedules());
    return "schedules/index";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String create(Model model) {
    model.addAttribute("apis", apiModelService.getApiModels());
    return "schedules/create";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String createSchedule(@RequestParam Long apiModelId,
                               @RequestParam String reference,
                               @RequestParam String cronExpression) {
    ScheduleDto schedule = ScheduleDto.builder()
        .apiModelDto(apiModelService.getApiModel(apiModelId))
        .reference(reference)
        .cronExpression(cronExpression)
        .build();
    scheduleService.updateSchedule(schedule);
    return "redirect:/schedules";
  }

  @RequestMapping(value = "/{scheduleId}/run", method = RequestMethod.GET)
  public String runSchedule(Model model, @PathVariable Long scheduleId) {
    apiCallScheduler.addSchedule(scheduleId);
    return "redirect:/schedules";
  }

  @RequestMapping(value = "/{scheduleId}/stop", method = RequestMethod.GET)
  public String stopSchedule(@PathVariable Long scheduleId) {
    apiCallScheduler.removeSchedule(scheduleId);
    return "redirect:/schedules";
  }
}
