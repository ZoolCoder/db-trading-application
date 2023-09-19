package de.db.product.tradingapplication.controller;

/**
 * Represents a trading signal request.
 * This class uses the builder pattern for constructing instances.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public class SignalRequest {
  private final int signal;

  private SignalRequest(Builder builder) {
    this.signal = builder.signal;
  }

  /**
   * Get the trading signal value.
   *
   * @return The trading signal value.
   */
  public int getSignal() {
    return signal;
  }

  /**
   * Builder class for constructing SignalRequest instances.
   */
  public static class Builder {
    private int signal;

    /**
     * Set the trading signal value.
     *
     * @param signal The trading signal value.
     * @return The builder instance.
     */
    public Builder signal(int signal) {
      this.signal = signal;
      return this;
    }

    /**
     * Build a SignalRequest instance.
     *
     * @return The constructed SignalRequest.
     */
    public SignalRequest build() {
      return new SignalRequest(this);
    }
  }

  /**
   * Create a new builder instance for SignalRequest.
   *
   * @return A new builder instance.
   */
  public static Builder builder() {
    return new Builder();
  }
}