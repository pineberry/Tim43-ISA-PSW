# Installing and Configuring

Clone the project to your local repositroy by running command:
```
$ git clone https://github.com/pineberry/Tim43-ISA-PSW.git
```

## Requirements

- Java SDK v1.8
- Apache Maven v3.5.3
- NodeJS v12.13.0
- PostgreSQL v12.0

## Installing

1. Open *pgAdmin* and create new datebase

2. Go to the ```ISCC/src/main/resources/application.properties``` 
  and for properties
  ```
  spring.datasource.username=
  spring.datasource.password=
  ```
  enter your username and password 

3. Open new console inside of fodler ```iscc_client``` and run command:
  ```
  npm install
  ```

## Using

### Backend

  If you are using IDE, open/import ```ISCC``` directory and run project, 
  and if you are using *Apache Maven* naviget to folder ISCC and open new console inside the root folder
  after that run commnd:
  ```
  mvn spring-boot:run
  ```

### Frontend

  Open new console in directory iscc_client and run command:
  ```
  npm run serve
  ```
  after that the application will listen on port 8081.
