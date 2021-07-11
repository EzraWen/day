FROM anapsix/alpine-java:8_server-jre_unlimited

VOLUME /tmp

ADD ./target/ttt-1.0.jar /app/
#ADD ./target/ttt-1.0.jar app.jar

# 运行jar包
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]