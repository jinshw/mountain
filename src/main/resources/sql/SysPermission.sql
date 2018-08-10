-- auto Generated on 2018-08-08 16:42:31 
-- DROP TABLE IF EXISTS `sys_permission`; 
CREATE TABLE `sys_permission`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
    `resource_type` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'resourceType',
    `url` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'url',
    `permission` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'permission',
    `parent_id` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'parentId',
    `parent_ids` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'parentIds',
    `available` TINYINT (3) NOT NULL DEFAULT 0 COMMENT 'available',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`sys_permission`';
