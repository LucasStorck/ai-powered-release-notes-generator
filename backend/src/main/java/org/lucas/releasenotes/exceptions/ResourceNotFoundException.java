package org.lucas.releasenotes.exceptions;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException(String message) {
        super(message);
    }
}
