FROM moxm/java:1.8-full

RUN mkdir -p /lind-fast-demo

WORKDIR /lind-fast-demo

ARG JAR_FILE=target/lind-fast-demo.jar

COPY ${JAR_FILE} app.jar

EXPOSE 3000

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD sleep 60; java -jar app.jar $JAVA_OPTS
