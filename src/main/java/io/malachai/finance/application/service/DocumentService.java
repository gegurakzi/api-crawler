package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.DocumentDto;

public interface DocumentService {
  void saveDocument(DocumentDto document, String reference);
}