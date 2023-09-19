package de.db.product.tradingapplication.exceptions;

/**
 * Custom exception class for signal processing errors.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public class SignalProcessingException extends RuntimeException {
  public SignalProcessingException(String message) {
    super(message);
  }

  public SignalProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}