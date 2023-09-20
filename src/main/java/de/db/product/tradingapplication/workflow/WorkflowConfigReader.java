package de.db.product.tradingapplication.workflow;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import org.yaml.snakeyaml.Yaml;

public class WorkflowConfigReader {
  private static WorkflowConfigReader instance = null;

  private WorkflowConfigReader() {}

  public static WorkflowConfigReader getInstance() {
    if (instance == null) {
      instance = new WorkflowConfigReader();
    }
    return instance;
  }

  public Map<Integer, List<Action>> readWorkflowConfig(String filePath) {
    Map<String, List<Map<String, Object>>> workflowConfig = null;

    try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)) {
      if (inputStream != null) {
        Yaml yaml = new Yaml();
        workflowConfig = yaml.load(inputStream);
      } else {
        throw new IOException("File not found: " + filePath);
      }
    } catch (IOException e) {
      throw new RuntimeException("Error reading workflow configuration file", e);
    }
    return parseWorkflowConfig(workflowConfig);
  }

  private Map<Integer, List<Action>> parseWorkflowConfig(Map<String, List<Map<String, Object>>> config) {
    Map<Integer, List<Action>> result = new HashMap<>();

    config.forEach((signalKey, signalConfigList) -> {
      List<Action> actions = signalConfigList.stream()
          .map(signalConfig -> {
            Integer signal = (Integer) signalConfig.get("signal");
            List<Map<String, Object>> actionList = (List<Map<String, Object>>) signalConfig.get("actions");

            List<Action> actionObjects = actionList.stream()
                .map(actionConfig -> {
                  return new Action((String) actionConfig.get("action"), parseParameters(actionConfig.get("params")));
                })
                .collect(Collectors.toList());

            result.put(signal, actionObjects);
            return actionObjects;
          })
          .flatMap(List::stream)
          .collect(Collectors.toList());
    });

    return result;
  }

  private Action parseAction(Map<String, Object> actionConfig) {
    String actionName = (String) actionConfig.get("action");
    List<Parameter> parameters = parseParameters(actionConfig.get("params"));

    return new Action(actionName, parameters);
  }

  private List<Parameter> parseParameters(Object paramsConfig) {
    if (!(paramsConfig instanceof List)) {
      return Collections.emptyList();
    }

    return ((List<Map<String, Object>>) paramsConfig).stream()
        .map(this::parseParameter)
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  }

  private Parameter parseParameter(Map<String, Object> paramConfig) {
    String paramName = (String) paramConfig.get("name");
    String paramType = (String) paramConfig.get("type");
    Object paramValue = paramConfig.get("value");

    return new Parameter(paramName, paramType, paramValue);
  }
}