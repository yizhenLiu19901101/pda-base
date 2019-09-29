FROM java
COPY target/jiaxin-1.0.jar app.jar
RUN bash -c 'touch ./app.jar'
CMD ["java","-jar","app.jar"]