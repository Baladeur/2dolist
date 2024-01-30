CREATE DATABASE  IF NOT EXISTS `todolist` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `todolist`;

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
                        `id` INT AUTO_INCREMENT PRIMARY KEY,
                        `email` varchar(255) NOT NULL,
                        `nickName` varchar(255) DEFAULT NULL,
                        `firstName` varchar(255) DEFAULT NULL,
                        `lastName` varchar(255) DEFAULT NULL,
                        `picture` varchar(255) DEFAULT NULL,
                        `address` varchar(255) DEFAULT NULL,
    -- deleted, active, blocked
                        `accountStatus` VARCHAR(10) DEFAULT 'active',
                        `emailVerified` TINYINT(1) DEFAULT 0,
                        `lastUpdated` varchar(255) DEFAULT NULL,
    -- user, admin, super_admin
                        `role` VARCHAR(20) DEFAULT 'user',
                        `passwordHash` varchar(255) DEFAULT NULL,
                        `registrationUrlHash` varchar(255) DEFAULT NULL,
                        `dateRequestRegistrationUrlHash` Date DEFAULT NULL
);

DROP TABLE IF EXISTS `PrivacySetting`;
CREATE TABLE `PrivacySetting` (
                                  `id` INT AUTO_INCREMENT PRIMARY KEY,
                                  `userId` INT NOT NULL,
                                  `email` VARCHAR(255) NULL,
                                  `nickName` VARCHAR(255) NULL,
                                  `firstName` VARCHAR(255) NULL,
                                  `lastName` VARCHAR(255) NULL,
                                  `picture` VARCHAR(255) NULL,
                                  `address` VARCHAR(255) NULL,
                                  FOREIGN KEY (`userId`) REFERENCES `User`(`id`)
);


-- Insert super admin
INSERT INTO `user` (`email`, `nickName`, `firstName`, `lastName`, `picture`, `address`, `accountStatus`, `emailVerified`, `lastUpdated`, `role`, `passwordHash`, `registrationUrlHash`, `dateRequestRegistrationUrlHash`)
VALUES ('superadmin@example.com', 'SuperAdmin', 'Super', 'Admin', NULL, NULL, 'active', 1, NOW(), 'super_admin', 'password_hash', NULL, NULL);

CREATE USER IF NOT EXISTS 'todo'@'localhost' IDENTIFIED BY 'todo';

GRANT ALL PRIVILEGES ON `todolist`.* TO 'todo'@'localhost';
FLUSH PRIVILEGES;