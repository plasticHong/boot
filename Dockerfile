FROM openjdk:11
ADD build/libs/boot-0.0.1-SNAPSHOT.jar boot-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "boot-0.0.1-SNAPSHOT.jar"]