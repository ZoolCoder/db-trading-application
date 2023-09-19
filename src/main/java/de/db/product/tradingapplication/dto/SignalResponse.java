package de.db.product.tradingapplication.dto;

import java.util.List;

/**
 * Represents a trading signal SignalResponse.
 * This class uses the builder pattern for constructing instances.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
public class SignalResponse {
 private final List<String> messages;

    private SignalResponse(Builder builder) {
        this.messages = builder.messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public static class Builder {
        private List<String> messages;

        public Builder messages(List<String> messages) {
            this.messages = messages;
            return this;
        }

        public SignalResponse build() {
            return new SignalResponse(this);
        }
    }
}