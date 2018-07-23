# Daily Advisor

Application for trainer and their students to keep them in progress, to make conversation and to stay in touch.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You need [Docker][1] and _docker-compose_ to run application in development mode

### Development

First enter repo root folder and run

```
$ docker-compose build
```

then run

```
$ docker-compose up
```

Be sure that you have port `8091`, `3000`, `5432` available on your host machine.

Then to populate database with initial data you need to make HTTP GET request on:

```
http://localhost:8091/populate
```

The application now is ready on:

```
http://localhost:3000
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

## Authors

- **[Bartek][5]**
- **[benq95][6]**
- **[makowskimarek][7]**
- **[Marcin Krawczyk][8]**
- **[Tomasz Ferens][9]**

[1]: https://www.docker.com/
[2]: https://nodejs.org/en/
[3]: http://localhost:3000
[4]: https://github.com/indexzero/http-server
[5]: https://github.com/BartoszBaczek
[6]: https://github.com/benq95
[7]: https://github.com/makowskimarek
[8]: https://github.com/marckraw
[9]: https://github.com/tomaszferens
