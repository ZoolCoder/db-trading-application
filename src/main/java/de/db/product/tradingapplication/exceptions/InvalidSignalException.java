package de.db.product.tradingapplication.exceptions;

/**
 * Custom exception class for invalid signal values.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public class InvalidSignalException extends IllegalArgumentException {
  public InvalidSignalException(String message) {
    super(message);
  }
}