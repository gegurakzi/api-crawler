package io.malachai.finance.presentation;

import io.malachai.finance.application.dto.ApiModelDto;
import io.malachai.finance.application.request.ApiModelCreateRequest;
import io.malachai.finance.application.service.ApiModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/apis")
public class ApiModelController {
  private final ApiModelService apiModelService;

  public ApiModelController(ApiModelService apiModelService) {
    this.apiModelService = apiModelService;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String home(Model model) {
    model.addAttribute("apis", apiModelService.getApiModels());
    return "apis/index";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String create(Model model) {
    return "apis/create";
  }
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String createApiModel(@RequestParam String url,
                               @RequestParam(required = false) String header) {
    apiModelService.updateApiModel(ApiModelDto.builder()
        .url(url)
        .header(header)
        .build()
    );
    return "redirect:/apis";
  }

}
