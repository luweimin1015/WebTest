CREATE TABLE `lwm`.`movie_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `chinese_name` VARCHAR(45) NULL,
  `english_name` VARCHAR(50) NULL,
  `score` FLOAT NULL,
  `person_num` FLOAT NULL,
  `director` VARCHAR(45) NULL,
  `actor` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));