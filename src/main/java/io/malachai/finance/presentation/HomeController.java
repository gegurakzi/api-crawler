package io.malachai.finance.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class HomeController {

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() {
    return "redirect:/overview";
  }

}
