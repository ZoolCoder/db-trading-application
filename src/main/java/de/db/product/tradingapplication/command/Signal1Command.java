package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;

/**
 * Represents a trading signal command for Signal 1.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public class Signal1Command implements SignalCommand {
  private Algo algo;

  public Signal1Command(Algo algo) {
    this.algo = algo;
  }

  @Override
  public void execute() {
    algo.setUp();
    algo.setAlgoParam(1, 60);
    algo.performCalc();
    algo.submitToMarket();
  }
}