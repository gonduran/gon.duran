FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/pacientes-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_NPVWMIQ0DLCMZLXR /app/oracle_wallet
EXPOSE 8090

CMD [ "java", "-jar", "app.jar" ]