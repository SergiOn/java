## Docker

```bash
docker run --name online-bank-java-angular-spring -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql
```

```bash
docker start online-bank-java-angular-spring
```

```bash
docker stop online-bank-java-angular-spring
```

## Connect by terminal

```bash
mysql -h 127.0.0.1 -P 3306 -u root -p
```
