package de.db.product.tradingapplication.workflow;

import de.db.product.tradingapplication.dto.WorkflowActionDTO;
import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 20-9-2023
 */
public class WorkflowConfig {

  private final Map<String, List<WorkflowActionDTO>> workflow;

  public WorkflowConfig(Map<String, List<WorkflowActionDTO>> workflow) {
    this.workflow = workflow;
  }

  public List<WorkflowActionDTO> getActionsForCommand(String command) {
    return workflow.getOrDefault(command, List.of());
  }
}
