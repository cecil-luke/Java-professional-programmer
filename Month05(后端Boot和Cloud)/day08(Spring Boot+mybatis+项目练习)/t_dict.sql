DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(32) NOT NULL,
  `name` varchar(32)  NOT NULL,
  `value` varchar(32) NOT NULL,
  `sort` int NOT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', 'level', '小型车', '1', '1');
INSERT INTO `t_dict` VALUES ('2', 'level', '中型车', '2', '2');
INSERT INTO `t_dict` VALUES ('3', 'level', '大型车', '3', '3');
INSERT INTO `t_dict` VALUES ('4', 'level', 'SUV', '4', '4');
INSERT INTO `t_dict` VALUES ('5', 'gearbox', '自动', '1', '1');
INSERT INTO `t_dict` VALUES ('6', 'gearbox', '手动', '2', '2');
INSERT INTO `t_dict` VALUES ('7', 'disp', '1.5L', '1', '1');
INSERT INTO `t_dict` VALUES ('8', 'disp', '1.6L', '2', '2');
INSERT INTO `t_dict` VALUES ('9', 'disp', '1.8L', '3', '3');
INSERT INTO `t_dict` VALUES ('10', 'disp', '2.0T', '4', '4');
INSERT INTO `t_dict` VALUES ('11', 'disp', '2.5L', '5', '5');
INSERT INTO `t_dict` VALUES ('12', 'disp', '3.0T', '6', '6');
INSERT INTO `t_dict` VALUES ('13', 'price', '3万以下', '0-3', '1');
INSERT INTO `t_dict` VALUES ('14', 'price', '3万-5万', '3-5', '2');
INSERT INTO `t_dict` VALUES ('15', 'price', '5万-8万', '5-8', '3');
INSERT INTO `t_dict` VALUES ('16', 'price', '8万-10万', '8-10', '4');
INSERT INTO `t_dict` VALUES ('17', 'price', '10万-20万', '10-20', '5');
INSERT INTO `t_dict` VALUES ('18', 'price', '20万以上', '20-999', '6');
INSERT INTO `t_dict` VALUES ('19', 'car_age', '一年以下', '0-1', '1');
INSERT INTO `t_dict` VALUES ('20', 'car_age', '1-3年', '1-3', '2');
INSERT INTO `t_dict` VALUES ('21', 'car_age', '3-5年', '3-5', '3');
INSERT INTO `t_dict` VALUES ('22', 'car_age', '5-10年', '5-10', '4');
INSERT INTO `t_dict` VALUES ('23', 'car_age', '10年以上', '10-0', '5');
