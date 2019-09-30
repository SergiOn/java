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










