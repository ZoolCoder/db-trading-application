package de.db.product.tradingapplication.service;

import de.db.product.tradingapplication.exceptions.InvalidSignalException;
import de.db.product.tradingapplication.exceptions.SignalProcessingException;
import de.db.product.tradingapplication.invoker.Algo;
import de.db.product.tradingapplication.command.SignalCommand;
import de.db.product.tradingapplication.command.SignalCommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for processing trading signals.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@Service
public class SignalService {
  private SignalCommandFactory commandFactory;
  private Algo algo;

  @Autowired
  public SignalService(SignalCommandFactory commandFactory, Algo algo) {
    this.commandFactory = commandFactory;
    this.algo = algo;
  }

  /**
   * Processes a trading signal.
   *
   * @param signal The trading signal to be processed.
   */
  public void processSignal(int signal) {
    // Validate the signal
    if (signal < 0 || signal > 3) {
      throw new InvalidSignalException("Invalid signal value: " + signal);
    }

    try {
      // Signal processing logic
      SignalCommand command = commandFactory.createCommand(signal, algo);
      command.execute();
    } catch (Exception e) {
      throw new SignalProcessingException("Error processing signal", e);
    }
  }
}