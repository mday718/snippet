## Snippet web application

### What it can be used for?
It can be used for management of your code snippets, diagrams and other small stuff that is not a really complete application.

### How to run?
a. Bootstrap Postgres in a docker container like this:
```
docker run -d -p 5432:5432 postgres:10.3
```
b. Create a new database called 'snippet'.

c. Start a server:
```
mvn clean compile exec:java
```
d. Create an ADMIN user by executing this query to 'snippet' db:
```
insert into users (datetime, username, password) values(now()::timestamp, '<your user name>', '<password encoded in base64>');
```

Then point your browser to the <i>localhost://8080</i>

### License
Apache License 2.0
