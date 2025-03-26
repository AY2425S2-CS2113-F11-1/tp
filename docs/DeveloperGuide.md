# Developer Guide

## Acknowledgements
[CS2113 individualProject (b1inmeister)](https://github.com/b1inmeister/ip)

## Design & implementation
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Product scope
### Target user profile
* Involved in a business that sells goods (i.e. physical products).
  * This business is a for-profit business.
  * This business sells a variety of goods.
* Prefer desktop applications over other types.
* Prefer typing over using a mouse to navigate menus.
* Has the ability to type reasonably fast and accurately.
* Is used to handling CLI applications.

### Value proposition
Busyness Manager can manage the inventory of a business in a faster and more organised way than a typical GUI-driven 
application. Moreover, it is cheaper to run and maintain, compared to other inventory management applications in the 
market.

## User Stories
| Version | As a ...                                 | I want to ...                                        | So that I can ...                                               |
|---------|------------------------------------------|------------------------------------------------------|-----------------------------------------------------------------|
| v1.0    | business owner                           | adapt my configuration according to my business type | manage my business based on its needs                           |
| v1.0    | business owner with new products to sell | add products to my database                          | indicate that these products are to be sold                     |
| v1.0    | business owner with outdated products    | remove products from my database                     | indicate that my business is not selling these products anymore |
| v1.0    | forgetful business owner                 | see all my products in one list                      | find out what products my business sells                        |
| v1.0    | curious business owner                   | the data of the products in my database              | find out more about the performance of that product             |
| v1.0    | business owner                           | modify the data of a product in my database          | keep the information about my product up-to-date                |
| v1.0    | business owner with many products        | search for a product in my database                  | find out if that product is sold by my business                 |
| v1.0    | for-profit business owner                | track my sales                                       | manage my business in a profitable manner                       |
| v2.0    | forgetful business owner                 | have a list of possible commands and formats         | recall what commands I can use                                  |
| v2.0    | business owner                           | save my database                                     | reuse the existing database when I restart the application      |

## Non-Functional Requirements
* Should work on any _mainstream OS_ with Java 17 installed.
* Should serve a business with up to 10,000 goods for sale.
* A user with decent typing speed for normal text should be able to complete most tasks faster through typing
out commands, compared to using the mouse to navigate a GUI application.

## Glossary
* **Mainstream OS** - Windows, Linux, Unix, MacOS

## Instructions for manual testing
### Launch
1. Download the `.jar` file and copy into an empty folder.
2. Open Command Prompt on Windows / Terminal on MacOS.
3. Change the current working directory to the folder containing the `.jar` file.
4. run `java -jar <file name>.jar` on Command Prompt / Terminal.

### Adding a product

### Deleting a product

### Updating a product

### Searching a product

### Calculation of Revenue

