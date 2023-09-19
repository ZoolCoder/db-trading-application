package de.db.product.tradingapplication.service.command;

/**
 * Represents a trading signal command.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public interface SignalCommand {
  /**
   * Executes the trading signal command.
   */
  void execute();
}