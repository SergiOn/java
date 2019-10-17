# Hibernate: Advanced Development Techniques

## Repositories

http://www.luv2code.com/download-advanced-hibernate-source-code

http://www.luv2code.com/download-advanced-hibernate-pdfs


## Links

https://www.udemy.com/course/hibernate-tutorial-advanced/


## Information

https://stackoverflow.com/questions/8969059/difference-between-onetomany-and-elementcollection


## Commands

#### section 2, lecture 7

```sql
CREATE DATABASE IF NOT EXISTS `hb_student_tracker`;

USE `hb_student_tracker`;
```

```sql
DROP TABLE IF EXISTS `student`;

CREATE TABLE student (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) DEFAULT NULL,
    `last_name` VARCHAR(45) DEFAULT NULL,
    `email` VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
```

```sql
DROP TABLE IF EXISTS `image`;

CREATE TABLE image (
    `student_id` INT(11) NOT NULL,
    `file_name` VARCHAR(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

