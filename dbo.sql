-- MySQL Script generated by MySQL Workbench
-- Thu Mar 23 23:32:19 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dbo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dbo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbo` DEFAULT CHARACTER SET utf8 ;
USE `dbo` ;

-- -----------------------------------------------------
-- Table `dbo`.`clientaddress`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`clientaddress` (
  `ClientAddressId` BIGINT NOT NULL auto_increment,
  `Country` VARCHAR(50) NULL DEFAULT NULL,
  `City` VARCHAR(50) NULL DEFAULT NULL,
  `PostIndex` INT(11) NULL DEFAULT NULL,
  `Street` VARCHAR(50) NULL DEFAULT NULL,
  `HouseNumber` VARCHAR(10) NULL DEFAULT NULL,
  `Apartment` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ClientAddressId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbo`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`clients` (
  `ClientId` BIGINT NOT NULL auto_increment,
  `ClientAddressId` BIGINT NULL,
  `FirstName` VARCHAR(50) NOT NULL,
  `LastName` VARCHAR(50) NOT NULL,
  `BirthDate` DATE NULL DEFAULT NULL,
  `Email` VARCHAR(100) NOT NULL unique,
  `Password` VARCHAR(80) NOT NULL,
  `IsEnabled` BIT(1) NOT NULL,
  PRIMARY KEY (`ClientId`),
  INDEX `fk_clients_clientaddress1_idx` (`ClientAddressId` ASC),
  CONSTRAINT `fk_clients_clientaddress1`
    FOREIGN KEY (`ClientAddressId`)
    REFERENCES `dbo`.`clientaddress` (`ClientAddressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbo`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`roles` (
  `RoleId` BIGINT NOT NULL auto_increment,
  `Name` VARCHAR(50) NOT NULL unique,
  PRIMARY KEY (`RoleId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbo`.`clientsroles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`clientsroles` (
  `ClientId` BIGINT NOT NULL,
  `RoleId` BIGINT NOT NULL,
  PRIMARY KEY (`ClientId`, `RoleId`),
  INDEX `fk_clientsroles_clients1_idx` (`ClientId` ASC),
  INDEX `fk_clientsroles_roles1_idx` (`RoleId` ASC),
  CONSTRAINT `fk_clientsroles_clients1`
    FOREIGN KEY (`ClientId`)
    REFERENCES `dbo`.`clients` (`ClientId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clientsroles_roles1`
    FOREIGN KEY (`RoleId`)
    REFERENCES `dbo`.`roles` (`RoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbo`.`goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`goods` (
  `GoodId` BIGINT NOT NULL auto_increment,
  `Title` VARCHAR(100) NOT NULL,
  `Price` DECIMAL(18,4) NOT NULL,
  `Category` VARCHAR(15) NULL DEFAULT NULL,
  `Characteristics` VARCHAR(1000) NULL DEFAULT NULL,
  `Weight` DECIMAL(10,3) NULL DEFAULT NULL,
  `Size` VARCHAR(20) NULL DEFAULT NULL,
  `Count` INT(11) NOT NULL,
  PRIMARY KEY (`GoodId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbo`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`orders` (
  `OrderId` BIGINT NOT NULL auto_increment,
  `ClientId` BIGINT NOT NULL,
  `ClientAddressId` BIGINT NOT NULL,
  `PaymentMethod` SMALLINT(6) NOT NULL,
  `DeliveryMethod` SMALLINT(6) NOT NULL,
  `PaymentStatus` BIT(1) NOT NULL,
  `OrderStatusId` SMALLINT(6) NOT NULL,
  `DateOfCreation` DATETIME(6) NOT NULL,
  `DateOfSale` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`OrderId`),
  INDEX `fk_orders_clients1_idx` (`ClientId` ASC),
  INDEX `fk_orders_clientaddress1_idx` (`ClientAddressId` ASC),
  CONSTRAINT `fk_orders_clientaddress1`
    FOREIGN KEY (`ClientAddressId`)
    REFERENCES `dbo`.`clientaddress` (`ClientAddressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_clients1`
    FOREIGN KEY (`ClientId`)
    REFERENCES `dbo`.`clients` (`ClientId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbo`.`orderdetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`orderdetails` (
  `OrderId` BIGINT NOT NULL,
  `GoodId` BIGINT NOT NULL,
  `Quantity` INT(11) NOT NULL,
  PRIMARY KEY (`OrderId`,`GoodId`),
  INDEX `fk_ordergoods_orders_idx` (`OrderId` ASC),
  INDEX `fk_ordergoods_goods1_idx` (`GoodId` ASC),
  CONSTRAINT `fk_ordergoods_goods1`
    FOREIGN KEY (`GoodId`)
    REFERENCES `dbo`.`goods` (`GoodId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordergoods_orders`
    FOREIGN KEY (`OrderId`)
    REFERENCES `dbo`.`orders` (`OrderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;