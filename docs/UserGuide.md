<!-- @@author amirhusaini06 -->
# User Guide for Busyness Manager

## Start Up:
1. Define business name and type (during first-time use)
2. Input:
    - **Integer** of business ID
    - **String** of business name
    - **String** of business password
    - **String** of business type (recognized as an enumeration, e.g., `FNB` / `RETAIL`)
3. List format and information will be structured according to the business type enumeration.

## Features:
- **Login**
    - Requires ID and password.
    - Subsequent features can be accessed after logging in.

- **Viewing Help: `help`**
    - Prints possible command instructions and expected formatting.

- **Adding a Product: `add`**
    - Adds a product to the products-for-sale list.

- **Deleting a Product: `delete`**
    - Removes a product from the products-for-sale list.

- **Checking Info of Products: `display`**
    - Shows data of the specified product.

- **Changing Info of Products: `update`**
    - Updates data of a specified product.
    - Data to be updated must be specified during command input.

- **Printing List of Products / Equipment: `print`**
    - Prints the entire list of products for sale (classified according to product ID).

- **Incrementing Sold Products List, Decrementing Inventory: `sold`**
    - Changes the quantity of the inventory and sold products list.

- **Clear the Quantity Sold: `clear`**
    - Changes the quantity sold of a product to 0.

- **Compute Total Amount of Sale Transaction: `revenue`**
    - Adds up all the prices of all products sold, multiplied by their quantity.
    - Given a product ID, provides the revenue of the product

- **Search for the ID of a Product: `search`**
    - Given a product ID, searches for the corresponding product.

---

## Command Summary:
> *Add a slash (`/`) in front of the tag.*

### **Adding a Product**
> **add** /name PRODUCT_NAME /qty QUANTITY /qty_sold QUANTITY_SOLD /price PRICE

- Creates a new product item & attaches different attributes to it (with auto-generated ID).
- `QUANTITY_SOLD` defaults to zero unless stated otherwise.
- Optional tags:
    - `/qty` → Quantity available.
    - `/price` → Price of product.

**Example:**
Input -> add /name MILK /qty 50 /price $2.50
Output -> _"MILK added, qty 50, price $2.50, ID = 0001"_

---

### **Deleting a Product**
> **delete** /id PRODUCT_ID

- Deletes the specific product with the given ID.
- Confirmation prompt (`yes/no`) before deletion.

**Example:**
Input -> delete /id 1
Output -> _"ID_0001: MILK removed"_

---

### **Displaying Product Info**
> **display** /id PRODUCT_ID

- Prints data of each product.
- Uses `toString()` of each product.
- Optional flags to display specific product data.

**Example:**
Input -> display /id 1
Output -> _"ID_0001: MILK, qty 50, price $2.50"_

---

### **Updating a Product**
> **update** /id PRODUCT_ID /name NEW_NAME /qty NEW_QUANTITY /price NEW_PRICE

- Changes one or more attributes of a product.
- Optional tags:
    - `/qty` → New quantity.
    - `/price` → New price.
    - `/name` → New name.

**Example:**
Input -> update /id 1 /name FRESH_MILK /qty 45 /price $3.00
Output -> _"ID_0001: updated to FRESH_MILK, qty 45, price $3.00"_

---

### **Printing Product List**
> **print**

- Prints the current list of products (default: quantity displayed).
- Optional flag `/price` → Displays price of each item.

**Example:**
Input -> print /price
Output -> _"ID_0001: MILK | $2.50"_

_Format: `ID_NUMBER: PRODUCT_NAME | PRICE`_

---

### **Recording a Sale**
> **sold** /id PRODUCT_ID /qty_sold QUANTITY_SOLD

- Changes sales record and updates the inventory list.

**Example:**
Input -> sold /id 0001 /qty 5
Output -> _"Sale Recorded: MILK (ID_0001), Quantity Sold: 5, Updated Inventory: 45 Remaining"_

---

### **Clearing Sold Quantity**
> **clear** /id PRODUCT_ID

- Sets the `/qty_sold` value to zero for the specified product.
- Confirmation prompt (`yes/no`) before executing.

**Example:**
Input -> clear /id 1
Output -> _"Qty Sold for MILK (ID_0001) has been set to 0"_

---

### **Computing Revenue**
> **revenue** /id PRODUCT_ID

- Computes the total amount of revenue.
- Optional `/id` flag to check revenue for a specific product.

**Example (Overall Revenue):**
Input -> revenue
Output -> _"Total revenue for your busyness is $0 dollars!"_

**Example (Specific Product Revenue):**
Input -> revenue /id 1
Output -> _"Revenue for MILK (ID_0001) is $0 dollars!"_

---

### **Searching for a Product by Name**
> **search** /name PRODUCT_NAME

- Returns the product ID of a product that matches the given name.

**Example:**
Input -> search /name MILK
Output -> _"MILK (ID_0001)"_
