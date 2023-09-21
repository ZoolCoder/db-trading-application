package de.db.product.tradingapplication.service;

import de.db.product.tradingapplication.dto.WorkflowDTO;
import de.db.product.tradingapplication.model.Workflow;
import java.util.List;
/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
public interface WorkflowService {

  List<WorkflowDTO> getAllWorkflows();

  WorkflowDTO getWorkflowById(Long id);

  WorkflowDTO getWorkflowBySignalID(int signalID);
  WorkflowDTO saveWorkflow(WorkflowDTO workflowDTO);

  WorkflowDTO updateWorkflow(Long id, WorkflowDTO workflowDTO);

  void deleteWorkflow(Long id);
}