package de.db.product.tradingapplication.workflow;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@RequiredArgsConstructor
@Component
public class WorkflowConfigReader {

  private static final String filePath = "signal-workflow.yaml";

  private final WorkflowConfigKey workflowConfigKey;
  private Map<Integer, List<Action>> workflowConfig;
  @PostConstruct
  public void init() {
    System.out.println("Initializing WorkflowConfig...");
    workflowConfig = readWorkflowConfig(filePath);
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
            Integer signal = (Integer) signalConfig.get(workflowConfigKey.SIGNAL);
            List<Map<String, Object>> actionList = (List<Map<String, Object>>) signalConfig.get(workflowConfigKey.ACTIONS);

            List<Action> actionObjects = actionList.stream()
                .map(actionConfig -> {
                  return new Action((String) actionConfig.get(workflowConfigKey.ACTION), parseParameters(actionConfig.get(workflowConfigKey.PARAMS)));
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
    String actionName = (String) actionConfig.get(workflowConfigKey.ACTION);
    List<Parameter> parameters = parseParameters(actionConfig.get(workflowConfigKey.PARAMS));

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
    String paramName = (String) paramConfig.get(workflowConfigKey.PARAM_NAME);
    String paramType = (String) paramConfig.get(workflowConfigKey.PARAM_TYPE);
    Object paramValue = paramConfig.get(workflowConfigKey.PARAM_VALUE);

    return new Parameter(paramName, paramType, paramValue);
  }

  public List<Action> getWorkflow(Integer signal) {
    return workflowConfig.getOrDefault(signal, List.of());
  }
}