/*
 Navicat MySQL Data Transfer

 Source Server         : localDb
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : pda

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 24/09/2019 18:32:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for DATABASECHANGELOG
-- ----------------------------
DROP TABLE IF EXISTS `DATABASECHANGELOG`;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of DATABASECHANGELOG
-- ----------------------------
BEGIN;
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_yhfx_report_regular', 'milo', 'classpath:/sql/init/init.xml', '2019-09-03 16:28:59', 1, 'EXECUTED', '8:71ae9a5a288ad632eb03d71de1f6c4e4', 'sql', '', NULL, '3.6.2', NULL, NULL, '7499339564');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_user', 'milo', 'classpath:/sql/init/init.xml', '2019-09-05 10:24:01', 2, 'MARK_RAN', '8:71ae9a5a288ad632eb03d71de1f6c4e4', 'sql', '', NULL, '3.6.2', NULL, NULL, '7650241953');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_user_token', 'milo', 'classpath:/sql/init/init.xml', '2019-09-05 10:25:42', 3, 'EXECUTED', '8:736bc5fa1d166c34096108d8c74c424b', 'sql', '', NULL, '3.6.2', NULL, NULL, '7650342451');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_menu', 'milo', 'classpath:/sql/init/init.xml', '2019-09-06 15:20:12', 4, 'EXECUTED', '8:0ec051edbb64da75238531346607cfdc', 'sql', '', NULL, '3.6.2', NULL, NULL, '7754412450');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_role', 'milo', 'classpath:/sql/init/init.xml', '2019-09-06 15:20:12', 5, 'EXECUTED', '8:98f0d180eefc8fe9fbf813105cddd9de', 'sql', '', NULL, '3.6.2', NULL, NULL, '7754412450');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_menu_role', 'milo', 'classpath:/sql/init/init.xml', '2019-09-06 15:20:12', 7, 'EXECUTED', '8:851a201ae17c6a0bef328378927e0080', 'sql', '', NULL, '3.6.2', NULL, NULL, '7754412450');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_menu_alter_column', 'milo', 'classpath:/sql/init/init.xml', '2019-09-06 16:09:34', 8, 'EXECUTED', '8:167258674ee7777b280675d591918443', 'sql', '', NULL, '3.6.2', NULL, NULL, '7757374413');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_role_alter_column', 'milo', 'classpath:/sql/init/init.xml', '2019-09-09 11:16:45', 9, 'EXECUTED', '8:1a5f970b1e89844caebe7a3382cd53e5', 'sql', '', NULL, '3.6.2', NULL, NULL, '7999004969');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_menu_role_and_tb_role_user_drop_columns', 'milo', 'classpath:/sql/init/init.xml', '2019-09-09 16:52:23', 10, 'EXECUTED', '8:d2215e2826a2402a712a21d6adbca01e', 'sql', '', NULL, '3.6.2', NULL, NULL, '8019143151');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_role_user', 'milo', 'classpath:/sql/init/init.xml', '2019-09-10 10:33:32', 11, 'MARK_RAN', '8:25cb0be232fe19da3dd3be09f23c126a', 'sql', '', NULL, '3.6.2', NULL, NULL, '8082812967');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_dictionary_type', 'milo', 'classpath:/sql/init/init.xml', '2019-09-24 17:59:26', 12, 'EXECUTED', '8:58d60f38a65085ca02c04dbb1719f524', 'sql', '', NULL, '3.6.2', NULL, NULL, '9319166439');
INSERT INTO `DATABASECHANGELOG` VALUES ('tb_dictionary', 'milo', 'classpath:/sql/init/init.xml', '2019-09-24 17:59:26', 13, 'EXECUTED', '8:d3592b806e10682019e732f4b6cb5fee', 'sql', '', NULL, '3.6.2', NULL, NULL, '9319166439');
COMMIT;

-- ----------------------------
-- Table structure for DATABASECHANGELOGLOCK
-- ----------------------------
DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of DATABASECHANGELOGLOCK
-- ----------------------------
BEGIN;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1, b'0', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary`;
CREATE TABLE `tb_dictionary` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `uuid` int(11) DEFAULT NULL,
  `item_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典项名称',
  `dictionary_type_id` int(11) DEFAULT NULL COMMENT '字典类型ID',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '状态，1代表删除，0代表未删除',
  `reversion` int(11) DEFAULT NULL COMMENT '版本',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典表';

-- ----------------------------
-- Table structure for tb_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary_type`;
CREATE TABLE `tb_dictionary_type` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `uuid` int(11) DEFAULT NULL,
  `type_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典种类名称',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '状态，1代表删除，0代表未删除',
  `reversion` int(11) DEFAULT NULL COMMENT '版本',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典类型表';

-- ----------------------------
-- Records of tb_dictionary_type
-- ----------------------------
BEGIN;
INSERT INTO `tb_dictionary_type` VALUES ('7a0eb5cd8dc84654b1c922c32655bf4e', 2, '餐饮', 0, 1, -1, '2019-09-24 18:01:52', -1, '2019-09-24 18:01:52');
INSERT INTO `tb_dictionary_type` VALUES ('df6513c16d9e4069936ae223591a1227', 1, '交通', 0, 3, -1, '2019-09-24 17:59:28', -1, '2019-09-24 18:17:29');
COMMIT;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '数据库主键',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `menu_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `parent_menu_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '父菜单ID',
  `menu_level` int(11) DEFAULT NULL COMMENT '菜单级别',
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标志，0代表未删除，1代表已删除',
  `reversion` int(11) DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='菜单表 ';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu` VALUES ('0ddccac437fe4165abbba417cfdb8823', 4, '用户', '0', 1, 0, 1, '-1', '2019-09-24 15:24:59', '-1', '2019-09-24 15:24:59');
INSERT INTO `tb_menu` VALUES ('652109ac26aa46c78da0b0b505b5fd26', 3, '角色', '0', 1, 0, 1, '-1', '2019-09-06 17:55:27', '-1', '2019-09-06 17:55:27');
INSERT INTO `tb_menu` VALUES ('72a53a3c66324d1ab83e2ea329e2a989', 2, '菜单', '0', 1, 0, 1, '-1', '2019-09-06 17:54:14', '-1', '2019-09-06 17:54:14');
INSERT INTO `tb_menu` VALUES ('bb6d90c3778b46e6b67f8748966a15b0', 1, '大看板', '0', 1, 0, 1, '-1', '2019-09-06 17:42:01', '-1', '2019-09-06 17:42:01');
INSERT INTO `tb_menu` VALUES ('ccecd5d6b0294c7d97a6aa6654dd0390', 1, '小看板', '0', 1, 1, 3, '-1', '2019-09-06 17:27:46', '-1', '2019-09-06 17:40:09');
COMMIT;

-- ----------------------------
-- Table structure for tb_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_role`;
CREATE TABLE `tb_menu_role` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '数据库主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `created_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='角色菜单关系表 ';

-- ----------------------------
-- Records of tb_menu_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu_role` VALUES ('231167925b8d4ee9a09eea86f312e27b', 1, 2, '-1', '2019-09-09 17:51:59', '-1', '2019-09-09 17:51:59');
INSERT INTO `tb_menu_role` VALUES ('c178364a23da4f7da7eb8266dd944b93', 1, 3, '-1', '2019-09-09 17:52:42', '-1', '2019-09-09 17:52:42');
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '数据库主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `role_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志(0代表未删除，1代表已删除)',
  `reversion` int(11) DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='角色表 ';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` VALUES ('10b531f458cf4a01b357b5dbfa0aa2de', 1, '系统管理员', 1, 4, '-1', '2019-09-09 11:48:07', '-1', '2019-09-09 14:55:16');
INSERT INTO `tb_role` VALUES ('3c05664bbb3a487ea753d9c6937aef88', 2, '菜单管理员', 0, 1, '-1', '2019-09-09 14:59:36', '-1', '2019-09-09 14:59:36');
INSERT INTO `tb_role` VALUES ('576657a2f2124b72b5652f8f4f8be46e', 1, '模块管理员', 0, 1, '-1', '2019-09-09 14:58:58', '-1', '2019-09-09 14:58:58');
COMMIT;

-- ----------------------------
-- Table structure for tb_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_user`;
CREATE TABLE `tb_role_user` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '数据库主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `created_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户角色关系表 ';

-- ----------------------------
-- Records of tb_role_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_user` VALUES ('499138d4e53c405f97b283be772e6521', 1, 8, '-1', '2019-09-10 10:33:34', '-1', '2019-09-10 10:33:34');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '数据库主键',
  `user_id` int(10) NOT NULL COMMENT '业务主键',
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `password` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '是否删除，0代表未删除,1代表已删除',
  `reversion` tinyint(3) DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户信息表 ';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('21ef8c4191794f70acd7769160870620', 5, 'jack', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, '-1', '2019-09-06 10:56:12', '-1', '2019-09-06 10:56:12');
INSERT INTO `tb_user` VALUES ('25b957908a1d4612807e0a8f6f0e88e5', 3, 'milo', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, '-1', '2019-09-06 10:54:56', '-1', '2019-09-06 10:54:56');
INSERT INTO `tb_user` VALUES ('27d98c37195e448193639c9c382235ef', 2, 'lele', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 2, '-1', '2019-09-05 10:25:44', '-1', '2019-09-05 10:27:43');
INSERT INTO `tb_user` VALUES ('3cfe5f0a9e6d42da9226f3aae6c4d5ac', 9, 'amanda', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, '-1', '2019-09-06 11:01:54', '-1', '2019-09-06 11:01:54');
INSERT INTO `tb_user` VALUES ('7d21ff0aa7a9424897b544f2e5df5f30', 8, 'liming', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, '-1', '2019-09-06 11:01:20', '-1', '2019-09-06 11:01:20');
INSERT INTO `tb_user` VALUES ('80bc9bf1702040f884a52fa0c4c0b4e9', 1, 'xuexue', '52C69E3A57331081823331C4E69D3F2E', 0, 3, '-1', '2019-09-04 16:49:07', '-1', '2019-09-04 17:23:15');
INSERT INTO `tb_user` VALUES ('8f36a8f2f44f46da81749fb8fa26869e', 4, 'danny', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, '-1', '2019-09-06 10:55:36', '-1', '2019-09-06 10:55:36');
INSERT INTO `tb_user` VALUES ('92598f2c8d5149a2b666d4fe6a0c3921', 7, 'joe', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, '-1', '2019-09-06 10:57:30', '-1', '2019-09-06 10:57:30');
INSERT INTO `tb_user` VALUES ('a8f67c704e9f4dc981698084dffe4ce8', 11, 'vivian', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, '-1', '2019-09-06 11:03:11', '-1', '2019-09-06 11:03:11');
INSERT INTO `tb_user` VALUES ('e1b292929aa146bda754dd143ab5b3e4', 6, 'jone', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, '-1', '2019-09-06 10:56:50', '-1', '2019-09-06 10:56:50');
INSERT INTO `tb_user` VALUES ('f97baf988b774a52ab2978214ff560b3', 10, 'ivone', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, '-1', '2019-09-06 11:02:36', '-1', '2019-09-06 11:02:36');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_token`;
CREATE TABLE `tb_user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT '用户ID',
  `user_token` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户token',
  `reversion` int(11) DEFAULT NULL COMMENT '版本',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标志',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `created_by` int(10) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(10) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_user_token
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_token` VALUES (1, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjgyNzI4MDE4OTgsInBheWxvYWQiOiIwIn0.-SttoHPl6mHIax3b7R1jQwyX8rM7wtfYQDi4_V635ZA', 2, 1, '2019-09-05 15:20:02', '2019-09-05 15:21:12', -1, -1);
INSERT INTO `tb_user_token` VALUES (2, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Njg2OTAyNTAzNDQsInBheWxvYWQiOiIwIn0.jqmNuxeAqbi9O7WxbG0g1JA2PqAcdGXiTHU9uCbrmzw', 1, 0, '2019-09-10 11:17:31', '2019-09-10 11:17:31', -1, -1);
INSERT INTO `tb_user_token` VALUES (3, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Njg2OTA3OTY5NDgsInBheWxvYWQiOiIwIn0.C9QZQL8nGxK24I-MEN9P8p7V7TAWNqFvp8iceEjUUh4', 1, 0, '2019-09-10 11:26:37', '2019-09-10 11:26:37', -1, -1);
INSERT INTO `tb_user_token` VALUES (4, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Njg2OTA4NjcyNTksInBheWxvYWQiOiIwIn0.tTp7cNfSgs9pUEcnXvbgl4q5ok8ZhEAuTUZOaFh8rk8', 1, 0, '2019-09-10 11:28:02', '2019-09-10 11:28:02', -1, -1);
INSERT INTO `tb_user_token` VALUES (5, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Njg2OTA5NTUwNjgsInBheWxvYWQiOiIxIn0.lBt7srIZJ-YuvAirDo36kwjuk8BWs73gtJQqrk12AG4', 1, 0, '2019-09-10 11:29:15', '2019-09-10 11:29:15', -1, -1);
INSERT INTO `tb_user_token` VALUES (6, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Njg2OTExNDMwNzcsInBheWxvYWQiOiIxIn0.SVtRojo_PXhMskZGLS8TqcrKeZ2d6sQTIqWNKwOVjps', 1, 0, '2019-09-10 11:32:23', '2019-09-10 11:32:23', -1, -1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
