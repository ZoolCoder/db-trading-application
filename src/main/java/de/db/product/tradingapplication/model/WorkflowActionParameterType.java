package de.db.product.tradingapplication.model;

import java.util.Arrays;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
public enum WorkflowActionParameterType {
    INT("int"),
    STRING("string"),
    BOOLEAN("boolean");
    // Add more types as needed

    private final String type;

  WorkflowActionParameterType(String type) {
      this.type = type;
    }

    public String getType() {
      return type;
    }

    public static WorkflowActionParameterType fromString(String text) {
      return Arrays.stream(values())
          .filter(type -> type.type.equalsIgnoreCase(text))
          .findFirst()
          .orElseThrow(() -> new IllegalArgumentException("Unknown parameter type: " + text));
    }
}
