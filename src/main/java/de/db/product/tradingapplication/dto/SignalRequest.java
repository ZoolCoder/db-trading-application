package de.db.product.tradingapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * Represents a trading signal request.
 * This class uses the builder pattern for constructing instances.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignalRequest {
  private int signal;
}