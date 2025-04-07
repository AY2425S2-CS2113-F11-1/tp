<!-- @@author rozaliesmit -->

# Rozalie Lisen Smit - Project Portfolio Page

## Overview of Project: Busyness Manager

Busyness Manager is a CLI-based inventory management application designed to help businesses track inventory, manage
sales, and compute revenue. The project is written in Java (~2.5kLoC) and incorporates authentication, secure data
handling, and structured command processing.

### Summary of Contributions

**Code Contributed:**
[RepoSense Link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=rozaliesmit&breakdown=true)

---

### New Features Implemented

- **Created the `SalesManager` class** to update sales transactions and manage inventory.
    - **What it does:** Updates sales transactions, decrements inventory and increases quantity sold, resets quantity
      sold through the `clear` command.
    - **Justification:** The application needs the ability to record sales transactions and update the inventory
      accordingly to effectively manage its transactions.
    - **Highlights:** This feature required synchronization of sales data and inventory levels, calling for validation
      of sales quantity and integration with the existing `InventoryManager` class to maintain consistency within the
      data.

---

### Enhancements Added

- Added a password recovery function

### Contributions to Documentation

#### User Guide:

- Reviewed descriptions of the `SalesManager` features.
- Updated descriptions

#### Developer's Guide:

- Added the design and implementation of the `SalesManager` class.

---

### Contributions to Team-Based Tasks

- Collaborated to improve code quality and maintain project standard consistency
- Discussed solutions and feedback to streamline feature integration
- Fixed bugs 162-164 from PED

---
