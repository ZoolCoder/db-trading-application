package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Represents a trading signal command for Signal 1.
 *
 * This command encapsulates the logic for executing Signal 1.
 * It sets up the Algo, configures parameters, performs calculations, and submits to the market.
 *
 * @author Abdallah Emad
 * @since 19-9-2023
 */
@RequiredArgsConstructor
@Component
public class Signal1Command implements SignalCommand {

  private static final Integer SIGNAL_TYPE = 1;
  private final Algo algo;

  /**
   * Executes the trading signal command for Signal 1.
   *
   */
  @Override
  public void execute() {
    algo.setUp();
    algo.setAlgoParam(1, 60);
    algo.performCalc();
    algo.submitToMarket();
  }

  /**
   * Gets the signal type associated with this command.
   *
   * @return The signal type (Signal 1).
   */
  @Override
  public Integer getSignalType() {
    return SIGNAL_TYPE;
  }
}