package de.db.product.tradingapplication.exceptions;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
public class WorkflowNotFoundException extends RuntimeException {

  public WorkflowNotFoundException() {
    super();
  }

  public WorkflowNotFoundException(String message) {
    super(message);
  }

  public WorkflowNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}