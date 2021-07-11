FROM docker.io/williamyeh/java8

VOLUME /tmp


ADD Thread-1.0.jar app.jar

# 运行jar包
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]