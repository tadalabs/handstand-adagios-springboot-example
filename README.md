# Example: Handstand + Adagios + SpringBoot

This repository represents an attempt at exhibiting the benefit of using Handstand, Adagios and SpringBoot. The example solution provided, is a "Todo List" App.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Backend

It's important to start the backend first, since there's an expectation that it lands on port 8080. Configuration exists within webpack to proxy any requests for the backend api to port 8080.

```
$ cd backend
$ mvn spring-boot:run
```

### Frontend

```
$ cd frontend
$ yarn/npm install
$ yarn/npm run build
$ yarn/npm start
```
