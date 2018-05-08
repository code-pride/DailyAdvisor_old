
#!/bin/sh
sh gradlew clean && sh gradlew build && sh gradlew bootJar && java -jar ./build/libs/DailyAdvisor.jar

