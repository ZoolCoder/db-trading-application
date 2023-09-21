package de.db.product.tradingapplication.workflow;
import de.db.product.tradingapplication.dto.WorkflowActionDTO;
import de.db.product.tradingapplication.dto.WorkflowActionParameterDTO;
import de.db.product.tradingapplication.model.WorkflowActionParameterType;
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
  private Map<Integer, List<WorkflowActionDTO>> workflowConfig;
  @PostConstruct
  public void init() {
    System.out.println("Initializing WorkflowConfig...");
    workflowConfig = readWorkflowConfig(filePath);
  }
  public Map<Integer, List<WorkflowActionDTO>> readWorkflowConfig(String filePath) {
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

  private Map<Integer, List<WorkflowActionDTO>> parseWorkflowConfig(Map<String, List<Map<String, Object>>> config) {
    Map<Integer, List<WorkflowActionDTO>> result = new HashMap<>();

    config.forEach((signalKey, signalConfigList) -> {

      signalConfigList.stream()
          .map(signalConfig -> {
            Integer signal = (Integer) signalConfig.get(workflowConfigKey.SIGNAL);
            List<Map<String, Object>> actionList = (List<Map<String, Object>>) signalConfig.get(workflowConfigKey.ACTIONS);

            List<WorkflowActionDTO> workflowActionDTOObjects = actionList.stream()
                .map(actionConfig -> {
                  return new WorkflowActionDTO((String) actionConfig.get(workflowConfigKey.ACTION), parseParameters(actionConfig.get(workflowConfigKey.PARAMS)));
                })
                .collect(Collectors.toList());

            result.put(signal, workflowActionDTOObjects);
            return workflowActionDTOObjects;
          })
          .flatMap(List::stream)
          .collect(Collectors.toList());
    });

    return result;
  }

  private WorkflowActionDTO parseAction(Map<String, Object> actionConfig) {
    String actionName = (String) actionConfig.get(workflowConfigKey.ACTION);
    List<WorkflowActionParameterDTO> parameters = parseParameters(actionConfig.get(workflowConfigKey.PARAMS));

    return new WorkflowActionDTO(actionName, parameters);
  }

  private List<WorkflowActionParameterDTO> parseParameters(Object paramsConfig) {
    if (!(paramsConfig instanceof List)) {
      return Collections.emptyList();
    }

    return ((List<Map<String, Object>>) paramsConfig).stream()
        .map(this::parseParameter)
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  }

  private WorkflowActionParameterDTO parseParameter(Map<String, Object> paramConfig) {
    String paramName = (String) paramConfig.get(workflowConfigKey.PARAM_NAME);
    String paramType = (String) paramConfig.get(workflowConfigKey.PARAM_TYPE);
    Object paramValue = paramConfig.get(workflowConfigKey.PARAM_VALUE);

    return new WorkflowActionParameterDTO(paramName, WorkflowActionParameterType.fromString(paramType), paramValue);
  }

  public List<WorkflowActionDTO> getWorkflow(Integer signal) {
    return workflowConfig.getOrDefault(signal, List.of());
  }
}