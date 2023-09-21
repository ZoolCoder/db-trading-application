package de.db.product.tradingapplication.workflow;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkflowConfigKey {

  @Value("${workflow.config.signal}")
  public String signal;

  @Value("${workflow.config.actions}")
  public String actions;

  @Value("${workflow.config.action}")
  public String action;

  @Value("${workflow.config.params}")
  public String params;

  @Value("${workflow.config.param.name}")
  public String param_name;

  @Value("${workflow.config.param.type}")
  public String param_type;

  @Value("${workflow.config.param.value}")
  public String param_value;
}