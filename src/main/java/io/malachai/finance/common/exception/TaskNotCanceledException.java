package io.malachai.finance.common.exception;

public class TaskNotCanceledException extends RuntimeException {
  public TaskNotCanceledException(String message) {
    super(message);
  }
}
