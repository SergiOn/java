## Docker (MySQL)

```bash
docker run --name spring-liquibase-okr-mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql
```

```bash
docker start spring-liquibase-okr-mysql
```

```bash
docker stop spring-liquibase-okr-mysql
```

## Connect by terminal

```bash
mysql -h 127.0.0.1 -P 3306 -u root -p
```


## Docker (ELK)

### Elasticsearch

```bash
docker run -d --name spring-liquibase-okr-elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:6.5.4
```

```bash
docker start spring-liquibase-okr-elasticsearch
```

```bash
docker stop spring-liquibase-okr-elasticsearch
```


### Kibana

```bash
docker run -d --name spring-liquibase-okr-kibana --link spring-liquibase-okr-elasticsearch:elasticsearch -p 5601:5601 kibana:6.5.4
```

```bash
docker start spring-liquibase-okr-kibana
```

```bash
docker stop spring-liquibase-okr-kibana
```



#### Links

http://pocketcultures.com/2008/10/30/say-hello-in-20-languages/

https://medium.com/omarelgabrys-blog/microservices-with-spring-boot-intro-to-microservices-part-1-c0d24cd422c3

https://medium.com/omarelgabrys-blog/microservices-with-spring-boot-creating-our-microserivces-gateway-part-2-31f8aa6b215b

https://medium.com/omarelgabrys-blog/microservices-with-spring-boot-authentication-with-jwt-part-3-fafc9d7187e8

https://medium.com/omarelgabrys-blog/microservices-with-spring-boot-circuit-breaker-log-tracing-part-4-9cdf5e898988

https://www.baeldung.com/spring-security-oauth-jwt

https://stackoverflow.com/questions/42449017/how-to-use-redis-to-persist-token-using-spring-security-oauth2

https://github.com/SergiOn/kotlin/blob/master/smlr/src/main/resources/sql/001_init.sql

https://github.com/SergiOn/java/blob/master/Application-Development-with-Spring-5-and-Angular-6/Server-Side-Search/searchapp-dataservice/src/main/resources/searchapp_db.sql

https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-2/

https://www.devglan.com/spring-security/spring-boot-jwt-auth

https://grokonez.com/spring-framework/spring-boot/angular-spring-boot-jwt-authentication-example-angular-6-spring-security-mysql-full-stack-part-2-build-backend

https://grokonez.com/spring-framework/spring-security/spring-security-jwt-authentication-restapis-springboot-spring-mvc-spring-security-spring-jpa-mysql

https://stackoverflow.com/questions/21992201/how-to-revoke-auth-token-in-spring-security

https://github.com/raonirenosto/silverauth/blob/master/src/main/java/com/silverauth/controller/TokenController.java

https://stackoverflow.com/questions/43934786/spring-boot-oauth2-how-to-retrieve-user-token-info-details

https://stackoverflow.com/questions/49127791/extract-currently-logged-in-user-information-from-jwt-token-using-spring-securit

https://medium.com/hprog99/spring-boot-logs-with-elasticsearch-logstash-and-kibana-elk-c61a378f3cb4

https://github.com/hitenpratap/spring-boot-elk

https://medium.com/oneclicklabs-io/streaming-spring-boot-application-logs-to-elk-stack-part-1-a68bd7cccaeb

https://github.com/SergiOn/spring-oauth2-redis-mysql

https://github.com/SergiOn/spring-boot-oauth2-jwt-revoke-token-silverauth

https://github.com/SergiOn/spring-security-jwt-demo

https://github.com/SergiOn/angular-6-jwt-authentication-example
