SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `secyield` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `secyield` ;

-- -----------------------------------------------------
-- Table `secyield`.`customer_security_sec_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `secyield`.`customer_security_sec_data` (
  `security_identifier` VARCHAR(128) NOT NULL,
  `report_date` DATETIME NOT NULL,
  `iv_type` VARCHAR(24) NOT NULL,
  `security_name` VARCHAR(512) NOT NULL,
  `final_maturity_date` DATETIME NOT NULL,
  `security_redemption_price` DECIMAL(20,7) NOT NULL,
  `interest_rt` DECIMAL(20,7) NOT NULL,
  `def_indicator` TINYINT(1) NULL,
  `der_step_indicator` TINYINT(1) NULL,
  `der_hybrid_indicator` TINYINT(1) NULL,
  `security_price` DECIMAL(20,7) NOT NULL,
  `fx_rate` DECIMAL(20,7) NOT NULL,
  `der_tips_inflationary_ratio` DECIMAL(20,7) NULL,
  `io_hybrid_field` VARCHAR(128) NULL,
  `as_400_rate_type` VARCHAR(128) NULL,
  `prospective_method` VARCHAR(128) NULL,
  PRIMARY KEY (`security_identifier`, `report_date`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `secyield`.`customer_fund_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `secyield`.`customer_fund_data` (
  `security_identifier` VARCHAR(128) NOT NULL,
  `portfolio_number` DECIMAL(20,7) NOT NULL,
  `portfolio_name` VARCHAR(256) NOT NULL,
  `report_date` DATETIME NOT NULL,
  PRIMARY KEY (`portfolio_number`, `report_date`),
  INDEX `customer_fd_fk_idx` (`security_identifier` ASC, `report_date` ASC),
  CONSTRAINT `customer_fd_s_fk`
    FOREIGN KEY (`security_identifier` , `report_date`)
    REFERENCES `secyield`.`customer_security_sec_data` (`security_identifier` , `report_date`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `secyield`.`customer_position_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `secyield`.`customer_position_data` (
  `security_identifier` VARCHAR(128) NOT NULL,
  `portfolio_number` DECIMAL(20,7) NOT NULL,
  `report_date` DATETIME NOT NULL,
  `earned_inflationary_compensation_base` DECIMAL(20,7) NULL,
  `accrued_income` DECIMAL(20,7) NOT NULL,
  `market_value` DECIMAL(20,7) NOT NULL,
  `share_per_amount` DECIMAL(20,7) NOT NULL,
  `earned_amortization_base` DECIMAL(20,7) NOT NULL,
  `position_val_inflation_adj_shares` DECIMAL(20,7) NULL,
  PRIMARY KEY (`portfolio_number`, `report_date`, `security_identifier`),
  INDEX `customer_fd_fk_idx` (`security_identifier` ASC, `report_date` ASC),
  CONSTRAINT `customer_pd_s_fk`
    FOREIGN KEY (`security_identifier` , `report_date`)
    REFERENCES `secyield`.`customer_security_sec_data` (`security_identifier` , `report_date`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `secyield`.`calculated_security_sec_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `secyield`.`calculated_security_sec_data` (
  `security_identifier` VARCHAR(128) NOT NULL,
  `report_date` DATETIME NOT NULL,
  `iv_type` VARCHAR(24) NOT NULL,
  `security_name` VARCHAR(512) NOT NULL,
  `final_maturity_date` DATETIME NOT NULL,
  `security_redemption_price` DECIMAL(20,7) NOT NULL,
  `interest_rt` DECIMAL(20,7) NOT NULL,
  `def_indicator` TINYINT(1) NULL,
  `der_step_indicator` TINYINT(1) NULL,
  `der_hybrid_indicator` TINYINT(1) NULL,
  `security_price` DECIMAL(20,7) NOT NULL,
  `fx_rate` DECIMAL(20,7) NOT NULL,
  `der_tips_inflationary_ratio` DECIMAL(20,7) NULL,
  `io_hybrid_field` VARCHAR(128) NULL,
  `as_400_rate_type` VARCHAR(128) NULL,
  `prospective_method` VARCHAR(128) NULL,
  `der_yield_calc_engine` VARCHAR(128) NULL,
  `der_income_calc_engine` VARCHAR(128) NULL,
  `der_one_day_secyrity_yield` DECIMAL(20,7) NULL,
  `der_redemption_date` DATETIME NULL,
  `der_redemption_price` DECIMAL(20,7) NULL,
  `der_clean_price` DECIMAL(20,7) NULL,
  `der_security_type` VARCHAR(128) NULL,
  PRIMARY KEY (`security_identifier`, `report_date`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `secyield`.`calculated_fund_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `secyield`.`calculated_fund_data` (
  `security_identifier` VARCHAR(128) NOT NULL,
  `portfolio_number` DECIMAL(20,7) NOT NULL,
  `portfolio_name` VARCHAR(256) NOT NULL,
  `report_date` DATETIME NOT NULL,
  PRIMARY KEY (`portfolio_number`, `report_date`),
  INDEX `customer_fd_fk_idx` (`security_identifier` ASC, `report_date` ASC),
  CONSTRAINT `customer_fd_s_fk0`
    FOREIGN KEY (`security_identifier` , `report_date`)
    REFERENCES `secyield`.`calculated_security_sec_data` (`security_identifier` , `report_date`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `secyield`.`calculated_position_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `secyield`.`calculated_position_data` (
  `security_identifier` VARCHAR(128) NOT NULL,
  `portfolio_number` DECIMAL(20,7) NOT NULL,
  `report_date` DATETIME NOT NULL,
  `earned_inflationary_compensation_base` DECIMAL(20,7) NULL,
  `accrued_income` DECIMAL(20,7) NOT NULL,
  `market_value` DECIMAL(20,7) NOT NULL,
  `share_per_amount` DECIMAL(20,7) NOT NULL,
  `earned_amortization_base` DECIMAL(20,7) NOT NULL,
  `position_val_inflation_adj_shares` DECIMAL(20,7) NULL,
  `der_one_day_security_income` DECIMAL(20,7) NULL,
  PRIMARY KEY (`portfolio_number`, `report_date`, `security_identifier`),
  INDEX `customer_fd_fk_idx` (`security_identifier` ASC, `report_date` ASC),
  CONSTRAINT `customer_pd_s_fk0`
    FOREIGN KEY (`security_identifier` , `report_date`)
    REFERENCES `secyield`.`calculated_security_sec_data` (`security_identifier` , `report_date`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;