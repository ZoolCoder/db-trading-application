package de.db.product.tradingapplication.command;

import de.db.product.tradingapplication.invoker.Algo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * Factory for creating SignalCommand instances based on signal type.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@Component
public class SignalCommandFactory {

  @Autowired
  private Algo algo;

  private final Map<Integer, SignalCommand> commandMap;

  @Autowired
  public SignalCommandFactory(List<SignalCommand> signalCommands) {
    this.commandMap = signalCommands
        .stream()
        .collect(Collectors.toMap(SignalCommand::getSignalType, command -> command));
  }

  public SignalCommand createCommand(int signal) {
    return commandMap.getOrDefault(signal, new DefaultSignalCommand(algo));
  }
}