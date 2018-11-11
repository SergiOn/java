## Docker

docker run -p 3306:3306 --name application-development-spring-5-angular-6 -e MYSQL_ROOT_PASSWORD=root -d mysql

docker start application-development-spring-5-angular-6

docker stop application-development-spring-5-angular-6

## Connect by terminal

mysql -h 127.0.0.1 -P 3306 -u root -p

## Apply schemas

### cd

/Users/serhii/Documents/Web/Training/Java/java/Application-Development-with-Spring-5-and-Angular-6/searchapp/src/main/resources

### apply

mysql -h 127.0.0.1 -P 3306 -u root -p < searchapp_db.sql

mysql -h 127.0.0.1 -P 3306 -u root -p searchapp < searchapp_db_data.sql

