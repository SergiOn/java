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








