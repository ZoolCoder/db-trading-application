package de.db.product.tradingapplication.controller;

import de.db.product.tradingapplication.dto.WorkflowDTO;
import de.db.product.tradingapplication.dto.WorkflowExecutionRequest;
import de.db.product.tradingapplication.service.WorkflowService;
/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
import de.db.product.tradingapplication.workflow.SignalExecutor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow")
public class WorkflowController {

  private final WorkflowService workflowService;

  @GetMapping("/getAll")
  public ResponseEntity<List<WorkflowDTO>> getAllWorkflows() {
    List<WorkflowDTO> workflows = workflowService.getAllWorkflows();
    return new ResponseEntity<>(workflows, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<WorkflowDTO> getWorkflowById(@PathVariable Long id) {
    WorkflowDTO workflow = workflowService.getWorkflowById(id);
    return new ResponseEntity<>(workflow, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<WorkflowDTO> createWorkflow(@RequestBody WorkflowDTO workflowDTO) {
    WorkflowDTO createdWorkflow = workflowService.saveWorkflow(workflowDTO);
    return new ResponseEntity<>(createdWorkflow, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<WorkflowDTO> updateWorkflow(@PathVariable Long id, @RequestBody WorkflowDTO workflowDTO) {
    WorkflowDTO updatedWorkflow = workflowService.updateWorkflow(id, workflowDTO);
    return new ResponseEntity<>(updatedWorkflow, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteWorkflow(@PathVariable Long id) {
    workflowService.deleteWorkflow(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}