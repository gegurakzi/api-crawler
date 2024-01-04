package io.malachai.finance.domain;

public class ApiModelFixture {
  private Long id = 1L;
  private String name = "test-api";
  private String apiGroup = null;
  private String url = "test-url";
  private String header = null;

  public static ApiModelFixture base() {
    return new ApiModelFixture();
  }

}
