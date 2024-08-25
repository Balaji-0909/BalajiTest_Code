# Login Validation Test Suite
Project contains a test suite for validating login functionality on a web application. 
Used primary programing language as Java
It uses Selenium WebDriver for browser automation.
Used the TestNG framework for test management.
Written code only to execute on chrome browser only

##Prerequisites

**Java Development Kit (JDK)**
**Maven**
**ChromeDriver**

## Setup
**Clone the Repository**: 
```bash 
git clone <https://github.com/Balaji-0909/BalajiTest_Code.git>
 cd BalajiTest_Code

##Install Dependencies:
## If using Maven:
**mvn install**

**Configure ChromeDriver**
Please adjust `chromedriver.exe` the path in the `LoginValidation.java` file.

## Running Tests 
1. **Using Maven**:
 ```bash 
mvn test ```

 2. **Using an IDE (Eclipse/IntelliJ IDEA)**: - 
Open the project in your IDE. - Right-click on `testng.xml`. - Select `Run As` > `TestNG Suite`.
 
## Test Scenarios

1. **Username with 2 Characters**
   - **Objective**: Verify that the username field highlights in red and an "Invalid Data" error message is displayed when the username contains 2 characters.
   - **Expected Result**: The username field should be highlighted in red, and the error message "Invalid Data" should be shown.

2. **Username with 3 Characters**
   - **Objective**: Verify that the user is redirected to the "Overview" tab when the username contains 3 characters.
   - **Expected Result**: The "Overview" tab should be visible after login.



