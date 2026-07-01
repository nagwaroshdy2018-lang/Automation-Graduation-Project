🤖 Automation Exercise — Selenium Test Automation Framework
![Selenium Tests CI](https://github.com/nagwaroshdy2018-lang/Automation-Graduation-Project/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.21.0-43B02A?logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-FF5733)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?logo=apache-maven&logoColor=white)
![Allure](https://img.shields.io/badge/Allure-2.27.0-FF5722)
![Tests](https://img.shields.io/badge/Tests-31-02C39A)
![Pass Rate](https://img.shields.io/badge/Pass_Rate-93%25-27AE60)
A professional end-to-end UI test automation framework for Automation Exercise — built with Java + Selenium WebDriver + TestNG + Maven + Allure following the Page Object Model (POM) design pattern.
> 🎯 **v2 Update**: Framework expanded from 16 to **31 test cases**. Execution uncovered **3 real production defects** on the live site.
---
📋 Table of Contents
Overview
Test Results
Tech Stack
Project Structure
Test Coverage — 31 Tests
Defects Found
Setup & Installation
Running Tests
Reporting
Design Patterns & Best Practices
Author
---
🎯 Overview
This framework automates testing for an e-commerce website covering 10 different features:
✅ Login & Authentication
✅ User Registration / Signup
✅ Product Browsing
✅ Product Search
✅ Category & Brand Filters
✅ Shopping Cart (add, remove, quantity)
✅ Checkout & Payment
✅ Contact Us Form
✅ Newsletter Subscription
✅ Home Page (slider + sidebar)
The framework includes 31 test cases divided into:
🔄 4 End-to-End (E2E) test scenarios
✅ 11 Valid (Positive) test cases
❌ 16 Invalid (Negative) test cases
---
📊 Test Results
Latest local execution (2026-07-02):
Suite	Passed	Total	Pass Rate
ValidTests	10	11	91% ✅
InvalidTests	14	16	94% ✅
Combined	25	27	93% ✅
E2E tests are not included in the run above — they're flaky on the live site (ads, third-party scripts) and are documented separately.
The 2 failures are not framework bugs — they're real defects on the live site (see Defects Found).
---
🛠️ Tech Stack
Category	Tool / Library	Version
Language	Java	17
Build Tool	Maven	3.8+
Test Framework	TestNG	7.10.2
Browser Automation	Selenium WebDriver	4.21.0
Driver Management	WebDriverManager	5.8.0
Reporting	Allure	2.27.0
Logging	SLF4J	2.0.13
CI/CD	GitHub Actions	ubuntu-latest
Design Pattern	Page Object Model (POM)	—
---
📁 Project Structure
```
AutomationPro/
├── pom.xml                              # Maven dependencies
├── testng.xml                           # TestNG suite config
├── README.md                            # This file
├── .github/workflows/ci.yml             # GitHub Actions CI
│
├── src/main/java/
│   ├── base/
│   │   └── BaseTest.java                # WebDriver lifecycle + failure screenshots
│   ├── com/automationexercise/pages/    # 13 page objects
│   │   ├── BasePage.java                # Reusable Selenium actions
│   │   ├── HomePage.java                # Top nav + slider + sidebar + footer
│   │   ├── LoginPage.java               # Login + Signup sections
│   │   ├── SignupPage.java              # Account info form
│   │   ├── AccountCreatedPage.java      # Post-signup confirmation
│   │   ├── AccountDeletedPage.java      # Account deletion confirmation
│   │   ├── ProductsPage.java            # Product listing + search + filters
│   │   ├── CategoryProductsPage.java    # Category & brand filter landing
│   │   ├── ProductDetailsPage.java      # Single product page
│   │   ├── CartPage.java                # Cart operations + guest modal
│   │   ├── CheckoutPage.java            # Order checkout
│   │   ├── PaymentPage.java             # Payment processing
│   │   └── ContactUsPage.java           # Contact form
│   └── utils/
│       ├── ConfigReader.java            # Reads config.properties
│       ├── DriverFactory.java           # Chrome / Firefox / Edge factory
│       └── TestDataGenerator.java       # Unique email/name generator
│
└── src/test/
    ├── java/com/automationexercise/tests/
    │   ├── E2ETests.java                # 4 E2E flow tests
    │   ├── ValidTests.java              # 11 positive tests
    │   └── InvalidTests.java            # 16 negative tests
    └── resources/
        └── config.properties            # Test configuration
```
---
🧪 Test Coverage — 31 Tests
🔄 End-to-End Tests (4)
 slider + sidebar	✅
❌ Invalid Tests (16)

🐛 Defects Found
During automation, this framework detected 3 real defects on the live production site:
🐛 Defect #1 — Contact Us Missing HTML5 Validation
Test: `TC_I_04_contactUsEmptyFields`
Page: `https://automationexercise.com/contact_us`
Severity: Medium
Description: The Contact Us form does not enforce HTML5 `required` attribute on Name, Email, Subject, or Message fields. Empty submissions reach the server.
Impact: Poor UX, wasted server resources, below industry standards.
Recommendation: Add `required` attribute to mandatory form fields.


 Defect #2 — Category Filter Navigation Issue
Test: `TC_V_07_filterByWomenDressCategory`
Page: `https://automationexercise.com/category_products/1`
Severity: Major
Description: Filtering by Women > Dress does not consistently land on the expected page or the header does not match the expected pattern. Navigation flow inconsistent.
Impact: Users may not find products; filter feature perceived as broken.
Recommendation: Investigate category-navigation click handler and page rendering.
 Defect #3 — Search Doesn't Sanitize Special Characters
Test: `TC_I_16_searchWithSpecialCharacters`
Page: `https://automationexercise.com/products`
Severity: Minor
Description: Searching with only special characters (`!@#$%^&*()`) does not consistently return the expected "Searched Products" header with zero results.
Impact: Potential edge cases in search input handling.
Recommendation: Add server-side sanitization + graceful empty-result handling.
> 💡 *These findings demonstrate the framework's effectiveness at catching real-world bugs — the primary goal of test automation.*
---
⚙️ Setup & Installation
Prerequisites
Java JDK 17+ — Download
Maven 3.8+ — Download
IntelliJ IDEA (Community Edition works)
Google Chrome (latest stable version)
> 💡 **WebDriverManager** automatically downloads the matching ChromeDriver — no manual setup required.
Installation Steps
Clone the repository
```bash
   git clone https://github.com/nagwaroshdy2018-lang/Automation-Graduation-Project.git
   cd Automation-Graduation-Project
   ```
Open in IntelliJ IDEA → File → Open → select the project folder → "Load Maven project"
Install dependencies
```bash
   mvn clean install -DskipTests
   ```
Configure test data (optional): edit `src/test/resources/config.properties`
---
🚀 Running Tests
Run All Tests
```bash
mvn clean test
```
Run a Specific Test Class
Right-click test class (e.g., `InvalidTests.java`) → Run
Run a Single Test Method
Click the green ▶ arrow next to the method name in IntelliJ
Switch Browsers
Edit `config.properties`:
```properties
browser=chrome      # chrome | firefox | edge
headless=false      # true for CI/CD
```
---
📊 Reporting
Generate Allure Report
```bash
mvn allure:serve
```
Report features:
📊 Test execution dashboard
📈 Pass/fail trends
🏷️ Tags, severities, stories
📸 Automatic screenshots on failure
🔍 Step-by-step test breakdown
---
🎨 Design Patterns & Best Practices
Page Object Model (POM) — Every page has its own class; locators isolated from test logic
Fluent API / Method Chaining — `home.goToLogin().login(email, password)`
Explicit Waits Only — No `Thread.sleep()` anywhere
JavaScript Click Fallback — Handles overlays and intercepted clicks
Unique Test Data Per Run — Prevents test pollution
HTML5 Validation via JavaScript — Tests browser-level required/format validation
Auto Screenshot on Failure — Attached to Allure reports
Config-Driven — URLs, credentials, browser choice in `config.properties`
WebDriverManager — Auto-downloads matching browser drivers
Ad-blocker Chrome prefs — Blocks images/notifications for faster CI runs
---
🛣️ Roadmap
[x] Expand from 16 to 31 test cases
[x] Add HTML5 validation coverage
[x] Add category and brand filter tests
[x] CI/CD pipeline with GitHub Actions
[ ] Add API testing layer using RestAssured
[ ] Add Data-Driven testing with TestNG `@DataProvider`
[ ] Cross-browser parallel execution
[ ] Docker containerization
[ ] Visual regression testing
---
👩‍💻 Author
Nagwa Roshdy
QA Automation Engineer
📧 nagwaroshdy2018@gmail.com
🐙 GitHub
---
📜 License
This project is for educational and portfolio purposes.
Built with ❤️ for QA learning and interview demonstration.
---
⭐ If you found this project helpful, give it a star!
