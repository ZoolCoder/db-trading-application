package de.db.product.tradingapplication.service;

import de.db.product.tradingapplication.dto.WorkflowDTO;
import de.db.product.tradingapplication.dto.WorkflowExecutionRequest;
import de.db.product.tradingapplication.exceptions.InvalidSignalException;
import de.db.product.tradingapplication.exceptions.SignalProcessingException;
import de.db.product.tradingapplication.invoker.Algo;
import de.db.product.tradingapplication.command.SignalCommand;
import de.db.product.tradingapplication.command.SignalCommandFactory;
import de.db.product.tradingapplication.workflow.SignalExecutor;
import de.db.product.tradingapplication.workflow.SignalWorkflowHandler;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service for processing trading signals.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@RequiredArgsConstructor
@Service
public class SignalService {
  private final SignalCommandFactory commandFactory;
  private final SignalWorkflowHandler workflowHandler;
  private final WorkflowService workflowService;
  private final SignalExecutor signalExecutor;
  /**
   * Processes a trading signal.
   *
   * @param signal The trading signal to be processed.
   */
  public void process(int signal) {
    // Validate the signal
    if (signal < 0 || signal > 3) {
      throw new InvalidSignalException("Invalid signal value: " + signal);
    }

    try {
      // Signal processing logic
      SignalCommand command = commandFactory.createCommand(signal);
      command.execute();
    } catch (Exception e) {
      throw new SignalProcessingException("Error processing signal", e);
    }
  }

  /**
   * Loads and executes a workflow for a given command.
   *
   * @param Signal The command for which to execute the workflow.
   */
  public void processConfigureWorkflow(Integer Signal) {
    workflowHandler.executeWorkflow(Signal);
  }

  /**
   * Loads and executes a workflow for a given command.
   *
   * @param Signal The command for which to execute the workflow.
   */
  public void processStoredWorkflow(Integer Signal) {
    // Retrieve the workflow from the database by its ID
    WorkflowDTO workflowDTO = workflowService.getWorkflowBySignalID(Signal);

    // Implement logic to execute the workflow based on the DTO
    workflowDTO.actions().forEach(signalExecutor::executeAction);
  }
}