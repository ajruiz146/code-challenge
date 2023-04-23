# 👋 About

Spring-boot REST microservice. CCprice

## ⚙️ Requirements

- JDK 17.
- Maven 3.9.1
- Spring Boot 3.0.6

## 📘 Technologies

### Common

| Technology | Purpose |
| ---------- |----------|
| [Spring-boot](https://quarkus.io/) | Java framework that helps you to create a REST microservice easily . |

### Database

| Technology | Purpose |
| ---------- |----------|
| [H2Database](https://www.h2database.com/html/main.html) | Very fast, open source, JDBC API. Support embedded and server modes; disk-based or in-memory databases |

### Specification

| Technology | Purpose |
| ---------- |----------|
| [springdoc-openapi-ui](https://springdoc.org/) | Helps to automate the generation of API documentation using spring boot projects. |

## 🚀 How to execute the application

Go to the project root directory and execute the following command to compile, test, package and install the different artifacts in your local maven repository.

```shell
mvn clean install
```

After creating all artifacts you can run the project with the following command:

```shell
mvn spring-boot:run
```

You should see in the console the following log line:

```log
......
2023-04-23T20:08:18.639+02:00  INFO 13228 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path '/ccprice'
2023-04-23T20:08:18.647+02:00  INFO 13228 --- [           main] es.ajrj.cc.price.CCpriceApplication      : Started CCpriceApplication in 3.577 seconds (process running for 3.865)
```

It means the application is running properly and it will provide the following endpoints:

- `http://localhost:8080/ccprice/api/v1/prices/avalaible`. GET http method that will receive three parameters.
- `http://localhost:8080/ccprice/h2-console` H2 embedded GUI console for browsing the contents of a database and running SQL queries
- `http://localhost:8080/ccprice/v3/api-docs`. OpenAPI schema auto-generated from the swagger annotation provided by the `springdoc` dependency.
- `http://localhost:8080/ccprice/swagger-ui.html`. Swagger interface based on the OpenAPI auto-generated schema that helps you to test the `prices` resource endpoint.