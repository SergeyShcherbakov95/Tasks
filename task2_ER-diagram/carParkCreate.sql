-- MySQL Workbench Synchronization
-- Generated: 2016-03-15 01:36
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Sergey

CREATE SCHEMA IF NOT EXISTS `CarPark` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `CarPark`.`Drivers` (
  `idDriver` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(150) NOT NULL,
  `dateOfBirth` DATE NOT NULL,
  `phoneNumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDriver`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `CarPark`.`CarParks` (
  `idCarPark` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `adress` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCarPark`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `CarPark`.`Cars` (
  `idCar` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `Carscol` YEAR NOT NULL,
  `idCarPark` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`idCar`),
  INDEX `idPark_idx` (`idCarPark` ASC),
    FOREIGN KEY (`idCarPark`)
    REFERENCES `CarPark`.`CarParks` (`idCarPark`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `CarPark`.`DriversAndCarParks` (
  `idDriver` INT(10) UNSIGNED NOT NULL,
  `idCarPark` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idDriver`, `idCarPark`),
  INDEX `idCarPark_idx` (`idCarPark` ASC),
    FOREIGN KEY (`idDriver`)
    REFERENCES `CarPark`.`Drivers` (`idDriver`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idCarPark`)
    REFERENCES `CarPark`.`CarParks` (`idCarPark`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `CarPark`.`DriversAndCars` (
  `idDriver` INT(10) UNSIGNED NOT NULL,
  `idCar` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idDriver`, `idCar`),
  INDEX `idCar_idx` (`idCar` ASC),
    FOREIGN KEY (`idDriver`)
    REFERENCES `CarPark`.`Drivers` (`idDriver`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`idCar`)
    REFERENCES `CarPark`.`Cars` (`idCar`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

