package io.malachai.finance.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Schedule {

  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne(optional = false)
  private ApiModel apiModel;
  @Column
  private String cronExpression;

}
