package de.db.product.tradingapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.db.product.tradingapplication.dto.SignalRequest;
import de.db.product.tradingapplication.service.SignalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SignalControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SignalService signalService;

  @Autowired
  private ObjectMapper objectMapper; // ObjectMapper to serialize/deserialize JSON

  @Test
  public void testProcessSignalEndpoint() throws Exception {

    // Create a SignalRequest object to send in the request body
    SignalRequest signalRequest = new SignalRequest(4);

    // Perform an HTTP POST request to the /api/signals endpoint
    mockMvc.perform(MockMvcRequestBuilders.post("/signal/process")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signalRequest)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Signal processed successfully"));
  }

  @Test
  public void testExecuteWorkflowForSignalEndpoint() throws Exception {

    // Create a SignalRequest object to send in the request body
    SignalRequest signalRequest = new SignalRequest(2);

    // Perform an HTTP POST request to the /api/configureSignals endpoint
    mockMvc.perform(MockMvcRequestBuilders.post("/signal/processConfigureWorkflow")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signalRequest)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Signal processed successfully"));
  }
}
