FROM maven:3.8.1-jdk-11-slim
# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies for offline use
#RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn clean install -DskipTests

# set the startup command to run your binary
CMD ["java", "-jar", "./target/backend-0.0.1-SNAPSHOT.jar"]