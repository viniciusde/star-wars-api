#  Star Wars API - 1.0

With this API, it is possible to add a new planet (name, climate and terrain) in addition to discovering the amount of films that this planet has officially appeared.

It is also possible to retrieve a list of planets by filtering them by id or name. Do not be alarmed, if you do not use these filters, the results will be returned paginated.

Delete a planet if you need to.

May the force be with you.

## Tools
* java
* maven  
* spring framework
* mongodb
* Docker

## Usage

### building
```
mvn clean package
```

### Running star wars api + mongodb
```
docker-compose up
```
### Swagger Documentation - Open API 3.0
```
http://localhost:8080/swagger-ui.html
```