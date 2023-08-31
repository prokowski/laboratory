# Laboratory Application

Example of Hexagonal Architecture (DDD + CQRS)

The aim of this application is to create a service to assign of test samples to racks in laboratory.

Service will assign rackID to sampleID with the following rules:
1. Each tube contains a material for a specific patient.
2. It is illegal to place patients of the same age into the same rack.
3. It is illegal to place patients working in the same company into the same rack.
4. It is illegal to place patients who live in the same city district into the same rack.
5. It is illegal to place patients with the same vision defect into the same rack.
6. The number of racks is very limited.

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

## Assign sample to rack

```
PUT localhost:8080/api/racks/assignSample/{sampleId}
```

# Tests

## Integration tests

[LaboratoryApplicationFullContextSpec](./src/integration/groovy/com/laboratory/LaboratoryApplicationFullContextSpec.groovy) - check if full context can stand up.\
[LaboratoryApplicationSpec](./src/integration/groovy/com/laboratory/LaboratoryApplicationSpec.groovy) - happy path for assignment samples to racks (REST API test).

## Facade tests

[PatientFacadeSpec](./src/test/groovy/com/laboratory/patient/domain/PatientFacadeSpec.groovy) - checks the creation of patients.\
[SampleFacadeSpec](./src/test/groovy/com/laboratory/sample/domain/SampleFacadeSpec.groovy) - checks the creation of samples. \
[RackFacadeSpec](./src/test/groovy/com/laboratory/rack/domain/RackFacadeSpec.groovy) - checks the creation of racks and samples assignment.
