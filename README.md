# Pharma Supply Chain - Company Practice Backend

This is a deliberately realistic **existing-codebase practice project**. It is not a toy CRUD exercise.

## Stack
- Java 17
- Spring Boot 3.3.5
- Spring Web / Validation
- Spring Data JPA
- H2 for local practice (no external DB required)
- Maven
- JUnit / MockMvc

## Existing business flow
Medicine -> Batch -> Distributor Order -> Shipment -> Inventory -> Pharmacy Supply

## Roles
SYSTEM_ADMIN, MANUFACTURER, DISTRIBUTOR, PHARMACIST

## How to run
1. Open the project in IntelliJ.
2. Use JDK 17.
3. Run:
   `mvn clean test`
4. Start `PharmaSupplyChainApplication`.
5. API base:
   `http://localhost:8080/api`

## Practice rule
Do NOT change code immediately when you receive a Jira ticket.
First:
1. Understand ticket + acceptance criteria.
2. Find controller -> service -> repository -> entity flow.
3. Reproduce/current behavior.
4. Identify impacted classes.
5. Implement the smallest safe change.
6. Run tests.
7. Test API manually.
8. Explain impact and test evidence as if you were giving a Jira update.

## Important
The code intentionally contains a business rule that is suitable for a Jira change request:
Distributor orders currently allow ordering a batch even when its quality status is `PENDING`.
Your first practice ticket will ask you to change this behavior.
