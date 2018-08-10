-- auto Generated on 2018-08-08 16:40:51 
-- DROP TABLE IF EXISTS `sys_role`; 
CREATE TABLE `sys_role`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'role',
    `description` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'description',
    `available` TINYINT (3) NOT NULL DEFAULT 0 COMMENT 'available',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`sys_role`';
