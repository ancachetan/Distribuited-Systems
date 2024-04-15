# DS2023_30643_Chetan_Anca_Assignment_3

## Name

Energy Management System

## Description

This is an Energy Management System that consists of a frontend and three
microservices designed to manage users, their associated smart energy
metering devices and the devices consumptions. The system can be accessed by two types of users after a
login process: administrator, and clients. The administrator can
perform CRUD (Create-Read-Update-Delete) operations on user accounts
, smart energy metering devices,
and on the mapping of users to devices (each user can own one or more smart devices
in different locations). A client can view on his/her page all the devices.
Every time a device exceeds the maximum limit consumption defined by the device, the
user receives a notification to be aware of it. Moreover, the user can consult charts related
to the daily consumption of their devices.
An administrator is able to communicate with the clients through a private chat. A client can 
only speak with administrators and an administrator can talk to many clients at the same
time.

## Prerequisites

Before running the application, ensure that you have the following installed:

- Java Development Kit (JDK) 17
- Apache Maven
- Node.js
- Angular CLI
- PostgreSQL
- Docker
- Git
- RabiitMQ

## Build and execution

Setup

Clone the repository containing all the microservices using the following command:
```agsl
git clone https://gitlab.com/ds2023_30643_chetan_anca/ds2023_30643_chetan_anca_assignment_2.git
```
Open the projects in IntelliJ or your preferred IDE.
Configure the PostgreSQL database settings in the application.properties file for each
backend microservice (User Management Microservice, Device Management Microservice, Monitoring Microservice, Chat Microservice)
including the simulator application too.
In the Sensor Simulator application there is a configuration file named config.txt where you need
to specify the device ids for which you want to simulate the consummations in this format:
```agsl
device: device_id
```

Build the projects.

After this make sure all the backend applications and frontend application are running: User Management Microservice
,Device management Microservice, Monitoring Microservice, Sensor Simulator, Chat Microservice and UI, and then you can access the fronted
through a web browser by visiting the URL: http://localhost:4200.

## Docker Deployment

Each microservice, both backend or frontend, is equipped with Dockerfiles and docker-compose.yml
files for deployment using Docker. It is imperative to customize these files with your designated
username and password for the database. Additionally, configure the PostgreSQL database settings in
the application.properties file for each backend microservice. The Sensor Simulator application is not
included in the deployment part, because this is just a simulator for some real sensors' behavior.

To ensure appropriate deployment, generate the .jar file for all backend microservices.

For RabbitMQ integration, execute the following command to enable access to a RabbitMQ image via a Docker container:
```agsl
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management
```
After that you need to run the following command for each microservice in order
to create the images and the containers.
```agsl
docker compose up -b
```
This process will generate and run a total of 5 containers for the microservices (both backend and frontend),
along with 4 additional containers dedicated to the databases for each backend microservice.
In order to start the Sensor Simulator application, you can choose an IDE to build the application and
start it, or you can do this through the jar file by running the command below:
```agsl
java -jar <jar-name>
```
Make sure all the containers are running and the simulator application too, then you can access the
frontend through a web browser by navigating to the URL: http://localhost:4200.

## Authors
Chetan Anca