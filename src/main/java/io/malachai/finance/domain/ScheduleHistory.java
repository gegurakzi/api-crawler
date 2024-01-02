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
  @Column
  private String apiName;
  @Column
  private String apiUrl;
  @Column
  private String apiHeader;
  @Column
  private String reference;
  @Column
  private String cronExpression;
  @Column
  private String state;
  @Column
  private String message;
  @Column(nullable = false)
  private Timestamp timestamp;

}
