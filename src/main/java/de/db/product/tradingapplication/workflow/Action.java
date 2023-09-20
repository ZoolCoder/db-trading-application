package de.db.product.tradingapplication.workflow;

import java.util.List;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 20-9-2023
 */
public record Action(String actionName, List<Parameter> parameters) {
}