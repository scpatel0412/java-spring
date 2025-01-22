# This spring-boot demo with docker
## To setup this repo you need to run following commands

## Prerequisites
-- 'Java 8 or higher'
-- 'Apache Maven'
-- 'Docker' and 'Docker Compose'

### Commands
#### To install
-- 'mvn clean install'
#### Create env variable inside env file by copying variable from '.env.example' and run this app
-- 'export $(cat .env | xargs) && ./mvnw spring-boot:run'
#### To build file spring-boot example
-- 'export $(cat .env | xargs) && mvn clean package'
#### To run build file
-- 'export $(cat .env | xargs) && java -jar ./target/demo-0.0.1-SNAPSHOT.jar'

### To migrate database from docker
#### run command to start database
-- 'docker-compose up -d'


## Contact
Created by [@sagarpatel0412] - feel free to contact me!