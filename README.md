# Reproducer for generic type reflection in Quarkus with native image

Command used:
```
./mvnw clean verify -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-native-image:19.3.1-java8
```
