-- MySQL Script generated by MySQL Workbench
-- 04/12/17 19:23:17
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ekeberg
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ekeberg` ;

-- -----------------------------------------------------
-- Schema ekeberg
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ekeberg` DEFAULT CHARACTER SET utf8 ;
USE `ekeberg` ;

-- -----------------------------------------------------
-- Table `ekeberg`.`Menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ekeberg`.`Menu` ;

CREATE TABLE IF NOT EXISTS `ekeberg`.`Menu` (
  `idMenu` INT NOT NULL AUTO_INCREMENT,
  `merke` VARCHAR(60) NULL,
  `type` VARCHAR(150) NULL,
  `studentPris` DOUBLE NULL,
  `ansattPris` DOUBLE NULL,
  `kategori` VARCHAR(45) NULL,
  PRIMARY KEY (`idMenu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ekeberg`.`OpeningHours`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ekeberg`.`OpeningHours` ;

CREATE TABLE IF NOT EXISTS `ekeberg`.`OpeningHours` (
  `idÅpningstider` INT NOT NULL AUTO_INCREMENT,
  `openingHours` VARCHAR(60) NULL,
  `day` VARCHAR(20) NULL,
  `announcement` MEDIUMTEXT NULL,
  PRIMARY KEY (`idÅpningstider`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ekeberg`.`DrMeny`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ekeberg`.`DrMeny` ;

CREATE TABLE IF NOT EXISTS `ekeberg`.`DrMeny` (
  `idDRmeny` INT NOT NULL AUTO_INCREMENT,
  `navn` VARCHAR(45) NULL,
  `serveringstid` VARCHAR(45) NULL,
  `studentPris` DOUBLE NULL,
  `ansattPris` DOUBLE NULL,
  `dag` VARCHAR(45) NULL,
  PRIMARY KEY (`idDRmeny`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ekeberg`.`Allergier`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ekeberg`.`Allergier` ;

CREATE TABLE IF NOT EXISTS `ekeberg`.`Allergier` (
  `idAlergier` INT NOT NULL AUTO_INCREMENT,
  `allergi` VARCHAR(45) NULL,
  PRIMARY KEY (`idAlergier`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ekeberg`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ekeberg`.`users` ;

CREATE TABLE IF NOT EXISTS `ekeberg`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `unique_id` VARCHAR(23) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `encrypted_password` VARCHAR(80) NOT NULL,
  `salt` VARCHAR(10) NOT NULL,
  `coffee` INT NOT NULL DEFAULT 0,
  `student` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uniqueId_UNIQUE` (`unique_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ekeberg`.`Allergier_has_DrMeny`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ekeberg`.`Allergier_has_DrMeny` ;

CREATE TABLE IF NOT EXISTS `ekeberg`.`Allergier_has_DrMeny` (
  `allergier_idAlergier` INT NOT NULL,
  `drmeny_idDRmeny` INT NOT NULL,
  PRIMARY KEY (`allergier_idAlergier`, `drmeny_idDRmeny`),
  INDEX `fk_almeny_DRmeny1_idx` (`drmeny_idDRmeny` ASC),
  CONSTRAINT `fk_almeny_Allergier1`
    FOREIGN KEY (`allergier_idAlergier`)
    REFERENCES `ekeberg`.`Allergier` (`idAlergier`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_almeny_DRmeny1`
    FOREIGN KEY (`drmeny_idDRmeny`)
    REFERENCES `ekeberg`.`DrMeny` (`idDRmeny`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ekeberg`.`Coffee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ekeberg`.`Coffee` ;

CREATE TABLE IF NOT EXISTS `ekeberg`.`Coffee` (
  `qrString` VARCHAR(45) NOT NULL,
  `pin` INT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`qrString`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `ekeberg`.`Menu`
-- -----------------------------------------------------
START TRANSACTION;
USE `ekeberg`;
INSERT INTO `ekeberg`.`Menu` (`idMenu`, `merke`, `type`, `studentPris`, `ansattPris`, `kategori`) VALUES (1, 'Litago', 'Sjokolademelk', 25, 27, 'Drikke');
INSERT INTO `ekeberg`.`Menu` (`idMenu`, `merke`, `type`, `studentPris`, `ansattPris`, `kategori`) VALUES (2, 'Litago', 'Jordbærmelk', 25, 27, 'Drikke');
INSERT INTO `ekeberg`.`Menu` (`idMenu`, `merke`, `type`, `studentPris`, `ansattPris`, `kategori`) VALUES (3, 'Tine', 'IsTe hvite te med guava', 23, 25, 'Drikke');
INSERT INTO `ekeberg`.`Menu` (`idMenu`, `merke`, `type`, `studentPris`, `ansattPris`, `kategori`) VALUES (4, 'Firele', 'Kaffe', 12, 15, 'Drikke');
INSERT INTO `ekeberg`.`Menu` (`idMenu`, `merke`, `type`, `studentPris`, `ansattPris`, `kategori`) VALUES (5, 'Toast', 'Stabburdet', 29, 35, 'Mat');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ekeberg`.`OpeningHours`
-- -----------------------------------------------------
START TRANSACTION;
USE `ekeberg`;
INSERT INTO `ekeberg`.`OpeningHours` (`idÅpningstider`, `openingHours`, `day`, `announcement`) VALUES (1, '08:00 - 15:00', 'monday', 'Test-Monday');
INSERT INTO `ekeberg`.`OpeningHours` (`idÅpningstider`, `openingHours`, `day`, `announcement`) VALUES (2, '08:00  - 14:00', 'tuesday', 'Test-Thuesday');
INSERT INTO `ekeberg`.`OpeningHours` (`idÅpningstider`, `openingHours`, `day`, `announcement`) VALUES (3, '08:00 - 15:00', 'wednesday', 'Test-Wednesday');
INSERT INTO `ekeberg`.`OpeningHours` (`idÅpningstider`, `openingHours`, `day`, `announcement`) VALUES (4, '08:00 - 14:00', 'thursday', 'Test-Thursdag');
INSERT INTO `ekeberg`.`OpeningHours` (`idÅpningstider`, `openingHours`, `day`, `announcement`) VALUES (5, '08:00 - 13:00', 'friday', 'Test-Friday');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ekeberg`.`DrMeny`
-- -----------------------------------------------------
START TRANSACTION;
USE `ekeberg`;
INSERT INTO `ekeberg`.`DrMeny` (`idDRmeny`, `navn`, `serveringstid`, `studentPris`, `ansattPris`, `dag`) VALUES (1, 'Pizza', '11:00 - 15:00', 44, 50, 'monday');
INSERT INTO `ekeberg`.`DrMeny` (`idDRmeny`, `navn`, `serveringstid`, `studentPris`, `ansattPris`, `dag`) VALUES (2, 'Hamburger', '11:00 - 13:00', 49, 57, 'tuesday');
INSERT INTO `ekeberg`.`DrMeny` (`idDRmeny`, `navn`, `serveringstid`, `studentPris`, `ansattPris`, `dag`) VALUES (3, 'Laks', '11:00 - 14:00', 45, 49, 'wednesday');
INSERT INTO `ekeberg`.`DrMeny` (`idDRmeny`, `navn`, `serveringstid`, `studentPris`, `ansattPris`, `dag`) VALUES (4, 'Tomatsuppe', '11:00 - 14:00', 29, 33, 'thursday');
INSERT INTO `ekeberg`.`DrMeny` (`idDRmeny`, `navn`, `serveringstid`, `studentPris`, `ansattPris`, `dag`) VALUES (5, 'Fiskepinner', '11:00 - 14:30', 38, 45, 'friday');
INSERT INTO `ekeberg`.`DrMeny` (`idDRmeny`, `navn`, `serveringstid`, `studentPris`, `ansattPris`, `dag`) VALUES (6, 'Pytt i panne', '09:00 - 10:00', 70, 80, 'saturday');
INSERT INTO `ekeberg`.`DrMeny` (`idDRmeny`, `navn`, `serveringstid`, `studentPris`, `ansattPris`, `dag`) VALUES (7, 'Pannekaker', '09:00 - 12:00', 100, 110, 'sunday');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ekeberg`.`Allergier`
-- -----------------------------------------------------
START TRANSACTION;
USE `ekeberg`;
INSERT INTO `ekeberg`.`Allergier` (`idAlergier`, `allergi`) VALUES (1, 'Gluten');
INSERT INTO `ekeberg`.`Allergier` (`idAlergier`, `allergi`) VALUES (2, 'Soya');
INSERT INTO `ekeberg`.`Allergier` (`idAlergier`, `allergi`) VALUES (3, 'Laktose');
INSERT INTO `ekeberg`.`Allergier` (`idAlergier`, `allergi`) VALUES (4, 'Skalldyr');
INSERT INTO `ekeberg`.`Allergier` (`idAlergier`, `allergi`) VALUES (5, 'Egg');
INSERT INTO `ekeberg`.`Allergier` (`idAlergier`, `allergi`) VALUES (6, 'Nøtt');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ekeberg`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `ekeberg`;
INSERT INTO `ekeberg`.`users` (`id`, `unique_id`, `email`, `encrypted_password`, `salt`, `coffee`, `student`) VALUES (1, '58e34cfe7af046.16599741', 'laupet', 'LigiuFG+mHurGukliBPOMCtsHPgyNGVhNzRkMzE4', '24ea74d318', 12, 1);
INSERT INTO `ekeberg`.`users` (`id`, `unique_id`, `email`, `encrypted_password`, `salt`, `coffee`, `student`) VALUES (2, '58ee429f8bc532.60195336', 'Test', 'B/d+kQO70QEIy82cTxxj4ZUOwT5lNWVkYmFlODlh', 'e5edbae89a', 12, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ekeberg`.`Allergier_has_DrMeny`
-- -----------------------------------------------------
START TRANSACTION;
USE `ekeberg`;
INSERT INTO `ekeberg`.`Allergier_has_DrMeny` (`allergier_idAlergier`, `drmeny_idDRmeny`) VALUES (2, 4);
INSERT INTO `ekeberg`.`Allergier_has_DrMeny` (`allergier_idAlergier`, `drmeny_idDRmeny`) VALUES (4, 3);
INSERT INTO `ekeberg`.`Allergier_has_DrMeny` (`allergier_idAlergier`, `drmeny_idDRmeny`) VALUES (1, 3);
INSERT INTO `ekeberg`.`Allergier_has_DrMeny` (`allergier_idAlergier`, `drmeny_idDRmeny`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ekeberg`.`Coffee`
-- -----------------------------------------------------
START TRANSACTION;
USE `ekeberg`;
INSERT INTO `ekeberg`.`Coffee` (`qrString`, `pin`, `description`) VALUES ('4YzY3vyBEl0gPoxuvCAB', 1304, 'refill');
INSERT INTO `ekeberg`.`Coffee` (`qrString`, `pin`, `description`) VALUES ('cOFD87zuFbrYdRfKwz5m', 0, 'buy');

COMMIT;

