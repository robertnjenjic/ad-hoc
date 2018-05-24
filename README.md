# Ad-hoc reports
Reporting service that lets you generate ad-hoc reports.

The service supports drill-downs and roll-ups:
- on dimensions: **_DATE_**, **_CAMPAIGN_ID_**, **_CAMPAIGN_NAME_**, **_AD_ID_**, **_AD_NAME_** and
- on metrics: **_IMPRESSIONS_**, **_CLICKS_**, **_INTERACTIONS_**, **_SWIPES_**, **_PINCHES_**, **_TOUCHES_**, **_UNIQUE_USERS_**

For a query of "How many impressions were trafficked each day for each campaign?", an example response is:
```json
//DATE,CAMPAIGN_NAME,IMPRESSIONS
[
  [
    "2018-05-03",
    "Campaign name 0",
    141
  ],
  [
    "2018-05-03",
    "Campaign name 1",
    54
  ],
  [
    "2018-05-03",
    "Campaign name 10",
    13
  ],
  [
    "2018-05-03",
    "Campaign name 11",
    242
  ],
  [
    "2018-05-03",
    "Campaign name 12",
    77
  ],
  [
    "2018-05-03",
    "Campaign name 13",
    452
  ],
  [
    "2018-05-03",
    "Campaign name 14",
    95
  ],...
 ]
```
## Configuration
```properties
# APPLICATION
server.port=8085

# ENABLING H2 CONSOLE (Database UI)
spring.h2.console.enabled=true

# TURN STATISTICS ON
spring.jpa.properties.hibernate.generate_statistics=false
#logging.level.org.hibernate.stat=debug

# SHOW ALL QUERIES
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
#logging.level.org.hibernate.type=trace

```
Configuration can be changed in: 
[application.properties](/src/main/resources/application.properties)

## Build and Run

The command below will run the application server, configure it and deploy the application. It will automatically inserts some data into database.
```bash
mvn clean install spring-boot:run
```
or
```bash
mvn spring-boot:run
```

or you can build a jar and run it like a standalone application
```bash
mvn package && java -jar target/{verion}
``` 

## Demo
- Application (REST API Swagger): [http://localhost:8085/swagger/index.html](http://localhost:8085/swagger/index.html)
- Database (H2): [http://localhost:8085/h2-console](http://localhost:8085/h2-console)

## Tools
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Swagger](https://swagger.io/)
- [REST API](https://jersey.github.io/)
- [Database H2](http://www.h2database.com/)
- [Apache Tomcat](http://tomcat.apache.org/)

## License
Copyright (c) 2018 Robert Njenjić

Licensed under the MIT license.