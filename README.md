# SHUBHA D / VodafoneZiggo assignment

## Building a microservice using springboot with

* APIs to Create and order and Get All the orders.
* Database of choice; Used MySQL in this application.
* Openapi yaml file: src/main/resources/openapi.yaml to describe the 2 APIs.
* Postman collection: postman_collection.json to have the 2 APIs.
* Dockerfile to Dockerize the application.
  * Added *docker-compose.yaml* file to start the application along with the database easily.

## Local setup / Running the application in local.

### Dependencies

#### Docker: Download docker in the local system: https://docs.docker.com/get-docker/

### Running the application

* Run below commands -

To build the docker image,

docker build -t assignment .

To run the application along with MySQL,

docker-compose up


Now the application will be running in localhost:8080.

### Swagger

* To view the swagger, go to http://localhost:8080/swagger-ui/index.html
* We can see the APIs to create a single order and get all the created orders

### Postman collection

* Use the postman_collection.json present in the root folder of the project.
* Open postman application and import the collection using the postman_collection.json file.
* We can see 2 APIs(to create a single order and get all the created orders)

### To view the data of MySQL,
* Run docker ps to list the running docker processes.
* Find the container id corresponding to the MySQL.
* Run docker exec -it {CONTAINER_ID} bash to login to the container.
* Run mysql -p to open the MySQL console.
* Enter root as the password to enter as a root user.
* Find the database vodafone_ziggo and 2 tables orders and users with the appropriate data.

## Author

* Name: Shubha Devarajan
* Email: shubhadevarajan.95@gmail.com