
#!/bin/sh

source .env.development


# $POSTGRES_HOST=mietek.krzb.pl:8090



sh gradlew clean && sh gradlew build && sh gradlew bootJar && java -jar ./build/libs/DailyAdvisor.jar --spring.datasource.url=jdbc:postgresql://$POSTGRES_HOST/daily_advisor --spring.datasource.username=$DATASOURCE_USERNAME --spring.datasource.password=$DATASOURCE_PASSWORD --server.port=$SERVER_PORT --facebook.client.clientId=$FACEBOOK_CLIENT_ID --facebook.client.clientSecret=$FACEBOOK_CLIENT_SECRET --google.client.clientId=$GOOGLE_CLIENT_ID --google.client.clientSecret=$GOOGLE_CLIENT_SECRET --spring.mail.username=$SRING_MAIL_USERNAME --spring.mail.password=$SPRING_MAIL_PASSWORD




# nohup java -jar $DA_SERVER_JAR_PATH --spring.datasource.url=$DA_DATABASE_URL --spring.datasource.username=$DA_DATABASE_USER --spring.datasource.password=$DA_DATABSE_PASS --server.port=$DA_SERVER_PORT &


# --spring.datasource.url=jdbc:postgresql://mietek.krzb.pl:8090/daily_advisor 
# --spring.datasource.username=root 
# --spring.datasource.password=pass 
# --server.port=8091 
# --facebook.client.clientId=clientId 
# --facebook.client.clientSecret=facebookSecret 
# --google.client.clientId=googleClientId 
# --google.client.clientSecret=googleSecret 
# --spring.mail.username=da.testserver@gmail.com 
# --spring.mail.password=passwordForGmail`