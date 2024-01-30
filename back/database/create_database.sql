CREATE DATABASE  IF NOT EXISTS `todolist` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `todolist`;

CREATE USER IF NOT EXISTS 'todo'@'localhost' IDENTIFIED BY 'todo';

GRANT ALL PRIVILEGES ON `todolist`.* TO 'todo'@'localhost';
FLUSH PRIVILEGES;