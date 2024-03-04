 CREATE TABLE `region` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB

CREATE TABLE `country` (
  `id` varchar(2) NOT NULL,
  `name` varchar(40) NOT NULL,
  `region` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `region` (`region`),
  CONSTRAINT `country_ibfk_1` FOREIGN KEY (`region`) REFERENCES `region` (`id`)
) ENGINE=InnoDB

CREATE TABLE `location` (
  `id` int NOT NULL,
  `street_address` varchar(40) DEFAULT NULL,
  `postal_code` varchar(12) DEFAULT NULL,
  `city` varchar(30) NOT NULL,
  `state_province` varchar(25) DEFAULT NULL,
  `country` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `country` (`country`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`country`) REFERENCES `country` (`id`)
) ENGINE=InnoDB


 CREATE TABLE `employee` (
  `id` int NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `hire_date` date NOT NULL,
  `salary` int DEFAULT NULL,
  `comission_pct` float(8,2) DEFAULT NULL,
  `manager` int DEFAULT NULL,
  `job` varchar(10) NOT NULL,
  `department` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_unik` (`email`),
  KEY `manager` (`manager`),
  KEY `job` (`job`),
  KEY `department` (`department`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`manager`) REFERENCES `employee` (`id`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`job`) REFERENCES `job` (`id`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`department`) REFERENCES `department` (`id`)
) ENGINE=InnoDB


CREATE TABLE `department` (
  `id` int NOT NULL,
  `name` varchar(30) NOT NULL,
  `location` int NOT NULL,
  `manager` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `location` (`location`),
  KEY `manager` (`manager`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`location`) REFERENCES `location` (`id`),
  CONSTRAINT `department_ibfk_2` FOREIGN KEY (`manager`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB

CREATE TABLE `job` (
  `id` varchar(10) NOT NULL,
  `title` varchar(35) NOT NULL,
  `min_salary` int DEFAULT NULL,
  `max_salary` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB

CREATE TABLE `history` (
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `department_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `job_id` varchar(10) NOT NULL,
  KEY `employee_id` (`employee_id`),
  KEY `job_id` (`job_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `history_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `history_ibfk_2` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `history_ibfk_3` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB
