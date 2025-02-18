# Beymen Web UI Automation Project

This project is a **Selenium-based UI Test Automation framework** designed to test the **Beymen.com** website. It leverages a number of technologies to ensure that the website is thoroughly tested, including **Cucumber BDD**, **Java**, **Maven**, and **Log4j**.

## Technologies Used

- **Selenium**: For automating web browser interaction.
- **Cucumber BDD**: A behavior-driven development style used for writing tests in an easily understandable format.
- **Java**: The programming language used for developing the test automation framework.
- **Maven**: A build automation tool to manage project dependencies and build the project.
- **POM (Page Object Model)**: A design pattern used to ensure the scalability and readability of test scripts.
- **Log4j**: Used for logging test execution and tracking test results.
- **JUnit**: For assertions and defining hooks within the test framework.
- **Apache POI**: Used to read test data from Excel files.

## Features

The project includes 6 key scenarios designed to test various aspects of the Beymen.com website, such as:

- Opening the home page
- Search functionality
- Shopping cart behavior


## Prerequisites

Before running the project, ensure that you have the following installed on your machine:

- **Java 8 or higher**
- **Maven** (for project dependency management and building)
- **IDE** (like IntelliJ IDEA, Eclipse, or Visual Studio Code)

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/nurdanakman/Beymen-Web-UI-Automation.git
   ```
2. Navigate to the project directory:
   ```sh
   cd Trello-API-Automation
   ```
3. Install dependencies using Maven:
   ```sh
   mvn clean install
   ```

## Project Structure

The project has the following structure:

- `src/test/java`: Contains the test scripts.
- `pom.xml`: Maven configuration file for dependencies.
- `logs`: Folder for log files generated by Log4j.
- `target`: Folder for compiled classes and test results.
- `.gitignore`: Git configuration to ignore unnecessary files.

## Usage

### Running Tests

To run the tests:

1. Navigate to the `src/test/java` directory.
2. Run the tests using Maven:
   ```bash
   mvn clean test
   ```
3. Run the tests using TestRunner:
   ```bash
   "TestRunner". "src > test > java > runner > TestRunner"
   ```

### Logging

Log4j is used for logging within the project. The log files are stored in the `logs` directory.
