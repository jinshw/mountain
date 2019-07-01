/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50726
Source Host           : 127.0.0.1:3306
Source Database       : mountain

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-07-01 18:13:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(60) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('-1', null, '一级部门', '0', '0');
INSERT INTO `sys_dept` VALUES ('1', '-1', '中交预科', '4', '0');
INSERT INTO `sys_dept` VALUES ('17', '1', '采购一部', '0', '-1');
INSERT INTO `sys_dept` VALUES ('18', '-1', '中软国际', '0', '0');
INSERT INTO `sys_dept` VALUES ('19', '-1', '东软集团', '0', '0');
INSERT INTO `sys_dept` VALUES ('20', '19', '交通事业部', '0', '0');
INSERT INTO `sys_dept` VALUES ('21', '18', '文化事业部', '0', '0');
INSERT INTO `sys_dept` VALUES ('22', '1', '22222', '2', '0');
INSERT INTO `sys_dept` VALUES ('23', '22', '33333', '0', '-1');
INSERT INTO `sys_dept` VALUES ('24', '22', '33333', '0', '-1');
INSERT INTO `sys_dept` VALUES ('25', '22', '55555555', '0', '-1');
INSERT INTO `sys_dept` VALUES ('27', '26', '6666', '0', '-1');
INSERT INTO `sys_dept` VALUES ('28', '-1', '1111', '0', '-1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(512) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('-1', null, '一级菜单', null, null, '0', null, null);
INSERT INTO `sys_menu` VALUES ('14', '-1', '权限管理', '', '', '0', '', '0');
INSERT INTO `sys_menu` VALUES ('16', '14', '菜单管理', '', 'userInfo:view', '1', '', '4');
INSERT INTO `sys_menu` VALUES ('22', '14', '部门管理', 'sysdept/getTree', 'sys:dept:list', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('23', '16', '菜单新增', '', 'menu:add', '2', '', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` text COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', null, null, '2018-10-23 17:33:58');
INSERT INTO `sys_role` VALUES ('24', '333', '333', null, '2019-07-01 10:06:27');
INSERT INTO `sys_role` VALUES ('25', '444', '4444', null, '2019-07-01 10:07:02');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `dept_id` (`dept_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `sys_role_dept_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_dept_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('1', '1', '1');
INSERT INTO `sys_role_dept` VALUES ('46', '-1', '24');
INSERT INTO `sys_role_dept` VALUES ('47', '18', '24');
INSERT INTO `sys_role_dept` VALUES ('48', '21', '24');
INSERT INTO `sys_role_dept` VALUES ('49', '19', '24');
INSERT INTO `sys_role_dept` VALUES ('50', '20', '24');
INSERT INTO `sys_role_dept` VALUES ('51', '1', '24');
INSERT INTO `sys_role_dept` VALUES ('52', '22', '24');
INSERT INTO `sys_role_dept` VALUES ('54', '19', '25');
INSERT INTO `sys_role_dept` VALUES ('55', '20', '25');
INSERT INTO `sys_role_dept` VALUES ('56', '28', '25');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('16', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('-1', '24', '65');
INSERT INTO `sys_role_menu` VALUES ('14', '24', '66');
INSERT INTO `sys_role_menu` VALUES ('22', '24', '67');
INSERT INTO `sys_role_menu` VALUES ('16', '24', '68');
INSERT INTO `sys_role_menu` VALUES ('23', '24', '69');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` int(11) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', null, null, '0', null, '2019-06-23 16:24:38');
INSERT INTO `sys_user` VALUES ('2', 'jinshw', 'jinshw', '12131313', '', '1', null, '2019-06-24 11:01:20');
INSERT INTO `sys_user` VALUES ('42', 'aaa', 'aaaa', 'aa@ss.cd', '', '1', null, '2019-07-01 17:57:41');
INSERT INTO `sys_user` VALUES ('43', 'bbbbb', 'bbbbb', 'bbb@ww.dd', '', '1', null, '2019-07-01 18:00:00');
INSERT INTO `sys_user` VALUES ('44', 'ggggg', 'ggggg', 'ggg@ggg.vvv', 'sss', '1', null, '2019-07-01 18:12:47');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
