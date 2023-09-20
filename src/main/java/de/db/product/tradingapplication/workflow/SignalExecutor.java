package de.db.product.tradingapplication.workflow;

import java.lang.reflect.Method;
import java.util.List;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 20-9-2023
 */
public class SignalExecutor {

  public void executeAction(Action action) {
    String actionName = action.actionName();
    List<Parameter> parameters = action.parameters();
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

  private Object[] getParameterValues(Class<?>[] paramTypes, List<Parameter> parameters) {
    Object[] paramValues = new Object[parameters.size()];

    for (int i = 0; i < parameters.size(); i++) {
      Parameter parameter = parameters.get(i);
      String paramType = parameter.type();
      Object paramValue = parameter.value();

      // Convert paramValue to the appropriate type
      paramValues[i] = convertValue(paramType, paramValue);
    }

    return paramValues;
  }

  private Object convertValue(String type, Object value) {
    switch (type.toLowerCase()) {
      case "int":
        return Integer.parseInt(value.toString());
      // Add more cases for other types as needed
      default:
        return value;
    }
  }
}