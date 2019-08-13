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

