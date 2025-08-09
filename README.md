# Spring-con-java---trabajo-para-P.O.Objetos-2
este es el trabajo que desarrollamos con mi grupo de cursada en la materia POObjetos 2

fue documentado con Swagger con controllers REST, en caso de que se quieran probar algunas funcionalidades orientadas a un front-end independiente.

este proyecto opera con JAVA 21 y MAVEN. su front fue formado a partir de thymeleaf, la siguientes configuraciones son vitales para que la api opere con normalidad...

variables de entorno requeridas:

      spring:
        datasource:
          url: ${DB_URL}
          username: ${DB_USER}
          password: ${DB_PASSWORD}
      
        jpa:
          show-sql: true
      
        mail:
          host: ${MAIL_HOST}
          port: ${MAIL_PORT}
          username: ${MAIL_USER}
          password: ${MAIL_PASSWORD}
          properties:
            mail:
              smtp:
                auth: true
                starttls:
                  enable: true

envio de mail:

        MAIL_HOST=smtp.gmail.com
        MAIL_PASSWORD=qejmqnizhzxzqqkn
        MAIL_PORT=587
        MAIL_USER=ticketerasoporte@gmail.com

base de datos SQL - mySQLserver:


        -- MySQL Workbench Forward Engineering
        
        SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
        SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
        SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
        
        CREATE SCHEMA IF NOT EXISTS `bd-ticketera` DEFAULT CHARACTER SET utf8mb4 ;
        USE `bd-ticketera` ;
        
        -- Tabla persona
        CREATE TABLE IF NOT EXISTS `persona` (
          `id_persona` INT NOT NULL AUTO_INCREMENT,
          `nombre` VARCHAR(45) NOT NULL,
          `apellido` VARCHAR(45) NOT NULL,
          `dni` INT NOT NULL,
          PRIMARY KEY (`id_persona`)
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla area
        CREATE TABLE IF NOT EXISTS `area` (
          `id_area` INT NOT NULL AUTO_INCREMENT,
          `nombre` VARCHAR(100) NOT NULL,
          PRIMARY KEY (`id_area`)
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla empleado
        CREATE TABLE IF NOT EXISTS `empleado` (
          `persona_id_persona` INT NOT NULL,
          `rol` VARCHAR(45) NOT NULL,
          `legajo` VARCHAR(45) NOT NULL,
          `area_id_area` INT NULL,
          PRIMARY KEY (`persona_id_persona`),
          INDEX `fk_empleado_persona1_idx` (`persona_id_persona` ASC) VISIBLE,
          INDEX `fk_empleado_area_idx` (`area_id_area` ASC) VISIBLE,
          CONSTRAINT `fk_empleado_persona1`
            FOREIGN KEY (`persona_id_persona`)
            REFERENCES `persona` (`id_persona`)
            ON DELETE CASCADE,
          CONSTRAINT `fk_empleado_area`
            FOREIGN KEY (`area_id_area`)
            REFERENCES `area` (`id_area`)
            ON DELETE SET NULL
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla tipo_de_ticket
        CREATE TABLE IF NOT EXISTS `tipo_de_ticket` (
          `id_tipo_de_ticket` INT NOT NULL AUTO_INCREMENT,
          `tipo` VARCHAR(45) NOT NULL,
          PRIMARY KEY (`id_tipo_de_ticket`)
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla cliente
        CREATE TABLE IF NOT EXISTS `cliente` (
          `persona_id_persona` INT NOT NULL,
          `nro_cliente` VARCHAR(45) NOT NULL,
          PRIMARY KEY (`persona_id_persona`),
          INDEX `fk_cliente_persona1_idx` (`persona_id_persona` ASC) VISIBLE,
          CONSTRAINT `fk_cliente_persona1`
            FOREIGN KEY (`persona_id_persona`)
            REFERENCES `persona` (`id_persona`)
            ON DELETE CASCADE
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla estado
        CREATE TABLE IF NOT EXISTS `estado` (
          `id_estado` INT NOT NULL AUTO_INCREMENT,
          `tipo` VARCHAR(45) NOT NULL,
          PRIMARY KEY (`id_estado`)
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla prioridad
        CREATE TABLE IF NOT EXISTS `prioridad` (
          `id_prioridad` INT NOT NULL AUTO_INCREMENT,
          `tipo` VARCHAR(45) NOT NULL,
          PRIMARY KEY (`id_prioridad`)
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla ticket
        CREATE TABLE IF NOT EXISTS `ticket` (
          `id_ticket` INT NOT NULL AUTO_INCREMENT,
          `titulo` VARCHAR(45) NOT NULL,
          `descripcion` VARCHAR(255) NOT NULL,
          `fecha_creacion` DATETIME NOT NULL,
          `fecha_cierre` DATETIME NULL DEFAULT NULL,
          `cliente_id_persona` INT NULL,
          `empleado_id_persona` INT NULL,
          `tipo_ticket_id` INT NULL,
          `prioridad_id` INT NULL,
          `estado_id` INT NULL,
          PRIMARY KEY (`id_ticket`),
          INDEX `fk_ticket_cliente1_idx` (`cliente_id_persona` ASC) VISIBLE,
          INDEX `fk_ticket_empleado1_idx` (`empleado_id_persona` ASC) VISIBLE,
          INDEX `fk_ticket_tipo1_idx` (`tipo_ticket_id` ASC) VISIBLE,
          INDEX `fk_ticket_prioridad1_idx` (`prioridad_id` ASC) VISIBLE,
          INDEX `fk_ticket_estado1_idx` (`estado_id` ASC) VISIBLE,
          CONSTRAINT `fk_ticket_tipo1`
            FOREIGN KEY (`tipo_ticket_id`)
            REFERENCES `tipo_de_ticket` (`id_tipo_de_ticket`),
          CONSTRAINT `fk_ticket_cliente1`
            FOREIGN KEY (`cliente_id_persona`)
            REFERENCES `cliente` (`persona_id_persona`),
            CONSTRAINT `fk_ticket_empleado1`
            FOREIGN KEY (`empleado_id_persona`)
            REFERENCES `empleado` (`persona_id_persona`),
          CONSTRAINT `fk_ticket_estado1`
            FOREIGN KEY (`estado_id`)
            REFERENCES `estado` (`id_estado`),
          CONSTRAINT `fk_ticket_prioridad1`
            FOREIGN KEY (`prioridad_id`)
            REFERENCES `prioridad` (`id_prioridad`)
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla actualizacion
        CREATE TABLE IF NOT EXISTS `actualizacion` (
          `id_actualizacion` INT NOT NULL AUTO_INCREMENT,
          `contenido` VARCHAR(100) NOT NULL,
          `fecha_actualizacion` DATETIME NOT NULL,
          `empleado_id_persona` INT NOT NULL,
          `ticket_id` INT NOT NULL,
          PRIMARY KEY (`id_actualizacion`),
          INDEX `fk_actualizacion_empleado1_idx` (`empleado_id_persona` ASC) VISIBLE,
          INDEX `fk_actualizacion_ticket1_idx` (`ticket_id` ASC) VISIBLE,
          CONSTRAINT `fk_actualizacion_empleado1`
            FOREIGN KEY (`empleado_id_persona`)
            REFERENCES `empleado` (`persona_id_persona`),
          CONSTRAINT `fk_actualizacion_ticket1`
            FOREIGN KEY (`ticket_id`)
            REFERENCES `ticket` (`id_ticket`)
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla contacto
        CREATE TABLE IF NOT EXISTS `contacto` (
          `id_contacto` INT NOT NULL AUTO_INCREMENT,
          `telefono` VARCHAR(20) NOT NULL,
          `email` VARCHAR(45) NULL DEFAULT NULL,
          `persona_id_persona` INT NOT NULL,
          PRIMARY KEY (`id_contacto`),
          INDEX `fk_contacto_persona_idx` (`persona_id_persona`),
          CONSTRAINT `fk_contacto_persona`
            FOREIGN KEY (`persona_id_persona`)
            REFERENCES `persona` (`id_persona`)
            ON DELETE CASCADE
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        -- Tabla login
        CREATE TABLE IF NOT EXISTS `login` (
          `id_login` INT NOT NULL AUTO_INCREMENT,
          `correo` VARCHAR(50) NOT NULL UNIQUE,
          `contrasenia` VARCHAR(100) NOT NULL,
          `persona_id_persona` INT NOT NULL,
          PRIMARY KEY (`id_login`),
          INDEX `fk_login_persona_idx` (`persona_id_persona`),
          CONSTRAINT `fk_login_persona`
            FOREIGN KEY (`persona_id_persona`)
            REFERENCES `persona` (`id_persona`)
            ON DELETE CASCADE
        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;
        
        /* PRIORIDAD */
        INSERT INTO prioridad VALUES (1, 'Alta'), (2, 'Media'), (3, 'Baja');
        
        /* TIPO DE TICKET*/
        INSERT INTO tipo_de_ticket VALUES (1, 'Consulta'), (2, 'Reclamo'), (3, 'Sugerencia'), (4, 'Incidencia'), (5, 'Solicitud');
        
        /* ESTADO */
        INSERT INTO estado VALUES (1, 'Abierto'), (2, 'Cerrado'), (3, 'A la espera');
        
        /* AREA */
        INSERT INTO area VALUES (1, 'Recursos humanos'), (2, 'Soporte tecnico'), (3, 'Infraestructura'), (4, 'Administracion'), (5, 'Mesa de ayuda'), (6, 'Sistemas');
        
        /* PERSONA */
        INSERT INTO persona VALUES (1, 'Admin', 'Admin', 11111111);
        INSERT INTO persona VALUES (2, 'Axel', 'De Pietro', 45131639);
        INSERT INTO persona VALUES (3, 'Marcos', 'Tambosco', 39708890);
        INSERT INTO persona VALUES (4, 'Julian', 'Sorli', 43384704); 
        INSERT INTO persona VALUES (5, 'Anahi', 'Mansilla', 38148699); 
        
        /* EMPLEADO */ 
        INSERT INTO empleado VALUE (1, 'Admin', 1111, 4);
        INSERT INTO empleado VALUES (2, 'Trainee', 1639, 2);
        INSERT INTO empleado VALUES(3, 'Senior', 8890, 5); 
        INSERT INTO empleado VALUES(4, 'QA', 4704, 3);  
        INSERT INTO empleado VALUES(5, 'Junior', 8699, 6);
        
        /* CONTACTO */
        INSERT INTO contacto VALUES (6, 1511487530, 'admin@gmail.com', 1);
        INSERT INTO contacto VALUES (3, 1598417800, 'axel@gmail.com', 2);
        INSERT INTO contacto VALUES (4, 1520153648, 'marcos@gmail.com', 3);
        INSERT INTO contacto VALUES (5, 1598416302, 'julian@gmail.com', 4);
        INSERT INTO contacto VALUES (2, 1533485933, 'anahi@gmail.com', 5);
        
        /* LOGIN */
        INSERT INTO login VALUES (6, 'admin@gmail.com', '$2a$12$pRhB1GTQrpd9Sgn32Mw3FuNF2.w12icGKrvCXbQZgc8EwKo9wT6oe', 1);
        INSERT INTO login VALUES (3, 'axel@gmail.com', '$2a$12$mS2/lfGfCj/y6IHxtYSNnemH.94GcQsGxDj4oJWtjFl60s6ISaTma', 2);
        INSERT INTO login VALUES (4, 'marcos@gmail.com', '$2a$12$s77Ze0sOyqUvLLdpWWP6sOCwOcd.gvHk0Y0papVNSmYlkGbLn7v4O', 3);
        INSERT INTO login VALUES (5, 'julian@gmail.com', '$2a$12$BlFICJeYnxmBhlEoBhFubO8f724V8jKPCJFxnS4GJ75nekD6ddjJW', 4);
        INSERT INTO login VALUES (2, 'anahi@gmail.com', '$2a$12$lvq8AV6sNY0nQurk9iveMuQE5lwkg/bF0wzXgm1RqIBvvsUWCMyxe', 5);
        
        SET SQL_MODE=@OLD_SQL_MODE;
        SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
        SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
        
        
        bd-tiketera-snakecase.sql
        Mostrando bd-tiketera-snakecase.sql
