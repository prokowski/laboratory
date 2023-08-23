# Laboratory Application
Where is my test tube?

Example of Hexagonal Architecture (DDD + CQRS)

The aim of this application is to create a service to track the placement of test samples in laboratory.

# Prerequisites

- Java 17
- Set the JAVA_HOME Variable

# Build  and Test

In order to build with facade and integration tests, simply execute

    ./gradlew clean build

# Run it

In order to run application, simply execute

    ./gradlew bootRun

# Rest API

## Add patient

```
POST localhost:8080/api/patients
{
    "age": int,
    "company": string,
    "cityDistrict": string,
    "visionDefect": string
}
```

## Get patient by id

```
GET localhost:8080/api/patients/{patientId}
```

## Add rack

```
POST localhost:8080/api/racks
{
    "capacity": int
}
```

## Get rack by id

```
GET localhost:8080/api/racks/{rackId}
```

## Add sample

```
POST localhost:8080/api/samples
{
    "patientId": string
}
```

## Get sample by id

```
GET localhost:8080/api/samples/{sampleId}
```

## Assign sample to rack

```
PUT localhost:8080/api/samples/{sampleId}/assignToRack
```

# Tests

## Integration tests

[LaboratoryApplicationFullContextSpec](./src/integration/groovy/com/laboratory/LaboratoryApplicationFullContextSpec.groovy) - check if full context can stand up.\
[LaboratoryApplicationSpec](./src/integration/groovy/com/laboratory/LaboratoryApplicationSpec.groovy) - happy path for assignment samples to racks (REST API test).

## Facade tests

[PatientFacadeSpec.groovy](./src/test/groovy/com/laboratory/patient/domain/PatientFacadeSpec.groovy) - checks the creation of patients.\
[RackFacadeSpec.groovy](./src/test/groovy/com/laboratory/rack/domain/RackFacadeSpec.groovy) - checks the creation of racks.\
[SampleFacadeSpec.groovy](./src/test/groovy/com/laboratory/sample/domain/SampleFacadeSpec.groovy) - checks the creation of samples and samples assignment.