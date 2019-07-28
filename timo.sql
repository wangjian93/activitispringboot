/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : timo2

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 07/04/2019 22:35:35 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `or_te`
-- ----------------------------
DROP TABLE IF EXISTS `or_te`;
CREATE TABLE `or_te` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `or_test`
-- ----------------------------
DROP TABLE IF EXISTS `or_test`;
CREATE TABLE `or_test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `or_tt`
-- ----------------------------
DROP TABLE IF EXISTS `or_tt`;
CREATE TABLE `or_tt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `or_tt2`
-- ----------------------------
DROP TABLE IF EXISTS `or_tt2`;
CREATE TABLE `or_tt2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `or_ty`
-- ----------------------------
DROP TABLE IF EXISTS `or_ty`;
CREATE TABLE `or_ty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_action_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_action_log`;
CREATE TABLE `sys_action_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clazz` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `ipaddr` varchar(255) DEFAULT NULL,
  `message` text,
  `method` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `oper_name` varchar(255) DEFAULT NULL,
  `record_id` bigint(20) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `oper_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_action_log`
-- ----------------------------
BEGIN;
INSERT INTO `sys_action_log` VALUES ('1', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:12:21', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null), ('2', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:12:31', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null), ('3', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:12:40', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null), ('4', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:13:10', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null), ('5', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:14:59', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null), ('6', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:15:10', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null), ('7', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:15:58', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null), ('8', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:16:06', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null), ('9', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:16:28', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null), ('10', 'com.linln.admin.system.controller.LoginController', '2019-06-18 09:17:38', '127.0.0.1', '后台登录失败：[admin]用户名或密码错误', 'login', null, '用户登录', 'admin', null, '2', null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `pids` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdixdvu6qwietc938np79oeyxh` (`creator`),
  KEY `FKjhcec24mp5aab1e6r0owx98v7` (`updater`),
  CONSTRAINT `FKdixdvu6qwietc938np79oeyxh` FOREIGN KEY (`creator`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKjhcec24mp5aab1e6r0owx98v7` FOREIGN KEY (`updater`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_dict`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('1', null, 'DATA_STATUS', '', '1', '数据状态', '2', null, '1:正常,2:冻结,3:删除', null, null, null, null), ('2', null, 'DICT_TYPE', '', '1', '字典类型', '2', null, '2:键值对', null, null, null, null), ('3', null, 'USER_SEX', '', '1', '用户性别', '2', null, '1:男,2:女', null, null, null, null), ('4', null, 'MENU_TYPE', '', '1', '菜单类型', '2', null, '1:一级菜单,2:子级菜单,3:不是菜单', null, null, null, null), ('5', null, 'SEARCH_STATUS', '', '1', '搜索栏状态', '2', null, '1:正常,2:冻结', null, null, null, null), ('6', null, 'LOG_TYPE', '', '1', '日志类型', '2', null, '1:业务,2:登录,3:系统', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_file`
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `mime` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `sha1` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `perms` varchar(255) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `pids` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `valid_flag` tinyint(4) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcadhp19ia8tgho609fefjnlkr` (`creator`),
  KEY `FKfblen5ld9poxhq33817jod74o` (`updater`),
  CONSTRAINT `FKcadhp19ia8tgho609fefjnlkr` FOREIGN KEY (`creator`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKfblen5ld9poxhq33817jod74o` FOREIGN KEY (`updater`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', null, '', 'system:menu:index', '2', '[0],[2]', '', '3', '菜单管理', '1', null, '/system/menu/index', '1', null, null, null, null, null), ('2', null, 'layui-icon layui-icon-set', '#', '0', '[0]', '', '8', '系统管理', '1', null, '', '1', null, null, null, null, null), ('3', null, '', 'system:menu:add', '1', '[0],[2],[1]', '', '1', '添加', '1', null, '/system/menu/add', '1', null, null, null, null, null), ('4', null, '', 'system:role:index', '2', '[0],[2]', '', '2', '角色管理', '1', null, '/system/role/index', '1', null, null, null, null, null), ('5', null, '', 'system:role:add', '4', '[0],[2],[4]', '', '1', '添加', '2', null, '/system/role/add', '1', null, null, null, null, null), ('7', null, '', 'system:user:index', '2', '[0],[2]', '', '1', '用户管理', '1', null, '/system/user/index', '1', null, null, null, null, null), ('8', null, '', 'system:menu:edit', '1', '[0],[2],[1]', '', '2', '编辑', '2', null, '/system/menu/edit', '1', null, null, null, null, null), ('9', null, '', 'system:menu:detail', '1', '[0],[2],[1]', '', '3', '详细', '2', null, '/system/menu/detail', '1', null, null, null, null, null), ('10', null, '', 'system:menu:status', '1', '[0],[2],[1]', '', '4', '修改状态', '2', null, '/system/menu/status', '1', null, null, null, null, null), ('11', null, '', 'system:role:edit', '4', '[0],[2],[4]', '', '2', '编辑', '2', null, '/system/role/edit', '1', null, null, null, null, null), ('12', null, '', 'system:role:auth', '4', '[0],[2],[4]', '', '3', '授权', '2', null, '/system/role/auth', '1', null, null, null, null, null), ('13', null, '', 'system:role:detail', '4', '[0],[2],[4]', '', '4', '详细', '2', null, '/system/role/detail', '1', null, null, null, null, null), ('14', null, '', 'system:role:status', '4', '[0],[2],[4]', '', '5', '修改状态', '2', null, '/system/role/status', '1', null, null, null, null, null), ('15', null, '', 'system:user:edit', '7', '[0],[2],[7]', '', '2', '编辑', '2', null, '/system/user/edit', '1', null, null, null, null, null), ('16', null, '', 'system:user:add', '7', '[0],[2],[7]', '', '1', '添加', '2', null, '/system/user/add', '1', null, null, null, null, null), ('17', null, '', 'system:user:pwd', '7', '[0],[2],[7]', '', '3', '修改密码', '2', null, '/system/user/pwd', '1', null, null, null, null, null), ('18', null, '', 'system:user:role', '7', '[0],[2],[7]', '', '4', '权限分配', '2', null, '/system/user/role', '1', null, null, null, null, null), ('19', null, '', 'system:user:detail', '7', '[0],[2],[7]', '', '5', '详细', '2', null, '/system/user/detail', '1', null, null, null, null, null), ('20', null, '', 'system:user:status', '7', '[0],[2],[7]', '', '6', '修改状态', '2', null, '/system/user/status', '1', null, null, null, null, null), ('21', null, '', 'system:dict:index', '2', '[0],[2]', '', '5', '字典管理', '1', null, '/system/dict/index', '1', null, null, null, null, null), ('22', null, '', 'system:dict:add', '21', '[0],[2],[21]', '', '1', '字典添加', '2', null, '/system/dict/add', '1', null, null, null, null, null), ('23', null, '', 'system:dict:edit', '21', '[0],[2],[21]', '', '2', '字典编辑', '2', null, '/system/dict/edit', '1', null, null, null, null, null), ('24', null, '', 'system:dict:detail', '21', '[0],[2],[21]', '', '3', '字典详细', '2', null, '/system/dict/detail', '1', null, null, null, null, null), ('25', null, '', 'system:dict:status', '21', '[0],[2],[21]', '', '4', '修改状态', '2', null, '/system/dict/status', '1', null, null, null, null, null), ('26', null, '', 'system:actionLog:index', '2', '[0],[2]', '', '6', '行为日志', '1', null, '/system/actionLog/index', '1', null, null, null, null, null), ('27', null, '', 'system:actionLog:detail', '26', '[0],[2],[26]', '', '1', '日志详细', '2', null, '/system/actionLog/detail', '1', null, null, null, null, null), ('28', null, '', 'system:actionLog:delete', '26', '[0],[2],[26]', '', '2', '日志删除', '2', null, '/system/actionLog/delete', '1', null, null, null, null, null), ('136', null, '', 'system:dept:index', '2', '[0],[2]', '', '4', '部门管理', '1', null, '/system/dept/index', '1', null, null, null, null, null), ('137', null, '', 'system:dept:add', '136', '[0],[2],[136]', '', '1', '添加', '2', null, '/system/dept/add', '1', null, null, null, null, null), ('138', null, '', 'system:dept:edit', '136', '[0],[2],[136]', '', '2', '编辑', '2', null, '/system/dept/edit', '1', null, null, null, null, null), ('139', null, '', 'system:dept:detail', '136', '[0],[2],[136]', '', '3', '详细', '2', null, '/system/dept/detail', '1', null, null, null, null, null), ('140', null, '', 'system:dept:status', '136', '[0],[2],[136]', '', '4', '改变状态', '2', null, '/system/dept/status', '1', null, null, null, null, null), ('200', null, 'layui-icon layui-icon-template', '#', '0', null, null, '4', '新产品', '1', null, '', '1', null, null, null, null, null), ('201', null, null, 'new:new', '200', null, null, '1', 'Total Cost', '1', null, '/cost/totalCost', '1', null, null, null, null, null), ('202', null, null, 'new:new', '200', null, null, '2', 'Total Ratio', '2', null, '/new2', '1', null, null, null, null, null), ('203', null, null, 'new:new', '200', null, null, '3', '尺寸-机种', '2', null, '/new3', '1', null, null, null, null, null), ('210', null, 'layui-icon layui-icon-app', '#', '0', null, null, '5', '量产品', '1', null, '', '1', null, null, null, null, null), ('211', null, null, 'new:new', '210', null, null, '1', '内部失败成本率', '2', null, '/new4', '1', null, null, null, null, null), ('212', null, null, 'new:new', '210', null, null, '2', '外部失败成本率', '2', null, '/new5', '1', null, null, null, null, null), ('220', null, 'layui-icon layui-icon-release', '#', '0', null, null, '6', '模拟计算', '1', null, '', '1', null, null, null, null, null), ('221', null, null, 'new:new', '220', null, null, '1', '新产品', '2', null, '/new6', '1', null, null, null, null, null), ('222', null, null, 'new:new', '220', null, null, '2', '量产品', '2', null, '/new7', '1', null, null, null, null, null), ('230', null, 'layui-icon layui-icon-unlink', '#', '0', null, null, '7', '子数据', '1', null, '', '1', null, null, null, null, null), ('231', null, null, 'new:new', '230', null, null, '1', '实验验证成本管控界面', '2', null, '/new8', '1', null, null, null, null, null), ('232', null, null, 'new:new', '230', null, null, '2', 'OBA费用报表', '2', null, '/new9', '1', null, null, null, null, null), ('240', null, 'layui-icon layui-icon-password', '#', '0', null, null, '3', 'VIP', '1', null, '', '1', null, null, null, null, null), ('241', null, null, 'new:new', '240', null, null, '1', 'COQ Cost', '2', null, '/new10', '1', null, null, null, null, null), ('242', null, null, 'new:new', '240', null, null, '2', 'COQ Ratio', '2', null, '/new11', '1', null, null, null, null, null), ('250', null, 'layui-icon layui-icon-form', '#', '0', null, null, '2', '费用总表', '1', null, '/cost/projectCost3', '1', null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `valid_flag` tinyint(4) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdot6ifsuys39t14l0hnaqk3n3` (`creator`),
  KEY `FKivwvq1qnshhkbjhlk5c1qfnxx` (`updater`),
  CONSTRAINT `FKdot6ifsuys39t14l0hnaqk3n3` FOREIGN KEY (`creator`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKivwvq1qnshhkbjhlk5c1qfnxx` FOREIGN KEY (`updater`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '2019-06-06 14:00:09', 'admin', null, '管理员', '2019-06-06 14:00:06', '1', 'admin', 'admin', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FKf3mud4qoc7ayew8nml4plkevo` (`menu_id`),
  CONSTRAINT `FKf3mud4qoc7ayew8nml4plkevo` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FKkeitxsgxwayackgqllio4ohn5` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('1', '1'), ('1', '2'), ('1', '3'), ('1', '4'), ('1', '5'), ('1', '7'), ('1', '8'), ('1', '9'), ('1', '10'), ('1', '11'), ('1', '12'), ('1', '13'), ('1', '14'), ('1', '15'), ('1', '16'), ('1', '17'), ('1', '18'), ('1', '19'), ('1', '20'), ('1', '21'), ('1', '22'), ('1', '23'), ('1', '24'), ('1', '25'), ('1', '26'), ('1', '27'), ('1', '28'), ('1', '136'), ('1', '137'), ('1', '138'), ('1', '139'), ('1', '140'), ('1', '200'), ('1', '201'), ('1', '202'), ('1', '203'), ('1', '210'), ('1', '211'), ('1', '212'), ('1', '220'), ('1', '221'), ('1', '222'), ('1', '230'), ('1', '231'), ('1', '232');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  `valid_flag` tinyint(4) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb3pkx0wbo6o8i8lj0gxr37v1n` (`dept_id`),
  KEY `FK6b5y922nhgrwe47xp25tbyjw` (`creator`),
  KEY `FKd4t4gconcy2h5x8j0v9ibfyve` (`updater`),
  CONSTRAINT `FK6b5y922nhgrwe47xp25tbyjw` FOREIGN KEY (`creator`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKb3pkx0wbo6o8i8lj0gxr37v1n` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`),
  CONSTRAINT `FKd4t4gconcy2h5x8j0v9ibfyve` FOREIGN KEY (`updater`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('admin', '2019-06-06 13:58:39', 'dba250393f57a4f356cd4838db65db1715bcdc497f4807fa67cc65afbdae4963', null, null, 'DXJzuP', '2019-06-06 13:58:27', 'admin', null, null, null, null, null, null, '1', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `valid_flag` tinyint(4) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `role_fk` bigint(20) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `user_fk` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKppom0rebsqwryts1wariu7rb0` (`creator`),
  KEY `FKn5f3o8riy25hu5jc6hpd8wlf9` (`role_fk`),
  KEY `FKt4yw5o8dtihslblxtuk7m9obv` (`updater`),
  KEY `FKdorr5412dm2ssu6y3gh3i6en1` (`user_fk`),
  CONSTRAINT `FKdorr5412dm2ssu6y3gh3i6en1` FOREIGN KEY (`user_fk`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKn5f3o8riy25hu5jc6hpd8wlf9` FOREIGN KEY (`role_fk`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKppom0rebsqwryts1wariu7rb0` FOREIGN KEY (`creator`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKt4yw5o8dtihslblxtuk7m9obv` FOREIGN KEY (`updater`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '2019-06-06 14:12:53', '2019-06-06 14:12:51', '1', 'admin', '1', 'admin', 'admin', '0', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
