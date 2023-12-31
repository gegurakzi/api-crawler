package io.malachai.finance.domain.repository;

import io.malachai.finance.domain.ApiModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ApiModelRepository extends Repository<ApiModel, Long>{

  Optional<List<ApiModel>> findAll();
  Optional<ApiModel> findById(Long id);
  Optional<List<ApiModel>> findAllByApiGroup(String group);
  void save(ApiModel apiModel);

}
