-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema billify_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema billify_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `billify_db` DEFAULT CHARACTER SET utf8 ;
USE `billify_db` ;

-- -----------------------------------------------------
-- Table `billify_db`.`Roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billify_db`.`Roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `permissions` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billify_db`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billify_db`.`Users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(20) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billify_db`.`UserRoles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billify_db`.`UserRoles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_UserRoles_Users_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_UserRoles_Roles1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_UserRoles_Users`
    FOREIGN KEY (`user_id`)
    REFERENCES `billify_db`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_UserRoles_Roles1`
    FOREIGN KEY (`role_id`)
    REFERENCES `billify_db`.`Roles` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
