# BraiNet Server

BraiNet project is about using brain signals to do privacy management for smartphones. This is similar to FaceID in iPhone X but instead of using face image you use brain signals. We can call it "Thought ID".

## Description

This is a RESTful API server for BraiNet project, which supports user authentication and authorization.

* `/user/signup`: Accept POST request for user signing up.
* `/login`: Accept POST request for user login.

For any other http requests, JWT is required for authorization.

## Tech Stack

* Spring Boot
* Spring Data JPA with Hibernate
* Spring Security
* Java JWT
* PostgreSQL

## How to Build

Before building the application, you must configure database parameters of your environment in `application.properties`, then create a database named `brainet`. You can use the following command:
```
psql -U postgres -f database.sql
```

#### Install JAR Libraries

The tools used for processing brain signal are implemented with Matlab and then packaged into JAR library. To use these tools properly, you first need to download the Matlab MCR R2016a and install it using the command `./install -mode silent -agreeToLicense yes`.

Then you must install the jar files used in this project to local maven repository. If you don't do so, maven won't include these jar files into the generated jar package, which is nearly impossible to run it in different environment.

1. Install JavaBuilder:
```
mvn install:install-file -Dfile="/usr/local/MATLAB/MATLAB_Runtime/v901/toolbox/javabuilder/jar/javabuilder.jar" -DgroupId=JavaBuilder -DartifactId=JavaBuilder -Dversion=1.0 -Dpackaging=jar
```

2. Install FeatureExt (Remember to correct the path to `FeatureExt.jar`):
```
mvn install:install-file -Dfile="<path_to_FeatureExt.jar>" -DgroupId=FeatureExt -DartifactId=FeatureExt -Dversion=1.0 -Dpackaging=jar
```

3. Install Comparator (Remember to correct the path to `Comparator.jar`):
```
mvn install:install-file -Dfile="<path_to_Comparator.jar>" -DgroupId=Comparator -DartifactId=Comparator -Dversion=1.0 -Dpackaging=jar
```

#### Install System Dependencies

Matlab MCR requires `libxt` and `libxmu` to run (no graphic environment required). You must install them before starting the application:
```
apt-get install libxt6 libxmu6
```

#### Start the Application

You are also required to provide correct linkage path for the application to start. Just use this command to start the application:
```
LD_LIBRARY_PATH=/usr/local/MATLAB/MATLAB_Runtime/v901/runtime/glnxa64:/usr/local/MATLAB/MATLAB_Runtime/v901/bin/glnxa64:/usr/local/MATLAB/MATLAB_Runtime/v901/sys/os/glnxa64:/usr/local/MATLAB/MATLAB_Runtime/v901/sys/opengl/lib/glnxa64 java -jar target/brainet-server-0.0.1-SNAPSHOT.jar
```

## Usage

Registers a new user:
```
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "username",
    "password": "brain_signal"
}' http://localhost:8080/user/signup
```

Logs into the application (JWT is generated):
```
curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "username",
    "password": "brain_signal"
}' http://localhost:8080/login
```

Issue a GET request, passing the JWT:
```
curl -H "Authorization: Bearer json_web_token" http://localhost:8080/other_get_request
```
