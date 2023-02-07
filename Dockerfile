# Docker example using fatjar
# - docker run amazoncorretto:17 java -version
# - docker build -t first/soltia-starter .
# - docker run -t -i -p 8888:8888 first/soltia-starter

FROM amazoncorretto:17

ENV FAT_JAR starter-1.0.0-SNAPSHOT-fat.jar

ENV APP_HOME /usr/app/

EXPOSE 8888

WORKDIR $APP_HOME
COPY build/libs/$FAT_JAR $APP_HOME

ENTRYPOINT ["sh","-c"]
CMD ["exec java -jar $FAT_JAR"]


