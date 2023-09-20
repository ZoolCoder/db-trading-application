package de.db.product.tradingapplication.workflow;

import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 20-9-2023
 */
public class WorkflowConfig {

  private final Map<String, List<Action>> workflow;

  public WorkflowConfig(Map<String, List<Action>> workflow) {
    this.workflow = workflow;
  }

  public List<Action> getActionsForCommand(String command) {
    return workflow.getOrDefault(command, List.of());
  }
}
