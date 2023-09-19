package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;
import org.springframework.stereotype.Component;

/**
 * Represents a trading signal command for Signal 3.
 * This command sets specific algorithm parameters and performs calculations before submitting to the market.
 * Implements the SignalCommand interface.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@Component
public class Signal3Command implements SignalCommand {

  private final Algo algo;
  private static final Integer SIGNAL_TYPE = 3;

  /**
   * Constructs a Signal3Command with the specified Algo instance.
   *
   * @param algo The Algo instance for executing the signal.
   */
  public Signal3Command(Algo algo) {
    this.algo = algo;
  }

  /**
   * Executes the trading signal command for Signal 3.
   *
   */
  @Override
  public void execute() {
    try {
      algo.setAlgoParam(1, 90);
      algo.setAlgoParam(2, 15);
      algo.performCalc();
      algo.submitToMarket();
    } catch (Exception e) {
      // Handle other exceptions
    }
  }

  /**
   * Gets the signal type associated with this command (Signal 3).
   *
   * @return The signal type, which is 3.
   */
  @Override
  public Integer getSignalType() {
    return SIGNAL_TYPE;
  }
}