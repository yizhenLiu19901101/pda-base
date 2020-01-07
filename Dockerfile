FROM java
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY build/libs/pda-1.0.0.jar pda.jar
RUN bash -c 'touch ./pda.jar'
CMD ["java","-jar","pda.jar"]