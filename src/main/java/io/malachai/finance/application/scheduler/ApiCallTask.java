package io.malachai.finance.application.scheduler;

import io.malachai.finance.application.dto.DocumentDto;
import io.malachai.finance.application.dto.ScheduleDto;
import io.malachai.finance.application.dto.ScheduleHistoryDto;
import io.malachai.finance.application.service.DocumentService;
import io.malachai.finance.application.service.ScheduleHistoryService;
import io.malachai.finance.domain.ScheduleHistory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.logging.Logger;

public class ApiCallTask implements Runnable {

  private static final Logger LOG = Logger.getLogger(ApiCallTask.class.getName());
  private final ScheduleDto schedule;
  private final DocumentService documentService;
  private final ScheduleHistoryService scheduleHistoryService;

  public ApiCallTask(ScheduleDto schedule, DocumentService documentService, ScheduleHistoryService scheduleHistoryService) {
    this.schedule = schedule;
    this.documentService = documentService;
    this.scheduleHistoryService = scheduleHistoryService;
  }

  @Override
  public void run() {
    LOG.info("API called: url="+schedule.apiModelDto.url+", header="+schedule.apiModelDto.header+", filePath="+schedule.reference);
    ScheduleHistoryDto history = ScheduleHistoryDto.builder()
            .scheduleDto(schedule)
            .build();
    LOG.info("headers: "+Arrays.toString(schedule.apiModelDto.header.split(",")));
    HttpClient client = HttpClient.newBuilder().build();
    HttpRequest request;
    String[] headers = schedule.apiModelDto.header.split(",");
    if(headers.length > 1) {
      request = HttpRequest.newBuilder()
          .GET()
          .uri(URI.create(schedule.apiModelDto.url))
          .headers(headers)
          .build();
    } else {
      request = HttpRequest.newBuilder()
          .GET()
          .uri(URI.create(schedule.apiModelDto.url))
          .build();
    }
    HttpResponse<String> response;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      history.setState("ERROR").setMessage("Failed to retrieve HttpResponse");
      throw new RuntimeException(e);
    }
    DocumentDto document = DocumentDto.builder()
        .name("file name" /* TODO: 파일 이름 */)
        .body(response.body())
        .build();
    try {
      documentService.saveDocument(document, schedule.reference);
    } catch (IOException e) {
      history.setState("ERROR").setMessage("Failed to save file");
      throw new RuntimeException(e);
    }
    history.setState("SUCCESS");
    scheduleHistoryService.updateHistory(history);
  }
}
