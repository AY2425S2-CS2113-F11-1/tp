<!-- @@author amirhusaini06 -->
# User Guide for Busyness Manager

## First-Time Setup:
- Define business information
   - Input:
      - Business ID: A whole number 
      - Business name: A set of words 
      - Business password: A sequence of letters
      - Business type: FNB or RETAIL (case-sensitive)

## Features:
- **Login**
    - Requires business ID and password.
    - Subsequent features can be accessed after logging in.

- **Viewing Help:** `help`
    - Provides possible command instructions and expected formatting.

- **Adding a Product:** `add`
    - Adds a product to the products-for-sale list.

- **Deleting a Product:** `delete`
    - Removes a product from the products-for-sale list.
  
- **Changing Info of Products:** `update`
    - Updates data of a specified product in the products-for-sale list.
    - Data to be updated must be specified during command input.

- **Printing List of Products:** `print`
    - Prints the entire list of products for sale (according to product ID).

- **Incrementing Sold Product Value, Decrementing Inventory Value:** `sold`
    - Changes the quantity of inventory available and the quantity sold.

- **Clear the Quantity Sold:** `clear`
    - Changes the quantity sold of a specified product to 0.

- **Compute Total Amount of Sale Transactions:** `revenue`
    - Adds up all the products sold, multiplied by their prices.
    - Given a product ID, provides the revenue of a specified product.

- **Search for a Product:** `search`
    - Given a product name, searches for the corresponding product.
    - Given a product ID, searches for the corresponding product.

---

## Command Summary:
> *Add a slash (`/`) in front of the tag.*

### **Adding a Product**
> **add** /name P_NAME /qty P_QTY /price P_PRICE

- Creates a new product object & attaches different attributes to it (with an auto-generated ID).
- Tags:
    - `/name` →  Name of the product to create.
    - `/qty` → Quantity of the product to create that is available for sale.
    - `/price` → Price of the product to create.

**Example:**
- Input -> add /name MILK /qty 50 /price 2.50
- Output -> _"MILK added, qty 50, price $2.50, ID = 0001"_

---

### **Deleting a Product**
> **delete** /id ID

- Deletes the specific product with the given ID.
- Tags:
  - `/id` → ID of product (just the non-zero part will suffice).

**Example:**
- Input -> delete /id 1
- Output -> _"ID_0001: MILK removed"_

---

### **Updating a Product**
> **update** /id ID /name NEW_NAME /qty NEW_QTY /price NEW_PRICE

- Changes one or more attributes of a product.
- Tags:
    - `/id` → ID of product (just the non-zero part will suffice).
    - `/name` → New name.
    - `/qty` → New quantity.
    - `/price` → New price.

**Example:**
- Input -> update /id 1 /name FRESH_MILK /qty 45 /price $3.00
- Output -> _"ID_0001: updated to FRESH_MILK, qty 45, price $3.00"_

---

### **Printing Product List**
> **print**

- Prints the current list of products.
- Attributes displayed:
  - Product ID
  - Product name
  - Quantity of product not sold
  - Quantity of product sold
  - Product price

**Example:**
- Input -> print
- Output -> _"ID_0001: MILK | $2.50"_

_Format of Product List: `ID_NUMBER: PRODUCT_NAME | PRICE`_

---

### **Recording a Sale**
> **sold** /id ID /qty QTY_SOLD

- Changes sales record and updates the inventory list.
- Tags:
  - `/id` → ID of the product that is sold (just the non-zero part will suffice).
  - `/qty` →

**Example:**
- Input -> sold /id 1 /qty 5
- Output -> _"Sale Recorded: MILK (ID_0001), Quantity Sold: 5, Updated Inventory: 45 Remaining"_

---
<!-- @@author b1inmeister -->
### **Clearing Sold Quantity**
> **clear** /id ID

- Sets the quantity sold to zero for the specified product.
- Tags:
  - `/id` → ID of the product to clear (just the non-zero part will suffice).

**Example:**
- Input -> clear /id 1
- Output -> _"qty Sold for MILK (ID_0001) has been set to 0"_

---
<!-- @@author himethcodes -->
### **Computing Revenue**
> **revenue** /id ID

- Computes the total amount of revenue.
- Optional `/id` tag (to check revenue for a specific product).

**Example (Overall Revenue):**
- Input -> revenue
- Output -> _"Total revenue for your busyness is $0 dollars!"_

**Example (Specific Product Revenue):**
- Input -> revenue /id 1
- Output -> _"Revenue for MILK (ID_0001) is $0 dollars!"_

---
<!-- @@author rozaliesmit -->
### **Searching for a Product by Name**
> **search** /name NAME

- Returns the ID of a product that matches the given name.
- Tags:
  - `/name` → Name of product to search for.

**Example:**
- Input -> search /name MILK
- Output -> _"MILK (ID_0001)"_

---
<!-- @@author LEESY02 -->
### **Searching for a Product by ID**
> **search** /id ID

- Returns the product name of a product that matches the given ID.
- Tags:
  - `/id` → ID of the product to search for (just the non-zero part will suffice).

**Example:**
- Input -> search /id 1
- Output -> _""_
