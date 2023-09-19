package de.db.product.tradingapplication.service.command;

import de.db.product.tradingapplication.invoker.Algo;

/**
 * Represents a trading signal command for Signal 2.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public class Signal2Command implements SignalCommand {
  private Algo algo;

  public Signal2Command(Algo algo) {
    this.algo = algo;
  }

  @Override
  public void execute() {
    algo.reverse();
    algo.setAlgoParam(1, 80);
    algo.submitToMarket();
  }
}