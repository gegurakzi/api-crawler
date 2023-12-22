package io.malachai.finance.domain.repository;

import io.malachai.finance.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ScheduleRepository extends Repository<Schedule, Long> {

  Optional<List<Schedule>> findAll();
  Optional<Schedule> findById(Long id);
  Optional<List<Schedule>> findAllByIdIn(List<Long> ids);
  void save(Schedule schedule);

}
