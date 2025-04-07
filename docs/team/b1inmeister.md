<!-- @@author b1inmeister -->
# Lee Ming Kai Joshua - Project Portfolio Page

## Overview of Project: Busyness Manager
Busyness Manager is a CLI-based inventory management application designed to help businesses track inventory, manage
sales, and compute revenue. The project is written in Java (~2.5kLoC) and incorporates authentication, secure data
handling, and structured command processing.

### Summary of Contributions

**Code Contributed:** 
[RepoSense Link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=b1inmeister&breakdown=true)

---

**New Features Implemented:**

> Added the ability to parse user input into usable information for execution.

  * _What it does:_ Splits the input String into the 'command' portion, and the 'information' portion.
  * _Justification:_ There is a need to extract the 'command' to call the right method for execution.
  * _Highlights:_ This feature required extension manipulation of Strings using the `.substring()` method. This means
  that thorough exception handling is needed to ensure that the splitting can be completed without termination.

> Implemented calling of respective execution methods, given information from the user input.

  * _What it does:_ Switches the execution methods based on the 'command' inputted from the user.
  * _Justification:_ The execution methods need to run for the features of Busyness Manager to work, thus making 
  this feature essential for basic operation.
  * _Highlights:_ This feature requires further splitting of the 'information' into separate Strings to act as 
  parameters. This aspect was challenging, as the chances of error resulting in termination is very high, needing
  careful and extensive error handling. 

---

**Enhancements Added:**

* Conducted automated text UI testing for Busyness Manager.
* Created JUnit tests for methods relevant to parsing.
* Resolved bugs from PE-D (#165 to #183)

---

**Contributions to Documentation:**

> _User Guide:_

  * Checked User Guide for irregularities before submission.
  * Added Known Bugs section and justified the presence of these bugs.

> _Developer's Guide:_

  * Added implementation and design details for the CommandParser class.
  * Added UML sequence diagrams for the CommandParser class and its methods.
  * Added Product Scope, User Stories, NFR and Testing Instructions. 

---

**Contributions to Team-Based Tasks:**

* Set up the team GitHub repository.
* Managed releases of executable files.
* Maintained the issue tracker and all corresponding issues.
* Maintained code quality throughout the development life-cycle.

---

**Contributions to Community / Review Tasks:**

* PRs Reviewed: [#66](https://github.com/AY2425S2-CS2113-F11-1/tp/pull/66#pullrequestreview-2681532166),
                [#71](https://github.com/AY2425S2-CS2113-F11-1/tp/pull/71#pullrequestreview-2688719182),
                [#126](https://github.com/AY2425S2-CS2113-F11-1/tp/pull/126#pullrequestreview-2709859483)
* Usage of Course Forum: [#27](https://github.com/nus-cs2113-AY2425S2/forum/issues/27#issue-2931206666)
