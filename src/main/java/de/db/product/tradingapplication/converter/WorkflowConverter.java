package de.db.product.tradingapplication.converter;

import de.db.product.tradingapplication.dto.WorkflowActionDTO;
import de.db.product.tradingapplication.dto.WorkflowDTO;
import de.db.product.tradingapplication.model.Workflow;
import de.db.product.tradingapplication.model.WorkflowAction;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
@RequiredArgsConstructor
@Component
public class WorkflowConverter {

  private final ActionConverter actionConverter;
  public WorkflowDTO toDTO(Workflow workflow) {
    if (workflow.getActions() != null) {
      List<WorkflowActionDTO> workflowActionDTOS = workflow.getActions()
          .stream()
          .map(actionConverter::toDTO)
          .collect(Collectors.toList());

      return new WorkflowDTO(workflow.getSignalID(), workflowActionDTOS);
    } else {
      return new WorkflowDTO(workflow.getSignalID(), null);
    }
  }

  public Workflow fromDTO(WorkflowDTO workflowDTO) {
    Workflow workflow = new Workflow(workflowDTO.signalID(), null);
    if (workflowDTO.actions() != null) {
      List<WorkflowAction> actions = workflowDTO.actions()
          .stream()
          .map((WorkflowActionDTO action) -> actionConverter.fromDTO(workflow, action))
          .collect(Collectors.toList());
      workflow.setActions(actions);
    }
    return workflow;
  }
}