# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# # Add a volume pointing to /tmp
# VOLUME /tmp

# # Make port 8091 available to the world outside this container
# EXPOSE 8091

# # The application's jar file
# ARG JAR_FILE=build/libs/DailyAdvisor.jar

# # Add the application's jar to the container
# ADD ${JAR_FILE} DailyAdvisor.jar

# # Run the jar file 
# ENTRYPOINT ["java","-jar","/DailyAdvisor.jar","--spring.datasource.url=jdbc:postgresql://db:5432/daily_advisor"]



RUN mkdir -p /backend
WORKDIR /backend

ADD /backend /backend

VOLUME ["/backend"]

ENTRYPOINT [ |"java", "-jar", "./backend/libs/DailyAdvisor.jar" ]

