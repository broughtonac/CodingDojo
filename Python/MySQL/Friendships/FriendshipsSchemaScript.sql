-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema friendships
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema friendships
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `friendships` DEFAULT CHARACTER SET utf8 ;
USE `friendships` ;

-- -----------------------------------------------------
-- Table `friendships`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `friendships`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `friendships`.`friendships`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `friendships`.`friendships` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `user_id` INT(11) NOT NULL,
  `friend_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_friendships_users_idx` (`user_id` ASC),
  INDEX `fk_friendships_users1_idx` (`friend_id` ASC),
  CONSTRAINT `fk_friendships_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `friendships`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_friendships_users1`
    FOREIGN KEY (`friend_id`)
    REFERENCES `friendships`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

/*
-- DATA
*/

-- Query: SELECT * FROM users
INSERT INTO `users` (`id`,`first_name`,`last_name`,`created_at`,`updated_at`) VALUES (1,'chris','baker','2017-12-04 10:38:25','2017-12-04 10:38:25');
INSERT INTO `users` (`id`,`first_name`,`last_name`,`created_at`,`updated_at`) VALUES (2,'diana','smith','2017-12-04 10:38:25','2017-12-04 10:38:25');
INSERT INTO `users` (`id`,`first_name`,`last_name`,`created_at`,`updated_at`) VALUES (3,'james','johnson','2017-12-04 10:38:25','2017-12-04 10:38:25');
INSERT INTO `users` (`id`,`first_name`,`last_name`,`created_at`,`updated_at`) VALUES (4,'jessica','davidson','2017-12-04 10:38:25','2017-12-04 10:38:25');

-- Query: SELECT * FROM friendships
INSERT INTO `friendships` (`id`,`created_at`,`updated_at`,`user_id`,`friend_id`) VALUES (1,'2017-12-04 11:24:49','2017-12-04 11:24:49',1,4);
INSERT INTO `friendships` (`id`,`created_at`,`updated_at`,`user_id`,`friend_id`) VALUES (2,'2017-12-04 11:24:49','2017-12-04 11:24:49',1,3);
INSERT INTO `friendships` (`id`,`created_at`,`updated_at`,`user_id`,`friend_id`) VALUES (3,'2017-12-04 11:24:49','2017-12-04 11:24:49',1,2);
INSERT INTO `friendships` (`id`,`created_at`,`updated_at`,`user_id`,`friend_id`) VALUES (4,'2017-12-04 11:24:49','2017-12-04 11:24:49',2,1);
INSERT INTO `friendships` (`id`,`created_at`,`updated_at`,`user_id`,`friend_id`) VALUES (5,'2017-12-04 11:24:49','2017-12-04 11:24:49',3,1);
INSERT INTO `friendships` (`id`,`created_at`,`updated_at`,`user_id`,`friend_id`) VALUES (6,'2017-12-04 11:24:49','2017-12-04 11:24:49',4,1);