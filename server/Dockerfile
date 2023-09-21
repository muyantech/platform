# Build jar file with cached dependencies
FROM gradle:7.2.0-jdk11-openj9 as builder
MAINTAINER xiangqian.liu@muyan.cloud

# Set Grails environment
ENV GRAILS_ENV production
ENV GRADLE_USER_HOME /home/gradle/cache_home

ADD . /home/gradle/muyan-platform
WORKDIR /home/gradle/muyan-platform
RUN mkdir /app
RUN gradle bootJar --no-daemon --info --stacktrace --no-watch-fs
RUN mv build/libs/*.jar /app/boot.jar

# Build tools not needed on runtime image, just copy build artifact to runtime image
FROM --platform=linux/x86_64 ibm-semeru-runtimes:open-11-jdk
MAINTAINER xiangqian.liu@muyan.cloud

RUN mkdir -p /app/data

COPY --from=builder /app/boot.jar /app/boot.jar
COPY ./scripts/docker-entrypoint.sh ./scripts/jdbc.sh /
RUN chmod 755 /docker-entrypoint.sh && \
    chmod 755 /jdbc.sh

# Download ES APM package
RUN curl -L https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.25.0/elastic-apm-agent-1.25.0.jar --output /app/elastic-apm-agent.jar

ADD ./data/groovy /app/data/groovy
ADD ./data/css /app/data/css
ADD ./data/attachments /app/data/attachments

# Copy staging, production, review profile seed data to /app/data
ADD ./data/review /app/data/production
ADD ./data/review /app/data/staging
ADD ./data/review /app/data/review

WORKDIR /app

USER root

EXPOSE 8080 10000

ENTRYPOINT ["/docker-entrypoint.sh"]
