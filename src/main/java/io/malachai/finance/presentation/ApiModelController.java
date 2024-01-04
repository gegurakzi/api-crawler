package io.malachai.finance.presentation;

import io.malachai.finance.application.dto.ApiModelDto;
import io.malachai.finance.application.scheduler.ApiCallScheduler;
import io.malachai.finance.application.service.ApiModelService;
import io.malachai.finance.application.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/apis")
public class ApiModelController {
  private final ApiCallScheduler apiCallScheduler;
  private final ApiModelService apiModelService;
  private final ScheduleService scheduleService;

  public ApiModelController(ApiCallScheduler apiCallScheduler, ApiModelService apiModelService, ScheduleService scheduleService) {
    this.apiCallScheduler = apiCallScheduler;
    this.apiModelService = apiModelService;
    this.scheduleService = scheduleService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String home(Model model) {
    model.addAttribute("apis", apiModelService.getApiModels());
    return "apis/index";
  }

  @RequestMapping(value = "/{apiModelId}", method = RequestMethod.GET)
  public String apiModel(@PathVariable Long apiModelId,
                         Model model) {
    ApiModelDto apiModel = apiModelService.getApiModel(apiModelId);
    if(apiModel == null) return "redirect:/apis";
    model.addAttribute("api", apiModel);
    return "apis/detail";
  }
  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String createApiModel() {
    return "apis/create";
  }
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String createApiModel(@RequestParam(required = false) Long id,
                               @RequestParam String name,
                               @RequestParam String group,
                               @RequestParam String url,
                               @RequestParam(required = false) String header,
                               @RequestParam(required = false) String cascade) {
    if(cascade != null) {
      apiCallScheduler.removeSchedulesByApiId(id);
      scheduleService.deleteSchedulesByApiId(id);
    }
    apiModelService.updateApiModel(ApiModelDto.builder()
        .id(id)
        .name(name)
        .apiGroup(group)
        .url(url)
        .header(header)
        .build()
    );
    return "redirect:/apis";
  }

}
