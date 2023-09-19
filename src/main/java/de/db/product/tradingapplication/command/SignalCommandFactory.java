package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;
import org.springframework.stereotype.Component;

/**
 * Factory for creating SignalCommand instances based on signal type.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@Component
public class SignalCommandFactory {
  /**
   * Creates a SignalCommand based on the given signal type.
   *
   * @param signal The type of signal.
   * @param algo   The Algo instance for executing the signal.
   * @return A SignalCommand for the specified signal type.
   */
  public SignalCommand createCommand(int signal, Algo algo) {
    switch (signal) {
      case 1:
        return new Signal1Command(algo);
      case 2:
        return new Signal2Command(algo);
      case 3:
        return new Signal3Command(algo);
      default:
        return new DefaultSignalCommand(algo);
    }
  }
}
