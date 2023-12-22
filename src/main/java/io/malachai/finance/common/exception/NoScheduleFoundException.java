package io.malachai.finance.common.exception;

public class NoScheduleFoundException extends RuntimeException{
  public NoScheduleFoundException(String message) {
    super(message);
  }

  public NoScheduleFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
