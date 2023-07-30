FROM openjdk:17

WORKDIR /usr/voteskill-auth-server

COPY . .

RUN pwd

RUN ls -al

RUN ./gradlew clean build

RUN mv /build/libs/*.jar build

RUN rm `ls * | grep -v build`

ENTRYPOINT [ "java", "-Dserver.port=80", "-jar", "build" ]