package de.db.product.tradingapplication.converter;

import de.db.product.tradingapplication.dto.WorkflowActionDTO;
import de.db.product.tradingapplication.dto.WorkflowActionParameterDTO;
import de.db.product.tradingapplication.model.Workflow;
import de.db.product.tradingapplication.model.WorkflowAction;
import de.db.product.tradingapplication.model.WorkflowActionParameter;
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
public class ActionConverter {

  private final ParameterConverter parameterConverter;
  public WorkflowActionDTO toDTO(WorkflowAction action) {
    if(action.getParameters() != null) {
      List<WorkflowActionParameterDTO> parameterDTOs = action.getParameters()
          .stream()
          .map(parameterConverter::toDTO)
          .collect(Collectors.toList());
      return new WorkflowActionDTO(action.getName(), parameterDTOs);
    } else {
      return new WorkflowActionDTO(action.getName(), null);
    }
  }

  public WorkflowAction fromDTO(Workflow workflow, WorkflowActionDTO workflowActionDTO) {
    WorkflowAction action = new WorkflowAction(workflowActionDTO.name(), null, workflow);
    if (workflowActionDTO.parameters() != null) {
      List<WorkflowActionParameter> parameters = workflowActionDTO.parameters()
          .stream()
          .map((WorkflowActionParameterDTO parameter) -> parameterConverter.fromDTO(action, parameter))
          .collect(Collectors.toList());
      action.setParameters(parameters);
    }
    return action;
  }
}