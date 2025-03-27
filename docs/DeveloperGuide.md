# Developer Guide

## Acknowledgements
[CS2113 individualProject (amirhusaini06)](https://github.com/amirhusaini06/ip)

## Design & implementation

The **Busyness Manager** is a command-line business management application designed for small business owners to manage their inventory, sales, and revenue tracking efficiently.

### Architecture Overview
- The project follows an **Object-Oriented Programming (OOP)** approach, with separate classes handling different aspects of business management.
- The **Command Parser** is responsible for processing user input and calling the appropriate methods.
- The **InventoryManager** manages the addition, removal, and modification of products.
- The **SalesManager** keeps track of product sales and revenue.
- The **RevenueCalculator** computes total revenue and sales reports.
- The **Credentials** class stores business authentication details, ensuring security.
- The **BusynessManager** acts as the central controller, orchestrating interactions between components.

### Key Features & Implementation Details
- **Data Persistence:** Business credentials and inventory data are stored in a text file within the `data/` directory, ensuring persistence across application restarts.
- **Authentication:** The application verifies business credentials at startup using stored authentication data.
- **User Commands:** The application accepts structured user input through a command-line interface, with validation for incorrect formats.


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* **Business Credentials** - Unique identifiers (ID, name, password) for a registered business.
* **Command Parser** - A component responsible for interpreting user commands and triggering appropriate actions.
* **Inventory Manager** - Manages stock, allowing addition, deletion, and updates of products.
* **Sales Manager** - Tracks product sales and ensures accurate revenue reporting.
* **Revenue Calculator** - Computes total revenue and generates sales reports.
* **CLI (Command-Line Interface)** - A text-based interface where users interact with the application.

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
