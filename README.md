# MicroframeworksWEB

A lightweight Java web microframework built on raw sockets that enables developers to build REST services using lambda expressions and serve static files, without any external dependencies.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What you need to install the software:

* Java JDK 17 or higher
* Maven 3.8 or higher
* Git

```
java -version
mvn -version
git --version
```

### Installing

A step by step series of examples that tell you how to get a development environment running.

Clone the repository:

```
git clone https://github.com/<your-username>/MicroframeworksWEB.git
```

Navigate to the project folder:

```
cd MicroframeworksWEB
```

Build the project with Maven:

```
mvn clean package
```

Run the web application:

```
java -jar target/MicroframeworksWEB-1.0-SNAPSHOT.jar
```

You should see in the console:

```
Listo para recibir ...
```

The server is now running on port **35000**. Open your browser and go to:

```
http://localhost:35000/index.html
```

## Running the tests

### End to end tests

The following URLs can be used to test the system manually from the browser or with curl:

Test the `/App/hello` REST endpoint with a query parameter:

```
http://localhost:35000/App/hello?name=Samuel
```

Expected response: `Hello Samuel`

Test the `/App/pi` REST endpoint:

```
http://localhost:35000/App/pi
```

Expected response: `3.141592653589793`

Test static file serving:

```
http://localhost:35000/index.html
```

Expected response: HTML welcome page

### Test evidence

#### GET /App/hello?name=Samuel

<img width="903" height="153" alt="image" src="https://github.com/user-attachments/assets/e7233e5d-5dab-49a7-9519-d5b72fb06bd4" />


#### GET /App/pi

<img width="1220" height="346" alt="image" src="https://github.com/user-attachments/assets/12cb2900-0b76-4627-b4aa-e04ccc5ba38a" />


#### GET /index.html

<img width="899" height="317" alt="image" src="https://github.com/user-attachments/assets/b5cc0a42-137f-4303-89d6-77a96d17e4f3" />

### Coding style tests

The project follows standard Java conventions. All classes are organized by package:

```
utilities   → HttpServer, Request, Response, Webmethod, EchoClient, EchoServer, URLParser, URLReader
appexamples → Webapp, Mathservice
```

## Deployment

To deploy on a live system, package the project as a fat JAR and run it on any machine with Java 17+:

```
mvn clean package
java -jar target/MicroframeworksWEB-1.0-SNAPSHOT.jar
```

Make sure port 35000 is open on the server firewall.

## Built With

* **Java Sockets** - Core networking layer
* **Maven** - Dependency management and build tool
* **Java** - Programming language

## Contributing

Please read **CONTRIBUTING.md** for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use **SemVer** for versioning. For the versions available, see the **tags on this repository**.

## Authors

Samuel Antonio Gil Romero 

TDSE


## Acknowledgments

* Luis Daniel Benavides Navarro - Lab guide and base concepts (Escuela Colombiana de Ingeniería)
* Oracle Java Networking Tutorial - https://docs.oracle.com/javase/tutorial/networking/index.html
* Inspiration from Spark Java microframework
