package io.malachai.finance.application.scheduler;

import io.malachai.finance.application.dto.ScheduleDto;
import io.malachai.finance.application.service.DocumentService;
import io.malachai.finance.application.service.ScheduleHistoryService;
import io.malachai.finance.application.service.ScheduleService;
import io.malachai.finance.common.exception.NotScheduledException;
import io.malachai.finance.infra.ScheduleManager;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.data.Index.atIndex;

import static org.mockito.Mockito.any;


public class ApiCallSchedulerTest {

  private final ScheduleService stubScheduleService = Mockito.mock(ScheduleService.class);
  private final DocumentService stubDocumentService = Mockito.mock(DocumentService.class);
  private final ScheduleHistoryService stubScheduleHistoryService = Mockito.mock(ScheduleHistoryService.class);
  private final ThreadPoolTaskScheduler stubThreadPoolTaskScheduler = Mockito.mock(ThreadPoolTaskScheduler.class);
  private final ScheduleManager stubScheduleManager = Mockito.mock(ScheduleManager.class);

  @BeforeEach
  public void setUp() {
    // Schedules in DB (1L, 2L, 3L, 4L, 5L)
    List<ScheduleDto> everySchedules = new ArrayList<>();
    everySchedules.add(ScheduleDto.builder()
        .id(1L).build());
    everySchedules.add(ScheduleDto.builder()
        .id(2L).build());
    everySchedules.add(ScheduleDto.builder()
        .id(3L).build());
    everySchedules.add(ScheduleDto.builder()
        .id(4L).build());
    everySchedules.add(ScheduleDto.builder()
        .id(5L).build());
    // Schedule to be registered/removed (3L)
    ScheduleDto specificSchedule = ScheduleDto.builder()
        .id(3L)
        .cronExpression("0 0 0 * * ?")
        .build();
    // Universal schedule list (1L, 2L, 3L)
    List<ScheduleDto> specificSchedules = new ArrayList<>();
    specificSchedules.add(ScheduleDto.builder()
        .id(1L).build());
    specificSchedules.add(ScheduleDto.builder()
        .id(2L).build());
    specificSchedules.add(ScheduleDto.builder()
        .id(3L).build());
    Mockito.when(stubScheduleService.getSchedules()).thenReturn(everySchedules);
    Mockito.when(stubScheduleService.getSchedule(3L)).thenReturn(specificSchedule);
    Mockito.when(stubScheduleService.getMultipleSchedules(any())).thenReturn(specificSchedules);
    Mockito.when(stubScheduleService.getSchedulesByApiModelId(any())).thenReturn(specificSchedules);

    // Registered Schedules(1L, 2L)
    List<Long> registeredIds = new ArrayList<>();
    registeredIds.add(1L);
    registeredIds.add(2L);
    Mockito.when(stubScheduleManager.getRegisteredScheduleIds()).thenReturn(registeredIds);
    Mockito.when(stubScheduleManager.isIdRegistered(1L)).thenReturn(true);
    Mockito.when(stubScheduleManager.isIdRegistered(2L)).thenReturn(true);
    Mockito.when(stubScheduleManager.isIdRegistered(3L)).thenReturn(false);
    Mockito.when(stubScheduleManager.isIdRegistered(4L)).thenReturn(false);
    Mockito.when(stubScheduleManager.isIdRegistered(5L)).thenReturn(false);
  }

  @Test
  public void getActiveSchedulesTest() {
    // given
    ApiCallScheduler sut = new ApiCallScheduler(
        stubScheduleService,
        stubDocumentService,
        stubScheduleHistoryService,
        stubThreadPoolTaskScheduler,
        stubScheduleManager
    );

    // when
    List<ScheduleDto> result = sut.getActiveSchedules();

    // then
    assertThat(result)
        .hasSize(3);
    assertThat(result)
        .extracting("id")
        .contains(1L, atIndex(0))
        .contains(2L, atIndex(1))
        .contains(3L, atIndex(2));
    assertThat(result)
        .extracting("isActive")
        .contains(true, atIndex(0))
        .contains(true, atIndex(1))
        .contains(true, atIndex(2));
  }

  @Test
  public void getInactiveSchedulesTest() {
    // given
    ApiCallScheduler sut = new ApiCallScheduler(
        stubScheduleService,
        stubDocumentService,
        stubScheduleHistoryService,
        stubThreadPoolTaskScheduler,
        stubScheduleManager
    );

    // when
    List<ScheduleDto> result = sut.getInactiveSchedules();

    // then
    assertThat(result)
        .hasSize(3);
    assertThat(result)
        .extracting("id")
        .contains(3L, atIndex(0))
        .contains(4L, atIndex(1))
        .contains(5L, atIndex(2));
  }

  @Test
  public void addScheduleTest() {
    // given
    ScheduleManager mockScheduleManager = Mockito.mock(ScheduleManager.class);

    ApiCallScheduler sut = new ApiCallScheduler(
        stubScheduleService,
        stubDocumentService,
        stubScheduleHistoryService,
        stubThreadPoolTaskScheduler,
        mockScheduleManager
    );

    // when
    sut.addSchedule(3L);

    // then
    Mockito.verify(mockScheduleManager, Mockito.atLeastOnce())
        .register(3L, null);
  }


  @Test
  public void removeRegisteredScheduleTest() {
    // given
    ScheduleManager mockScheduleManager = Mockito.mock(ScheduleManager.class);
    Mockito.when(mockScheduleManager.isIdRegistered(3L)).thenReturn(true);

    ApiCallScheduler sut = new ApiCallScheduler(
        stubScheduleService,
        stubDocumentService,
        stubScheduleHistoryService,
        stubThreadPoolTaskScheduler,
        mockScheduleManager
    );

    // when
    sut.removeSchedule(3L);

    // then
    Mockito.verify(mockScheduleManager, Mockito.atLeastOnce())
        .cancel(3L);
  }

  @Test
  public void removeUnregisteredScheduleTest() {
    // given
    ScheduleManager mockScheduleManager = Mockito.mock(ScheduleManager.class);
    Mockito.when(mockScheduleManager.isIdRegistered(3L)).thenReturn(false);

    ApiCallScheduler sut = new ApiCallScheduler(
        stubScheduleService,
        stubDocumentService,
        stubScheduleHistoryService,
        stubThreadPoolTaskScheduler,
        mockScheduleManager
    );

    // when, then
    assertThatThrownBy(
        () -> sut.removeSchedule(3L))
        .isInstanceOf(NotScheduledException.class);

  }

  @Test
  public void removeSchedulesByApiIdTest() {
    // given
    ScheduleManager mockScheduleManager = Mockito.mock(ScheduleManager.class);
    Mockito.when(mockScheduleManager.isIdRegistered(1L)).thenReturn(true);
    Mockito.when(mockScheduleManager.isIdRegistered(2L)).thenReturn(true);
    Mockito.when(mockScheduleManager.isIdRegistered(3L)).thenReturn(true);

    ApiCallScheduler sut = new ApiCallScheduler(
        stubScheduleService,
        stubDocumentService,
        stubScheduleHistoryService,
        stubThreadPoolTaskScheduler,
        mockScheduleManager
    );

    // when
    sut.removeSchedulesByApiId(null);

    // then
    Mockito.verify(mockScheduleManager, Mockito.atLeastOnce())
        .cancel(1L);
    Mockito.verify(mockScheduleManager, Mockito.atLeastOnce())
        .cancel(2L);
    Mockito.verify(mockScheduleManager, Mockito.atLeastOnce())
        .cancel(3L);
  }
}