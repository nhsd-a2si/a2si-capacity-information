# a2si-capacity-information
Capacity Information - simple Jar project that can be shared between projects.

Contains the capacity information class that is accessed via the Capacity Service and used by the DoS Wrapper to add in Capacity Information to the CapacitySummaryResponse payload from DoS.

This class is a simple POJO designed to allow the Capacity Information stored within the Capacity Service to be retrieved by the DoS Wrapper as well as created/updated by the services that get the information (such as A2SI Capacity Info Reader).

A simple jar is built using Maven.

## Getting started
1. Set up your [Development Environment](docs/dev_setup.md)

2. Clone this repo to your local machine.

3. Follow the instructions below to configure and run the application.


## Build The Project

```bash
cd {projectRoot}
mvn clean install
```

The Maven "install" goal stores the built artifact into the local Maven repository, making it accessible to other projects using this repository.
