package de.db.product.tradingapplication.controller;

import de.db.product.tradingapplication.service.SignalService;
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
}