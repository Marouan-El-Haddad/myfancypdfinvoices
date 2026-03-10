# My Fancy PDF Invoices

A REST API for creating and listing PDF invoices, built with an embedded Tomcat server and no framework - as part of the [Confident Spring Professional](https://www.marcobehler.com/courses/spring-professional) course by Marco Behler.

The course uses Java 21 and older patterns. This implementation uses **Java 25** with modern idioms: records, switch expressions, text blocks, unnamed variables, `var`, and the flexible launch protocol.

## Course Progress

- [x] 00 Introduction
- [ ] 01 Java Webapps without Spring
  - [x] Project Setup
  - [x] Rendering HTML Pages
  - [x] JSON Endpoints
  - [ ] Poor Man's Dependency Injection
- [ ] 02 Spring IoC Basics
- [ ] 03 Spring WebMVC: Rest Services
- [ ] 04 Spring WebMVC: HTML Pages
- [ ] 05 Spring Database Access
- [ ] 06 Spring Boot Basics
- [ ] 07 Spring Boot: Internals
- [ ] 08 Spring Data JDBC

## Tech Stack

- Java 25 (Temurin)
- Embedded Tomcat 11.0.18
- Jackson 2.21.1
- Maven 3.9.13 with Shade plugin

## Running
```bash
mvn clean package
java -jar target/myfancypdfinvoices-1.0-SNAPSHOT.jar
```

## Endpoints

| Method | Path       | Description          |
|--------|------------|----------------------|
| GET    | /          | HTML welcome page    |
| GET    | /invoices  | List all invoices    |
| POST   | /invoices  | Create a new invoice |

### Create an invoice
```
POST http://localhost:8080/invoices?user_id=freddieFox&amount=50
```

### List all invoices
```
GET http://localhost:8080/invoices
```