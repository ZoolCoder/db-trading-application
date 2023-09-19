# Trading Application

This is a trading application that processes trading signals using a predefined set of trading algorithms. The application is designed to be flexible, easy to maintain, and extensible.

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Customization](#customization)
- [License](#license)

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 17 or later
- Apache Maven (for building and managing dependencies)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ZoolCoder/db-trading-application
2. Navigate to the project directory:
   ```bash
   cd trading-application
3. Build the project using Maven:
   ```bash
   mvn clean install

### Usage
To use the trading application, follow these steps:

1. Run the application:
   ```bash
   java -jar target/trading-application.jar
2. The application exposes an HTTP endpoint to receive trading signals. Send a POST request to this endpoint with the trading signal in the request body.
Example using curl:
   ```bash
   curl -X POST -H "Content-Type: application/json" -d '{"signal": 1}' http://localhost:8080/trading/signal
3. The application will process the trading signal using the specified algorithm.

### Project Structure

### Customization
The trading application is designed to be flexible and extensible. team can customize various aspects of the application to suit new requirements.

### Adding New Trading Algorithms

The application uses the **Factory Method Design Pattern** to create instances of `SignalCommand`, which represents trading algorithms. To add new trading algorithms, follow these steps:

1. Create a new class that extends `SignalCommand`. For example, `MyCustomSignalCommand`:

   ```java
   public class MyCustomSignalCommand extends SignalCommand {
       // Implement the execute method with your custom algorithm logic
       @Override
       public void execute() {
           // Custom algorithm logic
       }
   }
2. Register your custom command in the SignalCommandFactory. Modify the createCommand method to handle your custom signal:
   ```java
    public SignalCommand createCommand(int signal, AlgoInterface algo) {
       switch (signal) {
           case 1:
                return new Signal1Command(algo);
           case 2:
                return new Signal2Command(algo);
           case 3:
                return new Signal3Command(algo);
           case 4:  // Add your custom signal
                return new MyCustomSignalCommand(algo);
           default:
                return new DefaultSignalCommand(algo);
       }
    }

Now, your custom trading algorithm is integrated into the application.

### License
This project is licensed under the MIT License. See the LICENSE file for details.
