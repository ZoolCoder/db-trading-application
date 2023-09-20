package de.db.product.tradingapplication.workflow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 20-9-2023
 */

@RequiredArgsConstructor
@Component
public class SignalWorkflowHandler {

  private final WorkflowConfigReader reader;
  public void executeWorkflow(Integer Signal) {
    reader.getWorkflow(Signal).forEach(new SignalExecutor()::executeAction);
  }
}