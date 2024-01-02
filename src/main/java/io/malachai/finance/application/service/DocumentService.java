package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.DocumentDto;

import java.io.IOException;

public interface DocumentService {
  void saveDocument(DocumentDto document, String reference) throws IOException;
}