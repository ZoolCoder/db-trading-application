package de.db.product.tradingapplication.controller;

import de.db.product.tradingapplication.dto.SignalRequest;
import de.db.product.tradingapplication.service.SignalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RestController
@RequestMapping("/api")
public class SignalController {

  private final SignalService signalService;

  @Autowired
  public SignalController(SignalService signalService) {
    this.signalService = signalService;
  }

  /**
   * Handles an HTTP POST request to process a trading signal.
   *
   * @param signalRequest The request body containing the trading signal to be processed.
   * @return A response indicating the result of signal processing.
   */
  @Operation(summary = " process a trading signal", description = "This api is used to process a trading signal")
  @ApiResponse(responseCode = "200", description = "trading signal processed successfully")
  @PostMapping("/signals")
  public ResponseEntity<String> processSignal(@RequestBody SignalRequest signalRequest) {
    try {
      int signal = signalRequest.getSignal();
      signalService.processSignal(signal);
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
  @Operation(summary = " process a trading signal", description = "This api is used to process a trading signal")
  @ApiResponse(responseCode = "200", description = "trading signal processed successfully")
  @PostMapping("/configureSignals")
  public ResponseEntity<String> executeWorkflowForSignal(@RequestBody SignalRequest signalRequest) {
    try {
      signalService.executeWorkflowForSignal(signalRequest.getSignal());
      return ResponseEntity.ok("Signal processed successfully");
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signal processing failed");
    }
  }
}