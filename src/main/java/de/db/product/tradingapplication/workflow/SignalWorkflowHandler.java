package de.db.product.tradingapplication.workflow;


import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 20-9-2023
 */

@Component
public class SignalWorkflowHandler {

  private static final String filePath = "signal-workflow.yaml";

  public void executeWorkflow(Integer Signal) {
    WorkflowConfigReader reader = WorkflowConfigReader.getInstance();
    Map<Integer, List<Action>> workflowConfig = reader.readWorkflowConfig(filePath);
    SignalExecutor executor = new SignalExecutor();
    List<Action> actions = workflowConfig.getOrDefault(Signal, List.of());
    actions.forEach(executor::executeAction);
  }
}