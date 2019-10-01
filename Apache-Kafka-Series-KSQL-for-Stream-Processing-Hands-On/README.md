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

ksql
` ksql> `

`ksql>`
exit


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


#### section 4, lecture 8

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic USERPROFILE

kafka-topics --bootstrap-server localhost:9092 --list


```json
{"userid": 1000, "firstname":"Alison", "lastname":"Smith", "countrycode":"GB", "rating":4.7}
```

kafka-console-producer --broker-list localhost:9092 --topic USERPROFILE << EOF
{"userid": 1000, "firstname":"Alison", "lastname":"Smith", "countrycode":"GB", "rating":4.7}
EOF


`ksql>`
list topics;

`ksql>`
CREATE STREAM userprofile (userid INT, firstname VARCHAR, lastname VARCHAR, countrycode VARCHAR, rating DOUBLE)
    WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'USERPROFILE');


`ksql>`
list streams;

```markdown
 Stream Name         | Kafka Topic                 | Format 
------------------------------------------------------------
 KSQL_PROCESSING_LOG | default_ksql_processing_log | JSON   
 USERPROFILE         | USERPROFILE                 | JSON   
------------------------------------------------------------
```

`ksql>`
describe userprofile;

```markdown
Name                 : USERPROFILE
 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 USERID      | INTEGER                   
 FIRSTNAME   | VARCHAR(STRING)           
 LASTNAME    | VARCHAR(STRING)           
 COUNTRYCODE | VARCHAR(STRING)           
 RATING      | DOUBLE                    
-----------------------------------------
For runtime statistics and query details run: DESCRIBE EXTENDED <Stream,Table>;
```


`ksql>`
select firstname, lastname, countrycode, rating from userprofile;

```markdown
Alison | Smith | GB | 4.7
```


kafka-console-producer --broker-list localhost:9092 --topic USERPROFILE << EOF
{"userid": 1001, "firstname":"Bob", "lastname":"Smith", "countrycode":"US", "rating":4.2}
EOF


`ksql>`
select firstname, lastname, countrycode, rating from userprofile;

```markdown
Alison | Smith | GB | 4.7
Bob | Smith | US | 4.2
```


#### section 4, lecture 9

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

```bash
cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master

cd datagen
cat userprofile.avro
```

ksql-datagen schema=./userprofile.avro format=json topic=USERPROFILE key=userid maxInterval=5000 iterations=100


`ksql>`
print 'USERPROFILE' interval 5;


#### section 4, lecture 10

`ksql>`
describe userprofile;

```markdown
Name                 : USERPROFILE
 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 USERID      | INTEGER                   
 FIRSTNAME   | VARCHAR(STRING)           
 LASTNAME    | VARCHAR(STRING)           
 COUNTRYCODE | VARCHAR(STRING)           
 RATING      | DOUBLE                    
-----------------------------------------
```

`ksql>`
select rowtime, firstname from userprofile;

`ksql>`
SELECT TIMESTAMPTOSTRING(rowtime, 'dd/MMM HH:mm') as createtime, firstname from userprofile;

`ksql>`
select TIMESTAMPTOSTRING(rowtime, 'dd/MMM HH:mm') as createtime, firstname || ' ' || ucase(lastname) as full_name from userprofile;


#### section 4, lecture 11

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
list streams;

```markdown
 Stream Name         | Kafka Topic                 | Format 
------------------------------------------------------------
 KSQL_PROCESSING_LOG | default_ksql_processing_log | JSON   
 USERPROFILE         | USERPROFILE                 | JSON   
------------------------------------------------------------
```

`ksql>`
describe userprofile;

```markdown
Name                 : USERPROFILE
 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 USERID      | INTEGER                   
 FIRSTNAME   | VARCHAR(STRING)           
 LASTNAME    | VARCHAR(STRING)           
 COUNTRYCODE | VARCHAR(STRING)           
 RATING      | DOUBLE                    
-----------------------------------------
```

cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master

`ksql>`
run script /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master/user_profile_pretty.ksql;

```markdown
 Message                    
----------------------------
 Stream created and running 
----------------------------
```

`ksql>`
list streams;

```markdown
 Stream Name         | Kafka Topic                 | Format 
------------------------------------------------------------
 KSQL_PROCESSING_LOG | default_ksql_processing_log | JSON   
 USERPROFILE         | USERPROFILE                 | JSON   
 USER_PROFILE_PRETTY | USER_PROFILE_PRETTY         | JSON   
------------------------------------------------------------
```

`ksql>`
describe extended user_profile_pretty;

```markdown
Name                 : USER_PROFILE_PRETTY
Type                 : STREAM
Key field            : 
Key format           : STRING
Timestamp field      : Not set - using <ROWTIME>
Value format         : JSON
Kafka topic          : USER_PROFILE_PRETTY (partitions: 1, replication: 1)

 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 DESCRIPTION | VARCHAR(STRING)           
-----------------------------------------

Queries that write into this STREAM
-----------------------------------
CSAS_USER_PROFILE_PRETTY_0 : CREATE STREAM USER_PROFILE_PRETTY WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'USER_PROFILE_PRETTY') AS SELECT concat(concat(concat(concat(concat(concat(concat(concat(USERPROFILE.FIRSTNAME, ' '), UCASE(USERPROFILE.LASTNAME)), ' from '), USERPROFILE.COUNTRYCODE), ' has a rating of '), CAST(USERPROFILE.RATING AS STRING)), ' stars. '), (CASE WHEN (USERPROFILE.RATING < 2.5) THEN 'Poor' WHEN (USERPROFILE.RATING BETWEEN 2.5 AND 4.2) THEN 'Good' ELSE 'Excellent' END)) "DESCRIPTION"
FROM USERPROFILE USERPROFILE;

For query topology and execution plan please run: EXPLAIN <QueryId>

Local runtime statistics
------------------------
messages-per-sec:         0   total-messages:       102     last-message: 2019-09-26T14:25:38.816Z

(Statistics of the local KSQL server interaction with the Kafka topic USER_PROFILE_PRETTY)
```

`ksql>`
select description from user_profile_pretty;


ksql-datagen schema=./ksql-course-master/userprofile.avro format=json topic=USERPROFILE key=userid maxInterval=5000 iterations=100


`ksql>`
drop stream user_profile_pretty;

```markdown
Cannot drop USER_PROFILE_PRETTY.
The following queries read from this source: [].
The following queries write into this source: [CSAS_USER_PROFILE_PRETTY_0].
You need to terminate them before dropping USER_PROFILE_PRETTY.
```

`ksql>`
terminate query CSAS_USER_PROFILE_PRETTY_0;

`ksql>`
drop stream user_profile_pretty;

```markdown
 Message                                                              
----------------------------------------------------------------------
 Source USER_PROFILE_PRETTY (topic: USER_PROFILE_PRETTY) was dropped. 
----------------------------------------------------------------------
```

`ksql>`
list streams;

```markdown
 Stream Name         | Kafka Topic                 | Format 
------------------------------------------------------------
 KSQL_PROCESSING_LOG | default_ksql_processing_log | JSON   
 USERPROFILE         | USERPROFILE                 | JSON   
------------------------------------------------------------
```

`ksql>`
drop stream IF EXISTS user_profile_pretty;

```markdown
 Message                                    
--------------------------------------------
 Source USER_PROFILE_PRETTY does not exist. 
--------------------------------------------
```

`ksql>`
drop stream IF EXISTS user_profile_pretty DELETE TOPIC;


#### section 4, lecture 12

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic COUNTRY-CSV

kafka-topics --bootstrap-server localhost:9092 --list

kafka-console-producer --broker-list localhost:9092 --topic COUNTRY-CSV --property "parse.key=true" --property "key.separator=:"

```markdown
AU:AU,Australia
IN:IN,India
GB:GB,UK
US:US,United States
GB:GB,United Kingdom
FR:FR,France
```


`ksql>`
CREATE TABLE COUNTRYTABLE (countrycode VARCHAR, countryname VARCHAR) WITH (KAFKA_TOPIC='COUNTRY-CSV', VALUE_FORMAT='DELIMITED', KEY='countrycode');

```markdown
 Message       
---------------
 Table created 
---------------
```

`ksql>`
show tables;

```markdown
 Table Name   | Kafka Topic | Format    | Windowed 
---------------------------------------------------
 COUNTRYTABLE | COUNTRY-CSV | DELIMITED | false    
---------------------------------------------------
```

`ksql>`
describe COUNTRYTABLE;

```markdown
Name                 : COUNTRYTABLE
 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 COUNTRYCODE | VARCHAR(STRING)           
 COUNTRYNAME | VARCHAR(STRING)           
-----------------------------------------
For runtime statistics and query details run: DESCRIBE EXTENDED <Stream,Table>;
```

`ksql>`
describe extended COUNTRYTABLE;

```markdown
Name                 : COUNTRYTABLE
Type                 : TABLE
Key field            : COUNTRYCODE
Key format           : STRING
Timestamp field      : Not set - using <ROWTIME>
Value format         : DELIMITED
Kafka topic          : COUNTRY-CSV (partitions: 1, replication: 1)

 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 COUNTRYCODE | VARCHAR(STRING)           
 COUNTRYNAME | VARCHAR(STRING)           
-----------------------------------------

Local runtime statistics
------------------------


(Statistics of the local KSQL server interaction with the Kafka topic COUNTRY-CSV)
```

`ksql>`
SET 'auto.offset.reset'='earliest';

```markdown
Successfully changed local property 'auto.offset.reset' to 'earliest'. Use the UNSET command to revert your change.
```

`ksql>`
select countrycode, countryname from countrytable;

```markdown
IN | India
US | United States
GB | United Kingdom
FR | France
AU | Australia
```

`ksql>`
select countrycode, countryname from countrytable where countrycode='GB' limit 1;

```markdown
GB | United Kingdom
Limit Reached
Query terminated
```

`ksql>`
select countrycode, countryname from countrytable where countrycode='FR';


#### section 5, lecture 13

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
SET 'auto.offset.reset'='earliest';

```markdown
Successfully changed local property 'auto.offset.reset' to 'earliest'. Use the UNSET command to revert your change.
```

`ksql>`
select * from userprofile;

```markdown
1569508462153 | 1000 | 1000 | Bob | Jones | IN | 3.9
1569508463564 | 1001 | 1001 | Bob | Smith | GB | 3.4
1569508465313 | 1002 | 1002 | Grace | Dotty | IN | 3.9
1569508466956 | 1003 | 1003 | Dan | Jones | US | 2.2
1569508470352 | 1004 | 1004 | Frank | Smith | IN | 4.9
1569508472067 | 1005 | 1005 | Bob | Jones | US | 4.9
1569508473330 | 1006 | 1006 | Frank | Fawcett | GB | 4.4
```


`ksql>`
describe userprofile;

```markdown
Name                 : USERPROFILE
 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 USERID      | INTEGER                   
 FIRSTNAME   | VARCHAR(STRING)           
 LASTNAME    | VARCHAR(STRING)           
 COUNTRYCODE | VARCHAR(STRING)           
 RATING      | DOUBLE                    
-----------------------------------------
For runtime statistics and query details run: DESCRIBE EXTENDED <Stream,Table>;

```

`ksql>`
select * from countrytable;

```markdown
1569531410180 | IN | IN | India
1569531451742 | US | US | United States
1569531478654 | GB | GB | United Kingdom
1569531492574 | FR | FR | France
1569532536477 | AU | AU | Australia
```


`ksql>`
describe countrytable;

```markdown
Name                 : COUNTRYTABLE
 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 COUNTRYCODE | VARCHAR(STRING)           
 COUNTRYNAME | VARCHAR(STRING)           
-----------------------------------------
For runtime statistics and query details run: DESCRIBE EXTENDED <Stream,Table>;
```


`ksql>`
select countrycode, countryname from countrytable;

```markdown
IN | India
US | United States
GB | United Kingdom
FR | France
AU | Australia
```


`ksql>`
select firstname, lastname, countrycode, rating from userprofile;

```markdown
Bob | Jones | IN | 3.9
Bob | Smith | GB | 3.4
Grace | Dotty | IN | 3.9
Dan | Jones | US | 2.2
Frank | Smith | IN | 4.9
Bob | Jones | US | 4.9
Frank | Fawcett | GB | 4.4
```


`ksql>`
select up.firstname, up.lastname, up.countrycode, ct.countryname
    from USERPROFILE up
    left join COUNTRYTABLE ct on ct.countrycode = up.countrycode;

```markdown
Bob | Jones | IN | India
Bob | Smith | GB | United Kingdom
Grace | Dotty | IN | India
Dan | Jones | US | United States
Frank | Smith | IN | India
Bob | Jones | US | United States
Frank | Fawcett | GB | United Kingdom
```


`ksql>`
create stream up_joined as
    select up.firstname
        || ' ' || ucase(up.lastname)
        || ' from ' || ct.countryname
        || ' has a rating of ' || cast(rating as varchar) || ' stars.' as description
    from USERPROFILE up
    left join COUNTRYTABLE ct on ct.countrycode = up.countrycode;

```markdown
 Message                    
----------------------------
 Stream created and running 
----------------------------
```


`ksql>`
describe up_joined;

```markdown
Name                 : UP_JOINED
 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 DESCRIPTION | VARCHAR(STRING)           
-----------------------------------------
For runtime statistics and query details run: DESCRIBE EXTENDED <Stream,Table>;
```


`ksql>`
describe extended up_joined;

```markdown
Name                 : UP_JOINED
Type                 : STREAM
Key field            : 
Key format           : STRING
Timestamp field      : Not set - using <ROWTIME>
Value format         : JSON
Kafka topic          : UP_JOINED (partitions: 1, replication: 1)

 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 DESCRIPTION | VARCHAR(STRING)           
-----------------------------------------

Queries that write into this STREAM
-----------------------------------
CSAS_UP_JOINED_1 : CREATE STREAM UP_JOINED WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'UP_JOINED') AS SELECT concat(concat(concat(concat(concat(concat(concat(UP.FIRSTNAME, ' '), UCASE(UP.LASTNAME)), ' from '), CT.COUNTRYNAME), ' has a rating of '), CAST(UP.RATING AS STRING)), ' stars.') "DESCRIPTION"
FROM USERPROFILE UP
LEFT OUTER JOIN COUNTRYTABLE CT ON ((CT.COUNTRYCODE = UP.COUNTRYCODE));

For query topology and execution plan please run: EXPLAIN <QueryId>

Local runtime statistics
------------------------
messages-per-sec:         0   total-messages:       109     last-message: 2019-09-27T11:27:58.782Z

(Statistics of the local KSQL server interaction with the Kafka topic UP_JOINED)
```


`ksql>`
select * from up_joined;

```markdown
1569508462153 | IN | Bob JONES from India has a rating of 3.9 stars.
1569508463564 | GB | Bob SMITH from United Kingdom has a rating of 3.4 stars.
1569508465313 | IN | Grace DOTTY from India has a rating of 3.9 stars.
1569508466956 | US | Dan JONES from United States has a rating of 2.2 stars.
1569508470352 | IN | Frank SMITH from India has a rating of 4.9 stars.
1569508472067 | US | Bob JONES from United States has a rating of 4.9 stars.
1569508473330 | GB | Frank FAWCETT from United Kingdom has a rating of 4.4 stars.
```


#### section 5, lecture 15

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic COMPLAINTS_CSV

```markdown
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
```


`ksql>`
CREATE STREAM complaints_csv (customer_name VARCHAR , complaint_type VARCHAR, trip_cost DOUBLE, new_customer BOOLEAN)
    WITH (VALUE_FORMAT = 'DELIMITED', KAFKA_TOPIC = 'COMPLAINTS_CSV');

```markdown
 Message        
----------------
 Stream created 
----------------
```

`ksql>`
select * from complaints_csv;

kafka-console-producer --broker-list localhost:9092 --topic COMPLAINTS_CSV

```markdown
Alice, Late arrival, 43.10, true
```

`ksql>`
```markdown
1569585564032 | null | Alice |  Late arrival | 43.1 | false
```

```markdown
Alice, Bob and Carole, Bad driver, 43.10, true
```


confluent local log ksql-server

confluent local log ksql-server -f
```markdown
doesn't work
```


#### section 5, lecture 16

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic COMPLAINTS_JSON

```markdown
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
```


`ksql>`
CREATE STREAM complaints_json (customer_name VARCHAR , complaint_type VARCHAR, trip_cost DOUBLE, new_customer BOOLEAN)
    WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'COMPLAINTS_JSON');

```markdown
 Message        
----------------
 Stream created 
----------------
```


`ksql>`
select * from complaints_json;


kafka-console-producer --broker-list localhost:9092 --topic COMPLAINTS_JSON

```json
{"customer_name":"Alice, Bob and Carole", "complaint_type":"Bad driver", "trip_cost": 22.40, "new_customer": true}
```

```json
{"customer_name":"Bad Data", "complaint_type":"Bad driver", "trip_cost": 22.40, "new_customer": ShouldBeABoolean}
```


#### section 5, lecture 17

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

kafka-avro-console-producer  --broker-list localhost:9092 --topic COMPLAINTS_AVRO --property value.schema='
{
  "type": "record",
  "name": "myrecord",
  "fields": [
      {"name": "customer_name",  "type": "string" }
    , {"name": "complaint_type", "type": "string" }
    , {"name": "trip_cost", "type": "float" }
    , {"name": "new_customer", "type": "boolean"}
  ]
}'
{"customer_name":"Carol", "complaint_type":"Late arrival", "trip_cost": 19.60, "new_customer": false}


`ksql>`
list topics;

```markdown
 Kafka Topic                 | Registered | Partitions | Partition Replicas | Consumers | ConsumerGroups 
---------------------------------------------------------------------------------------------------------
 _confluent-metrics          | false      | 12         | 1                  | 0         | 0              
 _confluent-monitoring       | false      | 1          | 1                  | 0         | 0              
 _schemas                    | false      | 1          | 1                  | 0         | 0              
 COMPLAINTS_AVRO             | false      | 1          | 1                  | 0         | 0              
 COMPLAINTS_CSV              | true       | 1          | 1                  | 0         | 0              
 COMPLAINTS_JSON             | true       | 1          | 1                  | 0         | 0              
 COUNTRY-CSV                 | true       | 1          | 1                  | 1         | 1              
 default_ksql_processing_log | true       | 1          | 1                  | 0         | 0              
 UP_JOINED                   | true       | 1          | 1                  | 0         | 0              
 USER_PROFILE_PRETTY         | false      | 1          | 1                  | 0         | 0              
 USERPROFILE                 | true       | 1          | 1                  | 1         | 1              
---------------------------------------------------------------------------------------------------------
```


`ksql>`
print COMPLAINTS_AVRO;

`ksql>`
print COMPLAINTS_AVRO from beginning;

`ksql>`
create stream complaints_avro with (kafka_topic='COMPLAINTS_AVRO', value_format='AVRO');

```markdown
 Message        
----------------
 Stream created 
----------------
```


`ksql>`
select * from complaints_avro;


kafka-avro-console-producer  --broker-list localhost:9092 --topic COMPLAINTS_AVRO --property value.schema='
{
  "type": "record",
  "name": "myrecord",
  "fields": [
      {"name": "customer_name",  "type": "string" }
    , {"name": "complaint_type", "type": "string" }
    , {"name": "trip_cost", "type": "float" }
    , {"name": "new_customer", "type": "boolean"}
  ]
}'
{"customer_name":"Bad Data", "complaint_type":"Bad driver", "trip_cost": 22.40, "new_customer": ShouldBeABoolean}


#### section 5, lecture 18

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

ksql

`ksql>`
SET 'auto.offset.reset'='earliest';


confluent local status

confluent local start

confluent local status

confluent local stop

confluent local start

confluent local status


http://localhost:9021/clusters


kafka-avro-console-producer  --broker-list localhost:9092 --topic COMPLAINTS_AVRO --property value.schema='
{
  "type": "record",
  "name": "myrecord",
  "fields": [
      {"name": "customer_name",  "type": "string" }
    , {"name": "complaint_type", "type": "string" }
    , {"name": "trip_cost", "type": "float" }
    , {"name": "new_customer", "type": "boolean"}
    , {"name": "number_of_rides", "type": "int", "default" : 1}
  ]
}'

```json
{"customer_name":"Ed", "complaint_type":"Dirty car", "trip_cost": 29.10, "new_customer": false, "number_of_rides": 22}
```


curl -s -X GET http://localhost:8081/subjects/COMPLAINTS_AVRO-value/versions

curl -s -X GET http://localhost:8081/subjects/COMPLAINTS_AVRO-value/versions/1 | jq '.'

curl -s -X GET http://localhost:8081/subjects/COMPLAINTS_AVRO-value/versions/2 | jq '.'


`ksql>`
select * from complaints_avro;

`ksql>`
describe complaints_avro;

`ksql>`
create stream complaints_avro_v2 with (kafka_topic='COMPLAINTS_AVRO', value_format='AVRO');

`ksql>`
list streams;

`ksql>`
describe complaints_avro_v2;

`ksql>`
select * from complaints_avro_v2;


#### section 5, lecture 19

cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master

cat demo-weather.json | kafka-console-producer --broker-list localhost:9092 --topic WEATHERNESTED


`ksql>`
CREATE STREAM weather (
    city STRUCT <name VARCHAR, country VARCHAR, latitude DOUBLE, longitude DOUBLE>,
    description VARCHAR,
    clouds BIGINT,
    deg BIGINT,
    humidity BIGINT,
    pressure DOUBLE,
    rain DOUBLE
) WITH (KAFKA_TOPIC='WEATHERNESTED', VALUE_FORMAT='JSON');

```markdown
 Message        
----------------
 Stream created 
----------------
```


`ksql>`
select * from weather;

```markdown
1569705650823 | null | {NAME=Sydney, COUNTRY=AU, LATITUDE=-33.8688, LONGITUDE=151.2093} | light rain | 92 | 26 | 94 | 1025.12 | 1.25
1569705650831 | null | {NAME=Seattle, COUNTRY=US, LATITUDE=47.6062, LONGITUDE=-122.3321} | heavy rain | 92 | 19 | 94 | 1025.12 | 7.0
1569705650831 | null | {NAME=San Francisco, COUNTRY=US, LATITUDE=37.7749, LONGITUDE=-122.4194} | fog | 92 | 19 | 94 | 1025.12 | 10.0
1569705650831 | null | {NAME=San Jose, COUNTRY=US, LATITUDE=37.3382, LONGITUDE=-121.8863} | light rain | 92 | 23 | 94 | 1025.12 | 3.0
1569705650831 | null | {NAME=Fresno, COUNTRY=US, LATITUDE=36.7378, LONGITUDE=-119.7871} | heavy rain | 92 | 22 | 94 | 1025.12 | 6.0
1569705650831 | null | {NAME=Los Angeles, COUNTRY=US, LATITUDE=34.0522, LONGITUDE=-118.2437} | haze | 92 | 19 | 94 | 1025.12 | 2.0
1569705650831 | null | {NAME=San Diego, COUNTRY=US, LATITUDE=32.7157, LONGITUDE=-117.1611} | fog | 92 | 19 | 94 | 1025.12 | 2.0
1569705650831 | null | {NAME=Birmingham, COUNTRY=UK, LATITUDE=52.4862, LONGITUDE=-1.8904} | light rain | 92 | 26 | 94 | 1025.12 | 4.0
1569705650831 | null | {NAME=London, COUNTRY=GB, LATITUDE=51.5074, LONGITUDE=-0.1278} | heavy rain | 92 | 19 | 94 | 1025.12 | 8.0
1569705650831 | null | {NAME=Manchester, COUNTRY=GB, LATITUDE=53.4808, LONGITUDE=-2.2426} | fog | 92 | 26 | 94 | 1025.12 | 3.0
1569705650831 | null | {NAME=Bristol, COUNTRY=GB, LATITUDE=51.4545, LONGITUDE=-2.5879} | light rain | 92 | 19 | 94 | 1025.12 | 3.0
1569705650831 | null | {NAME=Newcastle, COUNTRY=GB, LATITUDE=54.9783, LONGITUDE=-1.6178} | heavy rain | 92 | 19 | 94 | 1025.12 | 12.0
1569705650831 | null | {NAME=Liverpool, COUNTRY=GB, LATITUDE=53.4084, LONGITUDE=-2.9916} | haze | 92 | 23 | 94 | 1025.12 | 3.0
```


`ksql>`
describe weather;

```markdown
Name                 : WEATHER
 Field       | Type                                                                                     
--------------------------------------------------------------------------------------------------------
 ROWTIME     | BIGINT           (system)                                                                
 ROWKEY      | VARCHAR(STRING)  (system)                                                                
 CITY        | STRUCT<NAME VARCHAR(STRING), COUNTRY VARCHAR(STRING), LATITUDE DOUBLE, LONGITUDE DOUBLE> 
 DESCRIPTION | VARCHAR(STRING)                                                                          
 CLOUDS      | BIGINT                                                                                   
 DEG         | BIGINT                                                                                   
 HUMIDITY    | BIGINT                                                                                   
 PRESSURE    | DOUBLE                                                                                   
 RAIN        | DOUBLE                                                                                   
--------------------------------------------------------------------------------------------------------
For runtime statistics and query details run: DESCRIBE EXTENDED <Stream,Table>;
```


`ksql>`
SELECT city->name AS city_name, city->country AS city_country, city->latitude as latitude, city->longitude as longitude, description, rain from weather;

```markdown
Sydney | AU | -33.8688 | 151.2093 | light rain | 1.25
Seattle | US | 47.6062 | -122.3321 | heavy rain | 7.0
San Francisco | US | 37.7749 | -122.4194 | fog | 10.0
San Jose | US | 37.3382 | -121.8863 | light rain | 3.0
Fresno | US | 36.7378 | -119.7871 | heavy rain | 6.0
Los Angeles | US | 34.0522 | -118.2437 | haze | 2.0
San Diego | US | 32.7157 | -117.1611 | fog | 2.0
Birmingham | UK | 52.4862 | -1.8904 | light rain | 4.0
London | GB | 51.5074 | -0.1278 | heavy rain | 8.0
Manchester | GB | 53.4808 | -2.2426 | fog | 3.0
Bristol | GB | 51.4545 | -2.5879 | light rain | 3.0
Newcastle | GB | 54.9783 | -1.6178 | heavy rain | 12.0
Liverpool | GB | 53.4084 | -2.9916 | haze | 3.0
```


#### section 5, lecture 20

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
list streams;

```markdown
 Stream Name         | Kafka Topic                 | Format    
---------------------------------------------------------------
 COMPLAINTS_CSV      | COMPLAINTS_CSV              | DELIMITED 
 WEATHER             | WEATHERNESTED               | JSON      
 KSQL_PROCESSING_LOG | default_ksql_processing_log | JSON      
 UP_JOINED           | UP_JOINED                   | JSON      
 COMPLAINTS_AVRO     | COMPLAINTS_AVRO             | AVRO      
 USERPROFILE         | USERPROFILE                 | JSON      
 COMPLAINTS_AVRO_V2  | COMPLAINTS_AVRO             | AVRO      
 COMPLAINTS_JSON     | COMPLAINTS_JSON             | JSON      
---------------------------------------------------------------
```


`ksql>`
create stream weatherraw with (value_format='AVRO') as
    SELECT city->name AS city_name, city->country AS city_country, city->latitude as latitude, city->longitude as longitude, description, rain from weather;

```markdown
 Message                    
----------------------------
 Stream created and running 
----------------------------
```


`ksql>`
list streams;

```markdown
 Stream Name         | Kafka Topic                 | Format    
---------------------------------------------------------------
 COMPLAINTS_CSV      | COMPLAINTS_CSV              | DELIMITED 
 WEATHER             | WEATHERNESTED               | JSON      
 KSQL_PROCESSING_LOG | default_ksql_processing_log | JSON      
 UP_JOINED           | UP_JOINED                   | JSON      
 COMPLAINTS_AVRO     | COMPLAINTS_AVRO             | AVRO      
 USERPROFILE         | USERPROFILE                 | JSON      
 COMPLAINTS_AVRO_V2  | COMPLAINTS_AVRO             | AVRO      
 WEATHERRAW          | WEATHERRAW                  | AVRO      
 COMPLAINTS_JSON     | COMPLAINTS_JSON             | JSON      
---------------------------------------------------------------
```


`ksql>`
describe extended weatherraw;

```markdown
Name                 : WEATHERRAW
Type                 : STREAM
Key field            : 
Key format           : STRING
Timestamp field      : Not set - using <ROWTIME>
Value format         : AVRO
Kafka topic          : WEATHERRAW (partitions: 1, replication: 1)

 Field        | Type                      
------------------------------------------
 ROWTIME      | BIGINT           (system) 
 ROWKEY       | VARCHAR(STRING)  (system) 
 CITY_NAME    | VARCHAR(STRING)           
 CITY_COUNTRY | VARCHAR(STRING)           
 LATITUDE     | DOUBLE                    
 LONGITUDE    | DOUBLE                    
 DESCRIPTION  | VARCHAR(STRING)           
 RAIN         | DOUBLE                    
------------------------------------------

Queries that write into this STREAM
-----------------------------------
CSAS_WEATHERRAW_2 : CREATE STREAM WEATHERRAW WITH (REPLICAS = 1, PARTITIONS = 1, VALUE_FORMAT = 'AVRO', KAFKA_TOPIC = 'WEATHERRAW') AS SELECT
  FETCH_FIELD_FROM_STRUCT(WEATHER.CITY, 'NAME') "CITY_NAME"
, FETCH_FIELD_FROM_STRUCT(WEATHER.CITY, 'COUNTRY') "CITY_COUNTRY"
, FETCH_FIELD_FROM_STRUCT(WEATHER.CITY, 'LATITUDE') "LATITUDE"
, FETCH_FIELD_FROM_STRUCT(WEATHER.CITY, 'LONGITUDE') "LONGITUDE"
, WEATHER.DESCRIPTION "DESCRIPTION"
, WEATHER.RAIN "RAIN"
FROM WEATHER WEATHER;

For query topology and execution plan please run: EXPLAIN <QueryId>

Local runtime statistics
------------------------
messages-per-sec:         0   total-messages:        13     last-message: 2019-09-29T10:46:28.055Z

(Statistics of the local KSQL server interaction with the Kafka topic WEATHERRAW)
```


`ksql>`
select rowkey, city_name from weatherraw;

```markdown
null | Sydney
null | Seattle
null | San Francisco
null | San Jose
null | Fresno
null | Los Angeles
null | San Diego
null | Birmingham
null | London
null | Manchester
null | Bristol
null | Newcastle
null | Liverpool
```


`ksql>`
create stream weatherrekeyed as select * from weatherraw partition by city_name;

```markdown
 Message                    
----------------------------
 Stream created and running 
----------------------------
```


`ksql>`
describe extended weatherrekeyed;

```markdown
Name                 : WEATHERREKEYED
Type                 : STREAM
Key field            : CITY_NAME
Key format           : STRING
Timestamp field      : Not set - using <ROWTIME>
Value format         : AVRO
Kafka topic          : WEATHERREKEYED (partitions: 1, replication: 1)

 Field        | Type                      
------------------------------------------
 ROWTIME      | BIGINT           (system) 
 ROWKEY       | VARCHAR(STRING)  (system) 
 CITY_NAME    | VARCHAR(STRING)           
 CITY_COUNTRY | VARCHAR(STRING)           
 LATITUDE     | DOUBLE                    
 LONGITUDE    | DOUBLE                    
 DESCRIPTION  | VARCHAR(STRING)           
 RAIN         | DOUBLE                    
------------------------------------------

Queries that write into this STREAM
-----------------------------------
CSAS_WEATHERREKEYED_3 : CREATE STREAM WEATHERREKEYED WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'WEATHERREKEYED') AS SELECT *
FROM WEATHERRAW WEATHERRAW
PARTITION BY CITY_NAME;

For query topology and execution plan please run: EXPLAIN <QueryId>

Local runtime statistics
------------------------
messages-per-sec:      0.13   total-messages:        13     last-message: 2019-09-29T10:53:00.443Z

(Statistics of the local KSQL server interaction with the Kafka topic WEATHERREKEYED)
```


`ksql>`
select rowkey, city_name from weatherrekeyed;

```markdown
Sydney | Sydney
Seattle | Seattle
San Francisco | San Francisco
San Jose | San Jose
Fresno | Fresno
Los Angeles | Los Angeles
San Diego | San Diego
Birmingham | Birmingham
London | London
Manchester | Manchester
Bristol | Bristol
Newcastle | Newcastle
Liverpool | Liverpool
```


`ksql>`
create table weathernow with (kafka_topic='WEATHERREKEYED', value_format='AVRO', key='CITY_NAME');

```markdown
 Message       
---------------
 Table created 
---------------
```


`ksql>`
select * from weathernow;

```markdown
1569705650823 | Sydney | Sydney | AU | -33.8688 | 151.2093 | light rain | 1.25
1569705650831 | Seattle | Seattle | US | 47.6062 | -122.3321 | heavy rain | 7.0
1569705650831 | San Francisco | San Francisco | US | 37.7749 | -122.4194 | fog | 10.0
1569705650831 | San Jose | San Jose | US | 37.3382 | -121.8863 | light rain | 3.0
1569705650831 | Fresno | Fresno | US | 36.7378 | -119.7871 | heavy rain | 6.0
1569705650831 | Los Angeles | Los Angeles | US | 34.0522 | -118.2437 | haze | 2.0
1569705650831 | San Diego | San Diego | US | 32.7157 | -117.1611 | fog | 2.0
1569705650831 | Birmingham | Birmingham | UK | 52.4862 | -1.8904 | light rain | 4.0
1569705650831 | London | London | GB | 51.5074 | -0.1278 | heavy rain | 8.0
1569705650831 | Manchester | Manchester | GB | 53.4808 | -2.2426 | fog | 3.0
1569705650831 | Bristol | Bristol | GB | 51.4545 | -2.5879 | light rain | 3.0
1569705650831 | Newcastle | Newcastle | GB | 54.9783 | -1.6178 | heavy rain | 12.0
1569705650831 | Liverpool | Liverpool | GB | 53.4084 | -2.9916 | haze | 3.0
```


`ksql>`
select * from weathernow where city_name = 'San Diego';

```markdown
1569705650831 | San Diego | San Diego | US | 32.7157 | -117.1611 | fog | 2.0
```


cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master

cat demo-weather-changes.json | kafka-console-producer --broker-list localhost:9092 --topic WEATHERNESTED


`ksql>`
select * from weathernow where city_name = 'San Diego';


```markdown
1569755035454 | San Diego | San Diego | US | 32.7157 | -117.1611 | SUNNY | 2.0
```


#### section 5, lecture 21

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master

kafka-topics --bootstrap-server localhost:9092 --create --partitions 2 --replication-factor 1 --topic DRIVER_PROFILE

```markdown
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
```


kafka-console-producer --broker-list localhost:9092 --topic DRIVER_PROFILE

```json
{"driver_name":"Mr. Speedy", "countrycode":"AU", "rating":2.4}
```


ksql

`ksql>`
SET 'auto.offset.reset'='earliest';

`ksql>`
CREATE STREAM DRIVER_PROFILE (driver_name VARCHAR, countrycode VARCHAR, rating DOUBLE)
    WITH (VALUE_FORMAT='JSON', KAFKA_TOPIC='DRIVER_PROFILE');

```markdown
 Message        
----------------
 Stream created 
----------------
```


`ksql>`
select dp.driver_name, ct.countryname, dp.rating
    from DRIVER_PROFILE dp
    left join COUNTRYTABLE ct on ct.countrycode = dp.countrycode;

```markdown
Can't join DRIVER_PROFILE with COUNTRYTABLE since the number of partitions don't match. DRIVER_PROFILE partitions = 2; COUNTRYTABLE partitions = 1. Please repartition either one so that the number of partitions match.
Statement: select dp.driver_name, ct.countryname, dp.rating
    from DRIVER_PROFILE dp
    left join COUNTRYTABLE ct on ct.countrycode = dp.countrycode;
Caused by: Can't join DRIVER_PROFILE with COUNTRYTABLE since the number of
	partitions don't match. DRIVER_PROFILE partitions = 2; COUNTRYTABLE partitions =
	1. Please repartition either one so that the number of partitions match.
```


`ksql>`
create stream driverprofile_rekeyed with (partitions=1) as select * from DRIVER_PROFILE partition by driver_name;

```markdown
 Message                    
----------------------------
 Stream created and running 
----------------------------
```


`ksql>`
describe extended driver_profile;

```markdown
Name                 : DRIVER_PROFILE
Type                 : STREAM
Key field            : 
Key format           : STRING
Timestamp field      : Not set - using <ROWTIME>
Value format         : JSON
Kafka topic          : DRIVER_PROFILE (partitions: 2, replication: 1)

 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 DRIVER_NAME | VARCHAR(STRING)           
 COUNTRYCODE | VARCHAR(STRING)           
 RATING      | DOUBLE                    
-----------------------------------------

Local runtime statistics
------------------------
consumer-messages-per-sec:      0.01 consumer-total-bytes:        61 consumer-total-messages:         1     last-message: 2019-09-30T09:59:43.188Z

(Statistics of the local KSQL server interaction with the Kafka topic DRIVER_PROFILE)
```


`ksql>`
describe extended driverprofile_rekeyed;

```markdown
Name                 : DRIVERPROFILE_REKEYED
Type                 : STREAM
Key field            : DRIVER_NAME
Key format           : STRING
Timestamp field      : Not set - using <ROWTIME>
Value format         : JSON
Kafka topic          : DRIVERPROFILE_REKEYED (partitions: 1, replication: 1)

 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 DRIVER_NAME | VARCHAR(STRING)           
 COUNTRYCODE | VARCHAR(STRING)           
 RATING      | DOUBLE                    
-----------------------------------------

Queries that write into this STREAM
-----------------------------------
CSAS_DRIVERPROFILE_REKEYED_4 : CREATE STREAM DRIVERPROFILE_REKEYED WITH (KAFKA_TOPIC = 'DRIVERPROFILE_REKEYED', REPLICAS = 1, PARTITIONS = 1) AS SELECT *
FROM DRIVER_PROFILE DRIVER_PROFILE
PARTITION BY DRIVER_NAME;

For query topology and execution plan please run: EXPLAIN <QueryId>

Local runtime statistics
------------------------
messages-per-sec:         0   total-messages:         1     last-message: 2019-09-30T09:59:43.188Z

(Statistics of the local KSQL server interaction with the Kafka topic DRIVERPROFILE_REKEYED)
```


`ksql>`
select dp2.driver_name, ct.countryname, dp2.rating
    from DRIVERPROFILE_REKEYED dp2
    left join COUNTRYTABLE ct on ct.countrycode = dp2.countrycode;

```markdown
Mr. Speedy | Australia | 2.4
```


#### section 5, lecture 22

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master

`terminal #1`
ksql-datagen schema=./datagen/riderequest-europe.avro format=avro topic=riderequest-europe key=rideid maxInterval=5000 iterations=10000

`terminal #2`
ksql-datagen schema=./datagen/riderequest-america.avro format=avro topic=riderequest-america key=rideid maxInterval=5000 iterations=10000


`ksql>`
create stream rr_america_raw with (kafka_topic='riderequest-america', value_format='avro');

```markdown
 Message        
----------------
 Stream created 
----------------
```


`ksql>`
create stream rr_europe_raw with (kafka_topic='riderequest-europe', value_format='avro');

```markdown
 Message        
----------------
 Stream created 
----------------
```


`ksql>`
select * from rr_america_raw;

```markdown
1569877209385 | ride_919 | 1569877208980 | 41.89619374588752 | -106.29139265923145 | ride_919 | Wendy | Seattle
1569877212153 | ride_228 | 1569877212153 | 44.479693451566106 | -114.36813937507324 | ride_228 | Niaj | San Francisco
1569877215456 | ride_146 | 1569877215455 | 37.72891168387186 | -107.9027179323889 | ride_146 | Judy | San Diego
1569877219761 | ride_771 | 1569877219761 | 42.413920324005886 | -116.03196440320018 | ride_771 | Mike | Seattle
```


`ksql>`
select * from rr_europe_raw;

```markdown
1569877203146 | ride_176 | 1569877202697 | 51.989351598063095 | 1.5244433018606065 | ride_176 | Carol | Newcastle
1569877204763 | ride_187 | 1569877204763 | 53.35268098469431 | 1.8985104513063682 | ride_187 | Bob | Newcastle
1569877208299 | ride_712 | 1569877208298 | 51.93726956674109 | -0.5684191907781968 | ride_712 | Eve | Birmingham
1569877209170 | ride_458 | 1569877209169 | 52.55164715749092 | 0.5727971739495938 | ride_458 | Heidi | London
```


`ksql>`
create stream rr_word as select 'Europe' as data_source, * from rr_europe_raw;

```markdown
 Message                    
----------------------------
 Stream created and running 
----------------------------
```


`ksql>`
insert into rr_word select 'Americas' as data_source, * from rr_america_raw;

```markdown
 Message                       
-------------------------------
 Insert Into query is running. 
-------------------------------
```


`ksql>`
select * from rr_word;

```markdown
1569877203146 | ride_176 | Europe | 1569877202697 | 51.989351598063095 | 1.5244433018606065 | ride_176 | Carol | Newcastle
1569877204763 | ride_187 | Europe | 1569877204763 | 53.35268098469431 | 1.8985104513063682 | ride_187 | Bob | Newcastle
1569877208299 | ride_712 | Europe | 1569877208298 | 51.93726956674109 | -0.5684191907781968 | ride_712 | Eve | Birmingham
1569877209170 | ride_458 | Europe | 1569877209169 | 52.55164715749092 | 0.5727971739495938 | ride_458 | Heidi | London

1569877209385 | ride_919 | Americas | 1569877208980 | 41.89619374588752 | -106.29139265923145 | ride_919 | Wendy | Seattle
1569877212153 | ride_228 | Americas | 1569877212153 | 44.479693451566106 | -114.36813937507324 | ride_228 | Niaj | San Francisco
1569877215456 | ride_146 | Americas | 1569877215455 | 37.72891168387186 | -107.9027179323889 | ride_146 | Judy | San Diego
1569877219761 | ride_771 | Americas | 1569877219761 | 42.413920324005886 | -116.03196440320018 | ride_771 | Mike | Seattle
```


#### section 5, lecture 23

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
select data_source, city_name, count(*)
    from rr_word
    window tumbling (size 60 seconds)
    group by data_source, city_name;

```markdown
Europe | London | 13
Europe | Birmingham | 2
Europe | Manchester | 1
Americas | San Diego | 3
Europe | Bristol | 2
Americas | San Francisco | 14
Americas | Seattle | 2
Europe | Liverpool | 2
Americas | San Francisco | 15
Europe | Newcastle | 2
Americas | San Francisco | 1
Europe | London | 1
Europe | Manchester | 1
Europe | Bristol | 1
Americas | Seattle | 1
Europe | London | 2
Americas | San Francisco | 2
Europe | Bristol | 2
Americas | San Francisco | 3
Europe | Manchester | 2
Americas | San Francisco | 4
```


`ksql>`
select data_source, city_name, COLLECT_LIST(user)
    from rr_word
    window tumbling (size 60 seconds)
    group by data_source, city_name;

```markdown
Europe | London | [Eve, Eve, Alice, Carol, Ivan, Alice, Dan, Dan, Frank, Alice, Ivan, Eve, Dan]
Europe | Newcastle | [Frank]
Europe | Manchester | [Grace]
Americas | San Diego | [Walter, Walter]
Europe | Bristol | [Bob, Dan, Grace, Bob, Alice]
Europe | Birmingham | [Carol]
Americas | San Jose | [Ted, Walter, Ted, Trudy, Wendy]
Americas | Seattle | [Oscar]
Europe | Liverpool | [Ivan, Frank, Alice]
Americas | Los Angeles | [Walter, Judy]
Europe | London | [Eve, Frank, Alice, Heidi, Dan, Eve, Grace, Dan, Heidi, Carol, Frank, Alice, Dan, Ivan, Bob]
Americas | San Francisco | [Trudy, Peggy, Peggy, Oscar, Sybil, Sybil, Wendy, Wendy, Wendy, Niaj, Wendy, Walter, Wendy, Walter, Trudy, Ted]
Europe | Manchester | [Bob]
Americas | Seattle | [Wendy]
Americas | San Diego | [Oscar, Trudy]
Americas | San Jose | [Peggy, Wendy]
```


`ksql>`
select TIMESTAMPTOSTRING(WindowStart(), 'HH:mm:ss'),
       TIMESTAMPTOSTRING(WindowEnd(), 'HH:mm:ss'),
       data_source,
       TOPK(city_name, 3),
       count(*)
    from rr_word
    WINDOW TUMBLING (SIZE 1 minute)
    GROUP BY data_source;

```markdown
00:00:00 | 00:01:00 | Europe | [Newcastle, Newcastle, Newcastle] | 28
00:01:00 | 00:02:00 | Europe | [Newcastle, Newcastle, Newcastle] | 26
00:00:00 | 00:01:00 | Americas | [Seattle, Seattle, Seattle] | 22
00:01:00 | 00:02:00 | Americas | [Seattle, Seattle, Seattle] | 26
00:02:00 | 00:03:00 | Europe | [Newcastle, Manchester, London] | 22
00:02:00 | 00:03:00 | Americas | [Seattle, Seattle, Seattle] | 25
00:03:00 | 00:04:00 | Americas | [San Jose, San Jose, San Jose] | 19
00:03:00 | 00:04:00 | Europe | [Newcastle, Manchester, London] | 29
00:04:00 | 00:05:00 | Americas | [Seattle, Seattle, San Francisco] | 22
00:04:00 | 00:05:00 | Europe | [Newcastle, Newcastle, Manchester] | 23
00:05:00 | 00:06:00 | Americas | [Seattle, Seattle, Seattle] | 25
00:05:00 | 00:06:00 | Europe | [Newcastle, Newcastle, London] | 23
00:06:00 | 00:07:00 | Americas | [Seattle, San Jose, San Jose] | 25
00:06:00 | 00:07:00 | Europe | [Manchester, London, London] | 23
00:07:00 | 00:08:00 | Europe | [Newcastle, Newcastle, Newcastle] | 21
00:07:00 | 00:08:00 | Americas | [San Jose, San Jose, San Francisco] | 28
00:08:00 | 00:09:00 | Europe | [London, London, London] | 22
00:08:00 | 00:09:00 | Americas | [Seattle, Seattle, Seattle] | 23
00:09:00 | 00:10:00 | Europe | [Newcastle, Manchester, Manchester] | 24
00:09:00 | 00:10:00 | Americas | [Seattle, Seattle, Seattle] | 27
00:10:00 | 00:11:00 | Americas | [San Jose, San Jose, San Jose] | 23
00:10:00 | 00:11:00 | Europe | [Newcastle, Manchester, London] | 26
00:11:00 | 00:12:00 | Americas | [Seattle, San Jose, San Francisco] | 21
00:11:00 | 00:12:00 | Europe | [Newcastle, Newcastle, Newcastle] | 31
00:12:00 | 00:13:00 | Americas | [Seattle, Seattle, San Francisco] | 23
00:12:00 | 00:13:00 | Europe | [Newcastle, Newcastle, Manchester] | 22
00:13:00 | 00:14:00 | Americas | [Seattle, Seattle, San Jose] | 29
```


#### section 5, lecture 24

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
select * from rr_word;

```markdown
1569878755530 | ride_874 | Americas | 1569878755530 | 42.087376648417646 | -118.68475095319592 | ride_874 | Wendy | San Francisco
1569878757333 | ride_770 | Europe | 1569878757333 | 51.44288149211938 | 1.9120547789921596 | ride_770 | Eve | London
1569878760297 | ride_524 | Americas | 1569878760297 | 40.20963989997225 | -106.93109650993992 | ride_524 | Ted | San Diego
1569878761430 | ride_433 | Europe | 1569878761430 | 50.711828034715545 | -0.7968568885320106 | ride_433 | Carol | Newcastle
```


`ksql>`
describe rr_word;

```markdown
Name                 : RR_WORD
 Field       | Type                      
-----------------------------------------
 ROWTIME     | BIGINT           (system) 
 ROWKEY      | VARCHAR(STRING)  (system) 
 DATA_SOURCE | VARCHAR(STRING)           
 REQUESTTIME | BIGINT                    
 LATITUDE    | DOUBLE                    
 LONGITUDE   | DOUBLE                    
 RIDEID      | VARCHAR(STRING)           
 USER        | VARCHAR(STRING)           
 CITY_NAME   | VARCHAR(STRING)           
-----------------------------------------
For runtime statistics and query details run: DESCRIBE EXTENDED <Stream,Table>;
```


`ksql>`
create stream requested_journey as
    select rr.latitude as from_latitude,
           rr.longitude as from_longitude,
           rr.user,
           rr.city_name as city_name,
           w.city_country,
           w.latitude as to_latitude,
           w.longitude as to_longitude,
           w.description as weather_description,
           w.rain
    from rr_word rr
    left join weathernow w on rr.city_name = w.city_name;

```markdown
 Message                    
----------------------------
 Stream created and running 
----------------------------
```


`ksql>`
select * from requested_journey;

```markdown
1569879295041 | Los Angeles | 44.12888251597481 | -118.79168065220374 | Sybil | Los Angeles | US | 34.0522 | -118.2437 | haze | 2.0
1569879295072 | London | 51.728036848997 | 1.9217193102365546 | Eve | London | GB | 51.5074 | -0.1278 | heavy rain | 8.0
1569879296604 | London | 53.434572666603806 | 0.7518067856543964 | Heidi | London | GB | 51.5074 | -0.1278 | heavy rain | 8.0
1569879298087 | San Francisco | 44.389069059203756 | -119.64122676982434 | Wendy | San Francisco | US | 37.7749 | -122.4194 | SUNNY | 10.0
1569879298249 | Bristol | 50.19125513292464 | 0.48015118999664086 | Heidi | Bristol | GB | 51.4545 | -2.5879 | light rain | 3.0
1569879299722 | San Diego | 42.327649864755664 | -106.39592425868776 | Ted | San Diego | US | 32.7157 | -117.1611 | SUNNY | 2.0
1569879300132 | London | 51.472950587764174 | -0.6259174747327707 | Eve | London | GB | 51.5074 | -0.1278 | heavy rain | 8.0
1569879301480 | San Francisco | 38.463525099933314 | -115.52130464268086 | Sybil | San Francisco | US | 37.7749 | -122.4194 | SUNNY | 10.0
1569879301946 | San Francisco | 39.77074842676313 | -103.15390241684696 | Ted | San Francisco | US | 37.7749 | -122.4194 | SUNNY | 10.0
1569879304113 | Seattle | 42.268040429285605 | -108.12409155396625 | Walter | Seattle | US | 47.6062 | -122.3321 | heavy rain | 7.0
1569879304198 | San Jose | 41.55423321818248 | -119.67542996833639 | Oscar | San Jose | US | 37.3382 | -121.8863 | light rain | 3.0
1569879304947 | London | 49.529885033364955 | 1.58612003508714 | Ivan | London | GB | 51.5074 | -0.1278 | heavy rain | 8.0
```


`ksql>`
describe requested_journey;

```markdown
Name                 : REQUESTED_JOURNEY
 Field               | Type                      
-------------------------------------------------
 ROWTIME             | BIGINT           (system) 
 ROWKEY              | VARCHAR(STRING)  (system) 
 FROM_LATITUDE       | DOUBLE                    
 FROM_LONGITUDE      | DOUBLE                    
 USER                | VARCHAR(STRING)           
 CITY_NAME           | VARCHAR(STRING)           
 CITY_COUNTRY        | VARCHAR(STRING)           
 TO_LATITUDE         | DOUBLE                    
 TO_LONGITUDE        | DOUBLE                    
 WEATHER_DESCRIPTION | VARCHAR(STRING)           
 RAIN                | DOUBLE                    
-------------------------------------------------
For runtime statistics and query details run: DESCRIBE EXTENDED <Stream,Table>;
```


`ksql>`
select user,
       city_name,
       city_country,
       weather_description,
       rain,
       GEO_DISTANCE(from_latitude, from_longitude, to_latitude, to_longitude, 'km') as dist
    from requested_journey;

```markdown
Carol | London | GB | heavy rain | 8.0 | 57.14218225524198
Oscar | Seattle | US | heavy rain | 7.0 | 1240.8254500489131
Peggy | San Francisco | US | SUNNY | 10.0 | 568.9718989203743
Judy | San Francisco | US | SUNNY | 10.0 | 1460.1856014867678
Carol | Birmingham | UK | light rain | 4.0 | 179.55908884881953
Bob | London | GB | heavy rain | 8.0 | 95.01663803611274
Trudy | San Jose | US | light rain | 3.0 | 533.2588422483718
Walter | San Francisco | US | SUNNY | 10.0 | 776.5003420441011
Walter | San Francisco | US | SUNNY | 10.0 | 1094.7035413843184
Grace | Birmingham | UK | light rain | 4.0 | 306.9242424376605
Walter | Fresno | US | heavy rain | 6.0 | 1256.9108246905364
Sybil | Seattle | US | heavy rain | 7.0 | 1594.3245754368531
Eve | Bristol | GB | light rain | 3.0 | 263.69413021477385
```


`ksql>`
create stream ridetodest as
    select user,
           city_name,
           city_country,
           weather_description,
           rain,
           GEO_DISTANCE(from_latitude, from_longitude, to_latitude, to_longitude, 'km') as dist
    from requested_journey;

```markdown
 Message                    
----------------------------
 Stream created and running 
----------------------------
```


`ksql>`
select * from ridetodest;

```markdown
1569879876257 | London | Ivan | London | GB | heavy rain | 8.0 | 100.0269754616705
1569879878133 | London | Alice | London | GB | heavy rain | 8.0 | 93.2275465858676
1569879879702 | Birmingham | Heidi | Birmingham | UK | light rain | 4.0 | 172.51949260282214
1569879880313 | San Jose | Sybil | San Jose | US | light rain | 3.0 | 1709.3101707257324
1569879881397 | London | Alice | London | GB | heavy rain | 8.0 | 131.83233957505269
1569879884703 | London | Bob | London | GB | heavy rain | 8.0 | 62.53597797616088
1569879884796 | San Francisco | Trudy | San Francisco | US | SUNNY | 10.0 | 1211.1700557225993
1569879887800 | San Jose | Oscar | San Jose | US | light rain | 3.0 | 1324.94117724489
1569879887882 | London | Heidi | London | GB | heavy rain | 8.0 | 147.2187574583526
1569879888901 | London | Ivan | London | GB | heavy rain | 8.0 | 256.500142011494
1569879891763 | London | Carol | London | GB | heavy rain | 8.0 | 163.66014630509852
```


`ksql>`
select user
    || ' is travelling '
    || cast(round(dist) as varchar)
    || ' km to '
    || city_name
    || ' where the weather is reported as '
    || weather_description
    from ridetodest;

```markdown
Frank is travelling 194 km to Birmingham where the weather is reported as light rain
Sybil is travelling 1004 km to Fresno where the weather is reported as heavy rain
Eve is travelling 123 km to London where the weather is reported as heavy rain
Sybil is travelling 1058 km to San Francisco where the weather is reported as SUNNY
Dan is travelling 171 km to London where the weather is reported as heavy rain
Walter is travelling 1312 km to San Jose where the weather is reported as light rain
Carol is travelling 404 km to Liverpool where the weather is reported as haze
Bob is travelling 111 km to London where the weather is reported as heavy rain
Wendy is travelling 600 km to San Francisco where the weather is reported as SUNNY
Trudy is travelling 334 km to San Francisco where the weather is reported as SUNNY
Carol is travelling 92 km to London where the weather is reported as heavy rain
Trudy is travelling 1749 km to San Diego where the weather is reported as SUNNY
Oscar is travelling 1563 km to San Francisco where the weather is reported as SUNNY
```


#### section 6, lecture 25

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
LIST PROPERTIES;

```markdown
 Property                                               | Default override | Effective Value                                                                                    
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 ksql.access.validator.enable                           |                  | auto                                                                                               
 ksql.avro.maps.named                                   |                  | true                                                                                               
 ksql.extension.dir                                     |                  | ext                                                                                                
 ksql.functions.substring.legacy.args                   |                  | false                                                                                              
 ksql.insert.into.values.enabled                        |                  | true                                                                                               
 ksql.internal.topic.replicas                           |                  | 1                                                                                                  
 ksql.metrics.extension                                 |                  | NULL                                                                                               
 ksql.metrics.tags.custom                               |                  |                                                                                                    
 ksql.named.internal.topics                             |                  | on                                                                                                 
 ksql.output.topic.name.prefix                          |                  |                                                                                                    
 ksql.persistent.prefix                                 |                  | query_                                                                                             
 ksql.query.fields.key.legacy                           |                  | false                                                                                              
 ksql.query.persistent.active.limit                     |                  | 2147483647                                                                                         
 ksql.schema.registry.url                               |                  | http://localhost:8081                                                                              
 ksql.security.extension.class                          |                  | NULL                                                                                               
 ksql.service.id                                        |                  | default_                                                                                           
 ksql.sink.partitions                                   |                  | NULL                                                                                               
 ksql.sink.replicas                                     |                  | NULL                                                                                               
 ksql.sink.window.change.log.additional.retention       |                  | 1000000                                                                                            
 ksql.streams.application.id                            | SERVER           | KSQL_REST_SERVER_DEFAULT_APP_ID                                                                    
 ksql.streams.auto.offset.reset                         | SESSION          | earliest                                                                                           
 ksql.streams.bootstrap.servers                         | SERVER           | localhost:9092                                                                                     
 ksql.streams.cache.max.bytes.buffering                 | SERVER           | 10000000                                                                                           
 ksql.streams.commit.interval.ms                        | SERVER           | 2000                                                                                               
 ksql.streams.consumer.interceptor.classes              | SERVER           | io.confluent.monitoring.clients.interceptor.MonitoringConsumerInterceptor                          
 ksql.streams.default.deserialization.exception.handler | SERVER           | io.confluent.ksql.errors.LogMetricAndContinueExceptionHandler                                      
 ksql.streams.default.production.exception.handler      | SERVER           | io.confluent.ksql.errors.ProductionExceptionHandlerUtil$LogAndFailProductionExceptionHandler       
 ksql.streams.num.stream.threads                        | SERVER           | 4                                                                                                  
 ksql.streams.producer.interceptor.classes              | SERVER           | io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor                          
 ksql.streams.state.dir                                 | SERVER           | /var/folders/hf/kcy8xkw11_lg76v2_8q41rr00000gn/T/confluent.024FnuQC/ksql-server/data/kafka-streams 
 ksql.streams.topology.optimization                     | SERVER           | all                                                                                                
 ksql.transient.prefix                                  |                  | transient_                                                                                         
 ksql.udf.collect.metrics                               |                  | false                                                                                              
 ksql.udf.enable.security.manager                       |                  | true                                                                                               
 ksql.udfs.enabled                                      |                  | true                                                                                               
 ksql.windowed.session.key.legacy                       |                  | false                                                                                              
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
```


confluent local status

```markdown
    The local commands are intended for a single-node development environment
    only, NOT for production usage. https://docs.confluent.io/current/cli/index.html

control-center is [UP]
ksql-server is [UP]
connect is [UP]
kafka-rest is [UP]
schema-registry is [UP]
kafka is [UP]
zookeeper is [UP]
```


confluent local stop ksql-server

```markdown
    The local commands are intended for a single-node development environment
    only, NOT for production usage. https://docs.confluent.io/current/cli/index.html

Using CONFLUENT_CURRENT: /var/folders/hf/kcy8xkw11_lg76v2_8q41rr00000gn/T/confluent.024FnuQC
Stopping ksql-server
ksql-server is [DOWN]
```


cp /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master/java/pre-compiled/ksql-udf-taxi-1.0.jar \
   /Library/Kafka/confluent-5.3.1/ext


confluent local start ksql-server


`ksql>`
list functions;

```markdown
 Function Name         | Type      
-----------------------------------
 ABS                   | SCALAR    
 ARRAYCONTAINS         | SCALAR    
 CEIL                  | SCALAR    
 COLLECT_LIST          | AGGREGATE 
 COLLECT_SET           | AGGREGATE 
 COMPLEXFUNCTION       | SCALAR    
 CONCAT                | SCALAR    
 CONFIGURABLEUDF       | SCALAR    
 COUNT                 | AGGREGATE 
 DATETOSTRING          | SCALAR    
 E2ECONFIGURABLEUDF    | SCALAR    
 ELT                   | SCALAR    
 EXTRACTJSONFIELD      | SCALAR    
 FIELD                 | SCALAR    
 FLOOR                 | SCALAR    
 GEO_DISTANCE          | SCALAR    
 HISTOGRAM             | AGGREGATE 
 IFNULL                | SCALAR    
 LCASE                 | SCALAR    
 LEN                   | SCALAR    
 MASK                  | SCALAR    
 MASK_KEEP_LEFT        | SCALAR    
 MASK_KEEP_RIGHT       | SCALAR    
 MASK_LEFT             | SCALAR    
 MASK_RIGHT            | SCALAR    
 MAX                   | AGGREGATE 
 MIN                   | AGGREGATE 
 RANDOM                | SCALAR    
 ROUND                 | SCALAR    
 SOMEFUNCTION          | SCALAR    
 SPLIT                 | SCALAR    
 STRINGTODATE          | SCALAR    
 STRINGTOTIMESTAMP     | SCALAR    
 SUBSTRING             | SCALAR    
 SUM                   | AGGREGATE 
 TAXI_WAIT             | SCALAR      <--
 TEST_UDAF             | AGGREGATE 
 TEST_UDF              | SCALAR    
 TIMESTAMPTOSTRING     | SCALAR    
 TOPK                  | AGGREGATE 
 TOPKDISTINCT          | AGGREGATE 
 TRIM                  | SCALAR    
 UCASE                 | SCALAR    
 URL_DECODE_PARAM      | SCALAR    
 URL_ENCODE_PARAM      | SCALAR    
 URL_EXTRACT_FRAGMENT  | SCALAR    
 URL_EXTRACT_HOST      | SCALAR    
 URL_EXTRACT_PARAMETER | SCALAR    
 URL_EXTRACT_PATH      | SCALAR    
 URL_EXTRACT_PORT      | SCALAR    
 URL_EXTRACT_PROTOCOL  | SCALAR    
 URL_EXTRACT_QUERY     | SCALAR    
 WINDOWEND             | AGGREGATE 
 WINDOWSTART           | AGGREGATE 
-----------------------------------
```


`ksql>`
DESCRIBE FUNCTION TAXI_WAIT;

```markdown
Name        : TAXI_WAIT
Overview    : Return expected wait time in minutes
Type        : scalar
Jar         : /Library/Kafka/confluent-5.3.1/ext/ksql-udf-taxi-1.0.jar
Variations  : 

	Variation   : TAXI_WAIT(VARCHAR, DOUBLE)
	Returns     : DOUBLE
	Description : Given weather and distance return expected wait time in minutes
```


#### section 6, lecture 26

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
select * from ridetodest;

```markdown
1569880932107 | London | Frank | London | GB | heavy rain | 8.0 | 164.75873321703418
1569880934495 | San Jose | Oscar | San Jose | US | light rain | 3.0 | 789.2444214243801
1569880935908 | London | Heidi | London | GB | heavy rain | 8.0 | 163.46377468342524
1569880937567 | London | Grace | London | GB | heavy rain | 8.0 | 34.43187260750569
1569880937927 | Los Angeles | Oscar | Los Angeles | US | haze | 2.0 | 1789.101066073534
1569880939312 | San Francisco | Niaj | San Francisco | US | SUNNY | 10.0 | 1429.9182555246339
1569880939674 | San Francisco | Wendy | San Francisco | US | SUNNY | 10.0 | 839.8581788353116
```


`ksql>`
describe ridetodest;

```markdown
Name                 : RIDETODEST
 Field               | Type                      
-------------------------------------------------
 ROWTIME             | BIGINT           (system) 
 ROWKEY              | VARCHAR(STRING)  (system) 
 USER                | VARCHAR(STRING)           
 CITY_NAME           | VARCHAR(STRING)           
 CITY_COUNTRY        | VARCHAR(STRING)           
 WEATHER_DESCRIPTION | VARCHAR(STRING)           
 RAIN                | DOUBLE                    
 DIST                | DOUBLE                    
-------------------------------------------------
For runtime statistics and query details run: DESCRIBE EXTENDED <Stream,Table>;
```


`ksql>`
select user,
       round(dist) as dist,
       weather_description,
       round(TAXI_WAIT(weather_description, dist)) as taxi_wait_min
    from ridetodest;

```markdown
Frank | 165 | heavy rain | 13
Oscar | 789 | light rain | 32
Heidi | 163 | heavy rain | 13
Grace | 34 | heavy rain | 3
Oscar | 1789 | haze | 54
Niaj | 1430 | SUNNY | 29
Wendy | 840 | SUNNY | 17
```


`ksql>`
select user
       || ' will be waiting '
       || cast(round(TAXI_WAIT(weather_description, dist)) as varchar)
       || ' minutes for their trip of '
       || cast(round(dist) as varchar)
       || ' km to '
       || city_name
       || ' where it is '
       || weather_description
    from ridetodest;

```markdown
Walter will be waiting 21 minutes for their trip of 1071 km to San Francisco where it is SUNNY
Frank will be waiting 13 minutes for their trip of 165 km to London where it is heavy rain
Oscar will be waiting 32 minutes for their trip of 789 km to San Jose where it is light rain
Heidi will be waiting 13 minutes for their trip of 163 km to London where it is heavy rain
Grace will be waiting 3 minutes for their trip of 34 km to London where it is heavy rain
Oscar will be waiting 54 minutes for their trip of 1789 km to Los Angeles where it is haze
Niaj will be waiting 29 minutes for their trip of 1430 km to San Francisco where it is SUNNY
Wendy will be waiting 17 minutes for their trip of 840 km to San Francisco where it is SUNNY
```


#### section 7, lecture 27

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
SET 'auto.offset.reset'='earliest';

cat /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master/where-is-bob.ksql


confluent local stop ksql-server

cd /Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master

ksql-server-start /Library/Kafka/confluent-5.3.1/etc/ksql/ksql-server.properties --queries-file ./where-is-bob.ksql

ksql-server-start /Library/Kafka/confluent-5.3.1/etc/ksql/ksql-server-custom.properties --queries-file ./where-is-bob-custom.ksql

ksql

kafka-topics --bootstrap-server localhost:9092 --list --topic BOB

kafka-avro-console-consumer --bootstrap-server localhost:9092 --topic BOB


#### section 7, lecture 28

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

`ksql>`
select * from userprofile;

```markdown
1569836565610 | 1096 | 1096 | Bob | Dotty | GB | 3.9
1569836566163 | 1097 | 1097 | Eve | Edison | IN | 3.9
1569836568230 | 1098 | 1098 | Eve | Edison | GB | 4.9
1569836569870 | 1099 | 1099 | Frank | Jones | AU | 3.7
```


`ksql>`
create stream my_stream
    as select firstname
    from userprofile;

```markdown
 Message                    
----------------------------
 Stream created and running 
----------------------------
```


`ksql>`
show queries;

```markdown
 Query ID                     | Kafka Topic           | Query String                                                                                                                                                                                                                                                                                      
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 CSAS_UP_JOINED_1             | UP_JOINED             | CREATE STREAM UP_JOINED WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'UP_JOINED') AS SELECT concat(concat(concat(concat(concat(concat(concat(UP.FIRSTNAME, ' '), UCASE(UP.LASTNAME)), ' from '), CT.COUNTRYNAME), ' has a rating of '), CAST(UP.RATING AS STRING)), ' stars.') "DESCRIPTION"
FROM USERPROFILE UP
LEFT OUTER JOIN COUNTRYTABLE CT ON ((CT.COUNTRYCODE = UP.COUNTRYCODE)); 
 CSAS_RIDETODEST_8            | RIDETODEST            | CREATE STREAM RIDETODEST WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'RIDETODEST') AS SELECT
  REQUESTED_JOURNEY.USER "USER"
, REQUESTED_JOURNEY.CITY_NAME "CITY_NAME"
, REQUESTED_JOURNEY.CITY_COUNTRY "CITY_COUNTRY"
, REQUESTED_JOURNEY.WEATHER_DESCRIPTION "WEATHER_DESCRIPTION"
, REQUESTED_JOURNEY.RAIN "RAIN"
, GEO_DISTANCE(REQUESTED_JOURNEY.FROM_LATITUDE, REQUESTED_JOURNEY.FROM_LONGITUDE, REQUESTED_JOURNEY.TO_LATITUDE, REQUESTED_JOURNEY.TO_LONGITUDE, 'km') "DIST"
FROM REQUESTED_JOURNEY REQUESTED_JOURNEY; 
 CSAS_WEATHERREKEYED_3        | WEATHERREKEYED        | CREATE STREAM WEATHERREKEYED WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'WEATHERREKEYED') AS SELECT *
FROM WEATHERRAW WEATHERRAW
PARTITION BY CITY_NAME;                                                                                                                                   
 CSAS_RR_WORD_5               | RR_WORD               | CREATE STREAM RR_WORD WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'RR_WORD') AS SELECT
  'Europe' "DATA_SOURCE"
, *
FROM RR_EUROPE_RAW RR_EUROPE_RAW;                                                                                                                                       
 InsertQuery_6                | RR_WORD               | insert into rr_word select 'Americas' as data_source, * from rr_america_raw;                                                                                                                                                                                                                      
 CSAS_MY_STREAM_9             | MY_STREAM             | CREATE STREAM MY_STREAM WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'MY_STREAM') AS SELECT USERPROFILE.FIRSTNAME "FIRSTNAME"
FROM USERPROFILE USERPROFILE;                                                                                                                                  
 CSAS_WEATHERRAW_2            | WEATHERRAW            | CREATE STREAM WEATHERRAW WITH (REPLICAS = 1, PARTITIONS = 1, VALUE_FORMAT = 'AVRO', KAFKA_TOPIC = 'WEATHERRAW') AS SELECT
  FETCH_FIELD_FROM_STRUCT(WEATHER.CITY, 'NAME') "CITY_NAME"
, FETCH_FIELD_FROM_STRUCT(WEATHER.CITY, 'COUNTRY') "CITY_COUNTRY"
, FETCH_FIELD_FROM_STRUCT(WEATHER.CITY, 'LATITUDE') "LATITUDE"
, FETCH_FIELD_FROM_STRUCT(WEATHER.CITY, 'LONGITUDE') "LONGITUDE"
, WEATHER.DESCRIPTION "DESCRIPTION"
, WEATHER.RAIN "RAIN"
FROM WEATHER WEATHER; 
 CSAS_DRIVERPROFILE_REKEYED_4 | DRIVERPROFILE_REKEYED | CREATE STREAM DRIVERPROFILE_REKEYED WITH (KAFKA_TOPIC = 'DRIVERPROFILE_REKEYED', REPLICAS = 1, PARTITIONS = 1) AS SELECT *
FROM DRIVER_PROFILE DRIVER_PROFILE
PARTITION BY DRIVER_NAME;                                                                                                           
 CSAS_REQUESTED_JOURNEY_7     | REQUESTED_JOURNEY     | CREATE STREAM REQUESTED_JOURNEY WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'REQUESTED_JOURNEY') AS SELECT
  RR.LATITUDE "FROM_LATITUDE"
, RR.LONGITUDE "FROM_LONGITUDE"
, RR.USER "USER"
, RR.CITY_NAME "CITY_NAME"
, W.CITY_COUNTRY "CITY_COUNTRY"
, W.LATITUDE "TO_LATITUDE"
, W.LONGITUDE "TO_LONGITUDE"
, W.DESCRIPTION "WEATHER_DESCRIPTION"
, W.RAIN "RAIN"
FROM RR_WORD RR
LEFT OUTER JOIN WEATHERNOW W ON ((RR.CITY_NAME = W.CITY_NAME)); 
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
For detailed information on a Query run: EXPLAIN <Query ID>;
```


`ksql>`
explain CSAS_MY_STREAM_9;

```markdown
ID                   : CSAS_MY_STREAM_9
SQL                  : CREATE STREAM MY_STREAM WITH (REPLICAS = 1, PARTITIONS = 1, KAFKA_TOPIC = 'MY_STREAM') AS SELECT USERPROFILE.FIRSTNAME "FIRSTNAME"
FROM USERPROFILE USERPROFILE;

 Field     | Type                      
---------------------------------------
 ROWTIME   | BIGINT           (system) 
 ROWKEY    | VARCHAR(STRING)  (system) 
 FIRSTNAME | VARCHAR(STRING)           
---------------------------------------

Sources that this query reads from: 
-----------------------------------
USERPROFILE

For source description please run: DESCRIBE [EXTENDED] <SourceId>

Sinks that this query writes to: 
-----------------------------------
MY_STREAM

For sink description please run: DESCRIBE [EXTENDED] <SinkId>

Execution plan      
--------------      
 > [ SINK ] | Schema: [FIRSTNAME VARCHAR] | Logger: CSAS_MY_STREAM_9.MY_STREAM
		 > [ PROJECT ] | Schema: [FIRSTNAME VARCHAR] | Logger: CSAS_MY_STREAM_9.Project
				 > [ SOURCE ] | Schema: [USERPROFILE.ROWTIME BIGINT, USERPROFILE.ROWKEY VARCHAR, USERPROFILE.USERID INT, USERPROFILE.FIRSTNAME VARCHAR, USERPROFILE.LASTNAME VARCHAR, USERPROFILE.COUNTRYCODE VARCHAR, USERPROFILE.RATING DOUBLE] | Logger: CSAS_MY_STREAM_9.KsqlTopic


Processing topology 
------------------- 
Topologies:
   Sub-topology: 0
    Source: KSTREAM-SOURCE-0000000000 (topics: [USERPROFILE])
      --> KSTREAM-MAPVALUES-0000000001
    Processor: KSTREAM-MAPVALUES-0000000001 (stores: [])
      --> KSTREAM-TRANSFORMVALUES-0000000002
      <-- KSTREAM-SOURCE-0000000000
    Processor: KSTREAM-TRANSFORMVALUES-0000000002 (stores: [])
      --> KSTREAM-MAPVALUES-0000000003
      <-- KSTREAM-MAPVALUES-0000000001
    Processor: KSTREAM-MAPVALUES-0000000003 (stores: [])
      --> KSTREAM-MAPVALUES-0000000004
      <-- KSTREAM-TRANSFORMVALUES-0000000002
    Processor: KSTREAM-MAPVALUES-0000000004 (stores: [])
      --> KSTREAM-SINK-0000000005
      <-- KSTREAM-MAPVALUES-0000000003
    Sink: KSTREAM-SINK-0000000005 (topic: MY_STREAM)
      <-- KSTREAM-MAPVALUES-0000000004



Overridden Properties
---------------------
 Property          | Value 
---------------------------
 auto.offset.reset |       
---------------------------
```


https://zz85.github.io/kafka-streams-viz/

```markdown
Sub-topology: 0
    Source: KSTREAM-SOURCE-0000000000 (topics: [USERPROFILE])
      --> KSTREAM-MAPVALUES-0000000001
    Processor: KSTREAM-MAPVALUES-0000000001 (stores: [])
      --> KSTREAM-TRANSFORMVALUES-0000000002
      <-- KSTREAM-SOURCE-0000000000
    Processor: KSTREAM-TRANSFORMVALUES-0000000002 (stores: [])
      --> KSTREAM-MAPVALUES-0000000003
      <-- KSTREAM-MAPVALUES-0000000001
    Processor: KSTREAM-MAPVALUES-0000000003 (stores: [])
      --> KSTREAM-MAPVALUES-0000000004
      <-- KSTREAM-TRANSFORMVALUES-0000000002
    Processor: KSTREAM-MAPVALUES-0000000004 (stores: [])
      --> KSTREAM-SINK-0000000005
      <-- KSTREAM-MAPVALUES-0000000003
    Sink: KSTREAM-SINK-0000000005 (topic: MY_STREAM)
      <-- KSTREAM-MAPVALUES-0000000004
```


#### section 7, lecture 29

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home

/Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master/docker-compose-prod.yml

/Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master/docker-compose.yml


docker ps

docker-compose -f docker-compose-prod.yml up -d

ksql-datagen schame=/Users/serhii/Documents/Web/Training/Java/java/Apache-Kafka-Series-KSQL-for-Stream-Processing-Hands-On/ksql-course-master/datagen/userprofile.avro \
    format=json topic=USERPROFILE key=userid maxInterval=1000 iterations=10000

ksql

`ksql>`
CREATE STREAM userprofile (userid INT, firstname VARCHAR, lastname VARCHAR, countrycode VARCHAR, rating DOUBLE)
    WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'USERPROFILE');

`ksql>`
create stream up_lastseen as
    SELECT TIMESTAMPTOSTRING(rowtime, 'dd/MMM HH:mm:ss') as createtime, firstname
    from userprofile;

kafka-console-consumer --bootstrap-server localhost:9092 --topic UP_LASTSEEN

docker-compose -f docker-compose-prod.yml ps

docker-compose -f docker-compose-prod.yml stop ksql-server-1

docker-compose -f docker-compose-prod.yml ps

docker-compose -f docker-compose-prod.yml start ksql-server-1

docker-compose -f docker-compose-prod.yml stop ksql-server-2

docker-compose -f docker-compose-prod.yml ps

docker-compose -f docker-compose-prod.yml stop ksql-server-1

docker-compose -f docker-compose-prod.yml ps

docker-compose -f docker-compose-prod.yml start ksql-server-1

docker-compose -f docker-compose-prod.yml start ksql-server-2


