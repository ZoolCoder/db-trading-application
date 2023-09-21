package de.db.product.tradingapplication.converter;

import de.db.product.tradingapplication.dto.WorkflowActionParameterDTO;
import de.db.product.tradingapplication.model.WorkflowAction;
import de.db.product.tradingapplication.model.WorkflowActionParameter;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
@Component
public class ParameterConverter {

  public WorkflowActionParameterDTO toDTO(WorkflowActionParameter parameter) {
    return new WorkflowActionParameterDTO(parameter.getName(), parameter.getType(), parameter.getParamValue());
  }

  public WorkflowActionParameter fromDTO(WorkflowAction action, WorkflowActionParameterDTO parameterDTO) {
    return new WorkflowActionParameter(parameterDTO.name(), parameterDTO.type(), parameterDTO.value().toString(), action);
  }
}
