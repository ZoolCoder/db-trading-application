package de.db.product.tradingapplication.command;

/**
 * Represents a trading signal command.
 * This interface defines the contract for executing trading signal commands.
 * Each implementing class should provide specific behavior for executing a trading signal.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public interface SignalCommand {
  /**
   * Executes the trading signal command.
   */
  void execute();

  /**
   * Gets the signal type associated with this command.
   *
   * @return The signal type.
   */
  Integer getSignalType();
}