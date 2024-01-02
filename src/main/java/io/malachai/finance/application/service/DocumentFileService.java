package io.malachai.finance.application.service;

import io.malachai.finance.application.dto.DocumentDto;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class DocumentFileService implements DocumentService {

  private static final Logger LOG = Logger.getLogger(DocumentFileService.class.getName());
  @Override
  public void saveDocument(DocumentDto document, String reference) throws IOException {
    try (FileOutputStream output = new FileOutputStream(reference+"/"+document.getName()+".json")) {
      output.write(document.body.getBytes());
    } catch (IOException e) {
      throw new IOException(e);
    }
    LOG.info("document saved at path "+reference+"/"+document.getName());
  }
}
