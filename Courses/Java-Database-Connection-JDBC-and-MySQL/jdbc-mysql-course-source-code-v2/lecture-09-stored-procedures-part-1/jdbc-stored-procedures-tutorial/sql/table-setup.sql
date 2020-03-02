create database if not exists demo;

use demo;

drop table if exists employees;

CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(64) DEFAULT NULL,
  `first_name` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `department` varchar(64) DEFAULT NULL,
  `salary` DECIMAL(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (1,'Doe','John','john.doe@foo.com', 'HR', 55000.00);
INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (2,'Public','Mary','mary.public@foo.com', 'Engineering', 75000.00);
INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (3,'Queue','Susan','susan.queue@foo.com', 'Legal', 130000.00);

INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (4,'Williams','David','david.williams@foo.com', 'HR', 120000.00);
INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (5,'Johnson','Lisa','lisa.johnson@foo.com', 'Engineering', 50000.00);
INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (6,'Smith','Paul','paul.smith@foo.com', 'Legal', 100000.00);

INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (7,'Adams','Carl','carl.adams@foo.com', 'HR', 50000.00);
INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (8,'Brown','Bill','bill.brown@foo.com', 'Engineering', 50000.00);
INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (9,'Thomas','Susan','susan.thomas@foo.com', 'Legal', 80000.00);

INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (10,'Davis','John','john.davis@foo.com', 'HR', 45000.00);
INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (11,'Fowler','Mary','mary.fowler@foo.com', 'Engineering', 65000.00);
INSERT INTO `employees` (`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (12,'Waters','David','david.waters@foo.com', 'Legal', 90000.00);

--
-- DEFINE STORED PROCEDURES
-- 

DELIMITER $$
DROP PROCEDURE IF EXISTS `get_count_for_department`$$

CREATE DEFINER=`student`@`localhost` PROCEDURE `get_count_for_department`(IN the_department VARCHAR(64), OUT the_count INT)
BEGIN
	
	SELECT COUNT(*) INTO the_count FROM employees where department=the_department;

END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS `get_employees_for_department`$$

CREATE DEFINER=`student`@`localhost` PROCEDURE `get_employees_for_department`(IN the_department VARCHAR(64))
BEGIN

	SELECT * from employees where department=the_department;

END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS `greet_the_department`$$

CREATE DEFINER=`student`@`localhost` PROCEDURE `greet_the_department`(INOUT department VARCHAR(64))
BEGIN

	SET department = concat('Hello to the awesome ', department, ' team!');

END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS `increase_salaries_for_department`$$

CREATE DEFINER=`student`@`localhost` PROCEDURE `increase_salaries_for_department`(IN the_department VARCHAR(64), IN increase_amount DECIMAL(10,2))
BEGIN

	UPDATE employees SET salary= salary + increase_amount where department=the_department;

END$$
DELIMITER ;
