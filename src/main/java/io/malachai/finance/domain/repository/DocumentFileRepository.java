package io.malachai.finance.domain.repository;

import io.malachai.finance.domain.Document;

import java.util.List;
import java.util.Optional;

public class DocumentFileRepository implements DocumentRepository {
  @Override
  public Optional<List<Document>> findAll() {
    return Optional.empty();
  }

  @Override
  public Optional<Document> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public Optional<List<Document>> findAllByIdIn(List<Long> ids) {
    return Optional.empty();
  }

  @Override
  public void save(Document document) {

  }
}
