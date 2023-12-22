package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.DocumentDto;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DocumentFileService implements DocumentService{

  private static final Logger LOG = Logger.getLogger(DocumentFileService.class.getName());
  @Override
  public void saveDocument(DocumentDto document, String reference) {
    // TODO: 파일 저장
    LOG.info("document saved: "+document.body);
  }
}
