CREATE SCHEMA `telefonia`;

CREATE TABLE `telefonia`.`endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(255) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  `cidade` VARCHAR(255) NOT NULL,
  `numero` VARCHAR(255) NOT NULL,
  `cep` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `telefonia`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `id_endereco` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  INDEX `id_endereco_idx` (`id_endereco` ASC) VISIBLE,
  CONSTRAINT `id_endereco`
    FOREIGN KEY (`id_endereco`)
    REFERENCES `telefonia`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
 CREATE TABLE `telefonia`.`telefone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ddd` VARCHAR(2) NOT NULL,
  `numero` VARCHAR(10) NOT NULL,
  `tipo` INT NOT NULL COMMENT 'Fixo: 1\nMóvel: 2',
  `ativo` TINYINT NOT NULL,
  PRIMARY KEY (`id`));

 CREATE TABLE `telefonia`.`linha_telefonica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_telefone` INT NOT NULL,
  `id_cliente` INT NULL,
  `dt_ativacao` DATETIME NULL,
  `dt_desativacao` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `id_telefone_idx` (`id_telefone` ASC) VISIBLE,
  CONSTRAINT `id_telefone`
    FOREIGN KEY (`id_telefone`)
    REFERENCES `telefonia`.`telefone` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
