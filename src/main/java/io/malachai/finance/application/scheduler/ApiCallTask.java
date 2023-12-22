package io.malachai.finance.application.scheduler;

import io.malachai.finance.application.dto.ApiModelDto;
import io.malachai.finance.application.service.CatalogService;
import io.malachai.finance.application.service.ScheduleService;
import io.malachai.finance.domain.ApiModel;
import io.malachai.finance.domain.Document;
import io.malachai.finance.domain.Schedule;

import java.util.logging.Logger;

public class ApiCallTask implements Runnable {

  private static final Logger LOG = Logger.getLogger(ApiCallTask.class.getName());
  private final Long scheduleId;
  private final ApiModelDto apiModel;
  private final CatalogService catalogService;

  public ApiCallTask(Long scheduleId, ApiModelDto apiModel, CatalogService catalogService) {
    this.scheduleId = scheduleId;
    this.apiModel = apiModel;
    this.catalogService = catalogService;
  }

  @Override
  public void run() {
    // TODO: API 콜
    LOG.info("API called: url="+apiModel.url+", header="+apiModel.header);
    Document document = new Document();
    catalogService.saveDocument(document, apiModel.docType);
  }

}
