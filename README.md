# Health First Fitness Centre App


## Getting Started   
Please ensure Maven is installed on your local machine.  

## Quick Start with Docker
Make sure Docker is up and running and Make is installed on your local machine.  
From the project root directory use commands:  
- 'make deploy' to start up to app
- 'make purge' to stop the app and remove images and jars created  

## Notes for running in Intellij

Use the command 'mvn spring-boot:run' in the directory of the service you'd like to start:
- 'naming-server' must be started up first
- 'config-server' must be started next
- Switch the scope of the 'classes-service' dependency 'de.flapdoodle.embed.mongo' from 'test' to 'runtime'

## Email-service authentication
In the output to email-service there will be a link to authenticate the Health First gmail.  
If you have these credentials, click on the link and follow the on-screen instructions. You must add these credentials for email automation.

## Testing API endpoints

Navigate to the 'postman' directory and copy all the text.  
Then head to Postman and import this collection by pasting as raw text.

## Services
| Name              | Port | Description                          |
| -------------    |--------------|--------------------------------------|
| member-service         | 8080          | CRUD operations for members, persisting data in a MySQL DB |
| welcome-service   | 8110          | Handles events when a new member is created          |
| classes-service           | 8082         | CRUD operations for classes, persisting data in a Mongo DB                   |
| email-service   | 8100          | Handles all email functionality                  |
| config-server   | 8083       | Centralises each microservice's application properties                  |
| naming-server   | 8761       | Eureka server for service discovery and load-balancing                  |

## Stores and Utilities
| Name              | Port | Description                          |
| -------------    |--------------|--------------------------------------|
| mongodb        | 27017          | Stores all data about the available classes
| mongo-express        | 8081          | A GUI you can open in the browser to view the data stored in Mongo DB
| memberdb   | 3308         | A MySQL DB to store all member data         |
                 


