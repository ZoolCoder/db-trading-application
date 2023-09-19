package de.db.product.tradingapplication.service.command;

import de.db.product.tradingapplication.invoker.Algo;

/**
 * Represents a trading signal command for Signal 3.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public class Signal3Command implements SignalCommand {
  private Algo algo;

  public Signal3Command(Algo algo) {
    this.algo = algo;
  }

  @Override
  public void execute() {
    algo.setAlgoParam(1, 90);
    algo.setAlgoParam(2, 15);
    algo.performCalc();
    algo.submitToMarket();
  }
}