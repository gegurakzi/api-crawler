package io.malachai.finance.domain.repository;

import io.malachai.finance.domain.ApiModel;
import io.malachai.finance.domain.ScheduleHistory;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ScheduleHistoryRepository extends Repository<ScheduleHistory, Long> {

  Optional<List<ScheduleHistory>> findAll();
  Optional<ScheduleHistory> findById(Long id);
  void save(ScheduleHistory history);

}
