package de.db.product.tradingapplication.dto;

import de.db.product.tradingapplication.model.WorkflowActionParameterType;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 20-9-2023
 */
public record WorkflowActionParameterDTO(String name, WorkflowActionParameterType type, Object value) {

}
