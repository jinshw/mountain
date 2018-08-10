-- auto Generated on 2018-08-08 16:20:05 
-- DROP TABLE IF EXISTS `user_info`; 
CREATE TABLE `user_info`(
    `uid` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'uid',
    `username` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'username',
    `name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
    `password` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'password',
    `salt` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'salt',
    `state` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'state',
    PRIMARY KEY (`uid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`user_info`';
