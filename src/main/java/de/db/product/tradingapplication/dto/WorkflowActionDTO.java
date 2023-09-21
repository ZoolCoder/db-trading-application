package de.db.product.tradingapplication.dto;

import java.util.List;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 20-9-2023
 */
public record WorkflowActionDTO(String name, List<WorkflowActionParameterDTO> parameters) {
}