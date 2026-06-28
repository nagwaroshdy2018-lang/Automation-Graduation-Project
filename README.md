# 🤖 Automation Exercise — Selenium Test Automation Framework

A professional end-to-end UI test automation framework for [Automation Exercise](https://automationexercise.com) — built with **Java + Selenium WebDriver + TestNG + Maven + Allure** following the **Page Object Model (POM)** design pattern.

[![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.21.0-43B02A?logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-FF5733)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure-2.27.0-FF5722)](https://docs.qameta.io/allure/)

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Test Coverage](#-test-coverage)
- [Setup & Installation](#-setup--installation)
- [Running Tests](#-running-tests)
- [Reporting](#-reporting)
- [Design Patterns & Best Practices](#-design-patterns--best-practices)
- [Key Findings](#-key-findings)
- [Author](#-author)

---

##  Overview

This framework automates testing for an e-commerce website covering **8 different features**:

- ✅ **Login & Authentication**
- ✅ **User Registration / Signup**
- ✅ **Product Browsing**
- ✅ **Product Search**
- ✅ **Shopping Cart**
- ✅ **Checkout & Payment**
- ✅ **Contact Us Form**
- ✅ **Newsletter Subscription**

The framework includes **16 test cases** divided into:
- 🔄 **4 End-to-End (E2E)** test scenarios
- ✅ **6 Valid (Positive)** test cases
- ❌ **6 Invalid (Negative)** test cases

---

## 🛠️ Tech Stack

| Category | Tool / Library | Version |
|----------|---------------|---------|
| Language | Java | 17 |
| Build Tool | Maven | 3.8+ |
| Test Framework | TestNG | 7.10.2 |
| Browser Automation | Selenium WebDriver | 4.21.0 |
| Driver Management | WebDriverManager | 5.8.0 |
| Reporting | Allure | 2.27.0 |
| Logging | SLF4J | 2.0.13 |
| Design Pattern | Page Object Model (POM) | - |

---

## 📁 Project Structure
AutomationPro/

├── pom.xml                              # Maven dependencies

├── testng.xml                           # TestNG suite config

├── README.md                            # Project documentation

│

├── src/main/java/

│   ├── base/

│   │   └── BaseTest.java                # WebDriver lifecycle + screenshots

│   ├── com/automationexercise/pages/

│   │   ├── BasePage.java                # Reusable Selenium actions

│   │   ├── HomePage.java                # Top nav + footer

│   │   ├── LoginPage.java               # Login & Signup sections

│   │   ├── SignupPage.java              # Account info form

│   │   ├── AccountCreatedPage.java      # Post-signup confirmation

│   │   ├── AccountDeletedPage.java      # Account deletion page

│   │   ├── ProductsPage.java            # Products listing + search

│   │   ├── ProductDetailsPage.java      # Single product page

│   │   ├── CartPage.java                # Shopping cart

│   │   ├── CheckoutPage.java            # Order checkout

│   │   ├── PaymentPage.java             # Payment processing

│   │   └── ContactUsPage.java           # Contact form

│   └── utils/

│       ├── ConfigReader.java            # Reads config.properties

│       ├── DriverFactory.java           # Browser driver factory

│       └── TestDataGenerator.java       # Unique test data generator

│

└── src/test/

├── java/com/automationexercise/tests/

│   ├── E2ETests.java                # 4 E2E flow tests

│   ├── ValidTests.java              # 6 positive tests

│   └── InvalidTests.java            # 6 negative tests

└── resources/

└── config.properties            # Test configuration
---

## 🧪 Test Coverage

### 🔄 End-to-End Tests (4)

| ID | Feature | Description |
|----|---------|-------------|
| TC_E2E_01 | Account Lifecycle | Register → Login → Verify → Delete |
| TC_E2E_02 | Checkout Flow | Login → Add to cart → Checkout → Place order |
| TC_E2E_03 | Search & Cart | Login → Search → View → Add to cart → Logout |
| TC_E2E_04 | Engagement | Register → Subscribe → Submit Contact form |

### ✅ Valid Tests (6)

| ID | Feature | Description |
|----|---------|-------------|
| TC_V_01 | Login | Successful login with valid credentials |
| TC_V_02 | Signup | Register new user with valid data |
| TC_V_03 | Products | Products page loads correctly |
| TC_V_04 | Search | Search returns matching products |
| TC_V_05 | Cart | Add product to cart successfully |
| TC_V_06 | Contact Us | Submit form with valid data |

### ❌ Invalid Tests (6)

| ID | Feature | Description |
|----|---------|-------------|
| TC_I_01 | Login | Wrong credentials show error message |
| TC_I_02 | Signup | Duplicate email rejected |
| TC_I_03 | Search | Non-existent term returns no results |
| TC_I_04 | Contact Us | Empty required fields validation |
| TC_I_05 | Subscription | Invalid email format rejected |
| TC_I_06 | Cart | Empty cart cannot proceed to checkout |

---

## ⚙️ Setup & Installation

### Prerequisites

- **Java JDK 17+** — [Download](https://adoptium.net/temurin/releases/?version=17)
- **Maven 3.8+** — [Download](https://maven.apache.org/download.cgi)
- **IntelliJ IDEA** (Community Edition is fine)
- **Google Chrome** (latest stable version)

> 💡 **WebDriverManager** automatically downloads the matching ChromeDriver — no manual setup required.

### Installation Steps

1. **Clone the repository**
```bash
   git clone https://github.com/YOUR_USERNAME/AutomationPro.git
   cd AutomationPro
```

2. **Open in IntelliJ IDEA**
   - `File → Open` → select the project folder
   - Click **"Load Maven project"** when prompted

3. **Install dependencies**
```bash
   mvn clean install -DskipTests
```

4. **Configure test data** (optional)
   - Open `src/test/resources/config.properties`
   - Update credentials, browser, and timeouts as needed

---

## 🚀 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
- Right-click `testng.xml` → **Run 'testng.xml'**

### Run a Single Test Class
- Right-click test class (e.g., `InvalidTests.java`) → **Run**

### Run a Single Test Method
- Click the **green ▶** arrow next to the method name in IntelliJ

### Switch Browsers
Edit `config.properties`:
```properties
browser=chrome      # Options: chrome | firefox | edge
headless=false      # Set to true for CI/CD environments
```

---

##  Reporting

### Generate Allure Report

After tests complete, generate and view the interactive report:

```bash
mvn allure:serve
```

This launches a local server and opens the report in your browser.

### Report Features

-  Test execution dashboard
-  Pass/fail trends
-  Tags, severities, and stories
-  Automatic screenshots on failure
-  Step-by-step test breakdown

---

##  Design Patterns & Best Practices

### 1. **Page Object Model (POM)**
Every page has its own class — locators and actions are isolated from test logic.

### 2. **Fluent API / Method Chaining**
Tests read like user journeys:
```java
home.goToLogin().login(email, password);
```

### 3. **Explicit Waits Only — No Thread.sleep()**
All interactions wait for elements to be ready.

### 4. **JavaScript Click Fallback**
Handles overlays and intercepted clicks.

### 5. **Unique Test Data Per Run**
Prevents test pollution and false failures.

### 6. **HTML5 Validation via JavaScript**
Tests browser-level required/format validation.

### 7. **Auto Screenshot on Failure**
Attached to Allure reports automatically.

### 8. **Config-Driven**
URLs, credentials, browser choice in `config.properties` — no magic strings.

### 9. **WebDriverManager**
Auto-downloads matching browser drivers — works on any machine.

---

##  Key Findings

During automation, this framework **detected a real defect** on the live website:

###  Defect: Contact Us Form Missing Client-Side Validation

**Test**: `TC_I_04_contactUsEmptyFields`  
**Page**: `https://automationexercise.com/contact_us`  
**Severity**: Medium

**Description**: The Contact Us form does not enforce HTML5 `required` attribute validation on its input fields. Users can submit an empty form without triggering client-side validation errors.

**Impact**:
- Poor UX — users get no immediate feedback
- Server-side resources wasted processing empty submissions
- Inconsistent with industry best practices

**Recommendation**: Add `required` attribute to mandatory form fields (`name`, `email`, `subject`, `message`).

---


##  Author

**Nagwa Roshdy**  
QA Automation Engineer  

📧 nagwaroshdy2018@gmail.com  
🔗 [LinkedIn]((https://www.linkedin.com/in/nagwa-el-toukhy/))  
🐙 [GitHub]((https://github.com/nagwaroshdy2018-lang))  

---
