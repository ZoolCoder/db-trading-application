package de.db.product.tradingapplication.controller;

import de.db.product.tradingapplication.dto.SignalRequest;
import de.db.product.tradingapplication.dto.WorkflowDTO;
import de.db.product.tradingapplication.dto.WorkflowExecutionRequest;
import de.db.product.tradingapplication.service.SignalService;
import de.db.product.tradingapplication.service.WorkflowService;
import de.db.product.tradingapplication.workflow.SignalExecutor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling HTTP requests related to trading signals.
 * This controller provides an HTTP endpoint for receiving trading signals.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/signal")
public class SignalController {

  private final SignalService signalService;

  /**
   * Handles an HTTP POST request to process a trading signal.
   *
   * @param signalRequest The request body containing the trading signal to be processed.
   * @return A response indicating the result of signal processing.
   */
  @Operation(summary = " process a trading signal from Command classes", description = "This api is used to process a trading signal from Command classes")
  @ApiResponse(responseCode = "200", description = "trading signal processed successfully")
  @PostMapping("/process")
  public ResponseEntity<String> processSignal(@RequestBody SignalRequest signalRequest) {
    try {
      int signal = signalRequest.getSignal();
      signalService.process(signal);
      return ResponseEntity.ok("Signal processed successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signal processing failed");
    }
  }

  /**
   * Handles an HTTP POST request to process a trading signal.
   *
   * @param signalRequest The request body containing the trading signal to be processed.
   * @return A response indicating the result of signal processing.
   */
  @Operation(summary = " process a trading signal from a configure workflow", description = "This api is used to process a trading signal from a configure workflow")
  @ApiResponse(responseCode = "200", description = "trading signal processed successfully")
  @PostMapping("/processConfigureWorkflow")
  public ResponseEntity<String> processConfigureWorkflow(@RequestBody SignalRequest signalRequest) {
    try {
      signalService.processConfigureWorkflow(signalRequest.getSignal());
      return ResponseEntity.ok("Signal processed successfully");
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signal processing failed");
    }
  }

  @Operation(summary = " process a trading signal from a stored workflow", description = "This api is used to process a trading signal from a stored workflow")
  @ApiResponse(responseCode = "200", description = "trading signal processed successfully")
  @PostMapping("/processStoredWorkflow")
  public ResponseEntity<String> processStoredWorkflow(@RequestBody WorkflowExecutionRequest request) {
    try {
      signalService.processStoredWorkflow(request.signalID());
      return ResponseEntity.ok("Signal processed successfully");
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signal processing failed");
    }
  }
}