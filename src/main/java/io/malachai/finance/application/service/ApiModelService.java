package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.ApiModelDto;
import io.malachai.finance.common.exception.NoApiModelFoundException;
import io.malachai.finance.domain.ApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ApiModelService {

  List<ApiModelDto> getApiModels();

  ApiModelDto getApiModel(Long apiModelId);

  void updateApiModel(ApiModelDto apiModelDto);

  List<String> getApiGroupSet();
}
