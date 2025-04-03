<!-- @@author LEESY02 -->
# Lee Seung Yoon - Project Portfolio Page

## Overview of Project: Busyness Manager
Busyness Manager is a CLI-based inventory management application designed to help businesses track inventory, manage
sales, and compute revenue. The project is written in Java (~2.5kLoC) and incorporates authentication, secure data
handling, and structured command processing.

### Summary of Contributions

**Code Contributed:**
[RepoSense Link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=LEESY02&breakdown=true)
---
**New Features Implemented:**
> Added the ability to calculate Revenue of the business (or individual products)
* _What it does:_ Method calculates the revenue of each individual Product instance, given its quantitySold and price 
  attributes
* _Justification:_ Required for v1.0
* _Highlights:_ When RevenueCalculator is instantiated, we provide it an instance of SalesManager, which has an instance
  of InventoryManager. We then access the HashMap (ID : Product) contained under InventorManager. Revenue
  is then calculated from the attributes of individual Product instances
> Added UI and Constants classes
* _What it does:_ Streamlines printing of messages to the users, as well as holds different magic literals required
* _Justification:_ Enhances readability of the code, greater level of abstraction (Printing is done by UI class only)
* _Highlights:_ Messages could be printed directly using printMessage(), and formatted string could be printed using
  printFormattedMessage()
---

**Enhancements Added:**
* Created JUnit tests for methods relevant to InventoryManager, SalesManager, and RevenueCalculator.

---

**Contributions to Documentation:**
> _User Guide:_
* Formatting for enhanced readability and other miscellaneous additions
> _Developer's Guide:_
* Gave a general breakdown of how all the classes of the project interacts with one another
---
**Contributions to Team-Based Tasks:**
* Reviewed PRs
* Maintained code quality throughout the development life-cycle.
---
**Contributions to Community / Review Tasks:**
* PRs Reviewed: [#65](https://github.com/AY2425S2-CS2113-F11-1/tp/pull/65),
  [#68](https://github.com/AY2425S2-CS2113-F11-1/tp/pull/68),
  [#69](https://github.com/AY2425S2-CS2113-F11-1/tp/pull/69),
  [#125](https://github.com/AY2425S2-CS2113-F11-1/tp/pull/123),
  [#123](https://github.com/AY2425S2-CS2113-F11-1/tp/pull/125),
* Usage of Course Forum: [#1](https://github.com/nus-cs2113-AY2425S2/forum/issues/1),
  [#5](https://github.com/nus-cs2113-AY2425S2/forum/issues/5), 
  [#27](https://github.com/nus-cs2113-AY2425S2/forum/issues/27#issue-2931206666)
