# Apache Kafka Series - Learn Apache Kafka for Beginners

## Repositories

https://github.com/SergiOn/kafka-beginners-course

https://github.com/simplesteph/kafka-beginners-course


## Commands

kafka-topics.sh

zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties

zookeeper-server-start.sh /Library/Kafka/2.12-2.3.0/config/zookeeper.properties

cat /Library/Kafka/2.12-2.3.0/config/zookeeper.properties

cat $KAFKA_HOME/config/zookeeper.properties

nano /Library/Kafka/2.12-2.3.0/config/zookeeper.properties

nano $KAFKA_HOME/config/zookeeper.properties

dataDir=/Library/Kafka/2.12-2.3.0/data/zookeeper

zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties

nano $KAFKA_HOME/config/server.properties

log.dirs=/Library/Kafka/2.12-2.3.0/data/kafka

cat $KAFKA_HOME/config/server.properties


zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties

kafka-server-start.sh $KAFKA_HOME/config/server.properties


### section 6, lecture 32

zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties

kafka-server-start.sh $KAFKA_HOME/config/server.properties


kafka-topics.sh

[//]: <> (kafka-topics.sh --bootstrap-server localhost:9092 --topic first_topic --create --partitions 3 --replication-factor 2)

kafka-topics.sh --bootstrap-server localhost:9092 --topic first_topic --create --partitions 3 --replication-factor 1

kafka-topics.sh --bootstrap-server localhost:9092 --list

kafka-topics.sh --bootstrap-server localhost:9092 --topic first_topic --describe

kafka-topics.sh --bootstrap-server localhost:9092 --topic second_topic --create --partitions 6 --replication-factor 1

kafka-topics.sh --bootstrap-server localhost:9092 --list

kafka-topics.sh --bootstrap-server localhost:9092 --topic second_topic --delete

kafka-topics.sh --bootstrap-server localhost:9092 --list


### section 6, lecture 33

zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties

kafka-server-start.sh $KAFKA_HOME/config/server.properties


kafka-console-producer.sh

kafka-console-producer.sh --broker-list localhost:9092 --topic first_topic

kafka-console-producer.sh --broker-list localhost:9092 --topic first_topic --producer-property asks=all

kafka-console-producer.sh --broker-list localhost:9092 --topic new_topic

kafka-topics.sh --bootstrap-server localhost:9092 --list

kafka-topics.sh --bootstrap-server localhost:9092 --topic new_topic --describe


nano $KAFKA_HOME/config/server.properties

num.partitions=3


kafka-console-producer.sh --broker-list localhost:9092 --topic new_topic_2

kafka-topics.sh --bootstrap-server localhost:9092 --list

kafka-topics.sh --bootstrap-server localhost:9092 --topic new_topic_2 --describe


### section 6, lecture 34

kafka-server-start.sh $KAFKA_HOME/config/server.properties

zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties


kafka-console-consumer.sh

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic

kafka-console-producer.sh --broker-list localhost:9092 --topic first_topic


kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --from-beginning


### section 6, lecture 35

kafka-server-start.sh $KAFKA_HOME/config/server.properties

zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties


kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-application

kafka-console-producer.sh --broker-list localhost:9092 --topic first_topic


kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-second-application --from-beginning

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-second-application


### section 6, lecture 36

kafka-server-start.sh $KAFKA_HOME/config/server.properties

zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties


kafka-consumer-groups.sh

kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

my-first-application
my-second-application

kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group my-second-application

kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group my-first-application


kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-application

kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group my-first-application


### section 6, lecture 37

kafka-server-start.sh $KAFKA_HOME/config/server.properties

zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties


kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --to-earliest

kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --to-earliest --execute


kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --to-earliest --execute --topic first_topic

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-application

kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group my-first-application


kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --shift-by 2 --execute --topic first_topic

kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --shift-by -2 --execute --topic first_topic

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-application


### section 6, lecture 38

kafka-console-producer --broker-list 127.0.0.1:9092 --topic first_topic --property parse.key=true --property key.separator=,

> key,value

> another key,another value

kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --from-beginning --property print.key=true --property key.separator=,
