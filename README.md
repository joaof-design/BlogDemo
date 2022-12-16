# Springboot

## Running the Spring Boot application to test it locally
***
Create a jar file running the following command
```
$ mvn clean install
```

Build the docker image using the previous created jar
```
$ docker build -t spring_boot_kafka .
```

Run the containers
```
docker-compose -f ./docker-compose.yml up -d
```
