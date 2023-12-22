package io.malachai.finance.domain.repository;

import io.malachai.finance.domain.Document;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface DocumentDBRepository extends Repository<Document, Long>, DocumentRepository {
}
