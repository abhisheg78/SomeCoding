package com.highspot.mixtape.takehome.exceptions;

/**
 * exception indicate an object already exists.
 */
public class AlreadyExistException extends Exception {
  /**
   * constructor.
   * @param errorMsg error message.
   */
  public AlreadyExistException(final String errorMsg) {
    super(errorMsg);
  }
}
