# Apache Kafka Series - Kafka Connect Hands-on Learning

## Repositories

https://courses.datacumulus.com/kafka-connect-09a

https://github.com/simplesteph/kafka-connect-github-source

https://github.com/SergiOn/kafka-connect-github-source


## Links

https://stackoverflow.com/questions/32834082/how-to-increase-docker-machine-memory-mac


## Commands


### section 4, lecture 18

cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-Kafka-Connect-Hands-on-Learning

docker-compose up kafka-cluster

docker-compose down


### section 6, lecture 23

cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-Kafka-Connect-Hands-on-Learning/kafka-connect

docker-compose up kafka-cluster

docker-compose down


### section 6, lecture 24

cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-Kafka-Connect-Hands-on-Learning/kafka-connect

docker-compose up kafka-cluster

docker run --rm -it -v "$(pwd)":/tutorial --net=host landoop/fast-data-dev:2.3.0 bash

cd /tutorial/source/demo-1

kafka-topics --create --topic demo-1-standalone --partitions 3 --replication-factor 1 --bootstrap-server 127.0.0.1:9092

kafka-topics --bootstrap-server 127.0.0.1:9092 --list

connect-standalone worker.properties file-stream-demo-standalone.properties





