package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;
import org.springframework.stereotype.Component;

/**
 * Represents a trading signal command for Signal 2.
 * This command performs a trading operation associated with Signal 2.
 * It reverses the trading algorithm, sets parameters, and submits to the market.
 * The signal type for Signal 2 is 2.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@Component
public class Signal2Command implements SignalCommand {

  private static final Integer SIGNAL_TYPE = 2;
  private final Algo algo;

  /**
   * Constructs a Signal2Command with the specified Algo instance.
   *
   * @param algo The Algo instance for executing the signal.
   */
  public Signal2Command(Algo algo) {
    this.algo = algo;
  }

  /**
   * Executes the trading signal command for Signal 2.
   * This command reverses the trading algorithm, sets parameters, and submits to the market.
   */
  @Override
  public void execute() {
    algo.reverse();
    algo.setAlgoParam(1, 80);
    algo.submitToMarket();
  }

  /**
   * Gets the signal type associated with this command (Signal 2).
   *
   * @return The signal type, which is 2.
   */
  @Override
  public Integer getSignalType() {
    return SIGNAL_TYPE;
  }
}