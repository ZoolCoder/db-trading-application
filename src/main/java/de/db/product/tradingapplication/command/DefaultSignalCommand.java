package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;
import org.springframework.stereotype.Component;

/**
 * Represents a trading signal command for default Signal.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@Component
public class DefaultSignalCommand implements SignalCommand {

  private static final Integer SIGNAL_TYPE = 0;

  private Algo algo;

  /**
   * Constructs a DefaultSignalCommand with the specified Algo instance.
   *
   * @param algo The Algo instance for executing the signal.
   */
  public DefaultSignalCommand(Algo algo) {
    this.algo = algo;
  }

  /**
   * Executes the trading signal command by canceling trades using the Algo instance.
   */
  @Override
  public void execute() {
    algo.cancelTrades();
  }

  /**
   * Gets the signal type associated with this command (Signal 0).
   *
   * @return The signal type, which is 0.
   */
  @Override
  public Integer getSignalType() {
    return SIGNAL_TYPE;
  }
}