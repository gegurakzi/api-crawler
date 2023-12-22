package io.malachai.finance.application.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DocumentDto {

  public Long id;
  public String body;

  public static DocumentDto of(DocumentDto document) {
    return DocumentDto.builder()
        .id(document.getId())
        .body(document.getBody())
        .build();
  }
}
