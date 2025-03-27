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

The purpose of this application was to help businesses account for their sales and revenue.

Hence, we required multiple different classes to manage various different aspects of a business owner, such as:
1. Product and Inventory
2. Sales
3. Revenue
4. An appropriate and usable UI for the business to keep track of the above factors

The inventory of each business is therefore handled by a InventoryManager class, which contains a hashmap of various
key-value pairs of String ID and Product objects.

Each Product object contains various useful attributes, such as:
1. String ID
2. String name (of the product)
3. Current stock quantity
4. Quantity sold
5. Price of each instance of the product

InventoryManager(IM), while holding onto these objects in a data structure, has various methods to modify and display 
every product that is present in the business.

To utilise these functionalities present in IM, SalesManager and SearchManager each has an instance of the same 
InventoryManager object. For example, SalesManager will be in charge of recording the quantity sold of each item
while the SearchManager helps business owners to search and keep track of each product.

To calculate the revenue of the business (or each sales item), the RevenueCalculator class has an instance of 
SalesManager to calculate the revenue using the "Price" and "Quantity sold" of each Product object

To have an appropriate and usable interface for the users, we have the UI class, that handles printing of the different
output to its users, while the CommandParser class receives inputs and commands from its users.

All of these features are contained under the main BusynessManager class to provide its users a smooth experience in 
taking care of the different needs of businesses.

## Product scope
### Target user profile

Small business owners in Singapore who need a fast way to manage their inventory and its restocking.

### Value proposition

Busyness Manager will be a CLI-based inventory system that will allow users to track stock levels, set reorder alerts, 
and generate sales reports. Unlike generic inventory apps, there are plans to include Singapore-specific tax 
calculations (e.g. Goods and Services Tax) and supplier tracking

## User Stories

| Version | As a ...        | I want to ...                                    | So that I can ...|
|---------|-----------------|--------------------------------------------------|------------------|
| v1.0    | business owner  | adapt my business according to its business type |manage my business based on its needs|
| v1.0    | business owner with many assets| keep count of my assets                          |keep track of when I should restock|
| v1.0    | business owner with fast turnovers| add more products that I have bought             |keep my inventory filled|
| v1.0    | business owner with many products| be notified of when a product is running short   |replenish it before it runs out|
| v1.0    | business owner| track my sales                                   |manage my business in a profitable manner|
| v2.0    | business owner| manager customers                                |keep track of customers' needs|
| v2.0    | business owner| manage my suppliers                              | keep track of the most value for money suppliers|
| v2.0    | business owner| track the salaries of my employees               | everyone can work in peace|
| v2.0    | business owner| keep track of the different events I hold for my business as well as the cost incurred from it| keep my events in check|




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
