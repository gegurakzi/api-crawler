package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.ApiModelDto;
import io.malachai.finance.common.exception.NoApiModelFoundException;
import io.malachai.finance.domain.ApiModel;
import io.malachai.finance.domain.repository.ApiModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApiModelService {

  private final ApiModelRepository apiModelRepository;

  public ApiModelService(ApiModelRepository apiModelRepository) {
    this.apiModelRepository = apiModelRepository;
  }

  public List<ApiModelDto> getApiModels() {
    return apiModelRepository.findAll()
        .orElse(new ArrayList<>())
        .stream().map(ApiModelDto::of)
        .collect(Collectors.toList());
  }

  public ApiModelDto getApiModel(Long apiModelId) {
    return ApiModelDto.of(apiModelRepository.findById(apiModelId)
        .orElseThrow(() -> new NoApiModelFoundException("no api found: id="+apiModelId)));
  }

  public void updateApiModel(ApiModelDto apiModelDto) {
    ApiModel apiModel = new ApiModel(
        apiModelDto.id,
        apiModelDto.url,
        apiModelDto.header
    );
    System.out.println(apiModelDto.url +" "+ apiModelDto.header);
    apiModelRepository.save(apiModel);
  }

}
