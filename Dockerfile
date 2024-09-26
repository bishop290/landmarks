FROM gradle:8.1.1 AS build
COPY . /app
WORKDIR /app
RUN gradle build -x test

FROM tomcat:10.1.30-jre17
COPY --from=build /app/build/libs/ROOT.war /usr/local/tomcat/webapps/.