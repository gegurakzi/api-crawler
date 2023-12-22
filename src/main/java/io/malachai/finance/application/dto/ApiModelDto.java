package io.malachai.finance.application.dto;

import io.malachai.finance.domain.ApiModel;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiModelDto {

  public Long id;
  public String url;
  public String header;

  public static ApiModelDto of(ApiModel apiModel) {
    return ApiModelDto.builder()
        .id(apiModel.getId())
        .url(apiModel.getUrl())
        .header(apiModel.getHeader())
        .build();
  }
}
