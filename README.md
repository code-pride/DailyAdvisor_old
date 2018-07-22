# Daily Advisor

Application for trainer and their students to keep them in progress, to make conversation and to stay in touch.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You need to set up your development environment before you can do anything.

#### Frontend

* Install [Node.js® and npm][1] if they are not already on your machine.

#### Backend

* Install Java V1.8.0_161
* Install PostgreSQL V10.3

### Installing

#### Frontend

* Go to projects' frontend directory and install all dependencies

```
$ cd DailyAdvisor/frontend
$ npm install
```

* Start the application

```
$ npm run start
```

Your app will be opened on [http://localhost:3000][2]

#### Backend

##### Database

Creation:
```
$ sudo -u postgres psql -c 'create database daily_advisor;'
```
Login to database
```
$ sudo -u postgres psql postgres
```
Grant privilages
```
$ grant all privileges on database daily_advisor to postgres;
```

##### Configuration of PostgreSQL server

In backend\src\main\resources\application.properties check:
```
server.port = 8091
spring.datasource.url =jdbc:postgresql://localhost:5432/daily_advisor
spring.datasource.username =postgres
spring.datasource.password =postgres
```
##### Server
In backend root folder launch commands:
```
$ ./gradlew clean
$ ./gradlew build
$ ./gradlew bootJar
```

## Running the tests

There are multiple type of tests that you can run.

### End to end tests

To run e2e tests run:

```
$ npm run e2e
```

### Frontend unit tests

To run frontend unit tests run:

```
npm run test
```

### Backend tests

In backend root folder launch commands:
```
$ ./gradlew testClasses
```

## Deployment

#### Frontend

To build frontend run:

```
npm run build
```

This command will create highly optimized version of frontend. You can open it with any http server i.e. [http-server][3]:

```
$ cd dist
$ http-server ./
```

#### Backend
##### Run server
In backend/build/lib/ run commands
```
$ java -jar DailyAdvisor.java
```

## Authors

* **[Bartek][4]**
* **[benq95][5]**
* **[makowskimarek][6]**
* **[Marcin Krawczyk][7]**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


[1]: https://nodejs.org/en/
[2]: http://localhost:3000
[3]: https://github.com/indexzero/http-server
[4]: https://github.com/BartoszBaczek
[5]: https://github.com/benq95
[6]: https://github.com/makowskimarek
[7]: https://github.com/marckraw
