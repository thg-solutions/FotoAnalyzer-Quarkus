# FotoAnalyzer-Quarkus

Im Gegensatz zu MicroProfil verwendet die REST-Implementierung von Quarkus RESTEasy von JBoss. Deshalb sieht der REST-Controller insbesondere bei der Übertragung von `multipart/form-data` anders aus: Anstelle von `FormDataBodyPart` (org.glassfish) wird hier ein Objekt vom Typ `FileUpload` (org.jboss) erzeugt.

Wie Helidon, aber im Gegensatz zu Payara, ist Contructor-Injection möglich, ein No-Args-Constructor nicht erforderlich. Die Implementierung des FotoAnalyzer unterscheidet sich also - wenn auch nur geringfügig - von den beiden anderen.

## Anwendung

siehe https://github.com/thg-solutions/FotoAnalyzer-Helidon-MP/blob/main/README.md.

## Erweiterungen

### Swagger

Durch Integration der Extension `quarkus-smallrye-openapi` ist mittels
```
curl localhost:8080/swagger[?format=json]
```
die OpenAPI-Definition der REST-Schnittstelle abrufbar.

# fotoanalyzer-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/fotoanalyzer-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
