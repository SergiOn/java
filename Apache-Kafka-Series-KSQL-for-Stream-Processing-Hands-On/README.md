# Apache Kafka Series - KSQL for Stream Processing - Hands On!

## Repositories

https://courses.datacumulus.com/kafka-ksql-na2.html


## Links

https://www.rittmanmead.com/blog/2017/11/taking-ksql-for-a-spin-using-real-time-device-data/

https://medium.com/@simon.aubury/using-ksql-apache-kafka-a-raspberry-pi-and-a-software-defined-radio-to-find-the-plane-that-wakes-14f6f9e74584

https://medium.com/@simon.aubury/machine-learning-kafka-ksql-stream-processing-bug-me-when-ive-left-the-heater-on-bd47540cd1e8

https://www.confluent.io/download

https://stackoverflow.com/questions/18459945/how-to-solve-could-not-create-the-virtual-machine-error-of-java-virtual-machine

https://docs.confluent.io/current/cli/installing.html

https://stackoverflow.com/questions/35684649/how-can-i-find-kafka-config-file


## Information

Kafka config file: /Library/Kafka/confluent-5.3.1/etc/kafka


## Commands

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

confluent local start ksql-server

confluent local status

confluent local stop


#### section 3, lecture 4

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

confluent local start ksql-server

confluent local status

ksql
` ksql> `

exit

#### section 3, lecture 6

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

ksql

`http://localhost:8088/info`

`ksql>`
list topics;

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic USERS

`ksql>`
list topics;

kafka-console-producer --broker-list localhost:9092 --topic USERS
```
Alice,US
```

`ksql>`
print 'USERS';

```
Bob,GB
Carole,AU
Dan,US
```

control + c


`ksql>`
print 'USERS' from beginning;

`ksql>`
print 'USERS' from beginning limit 2;

`ksql>`
print 'USERS' from beginning interval 2 limit 2;


#### section 4, lecture 7

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
list topics;

`ksql>`
create stream users_stream (name VARCHAR, countrycode VARCHAR) WITH (KAFKA_TOPIC='USERS', VALUE_FORMAT='DELIMITED');

`ksql>`
list streams;

```markdown
 Stream Name         | Kafka Topic                 | Format    
---------------------------------------------------------------
 KSQL_PROCESSING_LOG | default_ksql_processing_log | JSON      
 USERS_STREAM        | USERS                       | DELIMITED 
---------------------------------------------------------------
```

`ksql>`
select name, countrycode from users_stream;

```
Ed,GB
```

`ksql>`
SET 'auto.offset.reset'='earliest';

`ksql>`
select name, countrycode from users_stream;

```markdown
Alice | US
Bob | GB
Carole | AU
Dan | US
Ed | GB
```


`ksql>`
select name, countrycode from users_stream limit 4;

```markdown
Alice | US
Bob | GB
Carole | AU
Dan | US
Limit Reached
Query terminated
```


`ksql>`
select countrycode, count(*) from users_stream group by countrycode;

```markdown
AU | 1
US | 2
GB | 2
```


`ksql>`
drop stream if exists users_stream delete topic;

`ksql>`
list streams;

`ksql>`
list topics;

`ksql>`
show streams;

`ksql>`
show topics;







