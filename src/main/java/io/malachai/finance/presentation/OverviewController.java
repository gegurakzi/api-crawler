package io.malachai.finance.presentation;

import io.malachai.finance.application.service.ScheduleHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/overview")
public class OverviewController {

  private final ScheduleHistoryService scheduleHistoryService;

  public OverviewController(ScheduleHistoryService scheduleHistoryService) {
    this.scheduleHistoryService = scheduleHistoryService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String overview(Model model) {
    model.addAttribute("histories", scheduleHistoryService.getHistories());
    return "overview/index";
  }

}
