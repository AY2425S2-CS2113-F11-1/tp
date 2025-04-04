<!-- @@author amirhusaini06 -->
# Amir Husaini Bin Musa - Project Portfolio Page

## Overview of Project: Busyness Manager
Busyness Manager is a CLI-based inventory management application designed to help businesses track inventory, manage 
sales, and compute revenue. The project is written in Java (~2.5kLoC) and incorporates authentication, secure data 
handling, and structured command processing.

### Summary of Contributions

#### Code Contributed
- [Code Dashboard Link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=amirhusaini06&tabRepo=AY2425S2-CS2113-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Enhancements Implemented
- **Authentication System**: Implemented password encryption using bcrypt for secure login and authentication 
persistence across reboots.
- **Command Parsing System**: Refactored the command parser to improve modularity and maintainability.
- **Data Storage and Persistence**: Developed functionality to store and retrieve business credentials and inventory 
data.
- **Business Operations Management**: Created the `BusynessManager` class to handle core functionalities such as stock 
tracking, sales logging, and revenue calculation.
- **Error Handling**: Implemented validation checks and ensured robust error handling for incorrect command formats and 
missing attributes.

#### Contributions to the User Guide (UG)
- Documented authentication features, including ID-based login.
- Provided structured explanations for command usage, including:
    - `add`, `delete`, `update`, `display`, `print`, `search`, `sold`, `clear`, `revenue`
    - Command summaries and expected outputs for better user understanding.
- Created example scenarios demonstrating correct and incorrect usage of commands.

#### Contributions to the Developer Guide (DG)
- **Design & Implementation**:
    - Wrote the **Architecture Overview**, explaining the OOP design of `BusynessManager`, `InventoryManager`, 
    `SalesManager`, and `RevenueCalculator`.
    - Documented the **Authentication System**, including password storage mechanisms and verification methods.
    - Authored explanations for key modules, including **Inventory Management** and **Revenue Calculation**.
- **Manual Testing Instructions**:
    - Created test cases for adding, deleting, updating, and searching products.
    - Included edge cases such as invalid inputs, missing parameters, and negative values.
- **Glossary**:
    - Defined core terms like **Business Credentials**, **Command Parser**, **CLI**, and **Revenue Calculator**.

#### Contributions to Team-Based Tasks
- Led discussions on security implementations, ensuring proper encryption methods were used.
- Assisted in debugging authentication-related issues and refining class structures.
- Conducted code reviews, improving efficiency and maintainability.

#### Review/Mentoring Contributions
- Reviewed multiple pull requests, focusing on security, error handling, and best practices.
- Provided guidance on implementing secure password handling using hashing techniques.
- Assisted team members in refining their OOP design and class interactions.
