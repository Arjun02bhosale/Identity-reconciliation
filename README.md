# 🕵️‍♂️ Identity Reconciliation - Spring Boot API

## 📌 Overview
This Spring Boot application provides a RESTful API for identifying and consolidating user contact data (email and phone number) into a single unified contact profile, even when different identifiers are used across different purchases (like Doc from Zamazon.com!).

## 🧩 Problem Statement
Moonrider is helping Zamazon.com consolidate customer identity by recognizing multiple entries with different emails or phone numbers as the same person. This backend service:

- Accepts contact info via `/identify`
- Links matching contacts
- Maintains `primary` and `secondary` relationships
- Returns all associated emails and phone numbers


## 🔧 Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL (or any relational DB)
- Lombok


## 🗃️ Database Schema

Contact {
  id INT PRIMARY KEY,
  email VARCHAR(255),
  phoneNumber VARCHAR(20),
  linkedId INT, -- refers to another Contact.id
  linkPrecedence ENUM('PRIMARY', 'SECONDARY'),
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME
}


## 🚀 How to Run Locally

### 1. Clone the Repo

git clone https://github.com/your-username/identity-reconciliation.git
cd identity-reconciliation


### 2. Setup Database
- Create MySQL DB:

CREATE DATABASE identitydb;


### 3. Configure `application.properties`

spring.datasource.url=jdbc:mysql://localhost:3306/identitydb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


### 4. Build & Run

mvn clean install
mvn spring-boot:run


## 📮 API Endpoint
### `POST /identify`
**Request Body:**

{
  "email": "doc@zamazon.com",
  "phoneNumber": "9999999999"
}


**Response:**

{
  "primaryContactId": 1,
  "emails": ["doc@zamazon.com", "d.chandra@zamazon.com"],
  "phoneNumbers": ["9999999999"],
  "secondaryContactIds": [2]
}



## 🧪 Sample Test Cases
- New email & phone → Creates primary contact
- Same phone, new email → Adds secondary contact
- Existing combo → Returns existing mapping


## 📦 Project Structure

src/main/java/com/example/identity
├── controller/IdentifyController.java
├── dto/IdentifyRequest.java
├── dto/IdentifyResponse.java
├── model/Contact.java
├── model/LinkPrecedence.java
├── repository/ContactRepository.java
└── service/ContactService.java


## 🤝 Contribution
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.


