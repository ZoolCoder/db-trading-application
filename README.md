# Trading Application

The Trading Application is a flexible and extensible trading signal processing system that leverages various solutions for handling trading signals. This application is designed to efficiently manage a growing number of trading signals, making it ideal for evolving trading strategies and scenarios.

## Table of Contents
- [Trading Signal Handling](#trading-signal-handling)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Customization](#customization)
- [License](#license)

## Trading Signal Handling

The Trading Application is expected to handle a growing number of trading signals, with up to 50 new signals being added per month. To accommodate this scalability, three solutions have been implemented for handling trading signals:
### Solution 1: Command Classes
In this solution, the application relies on Command Classes, each representing various trading algorithms. The SignalCommandFactory is used to determine and execute the appropriate command class based on the signalID provided.

##### Pros:
* High Performance: Command classes offer high performance as they directly execute the trading algorithm without the need for additional configuration parsing.
* Extensibility: Adding new trading algorithms is straightforward. You can create a new class extending SignalCommand and register it in the factory.

##### Cons:
* Limited Flexibility: The trading algorithms are tightly coupled with code, making it less flexible for users to modify or extend them.
* Code Changes Required: Any changes or additions to trading algorithms require code modifications, potentially leading to deployment hassles.
### Solution 2: Predefined Workflow Configurations
In this solution, trading signals are processed using predefined workflow configurations loaded from a YAML file (signal-workflow.yaml). A dedicated handler reads the configuration file, converts it into a structured object, and stores it in a data structure such as a hashmap. These configurations define the sequence of actions and parameters to be executed for each trading signal. To extend or modify the processing logic, you can simply update the configuration file without changing the application's code.

##### Pros:

* Flexibility: The application becomes more flexible as trading strategies can be modified or extended by merely updating the configuration file.
* Separation of Concerns: Trading logic is decoupled from the codebase, making it easier to maintain and manage.
* User-Friendly: Traders and analysts can easily tweak and customize trading strategies without coding skills.
##### Cons:
* Parsing Overhead: Reading and parsing configuration files may introduce a slight performance overhead compared to Solution 1.
* Complex Configurations: Complex trading strategies may result in complex configuration files that are harder to manage.

### Solution 3: Database-Persisted Workflows
In this solution, trading signals are processed from stored workflows based on a specified signal ID. A robust database layer is implemented to store workflows, their associated actions, and parameters. When a signal is received, the application queries the database for the corresponding workflow based on the signal ID. It then executes the defined actions and parameters to process the signal. This approach allows for dynamic management of workflows, making it easy to create, modify, or delete workflows as trading strategies evolve.

##### Pros:
* Dynamic Management: The ability to create, modify, or delete workflows from a database provides dynamic management of trading strategies.
* Easy Auditing: Storing workflows and their execution history in a database allows for easy auditing and compliance tracking.
* Scalability: As the number of trading signals grows, a database-backed solution can scale effectively.
##### Cons:
* Database Overhead: Maintaining a database adds overhead compared to Solution 2, especially for smaller-scale applications.
* Complexity: Implementing a database layer introduces complexity in terms of setup, maintenance, and backups.
* Each solution has its merits, and the choice depends on your specific requirements, performance considerations, and the level of customization and flexibility needed for your trading application.

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


### License
This project is licensed under the MIT License. See the LICENSE file for details.
