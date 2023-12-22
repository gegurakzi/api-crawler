package io.malachai.finance.application.scheduler;

import io.malachai.finance.application.dto.ApiModelDto;
import io.malachai.finance.application.dto.DocumentDto;
import io.malachai.finance.application.dto.ScheduleDto;
import io.malachai.finance.application.service.DocumentService;
import io.malachai.finance.domain.Document;

import java.util.logging.Logger;

public class ApiCallTask implements Runnable {

  private static final Logger LOG = Logger.getLogger(ApiCallTask.class.getName());
  private final ScheduleDto schedule;
  private final DocumentService documentService;

  public ApiCallTask(ScheduleDto schedule, DocumentService documentService) {
    this.schedule = schedule;
    this.documentService = documentService;
  }

  @Override
  public void run() {
    // TODO: API 콜
    LOG.info("API called: url="+schedule.apiModelDto.url+", header="+schedule.apiModelDto.header+", saveType="+schedule.reference);
    DocumentDto document = DocumentDto.builder()
        .id(null)
        .body(null)
        .build();
    documentService.saveDocument(document, schedule.reference);
  }
}
