# BankAccount
A simple micro web service to mimic a “Bank Account”.
## Technology Stack
This is a Maven project based on the following technology stack:
* [Maven](https://maven.apache.org/) - Dependency Management
* [WildFly 11](https://wildfly.org) - Application Server
* [Hibernate ORM](https://hibernate.org/orm/) - Object-relational mapping
* [Bootstrap 3](https://getbootstrap.com/) - Frontend
* [Mysql](https://www.mysql.com/downloads/) - Database
* Jax-Rs - Building the rest api
* JSP & JSTL Tags - Frontend & Displaying data from Backend
* Servlets
* Advanced REST Client - To test REST api
## Getting Started
These instructions will get you a copy of the project up and running on your local machine:
* Unzip WildFly Application server to your preferred Disk
* Install Maven
* Install Java 8 if not installed yet
* Install MySQL if not installed already and create schema "bankaccount".
* Import the SQL script provided
* Open Widlfly/BIN folder and run standalone.bat (windows) or standalone.sh (Linux)
* Once the server has started, go to the project folder i.e BankAccount and start CMD here.
* Run the following command to deploy the project:
```
mvn clean compile package wildfly:deploy
```
* The project should now run succesfuly on your browser at:
```
http://localhost:8080/BankAccount/summary
```
## API
Use the following links to access the API endpoints:
### Check Current Account Balance
```
http://localhost:8080/BankAccount/api/balance/current
```
### Deposit Cash
```
http://localhost:8080/BankAccount/api/deposit/amount/{amount}
```
### Withdraw Cash
```
http://localhost:8080/BankAccount/api/withdraw/amount/{amount}
```

