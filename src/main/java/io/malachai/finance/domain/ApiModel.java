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
public class ApiModel {

  @Id
  @GeneratedValue
  Long id;
  @Column(nullable = false)
  String name;
  @Column(nullable = false)
  String url;
  @Column
  String header;

}
