# Expense Tracker
Expense Tracker is a microservices-based application that allows users to manage and track their expenses efficiently. The project is built using Spring Boot, MongoDB, and various other technologies.

## Tools Used
- Spring Boot: Microservices are implemented using Spring Boot, providing a robust and scalable backend.
- MongoDB and PostgreSQL: PostgreSQL is used for storing user-specific data, while MongoDB is utilized for expenses storage.
- Docker: Containerization is employed for seamless deployment and scaling of microservices.
- Postman: The API endpoints are documented and can be tested using Postman.

## Microservices Architecture
The project consists of the following microservices:

- Gateway Service:
  Responsible for directing and filtering requests to user-service and expense-service.
  Utilizes an authentication filter to manage user access.
- User Service:
  Manages user-related operations such as registration, login, and logout.
  Stores user data in PostgreSQL.
- Expense Service:
  Handles expense-related functionalities like adding, updating, and deleting expenses.
  Stores expense data in MongoDB.
## How to Run
Follow the steps below to run the Expense Tracker microservices:

### Prerequisites
- Ensure you have Docker installed on your machine.
- Have Maven installed for building Java projects.
### Steps
- Clone the Repository
```
git clone https://github.com/DohaRamadan/cloud-expense-tracker.git
cd <your_project_directory>
``` 
- Build the Microservices:
  1- Build User Service:
    ```
    cd user-service
    mvn clean package
    ```
  2- Build Expense Service:
    ```
    cd expense-service
    mvn clean package
    ```
  3- Build Gateway Service:
    ```
    cd gateway-service
    mvn clean package
    ```
- Run the Docker Compose:
```
cd gateway
docker-compose up --build -d

```
- Access the Services:
  - Gateway Service: http://localhost:8080
  - User Service: http://localhost:8001
  - Expense Service: http://localhost:8000
- Test Endpoints:
  - Use the provided Postman documentation to test the API endpoints.
- Authentication:
  - Use the provided JWT token in the Authorization header for secured endpoints.
### [Postman Documentation](https://documenter.getpostman.com/view/17126593/2s9YsT6oQU)
Explore and test the API endpoints using the Postman documentation.
