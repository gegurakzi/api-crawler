package io.malachai.finance.application.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DocumentDto {

  public String name;
  public String body;

}
