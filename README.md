![UpWork align=](https://logos.bugcrowdusercontent.com/logos/2588/cc10/bf2e0ce4/small_84a6821a6ab01e5904e1867aee28fac7_new_300_UpworkLogo_UpGreen_WithClearspace.png)

# API-Center

API-Center is a microservice that's designed for maintaining and sharing the data in different types received from defined APIs.

## Description

This orchestrator helps you to receive the data with the type that you want. For this purpose, pass the data structure and the data type as a parameter to your HTTP request.
Example:
```
http://localhost:8080/sync/{data_structure-data_type}
```
API-Center allows to use the next parameters:
* `json-catalog`
* `yaml-catalog`
* `text-catalog`

> **Note**
> The data updates automatically per minute because of the scheduler inside the API-Center.

> **Warning**
> Currently only the `json-catalog` works with the real API and updates the data. Other parameters return the same mock on each request.

## Getting Started

### Dependencies

* Groovy 3.0.13
* Micronaut 3.6.0
* HyperSQL 2.7.0
* JDK 1.8

### Installing

* Fork the project and feel free to use it.

### Executing program

* Enter the next command to run the application:
```
.\gradlew run
```

## Authors

The one whose name shouldn't be called...
