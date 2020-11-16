# stockApp

## Run these commands on docker
* docker build -t reading-is-good-docker .
* docker run -p 8080:8080 reading-is-good-docker


## Do not forget the Bearer token on your request header! 
* ex: header.key="Authorization" header.value="Bearer xyzxyzxyzxyzxyz".
* Header will be shown on application console when application running.

## General order scenario steps;
* first create customer
* create book
* create stock (for created book)
* create order

## Swagger URL
* http://localhost:8080/api/swagger-ui/index.html?configUrl=/api/v3/api-docs/swagger-config (no user registration needs)
