package io.malachai.finance.common.exception;

public class AlreadyScheduledException extends RuntimeException{
  public AlreadyScheduledException(String message) {
    super(message);
  }
}
