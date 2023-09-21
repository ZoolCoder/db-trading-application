package de.db.product.tradingapplication.service;

import de.db.product.tradingapplication.converter.WorkflowConverter;
import de.db.product.tradingapplication.dto.WorkflowDTO;
import de.db.product.tradingapplication.exceptions.WorkflowNotFoundException;
import de.db.product.tradingapplication.model.Workflow;
import de.db.product.tradingapplication.repository.WorkflowRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
@RequiredArgsConstructor
@Service
public class WorkflowServiceImpl implements WorkflowService {

  private final WorkflowRepository workflowRepository;
  private final WorkflowConverter workflowConverter;
  @Override
  public List<WorkflowDTO> getAllWorkflows() {
    List<Workflow> workflowEntities = workflowRepository.findAll();
    return workflowEntities.stream()
        .map(workflowConverter::toDTO)
        .collect(Collectors.toList());
  }

  @Override
  public WorkflowDTO getWorkflowById(Long id) {
    Optional<Workflow> workflowOptional = workflowRepository.findById(id);
    if (workflowOptional.isPresent()) {
      return workflowConverter.toDTO(workflowOptional.get());
    } else {
      throw new WorkflowNotFoundException("Workflow not found with ID: " + id);
    }
  }

  @Override
  public WorkflowDTO getWorkflowBySignalID(int signalID) {
    Workflow workflow = workflowRepository.findBySignalID(signalID);
    if (workflow != null) {
      return workflowConverter.toDTO(workflow);
    } else {
      throw new WorkflowNotFoundException("Workflow not found with ID: " + signalID);
    }
  }
  @Override
  public WorkflowDTO saveWorkflow(WorkflowDTO workflowDTO) {
    Workflow workflow = workflowConverter.fromDTO(workflowDTO);
    workflow = workflowRepository.save(workflow);
    return workflowConverter.toDTO(workflow);
  }

  @Override
  public WorkflowDTO updateWorkflow(Long id, WorkflowDTO workflowDTO) {
    Optional<Workflow> existingWorkflowOptional = workflowRepository.findById(id);
    if (existingWorkflowOptional.isPresent()) {
      Workflow existingWorkflow = existingWorkflowOptional.get();
      Workflow updatedWorkflow = workflowConverter.fromDTO(workflowDTO);
      existingWorkflow.setSignalID(updatedWorkflow.getSignalID());
      existingWorkflow.setActions(updatedWorkflow.getActions());
      existingWorkflow = workflowRepository.save(existingWorkflow);
      return workflowConverter.toDTO(existingWorkflow);
    } else {
      throw new WorkflowNotFoundException("Workflow not found with ID: " + id);
    }
  }

  @Override
  public void deleteWorkflow(Long id) {
    Optional<Workflow> workflowOptional = workflowRepository.findById(id);
    if (workflowOptional.isPresent()) {
      workflowRepository.delete(workflowOptional.get());
    } else {
      throw new WorkflowNotFoundException("Workflow not found with ID: " + id);
    }
  }
}