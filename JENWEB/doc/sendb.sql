/*=======================建库、用户脚本=======================================*/
CREATE DATABASE sendb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

grant all privileges on sendb.* to jensen @'%' identified by 'jenson180#jenson' with grant option;

FLUSH   PRIVILEGES;
-- ----------------------------
-- Table structure for t_right
-- ----------------------------
DROP TABLE IF EXISTS `t_right`;
CREATE TABLE `t_right` (
  `right_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `right_code` varchar(50) DEFAULT NULL,
  `right_parent_id` bigint(20) DEFAULT NULL,
  `right_order` smallint(6) DEFAULT NULL,
  `right_level` smallint(6) DEFAULT NULL,
  `right_name` varchar(50) DEFAULT NULL,
  `right_img` varchar(255) NOT NULL,
  `right_url` varchar(255) DEFAULT NULL,
  `perms` varchar(255) DEFAULT NULL COMMENT '授权码',
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '1：有效\r\n            0：无效',
  `remark` varchar(300) DEFAULT NULL,
  `create_userid` bigint(20) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_userid` bigint(20) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`right_id`),
  KEY `ind_t_right_perms` (`perms`(191))
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_right
-- ----------------------------
INSERT INTO `t_right` VALUES ('1', '001', null, '1', '1', '系统管理', 'fa fa-server', null, null, '1', null, '-1', '2017-01-12 16:11:15', '-1', '2017-01-12 16:11:26');
INSERT INTO `t_right` VALUES ('2', '001001', '1', '1', '2', '用户管理', 'fa fa-user', 'system/user/userManage', 'sys:user', '1', null, '-1', '2017-01-12 16:12:28', '0', '2017-01-12 16:12:33');
INSERT INTO `t_right` VALUES ('3', '001002', '1', '2', '2', '角色管理', 'fa fa-group', 'system/role/roleManage', 'sys:role', '1', '', '-1', '2017-01-12 16:12:28', '0', '2017-01-12 16:12:33');
INSERT INTO `t_right` VALUES ('4', '002', null, '2', '1', '1菜单', 'fa fa-star', null, null, '1', null, '0', '2017-01-23 10:05:56', '0', '2017-01-23 10:06:00');
INSERT INTO `t_right` VALUES ('5', '002001', '4', '1', '2', '1-1级菜单', 'fa fa-star', '', null, '1', '', '0', '2017-01-23 10:05:56', '0', '2017-01-23 10:06:00');
INSERT INTO `t_right` VALUES ('6', '002002', '4', '2', '2', '1-2级菜单', 'fa fa-star', '', null, '1', '', '0', '2017-01-23 10:05:56', '0', '2017-01-23 10:06:00');
INSERT INTO `t_right` VALUES ('7', '002003', '4', '3', '2', '1-3级菜单', 'fa fa-star', '', null, '1', '', '0', '2017-01-23 10:05:56', '0', '2017-01-23 10:06:00');
INSERT INTO `t_right` VALUES ('8', '003', null, '3', '1', '2菜单', 'fa fa-star', '', null, '1', '', '0', '2017-01-23 10:05:56', '0', '2017-01-23 10:06:00');
INSERT INTO `t_right` VALUES ('9', '003001', '8', '1', '2', '2-1级菜单', 'fa fa-star', '', null, '1', '', '0', '2017-01-23 10:05:56', '0', '2017-01-23 10:06:00');
INSERT INTO `t_right` VALUES ('10', '003002', '8', '2', '2', '2-2级菜单', 'fa fa-star', '', null, '1', '', '0', '2017-01-23 10:05:56', '0', '2017-01-23 10:06:00');
INSERT INTO `t_right` VALUES ('11', '003003', '8', '3', '2', '2-3级菜单', 'fa fa-star', '', null, '1', '', '0', '2017-01-23 10:05:56', '0', '2017-01-23 10:06:00');
INSERT INTO `t_right` VALUES ('12', '001001001', '2', '3', '3', '用户管理-1', 'fa fa-star', 'system/user/userManage.do', null, '1', '', '-1', '2017-01-12 16:12:28', '0', '2017-01-12 16:12:33');

-- ----------------------------
-- Table structure for t_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_roles`;
CREATE TABLE `t_roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_order` int(5) DEFAULT NULL COMMENT '排序',
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '1：有效\r\n            0：无效',
  `remark` varchar(300) DEFAULT NULL,
  `create_userid` bigint(20) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_userid` bigint(20) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_roles
-- ----------------------------
INSERT INTO `t_roles` VALUES ('12', null, '角色1(test)', null, '1', 'test', null, '2017-01-01 14:48:42', null, '2017-04-01 11:32:20');
INSERT INTO `t_roles` VALUES ('13', null, '角色2(test)', null, '1', 'test', null, '2017-01-03 16:08:54', null, '2017-03-21 16:09:00');
INSERT INTO `t_roles` VALUES ('14', null, '角色3(test)', null, '1', 'test', null, '2017-01-03 16:09:16', null, '2017-01-03 16:09:16');
INSERT INTO `t_roles` VALUES ('15', '', '角色4(test)', null, '1', 'test', null, '2017-01-03 16:08:54', null, '2017-01-04 18:00:01');
INSERT INTO `t_roles` VALUES ('16', '', '角色5(test)', null, '1', 'test', null, '2017-01-03 16:08:54', null, '2017-01-04 18:00:20');
INSERT INTO `t_roles` VALUES ('17', '', '角色6(test)', null, '1', 'test', null, '2017-01-03 16:08:54', null, '2017-04-01 11:35:11');

-- ----------------------------
-- Table structure for t_role_right
-- ----------------------------
DROP TABLE IF EXISTS `t_role_right`;
CREATE TABLE `t_role_right` (
  `rori_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `right_id` bigint(20) NOT NULL COMMENT '权限ID',
  `create_userid` bigint(20) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_userid` bigint(20) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`rori_id`),
  KEY `FK_roleright_roles_roleid` (`role_id`),
  KEY `FK_roleright_right_rightid` (`right_id`),
  CONSTRAINT `FK_roleright_right_rightid` FOREIGN KEY (`right_id`) REFERENCES `t_right` (`right_id`),
  CONSTRAINT `FK_roleright_roles_roleid` FOREIGN KEY (`role_id`) REFERENCES `t_roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role_right
-- ----------------------------
INSERT INTO `t_role_right` VALUES ('26', '13', '1', null, '2017-03-21 16:09:00', null, '2017-03-21 16:09:00');
INSERT INTO `t_role_right` VALUES ('27', '13', '2', null, '2017-03-21 16:09:00', null, '2017-03-21 16:09:00');
INSERT INTO `t_role_right` VALUES ('28', '13', '3', null, '2017-03-21 16:09:00', null, '2017-03-21 16:09:00');
INSERT INTO `t_role_right` VALUES ('29', '13', '8', null, '2017-03-21 16:09:00', null, '2017-03-21 16:09:00');
INSERT INTO `t_role_right` VALUES ('30', '13', '9', null, '2017-03-21 16:09:00', null, '2017-03-21 16:09:00');
INSERT INTO `t_role_right` VALUES ('31', '13', '10', null, '2017-03-21 16:09:00', null, '2017-03-21 16:09:00');
INSERT INTO `t_role_right` VALUES ('32', '13', '11', null, '2017-03-21 16:09:00', null, '2017-03-21 16:09:00');
INSERT INTO `t_role_right` VALUES ('33', '12', '1', null, '2017-04-01 11:32:20', null, '2017-04-01 11:32:20');
INSERT INTO `t_role_right` VALUES ('34', '12', '2', null, '2017-04-01 11:32:20', null, '2017-04-01 11:32:20');
INSERT INTO `t_role_right` VALUES ('35', '12', '3', null, '2017-04-01 11:32:20', null, '2017-04-01 11:32:20');
INSERT INTO `t_role_right` VALUES ('36', '12', '4', null, '2017-04-01 11:32:20', null, '2017-04-01 11:32:20');
INSERT INTO `t_role_right` VALUES ('37', '12', '5', null, '2017-04-01 11:32:20', null, '2017-04-01 11:32:20');
INSERT INTO `t_role_right` VALUES ('38', '12', '6', null, '2017-04-01 11:32:20', null, '2017-04-01 11:32:20');
INSERT INTO `t_role_right` VALUES ('39', '12', '8', null, '2017-04-01 11:32:20', null, '2017-04-01 11:32:20');
INSERT INTO `t_role_right` VALUES ('40', '12', '9', null, '2017-04-01 11:32:20', null, '2017-04-01 11:32:20');
INSERT INTO `t_role_right` VALUES ('41', '17', '1', null, '2017-04-01 11:35:11', null, '2017-04-01 11:35:11');
INSERT INTO `t_role_right` VALUES ('42', '17', '2', null, '2017-04-01 11:35:11', null, '2017-04-01 11:35:11');
INSERT INTO `t_role_right` VALUES ('43', '17', '12', null, '2017-04-01 11:35:11', null, '2017-04-01 11:35:11');
INSERT INTO `t_role_right` VALUES ('44', '17', '3', null, '2017-04-01 11:35:11', null, '2017-04-01 11:35:11');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(60) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_sex` smallint(6) DEFAULT NULL,
  `user_iphone` varchar(30) DEFAULT NULL,
  `user_birthday` date DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_address` varchar(200) DEFAULT NULL,
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '1：有效\r\n            0：无效',
  `remark` varchar(300) DEFAULT NULL,
  `create_userid` bigint(20) unsigned DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_userid` bigint(20) unsigned DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=438 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '443ed776908ee043fde5f7c0d8841a91bc46261832794b5d7c3b6f87a75d6ca1', 'admin', '1', '', '2017-02-28', '', '阿斯蒂芬asd1', '1', 'asdf的萨芬告诉对方', '0', '2017-02-28 18:04:27', '0', '2017-02-28 18:04:37');
INSERT INTO `t_user` VALUES ('381', 'abo111', '443ed776908ee043fde5f7c0d8841a91bc46261832794b5d7c3b6f87a75d6ca1', '用户一', '2', '13928176252', '1992-09-01', 'abo@1631.com', '阿斯蒂芬asd1', '1', 'asdf的萨芬告诉对方1123阿萨德89ff33', '0', '2017-01-20 14:19:20', '0', '2017-03-31 13:38:10');
INSERT INTO `t_user` VALUES ('382', 'abo2阿萨德1', '443ed776908ee043fde5f7c0d8841a91bc46261832794b5d7c3b6f87a75d6ca1', '用户二', '2', '13928176255', '1992-08-31', 'abo@163.com', '阿斯蒂芬asd', '1', 'asdf的萨芬告诉对方', '0', '2017-01-04 17:49:02', '0', '2017-03-31 13:38:19');
INSERT INTO `t_user` VALUES ('383', 'test_abo3', '443ed776908ee043fde5f7c0d8841a91bc46261832794b5d7c3b6f87a75d6ca1', '用户三', '2', '13928176255', '1992-08-31', 'abo@163.com', '阿斯蒂芬asd', '0', 'asdf的萨芬告诉对方', '0', '2016-09-28 15:56:38', '0', '2017-03-31 13:39:43');
INSERT INTO `t_user` VALUES ('433', 'asdasdasd', '443ed776908ee043fde5f7c0d8841a91bc46261832794b5d7c3b6f87a75d6ca1', 'dsagsadf', '2', '13636521123', '1923-02-13', 'asdf@sina.com', 'adsfasdfsadf', '0', '读书噶大哥哥sad分儿童斯蒂芬', null, '2017-03-31 16:50:20', null, '2017-03-31 16:50:45');
INSERT INTO `t_user` VALUES ('434', 'test_user1', '443ed776908ee043fde5f7c0d8841a91bc46261832794b5d7c3b6f87a75d6ca1', 'asdsdf', '1', '13637298122', null, '', '', '1', '', null, '2017-04-01 10:48:34', null, '2017-04-01 10:48:34');
INSERT INTO `t_user` VALUES ('436', 'test_user2', '443ed776908ee043fde5f7c0d8841a91bc46261832794b5d7c3b6f87a75d6ca1', 'adsf', '1', '13838729211', null, 'jenson.wzc@gmail.com', '大木桥路238好2201', '1', '', null, '2017-04-01 11:29:13', null, '2017-04-01 11:29:13');
INSERT INTO `t_user` VALUES ('437', 'test_user3', '443ed776908ee043fde5f7c0d8841a91bc46261832794b5d7c3b6f87a75d6ca1', 'adsf', '1', '', null, '', '', '1', '', null, '2017-04-01 11:31:54', null, '2017-04-01 11:31:54');

-- ----------------------------
-- Table structure for t_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_user_roles`;
CREATE TABLE `t_user_roles` (
  `usro_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_userid` bigint(20) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_userid` bigint(20) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`usro_id`),
  KEY `FK_userroles_user_userid` (`user_id`),
  KEY `FK_userroles_roles_roleid` (`role_id`),
  CONSTRAINT `FK_userroles_roles_roleid` FOREIGN KEY (`role_id`) REFERENCES `t_roles` (`role_id`),
  CONSTRAINT `FK_userroles_user_userid` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_roles
-- ----------------------------
INSERT INTO `t_user_roles` VALUES ('4', '382', '14', '0', '2017-01-04 16:30:12', '0', '2017-01-04 16:30:12');
INSERT INTO `t_user_roles` VALUES ('5', '382', '15', '0', '2017-01-04 16:30:12', '0', '2017-01-04 16:30:12');
INSERT INTO `t_user_roles` VALUES ('6', '382', '13', '0', '2017-01-04 16:30:12', '0', '2017-01-04 16:30:12');
INSERT INTO `t_user_roles` VALUES ('29', '381', '12', '-1', '2017-01-17 16:05:34', '-1', '2017-01-17 16:05:34');
INSERT INTO `t_user_roles` VALUES ('30', '381', '13', '-1', '2017-01-17 16:05:34', '-1', '2017-01-17 16:05:34');
INSERT INTO `t_user_roles` VALUES ('31', '381', '15', '-1', '2017-01-17 16:05:34', '-1', '2017-01-17 16:05:34');
INSERT INTO `t_user_roles` VALUES ('32', '381', '16', '-1', '2017-01-17 16:05:34', '-1', '2017-01-17 16:05:34');
INSERT INTO `t_user_roles` VALUES ('33', '381', '17', '-1', '2017-01-17 16:05:34', '-1', '2017-01-17 16:05:34');
INSERT INTO `t_user_roles` VALUES ('34', '437', '12', '-1', '2017-04-01 11:32:05', '-1', '2017-04-01 11:32:05');
INSERT INTO `t_user_roles` VALUES ('35', '437', '13', '-1', '2017-04-01 11:32:05', '-1', '2017-04-01 11:32:05');
