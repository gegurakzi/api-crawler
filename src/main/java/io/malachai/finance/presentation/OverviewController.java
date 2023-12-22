package io.malachai.finance.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/overview")
public class OverviewController {

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String overview(Model model) {
    return "overview/index";
  }

}
