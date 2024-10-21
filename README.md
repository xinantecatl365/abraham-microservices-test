# Microservices test 

## Description
This project is for the recruitment of a microservices job listing

## Table of Contents
- [Description](#description)
- [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/xinantecatl365/abraham-microservices-test.git
   cd my-awesome-project
   docker compose up
I would recommend importing the project inside Intellij and start the services 1 by 1 starting with the config-server; then the discovery-server; the rest can be instantiated after.


### 1.5 **Usage**
- Right now there is no client web or frontend
- The way to check everything is working is using postman
- Keycloak (authentication server and branches server) and the gateway (CORS security) while working are disabled right now because of the lack of frontend

   ```markdown
   ## Usage
   credits_url: http://localhost:8070/api/v1/credits
   customer_url: http://localhost:8072/api/v1/customers

## Documentation
1. Basic diagrams
![credits](https://drive.google.com/file/d/180s7veU3d5Azx2PjL4ogHP75UBLk9GTz/view)
