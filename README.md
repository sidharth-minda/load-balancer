# Load Balancer Application

This project implements a **Load Balancer** that efficiently distributes requests among backend servers using configurable algorithms like **Round Robin** and **Random**. The solution is designed following **SOLID principles**, ensuring extensibility, maintainability, and testability.

---

## Features

- **Dynamic Load Balancing Algorithms**: Supports Round Robin (default) and Random algorithms.
- **Add and Remove Servers**: Manage backend servers dynamically via APIs.
- **Health Monitoring**: Automatically marks unhealthy servers during failures.
- **Switch Algorithms at Runtime**: Dynamically change the load-balancing strategy via an API.
- **Extensibility**: New algorithms can be added without making changes to the core business logic. Just add an implementation of LoadBalancingStrategy interface. 

---

## Setup Instructions

### Prerequisites

- **Java 11 or later**
- **Maven** (to build and manage dependencies)
- **Spring Boot** framework

### Steps to Setup and Run

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd load-balancer
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Test API Endpoints**  
   Use tools like **Postman** to interact with the APIs.


5. **Run Unit Tests**
   ```bash
   mvn test
   ```

---

## API Endpoints

### Server Management

| Method | Endpoint                      | Description                          |
|--------|-------------------------------|--------------------------------------|
| GET    | `/servers`                    | Retrieve all backend servers.        |
| POST   | `/servers`                    | Add a new server.                   |
| DELETE | `/servers/{id}`               | Remove a server by ID.              |

### Load Balancing

| Method | Endpoint                       | Description                             |
|--------|--------------------------------|-----------------------------------------|
| PUT    | `/load-balancer/configuration` | Switch load-balancing algorithm.       |
| POST   | `/load-balancer/requests`      | Forward a request to a backend server. |


**Query Parameters and Payloads:**  
Refer to the source code for details about request parameters and body structure.

---

## Architecture Overview

### High-Level Design

1. **Client Request Handling**:  
   Incoming requests are routed to a backend server based on the active load-balancing algorithm.

2. **Load-Balancing Strategies**:
    - **Round Robin**: Cycles through servers sequentially.
    - **Random**: Randomly selects a server.

3. **Dynamic Strategy Switching**:  
   Supports Open/Closed Principle by allowing new strategies without modifying the existing ones.

4. **Health Monitoring**:  
   Servers are marked unhealthy and excluded from routing if a request fails.

### Low-Level Design

- **BackendServer**: Represents a server, including its health status and unique ID.
- **LoadBalancingStrategy**: Interface for defining load-balancing algorithms.
- **Strategy Factory**: Retrieves the appropriate strategy based on user input.
- **HttpService**: Handles HTTP interactions with backend servers.

---
