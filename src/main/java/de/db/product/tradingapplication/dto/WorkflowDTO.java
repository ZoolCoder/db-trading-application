package de.db.product.tradingapplication.dto;

import java.util.List;
/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
public record WorkflowDTO(int signalID, List<WorkflowActionDTO> actions) {

}