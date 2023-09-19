package de.db.product.tradingapplication.service.command;

import de.db.product.tradingapplication.invoker.Algo;

/**
 * Represents a trading signal command for default Signal.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public class DefaultSignalCommand implements SignalCommand {
  private Algo algo;

  public DefaultSignalCommand(Algo algo) {
    this.algo = algo;
  }

  @Override
  public void execute() {
    algo.cancelTrades();
  }
}