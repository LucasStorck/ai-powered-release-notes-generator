package org.lucas.notesgenerator.exceptions;

public class MethodArgumentNotValidException extends RuntimeException {
  public MethodArgumentNotValidException(String message) {
    super(message);
  }
}
