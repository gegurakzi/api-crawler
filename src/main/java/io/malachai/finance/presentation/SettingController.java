package io.malachai.finance.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/settings")
public class SettingController {

  @RequestMapping(value = "/model", method = RequestMethod.POST)
  public String submitApiModel() {
    return "redirect:/settings";
  }

}
