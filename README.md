# VRP QA Challenge
This is a challenge to show the skills for a QA Automation Engineer position at VRP Consulting

## Challenge
The challenge is to automate the following scenario using Selenium WebDriver and Java:

#### The preconditions:
- An Organization has a Salesforce account
- Open the browser and navigate to the URL: https://login.salesforce.com
- Login with the credentials which are in the configuration file

#### The scenario 1 is:
- Create a new Account with information from the data file (**OrgAccountsData.csv**)
- Verify that the Account was created successfully by checking the message content in the page
- Verify that the Account was created successfully by checking the Account Name in the accounts list page

#### The scenario 2 is:
- Edit the Account created in the scenario 1 with information from the data file (**OrgAccountsData.csv**)
- Verify that the Account was edited successfully by checking the message content in the page
- Verify that the Account was edited successfully by checking the Account Name in the accounts list page

## Solution
The solution is a Maven project with the following structure:
- **src/main/java/com/vrp/qachallenge/pages**: Contains the classes to manage interface of the Salesforce page
- **src/test/java/com/vrp/qachallenge/tests**: Contains the classes to run the tests
- **src/test/java/com/vrp/qachallenge/utils**: Contains the classes to manage the configuration and data files. Also, contains the class to manage the WebDriver. 
- **src/test/java/com/vrp/qachallenge/models**: Contains the classes to manage the data from the data file 
- **src/test/resources/data**: Contains the data file
- **src/test/resources**: Contains the configuration file
- **pom.xml**: Contains the dependencies and the configuration to run the tests
- **Jenkinsfile**: Contains the configuration to run the tests in Jenkins
- **README.md**: Contains the instructions to run the tests

The tests are implemented using the **Page Object Model** (POM), **Page Factory** and the **Data-Driven-Testing** approach. The tests are implemented using the TestNG framework and the Allure report to show the results of the tests.

## Instructions
To run the tests, you need to have the following tools installed:
- Java 17
- Maven
- Chrome, Firefox or Edge browsers
- Git
- IDE (Eclipse, IntelliJ, etc)

To run the tests, you need to follow the steps:
1. Clone the repository
2. Open the project in the IDE
3. You can run the tests the command line:
   - Open the terminal
   - Navigate to the project folder
   - Run the command: `mvn clean test`
   - The tests will run and the results will be shown in the terminal
   - The reports of the tests will be saved in the folder **target/allure-results** in the project folder
   - To see the reports, you need to run the command: `mvn allure:serve`
   - The reports will be shown in the browser

## Notes
- The tests are running in the Chrome browser by default, but you can change the browser in the configuration file.
- The third test (`testDeleteOrgAccount`) is implemented to fail to show the report of the failed test in the Allure report and the screenshot of the failed test attached to the report.
- You will see that to open the Accounts page, I use the `driver.get()` method instead of the `click` method. This is because the click method was not working for that elements in the navbar on **chrome** and **edge** (chromium) drivers. The error message was: `org.openqa.selenium.JavascriptException: javascript error: Cannot read properties of undefined (reading 'defaultView')`. I'm still investigating the cause of this error. The click method works fine in the **firefox** driver. 
