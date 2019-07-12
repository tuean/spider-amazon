CREATE TABLE `amazon`.`product` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `asin` VARCHAR(45) NULL,
  `product_name` VARCHAR(100) NULL,
  `price` VARCHAR(45) NULL,
  `commentNum` INT NULL,
  `grade` VARCHAR(45) NULL,
  `picUrl` VARCHAR(300) NULL,
  `salesNum` INT NULL,
  `productDetailUrl` VARCHAR(300) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `amazon`.`price_history` (
  `id` INT UNSIGNED NOT NULL,
  `asin` VARCHAR(45) NULL,
  `price` VARCHAR(45) NULL,
  `create_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`));
