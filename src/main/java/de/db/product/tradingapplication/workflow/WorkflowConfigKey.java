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
  public String SIGNAL;

  @Value("${workflow.config.actions}")
  public String ACTIONS;

  @Value("${workflow.config.action}")
  public String ACTION;

  @Value("${workflow.config.params}")
  public String PARAMS;

  @Value("${workflow.config.param.name}")
  public String PARAM_NAME;

  @Value("${workflow.config.param.type}")
  public String PARAM_TYPE;

  @Value("${workflow.config.param.value}")
  public String PARAM_VALUE;
}