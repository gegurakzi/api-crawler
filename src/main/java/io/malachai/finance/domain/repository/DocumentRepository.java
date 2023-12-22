package io.malachai.finance.domain.repository;

import io.malachai.finance.domain.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository {

  Optional<List<Document>> findAll();
  Optional<Document> findById(Long id);
  Optional<List<Document>> findAllByIdIn(List<Long> ids);
  void save(Document document);

}
