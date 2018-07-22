#!/bin/sh
source .env.development

sh gradlew clean && sh gradlew build && sh gradlew bootJar && java -jar ./build/libs/DailyAdvisor.jar --spring.datasource.url=jdbc:postgresql://$POSTGRES_HOST/daily_advisor --spring.datasource.username=$DATASOURCE_USERNAME --spring.datasource.password=$DATASOURCE_PASSWORD --server.port=$SERVER_PORT --facebook.client.clientId=$FACEBOOK_CLIENT_ID --facebook.client.clientSecret=$FACEBOOK_CLIENT_SECRET --google.client.clientId=$GOOGLE_CLIENT_ID --google.client.clientSecret=$GOOGLE_CLIENT_SECRET --spring.mail.username=$SRING_MAIL_USERNAME --spring.mail.password=$SPRING_MAIL_PASSWORD
