package de.db.product.tradingapplication.workflow;

import de.db.product.tradingapplication.dto.WorkflowActionDTO;
import de.db.product.tradingapplication.dto.WorkflowActionParameterDTO;
import de.db.product.tradingapplication.model.WorkflowActionParameterType;
import java.lang.reflect.Method;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 20-9-2023
 */
@Component
public class SignalExecutor {

  public void executeAction(WorkflowActionDTO workflowActionDTO) {
    String actionName = workflowActionDTO.name();
    List<WorkflowActionParameterDTO> parameters = workflowActionDTO.parameters();
    try {
      // Find the Algo class using reflection
      Class<?> algoClass = Class.forName("de.db.product.tradingapplication.invoker.Algo");

      // Find the Algo method with the specified name
      Method algoMethod = findAlgoMethod(algoClass, actionName);

      if (algoMethod != null) {
        // Create an array to hold parameter values
        Object[] paramValues = getParameterValues(algoMethod.getParameterTypes(), parameters);

        // Execute the Algo method with parameters
        algoMethod.invoke(algoClass.getDeclaredConstructor().newInstance(), paramValues);
      } else {
        System.err.println("Action method not found: " + actionName);
      }
    } catch (Exception e) {
      e.printStackTrace();
      // Handle exceptions as needed
    }
  }

  private Method findAlgoMethod(Class<?> algoClass, String methodName) {
    Method[] methods = algoClass.getMethods();
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        return method;
      }
    }
    return null;
  }

  private Object[] getParameterValues(Class<?>[] paramTypes, List<WorkflowActionParameterDTO> parameters) {
    Object[] paramValues = new Object[parameters.size()];

    for (int i = 0; i < parameters.size(); i++) {
      WorkflowActionParameterDTO parameter = parameters.get(i);
      WorkflowActionParameterType paramType = parameter.type();
      Object paramValue = parameter.value();

      // Convert paramValue to the appropriate type
      paramValues[i] = convertValue(paramType, paramValue);
    }

    return paramValues;
  }

  private Object convertValue(WorkflowActionParameterType type, Object value) {
    switch (type) {
      case INT:
        return Integer.parseInt(value.toString());
      // Add more cases for other types as needed
      default:
        return value;
    }
  }
}