package com.highspot.mixtape.takehome.exceptions;

/**
 * Exception indicates an object does not exists.
 */
public class NotFoundException extends Exception {
  /**
   * constructor.
   * @param errorMsg error message
   */
  public NotFoundException(final String errorMsg) {
    super(errorMsg);
  }
}
