FROM ubuntu:17.10

MAINTAINER  Andrew Rendell <andrew@rendell.org>

RUN apt-get -y update
RUN apt-get --yes install openjdk-8-jdk
RUN apt-get --yes install curl

RUN groupadd kafka
RUN mkdir -p /opt/app/
RUN useradd -g kafka -m -d /opt/app/kafka -s /bin/bash kafka

ADD download_kafka.sh /opt/app/kafka/download_kafka.sh
RUN chown kafka:kafka /opt/app/kafka/download_kafka.sh
RUN chmod +x /opt/app/kafka/download_kafka.sh
RUN /opt/app/kafka/download_kafka.sh
RUN chown -R kafka:kafka /opt/app/kafka/kafka_2.11-1.0.0

EXPOSE 2181
EXPOSE 9092

ADD start_kafka.sh /opt/app/kafka/start_kafka.sh
RUN chown kafka:kafka /opt/app/kafka/start_kafka.sh
RUN chmod +x /opt/app/kafka/start_kafka.sh


CMD ["/opt/app/kafka/start_kafka.sh"]


