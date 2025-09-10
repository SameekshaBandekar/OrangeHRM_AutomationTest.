**OrangeHRM Automation Project**


**Project Overview**

This project contains automation scripts for the OrangeHRM web application using Selenium WebDriver with Java.
The project demonstrates automation with both positive and negative scenarios using TestNG, DataProvider, and Page Object Model (POM) design pattern.


**Project Structure**

OrangeHRMAutomation/

├─ src/main/java/            # Page Object classes, #Resources, #Utils

├─ src/test/java/            # orangeHRMTests, #TestComponents

├─ testng.xml                # OrangeHRMTestNG suite configuration

├─ pom.xml                   # Maven project file

├─ README.md            # Project documentation

└─ reports/                  # Test execution extent reports


**Tools & Technologies**

•	Selenium WebDriver - Browser automation

•	Java - Programming language

•	IDE - Eclipse

•	TestNG - Test framework

•	Page Object Model (POM) - Design pattern

•	DataProvider - Parameterized testing

•	Repots – Extent Reports

•	Maven - Project build and dependency management

•	Git - Version control

•	Jenkins - Continuous Integration and execution


**Test Scenarios**
**Positive Scenarios**
1.	Login and edit Employee details
   
3.	Validate Timesheet Submission
   
**Negative Scenarios**
1.	Invalid login with wrong credentials
   
3.	Search for invalid employee name.

   
   
**How to Run**

**	Prerequisites**

•	Java 8+ installed

•	Maven installed

•	Chrome/Edge/Firefox browser installed

•	Git cloned project repository


**	Steps to Execute**

1.	Clone the repository:
git clone < https://github.com/SameekshaBandekar/OrangeHRM_AutomationTest..git>
2.	Navigate to the project directory:
cd OrangeHRMAutomation
3.	Run tests using Maven:
mvn clean test
4.	Or run from TestNG XML directly:
Right-click testng.xml -> Run as TestNG Suite
5.	Test reports will be generated under reports/ directory.

   

**Jenkins Integration**

•	Jenkins is configured to pull code from the Git repository.

•	Tests are executed using Maven goals:
clean test

•	Execution results can be viewed in Jenkins console output.

 
**Project Highlights**

•	Uses Page Object Model for maintainable and reusable code

•	DataProvider is used for parameterized testing

•	Automated both positive and negative scenarios

•	Integrated with Jenkins for continuous execution


**Contact**

For any questions regarding this project, please contact:

Name: Sameeksha Bandekar

Email: sameeksha.bandekar@gmail.com
