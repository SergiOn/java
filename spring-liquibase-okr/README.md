# About project

### Greeting service
Service works with "greeting" database, uses elk, prometheus, grafana

### Detail service
Service calls to "greeting service", has fallback logic if service is unavailable, uses elk

### Authorization service
Service works with "authorization" database, responsible for security, registering, gives access jwt token, uses elk

### Zuul service
Gateway proxy for "greeting", "detail", "authorization" services, uses elk

### Eureka service
Service discovery, shows the availability of services, uses elk

### Zipkin service
Shows request/response tracing between microservices, uses elk

### Config service
Provides application config for all microservices, uses elk


# Docker

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


### Logstash

```bash
logstash -f /Users/serhii/Documents/Web/Training/Java/java/spring-liquibase-okr/logstash.conf

logstash -f /Users/work/Documents/Training/Java/java/spring-liquibase-okr/logstash.conf
```


## Docker (Prometheus, Grafana)

### Prometheus

```bash
docker run -d --name spring-liquibase-okr-prometheus -p 9090:9090 -v /Users/serhii/Documents/Web/Training/Java/java/spring-liquibase-okr/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml

docker run -d --name spring-liquibase-okr-prometheus -p 9090:9090 -v /Users/work/Documents/Training/Java/java/spring-liquibase-okr/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml

docker run -d --name spring-liquibase-okr-prometheus -p 9090:9090 prom/prometheus
```

```bash
docker start spring-liquibase-okr-prometheus
```

```bash
docker stop spring-liquibase-okr-prometheus
```

```bash
docker -D exec -it spring-liquibase-okr-prometheus sh
vi prometheus.yml
```

```yaml
global:
  scrape_interval:     5s # Set the scrape interval to every 5 seconds.
  evaluation_interval: 5s # Evaluate rules every 5 seconds.
scrape_configs:
  - job_name: 'greeting-service'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['192.168.1.103:8103']

https://2ip.ua/ua/
Ваша локальна IP адреса
```


### Grafana

```bash
docker run -d --name spring-liquibase-okr-grafana --link spring-liquibase-okr-prometheus:prometheus -p 3000:3000 grafana/grafana
```

```bash
docker start spring-liquibase-okr-grafana
```

```bash
docker stop spring-liquibase-okr-grafana
```

```bash
http://prometheus:9090
```

```text
greeting-service
dashboard name: Greeting service

Graph:
1)
http_server_requests_seconds_max{uri="/hello/{name}"}
Requests Seconds Max: "/hello/{name}"
2)
http_server_requests_seconds_count
Requests Seconds Count
3)
system_cpu_usage
System CPU Usage
```


## Docker (Redis)

```bash
docker run --name spring-liquibase-okr-redis -p 6379:6379 -d redis --requirepass "root"
```

```bash
docker start spring-liquibase-okr-redis
```

```bash
docker stop spring-liquibase-okr-redis
```



### Links

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

https://www.youtube.com/watch?v=o4nt9IR8iL8&t=3952

https://cloud.spring.io/spring-cloud-netflix/multi/multi__router_and_filter_zuul.html

https://github.com/SergiOn/spring-cloud-zuul-filters-sample

https://github.com/SergiOn/java/blob/master/Build-A-Web-App-With-Spring-Framework-and-Angular-2/backend/src/main/java/com/ldeng/controller/UserController.java

https://stackoverflow.com/questions/19825946/how-to-add-a-filter-class-in-spring-boot

https://www.javadevjournal.com/spring-boot/spring-boot-add-filter/

https://stackoverflow.com/questions/31082981/spring-boot-adding-http-request-interceptors

https://stackoverflow.com/questions/2811769/adding-an-http-header-to-the-request-in-a-servlet-filter

https://stackoverflow.com/questions/51137893/feign-client-concurrency-issue

http://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/1.2.0.RELEASE/#_propagating_the_security_context_or_using_spring_scopes

https://dzone.com/articles/monitoring-using-spring-boot-2-prometheus-and-graf

https://dzone.com/articles/monitoring-using-spring-boot-20-prometheus-and-gra

https://stackoverflow.com/questions/24319662/from-inside-of-a-docker-container-how-do-i-connect-to-the-localhost-of-the-mach
