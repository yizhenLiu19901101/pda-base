FROM java
COPY build/libs/pda-1.0.0.jar pda.jar
RUN bash -c 'touch ./pda.jar'
CMD ["java","-jar","pda.jar"]