## Docker

```bash
docker run --name learn-microservices-spring-boot-spring-cloud -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql
```

```bash
docker start learn-microservices-spring-boot-spring-cloud
```

```bash
docker stop learn-microservices-spring-boot-spring-cloud
```

## Connect by terminal

```bash
mysql -h 127.0.0.1 -P 3306 -u root -p
```