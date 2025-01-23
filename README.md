# Spring Boot demo 
## To setup this repo you need to run following commands


## Prerequisites
```bash
Java 8 or higher
Apache Maven
Docker and Docker Compose
```
### Commands
#### To install
```bash
mvn clean install
```
#### Create env variable inside env file by copying variable from '.env.example' and run this app
```bash
export $(cat .env | xargs) && ./mvnw spring-boot:run
```
#### To build file spring-boot example
```bash
export $(cat .env | xargs) && mvn clean package
```
#### To run build file
```bash 
export $(cat .env | xargs) && java -jar ./target/demo-0.0.1-SNAPSHOT.jar
```

### To migrate database from docker
#### run command to start database
```bash
docker-compose up -d
```

#### run this command to get inside mysql-container
```bash
docker exec -it your-container mysql -u root -p
```
#### get ip address of container from docker
```bash
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' your_mysql_container_name_or_id
```
 

## Authors
### Feel free to contact me
- [@sagarpatel0412](https://github.com/sagarpatel0412)

