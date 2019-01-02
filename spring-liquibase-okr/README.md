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
