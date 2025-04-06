<!-- @@author amirhusaini06 -->
# User Guide for Busyness Manager

## First-Time Setup:

* Define business information
  * Input:
    * Business ID: A whole number 
    * Business name: A set of words 
    * Business password: A sequence of letters
    * Business type: FNB or RETAIL (case-sensitive)

## Features:

* **Login**
  * Requires business ID and password.
  * Subsequent features can be accessed after logging in.

* **Viewing Help:** `help`
  * Provides possible command instructions and expected formatting.

* **Adding a Product:** `add`
  * Adds a product to the products-for-sale list.

- **Deleting a Product:** `delete`
    - Removes a product from the products-for-sale list.

* **Deleting a Product:** `delete`
  * Removes a product from the products-for-sale list.


* **Changing Info of Products:** `update`
  * Updates data of a specified product in the products-for-sale list.
  * Data to be updated must be specified during command input.

- **Printing List of Products:** `print`
    - Prints the entire list of products for sale (according to product ID).

* **Printing List of Products:** `list`
  * Prints the entire list of products for sale (according to product ID).

- **Incrementing Sold Product Value, Decrementing Inventory Value:** `sold`
    - Changes the quantity of available inventory and the quantity sold.

* **Incrementing Sold Product Value, Decrementing Inventory Value:** `sold`
  * Changes the quantity of available inventory and the quantity sold.

- **Clear the Quantity Sold:** `clear`
    - Changes the quantity sold of a specified product to 0.

* **Clear the Quantity Sold:** `clear`
  * Changes the quantity sold of a specified product to 0.

* **Compute Total Amount of Sale Transactions:** `revenue`
  * Adds up all the products sold, multiplied by their respective prices.
    * Given a product ID, provides the revenue of a specified product.

* **Search for a Product:** `search`
  * Given a product name, searches for the corresponding product.
  * Given a product ID, searches for the corresponding product.

---

## Command Summary:

**Important things to take note:**

* Add a **slash** (`/`) in front of any tag.
* All commands (e.g. `add` / `delete`) are **LOWERCASE**.
* To input ID of product, just the **non-zero part** will suffice. *(i.e. 1 instead of ID_0001)*
* Quantity of all products must be a **WHOLE** number.
* Price of all products must be to **2 DECIMAL PLACES**.
* Names of all products must be **ONE-WORD** long. (further explained [below](#known-bugs))
* IDs will **NOT** be usable after deletion. (further explained [below](#known-bugs)) 

### **Adding a Product**

> `add P_NAME P_QTY P_PRICE`

* Creates a new product object & attaches different attributes to it (with an auto-generated ID).
* Values:
  * `P_NAME` →  Name of the product to create. *(must be **one-word only**)*
  * `P_QTY` → Quantity of the product to create that is available for sale. *(must be **whole number**)*
  * `P_PRICE` → Price of the product to create. *(must be **2 decimal places**)*

**Example:**
* Input -> `add MILK 50 2.50`
* Output ->`Product added: ID_0001: MILK | Qty: 50 | Sold: 0 | Price: $2.50`

---

### **Deleting a Product**

> `delete P_ID`

* Deletes a specified product with the given ID.
* Values:
  * `P_ID` → ID of the product to delete. *(just the non-zero part will suffice)*


**Example:**

* Input -> `delete 1`
* Output -> `Product removed: ID_0001: MILK | Qty: 50 | Sold: 0 | Price: $2.50`

---

### **Updating a Product Name**

> `update P_ID /name NEW_NAME`

* Changes one or more attributes of a specified product.
* Tags:
  * `/name` → Tag to indicate the attribute to edit is the product name.
* Values: 
  * `P_ID` → ID of the product to modify. *(just the non-zero part will suffice)*
  * `NEW_NAME` → New name of the product to modify. *(must be **one-word only**)*

**Example:**

* Input -> `update 1 /name FRESH_MILK`
* Output -> `Product updated: ID_0001: FRESH_MILK | Qty: 50 | Sold: 0 | Price: $2.50`

---

### **Updating a Product Quantity**

> `update P_ID /qty NEW_QTY`

* Changes one or more attributes of a specified product.
* Tags:
  * `/qty` → Tag to indicate the attribute to edit is the product quantity.
* Values:
  * `P_ID` → ID of the product to modify. *(just the non-zero part will suffice)*
  * `NEW_QTY` → New quantity of the product to modify. *(must be **whole number**)*

**Example:**

* Input -> `update 1 /qty 60`
* Output -> `Product updated: ID_0001: FRESH_MILK | Qty: 60 | Sold: 0 | Price: $2.50`

---

### **Updating a Product Price**

> `update P_ID /price NEW_PRICE`

* Changes one or more attributes of a specified product.
* Tags:
  * `/price` → Tag to indicate the attribute to edit is the product price.
* Values:
  * `P_ID` → ID of the product to modify. *(just the non-zero part will suffice)*
  * `NEW_PRICE` → New price of the product to modify. *(must be **2 decimal places**)*

**Example:**

* Input -> `update 1 /price 3.00`
* Output -> `Product updated: ID_0001: FRESH_MILK | Qty: 60 | Sold: 0 | Price: $3.00`

---

### **Printing Product List**

> `list`

* Prints the current list of products.
* Attributes displayed:
  * Product ID
  * Product name
  * Quantity of product not sold
  * Quantity of product sold
  * Product price

**Example:**

* Input -> `list`
* Output -> `Product list: (next line) ID_0001: FRESH_MILK | Qty: 60 | Sold: 0 | Price: $3.00`

_Format of Product List:_ `ID_NUMBER: PRODUCT_NAME | QTY | QTY_SOLD | PRICE`

---

### **Recording a Sale**

> `sold P_ID QTY_SOLD`

* Changes the sales record and updates the inventory list.
* Tags:
  * `P_ID` → ID of the product that was sold. *(just the non-zero part will suffice)*
  * `QTY_SOLD` → Quantity of the product that was sold. *(must be **whole number**)*

**Example:**

* Input -> `sold 1 5`
* Output -> `Sales recorded: Product ID: ID_0001, Quantity Sold: 5`

---

### **Clearing Sold Quantity**

> `clear P_ID`

* Sets the quantity sold to zero for the specified product.
* Tags:
  * `P_ID` → ID of the product to clear. *(just the non-zero part will suffice)*

**Example:**

* Input -> `clear 1`
* Output -> `Sales cleared: Product ID: ID_0001`

---
<!-- @@author himethcodes -->
### **Computing Revenue**

> `revenue` / `revenue P_ID`

* Computes the total amount of revenue.
* Optional `P_ID` tag (to check revenue for a specific product).

**Example (Overall Revenue):**

* Input -> `revenue`
* Output -> `Revenue of FRESH_MILK: 15.00 (next line) Total Revenue: 15.00`

**Example (Specific Product Revenue):**

* Input -> `revenue 1`
* Output -> `Revenue of FRESH_MILK: 15.00`

---
<!-- @@author rozaliesmit -->
### **Searching for a Product by Name**

> `search /name P_NAME`

* Returns the ID of the product that matches the given name.
* Tags:
  * `/name` → Name of product to search for. *(must be **one-word only**)*

**Example:**

- Input -> `search /name FRESH_MILK`
- Output -> `Product ID of FRESH_MILK: ID_0001`

---
<!-- @@author LEESY02 -->
### **Searching for a Product by ID**

> `search /id P_ID`

* Returns the name of the product that matches the given ID.
* Tags:
  * `/id` → ID of the product to search for. *(just the non-zero part will suffice)*

**Example:**

* Input -> `search /id 1`
* Output -> `Product name of ID_0001: FRESH_MILK`

<!-- @@author b1inmeister -->
## Known Bugs:

1. The names of all products in the inventory must be one-word only. This means that names like *fresh milk* and *cheese
tofu* are not accepted. To add products with names like these, the space in their names must be replaced by underscores.
For instance, *fresh milk* must be replaced by **fresh_milk**, and *cheese tofu* must be replaced by **cheese_tofu**.
This bug is due to implementation limitations, and will be addressed in future versions.
2. After an ID is deleted, it will not be usable within that business's inventory. While this may be a bug to some 
users, this is by design. In the context of business operations, product IDs ought not to be re-used for data integrity
purposes. Furthermore, in databases like our inventory, there is the need to ensure that the primary keys in databases, 
which in this case are the product IDs, are unique and immutable. Thus, in Busyness Manager, the product IDs will not be
reusable after deletion from the inventory.
