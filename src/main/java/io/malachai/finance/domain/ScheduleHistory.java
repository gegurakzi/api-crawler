package io.malachai.finance.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ScheduleHistory {

  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne(optional = false)
  private Schedule schedule;
  @Column
  private String state;
  @Column
  private String message;
  @Column(nullable = false)
  private Timestamp timestamp;

}
