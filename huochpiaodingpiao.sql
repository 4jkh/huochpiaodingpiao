/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : huochpiaodingpiao

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 22/03/2025 19:44:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for checi
-- ----------------------------
DROP TABLE IF EXISTS `checi`;
CREATE TABLE `checi`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `checi_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车次标题 Search111 ',
  `checi_photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '火车照片',
  `checi_types` int(0) NULL DEFAULT NULL COMMENT '火车类型 Search111',
  `checi_new_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '现价',
  `checi_chufadi` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发地',
  `checi_mudidi` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目的地',
  `checi_time` timestamp(0) NULL DEFAULT NULL COMMENT '出发时间',
  `section_number` int(0) NULL DEFAULT NULL COMMENT '车厢',
  `zuowei_number` int(0) NULL DEFAULT NULL COMMENT '座位',
  `shangxia_types` int(0) NULL DEFAULT NULL COMMENT '是否上架 ',
  `checi_delete` int(0) NULL DEFAULT NULL COMMENT '逻辑删除',
  `checi_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '经停站、到达时间详情',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间  show1 show2 photoShow',
  `yideng` float(255, 2) NULL DEFAULT NULL COMMENT '一等',
  `erdeng` float(255, 2) NULL DEFAULT NULL COMMENT '二等',
  `shangwu` float(255, 2) NULL DEFAULT NULL COMMENT '商务',
  `dongwo` float(255, 2) NULL DEFAULT NULL COMMENT '动卧',
  `yingzuo` float(255, 2) NULL DEFAULT NULL COMMENT '硬座',
  `ruanzuo` float(255, 2) NULL DEFAULT NULL COMMENT '软座',
  `yingwo` float(255, 2) NULL DEFAULT NULL COMMENT '硬卧',
  `ruanwo` float(255, 2) NULL DEFAULT NULL COMMENT '软卧',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车次信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checi
-- ----------------------------
INSERT INTO `checi` VALUES (1, 'G2056 ', 'http://localhost:8080/huochpiaodingpiao/upload/1648621950417.jpeg', 5, 862.00, '广州南', '济南西', '2022-03-30 14:23:57', 16, 20, 1, 1, '<p><span style=\"color: var(--md-box-samantha-deep-text-color);\">经停站</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：韶关（08:49/08:51）停车 2 分钟；长沙南（10:56/11:00）停车 4 分钟；武汉（12:21/12:26）停车 5 分钟；郑州东（14:49/14:52）停车 3 分钟</span></p><p><span style=\"color: var(--md-box-samantha-deep-text-color);\">终点站到达时间</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：19:47</span></p>', '2022-03-30 14:23:57', 900.00, 862.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `checi` VALUES (2, 'T109', 'http://localhost:8080/huochpiaodingpiao/upload/1648621941816.jpeg', 4, 321.50, '北京', '上海', '2022-03-30 14:23:57', 18, 20, 1, 1, '<p><span style=\"color: var(--md-box-samantha-deep-text-color);\">经停站</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：徐州（02:31/02:37）停车 6 分钟；南京（04:30/04:34）停车 4 分钟；无锡（05:32/05:36）停车 4 分钟</span></p><p><span style=\"color: var(--md-box-samantha-deep-text-color);\">终点站到达时间</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：06:58（次日）</span></p>', '2022-03-30 14:23:57', 400.00, 321.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `checi` VALUES (3, 'D6509', 'http://localhost:8080/huochpiaodingpiao/upload/1648621934089.jpeg', 6, 254.50, '南昌西', '抚州', '2022-03-30 14:23:57', 8, 20, 1, 1, '<p><span style=\"color: var(--md-box-samantha-deep-text-color);\">经停站</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：南城（11:02/11:04）停车 2 分钟；南丰（11:19/11:21）停车 2 分钟</span></p><p><span style=\"color: var(--md-box-samantha-deep-text-color);\">终点站到达时间</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：11:40</span></p>', '2022-03-30 14:23:57', 70.00, 47.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `checi` VALUES (4, 'K288 ', 'http://localhost:8080/huochpiaodingpiao/upload/1648621926297.jpeg', 2, 263.50, '南昌', '上海南', '2022-03-30 14:23:57', 4, 20, 1, 1, '<p><span style=\"color: var(--md-box-samantha-deep-text-color);\">经停站</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：鹰潭（19:12/19:16）停车 4 分钟；上饶（20:00/20:04）停车 4 分钟；杭州南（22:32/22:36）停车 4 分钟</span></p><p><span style=\"color: var(--md-box-samantha-deep-text-color);\">终点站到达时间</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：00:30（次日）</span></p>', '2022-03-30 14:23:57', 300.00, 263.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `checi` VALUES (5, 'Z1', 'http://localhost:8080/huochpiaodingpiao/upload/1648621917229.jpeg', 3, 704.50, '北京西', '长沙', '2022-03-30 14:23:57', 17, 20, 1, 1, '<p><span style=\"color: var(--md-box-samantha-deep-text-color);\">经停站</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：无（直达）</span></p><p><span style=\"color: var(--md-box-samantha-deep-text-color);\">终点站到达时间</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：07:18（次日）</span></p>', '2022-03-30 14:23:57', 900.00, 704.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `checi` VALUES (6, 'G6', 'http://localhost:8080/huochpiaodingpiao/upload/1742182237984.jpg', 7, 662.00, '上海虹桥', '北京南', '2025-03-03 00:00:00', 16, 20, 1, 1, '<p><span style=\"color: var(--md-box-samantha-deep-text-color);\">经停站</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：南京南（08:15/08:17）停车 2 分钟；济南西（09:45/09:47）停车 2 分钟</span></p><p><span style=\"color: var(--md-box-samantha-deep-text-color);\">终点站到达时间</span><span style=\"color: rgba(0, 0, 0, 0.85);\">：10:53</span></p>', '2025-03-17 11:31:27', 800.00, 662.00, NULL, NULL, 44.40, 55.50, 66.60, 77.70);
INSERT INTO `checi` VALUES (7, 'G72', 'http://localhost:8080/huochpiaodingpiao/upload/1742182458816.jpg', 5, 862.00, '广州南', '北京西', '2025-03-05 00:00:00', 16, 22, 1, 1, '<p><span style=\"color: var(--md-box-samantha-deep-text-color);\">经停站</span>：韶关（09:40/09:42）停车 2 分钟；长沙南（11:47/11:51）停车 4 分钟；武汉（13:35/13:40）停车 5 分钟；郑州东（15:16/15:19）停车 3 分钟</p><p><span style=\"color: var(--md-box-samantha-deep-text-color);\">终点站到达时间</span>：17:00</p><p><br></p>', '2025-03-17 11:34:33', 1458.00, 862.00, NULL, NULL, 44.40, 55.50, 66.60, 77.70);
INSERT INTO `checi` VALUES (8, 'G66', 'http://localhost:8080/huochpiaodingpiao/upload/1742182553583.jpg', 5, 862.00, '广州南', '北京西', '2025-03-06 00:00:00', 16, 25, 1, 1, '<p><span style=\"color: var(--md-box-samantha-deep-text-color);\">经停站</span>：郴州西（08:53/08:55）停车 2 分钟；长沙南（09:58/10:02）停车 4 分钟；武汉（11:46/11:51）停车 5 分钟；郑州东（13:27/13:30）停车 3 分钟</p><p><span style=\"color: var(--md-box-samantha-deep-text-color);\">终点站到达时间</span>：15:50</p>', '2025-03-17 11:35:55', 1458.00, 862.00, 123.00, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `checi` VALUES (9, '测试', 'http://localhost:8080/huochpiaodingpiao/upload/1742634517335.png', 1, 2.00, '2', '2', '2025-03-04 00:00:00', 2, 22, 1, 2, '<p>11</p>', '2025-03-22 17:08:51', 22.20, 11.30, 33.30, 2.00, 44.40, 55.50, 66.60, 77.70);
INSERT INTO `checi` VALUES (10, '22', 'http://localhost:8080/huochpiaodingpiao/upload/1742642487312.png', 5, 2.00, '2', '2', '2025-03-04 00:00:00', 2, 2, 1, 2, '<p>2</p>', '2025-03-22 19:21:41', 2.00, 2.00, 2.00, 2.00, NULL, 2.00, 2.00, 2.00);
INSERT INTO `checi` VALUES (11, '高铁测试', 'http://localhost:8080/huochpiaodingpiao/upload/1742642553261.png', 5, 1.00, '1', '1', '2025-03-18 00:00:00', 11, 11, 1, 1, '<p>22</p>', '2025-03-22 19:22:48', 1.00, 2.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00);
INSERT INTO `checi` VALUES (12, '普速列车', 'http://localhost:8080/huochpiaodingpiao/upload/1742642594926.png', 1, 3.00, '3', '3', '2025-03-14 00:00:00', 33, 33, 1, 1, '<p>4</p>', '2025-03-22 19:23:27', NULL, NULL, NULL, NULL, 1.00, 2.00, 3.00, 4.00);

-- ----------------------------
-- Table structure for checi_order
-- ----------------------------
DROP TABLE IF EXISTS `checi_order`;
CREATE TABLE `checi_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `checi_order_uuid_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号 Search111 ',
  `checi_id` int(0) NULL DEFAULT NULL COMMENT '车次',
  `yonghu_id` int(0) NULL DEFAULT NULL COMMENT '会员',
  `checi_order_true_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '实付价格 ',
  `checi_order_types` int(0) NULL DEFAULT NULL COMMENT '订单类型',
  `buy_section_number` int(0) NULL DEFAULT NULL COMMENT '车厢',
  `buy_zuowei_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购买的座位',
  `buy_zuowei_time` date NULL DEFAULT NULL COMMENT '订购日期',
  `insert_time` timestamp(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间 show3',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `dengji` float(255, 2) NULL DEFAULT NULL COMMENT '等级的价格',
  `zhekou` int(0) NULL DEFAULT NULL COMMENT '折扣',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购票订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checi_order
-- ----------------------------
INSERT INTO `checi_order` VALUES (1, '1648622220219', 4, 1, 1120.05, 1, 1, '1,2,3', '2022-03-30', '2022-03-30 14:37:00', '2022-03-30 14:37:00', NULL, NULL, NULL);
INSERT INTO `checi_order` VALUES (2, '1648622337704', 4, 2, 2.00, 2, 1, '4,5', '2022-03-30', '2022-03-30 14:38:58', '2022-03-30 14:38:58', NULL, NULL, NULL);
INSERT INTO `checi_order` VALUES (3, '1648622350043', 2, 3, 1412.46, 3, 1, '3,4,5', '2022-03-30', '2022-03-30 14:39:10', '2022-03-30 14:39:10', NULL, NULL, NULL);
INSERT INTO `checi_order` VALUES (4, '1741958853483', 4, 4, 373.35, 2, 1, '5', '2025-03-14', '2025-03-14 21:27:33', '2025-03-14 21:27:33', '已退款', NULL, NULL);
INSERT INTO `checi_order` VALUES (5, '1742005552538', 2, 5, 470.82, 2, 1, '4', '2025-03-15', '2025-03-15 10:25:53', '2025-03-15 10:25:53', '已退款', NULL, NULL);
INSERT INTO `checi_order` VALUES (6, '1742005684736', 1, 4, 260.63, 3, 1, '8', '2025-03-15', '2025-03-15 10:28:05', '2025-03-15 10:28:05', NULL, NULL, NULL);
INSERT INTO `checi_order` VALUES (7, '1742005781132', 5, 4, 191.86, 1, 1, '7', '2025-03-15', '2025-03-15 10:29:41', '2025-03-15 10:29:41', NULL, NULL, NULL);
INSERT INTO `checi_order` VALUES (8, '1742008916163', 3, 4, 27.50, 2, 1, '5', '2025-03-15', '2025-03-15 11:21:56', '2025-03-15 11:21:56', '已退款', 55.00, NULL);
INSERT INTO `checi_order` VALUES (9, '1742020648997', 2, 4, 1.00, 1, 2, '6', '2025-03-15', '2025-03-15 14:37:29', '2025-03-15 14:37:29', NULL, 2.00, NULL);
INSERT INTO `checi_order` VALUES (10, '1742027688144', 5, 4, 999.00, 3, 1, '8', '2025-03-15', '2025-03-15 16:34:48', '2025-03-15 16:34:48', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (11, '1742027728070', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:35:28', '2025-03-15 16:35:28', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (12, '1742027854925', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:37:35', '2025-03-15 16:37:35', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (13, '1742027961553', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:39:22', '2025-03-15 16:39:22', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (14, '1742028114251', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:41:54', '2025-03-15 16:41:54', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (15, '1742028123911', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:42:04', '2025-03-15 16:42:04', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (16, '1742028540321', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:49:00', '2025-03-15 16:49:00', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (17, '1742028554315', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:49:14', '2025-03-15 16:49:14', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (18, '1742028600636', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:50:01', '2025-03-15 16:50:01', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (19, '1742028649997', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:50:50', '2025-03-15 16:50:50', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (20, '1742028804996', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:53:25', '2025-03-15 16:53:25', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (21, '1742028859912', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:54:20', '2025-03-15 16:54:20', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (22, '1742028909194', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:55:09', '2025-03-15 16:55:09', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (23, '1742028983806', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:56:24', '2025-03-15 16:56:24', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (24, '1742029111736', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:58:32', '2025-03-15 16:58:32', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (25, '1742029127473', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:58:47', '2025-03-15 16:58:47', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (26, '1742029138961', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:58:59', '2025-03-15 16:58:59', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (27, '1742029176702', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 16:59:37', '2025-03-15 16:59:37', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (28, '1742029211729', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:00:12', '2025-03-15 17:00:12', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (29, '1742029269905', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:01:10', '2025-03-15 17:01:10', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (30, '1742029310442', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:01:50', '2025-03-15 17:01:50', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (31, '1742029366745', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:02:47', '2025-03-15 17:02:47', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (32, '1742029544005', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:05:44', '2025-03-15 17:05:44', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (33, '1742029611115', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:06:51', '2025-03-15 17:06:51', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (34, '1742029616946', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:06:57', '2025-03-15 17:06:57', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (35, '1742029669086', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:07:49', '2025-03-15 17:07:49', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (36, '1742029692362', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:08:12', '2025-03-15 17:08:12', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (37, '1742029730198', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:08:50', '2025-03-15 17:08:50', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (38, '1742029770653', 5, 4, 11.00, 1, 1, '8', '2025-03-15', '2025-03-15 17:09:31', '2025-03-15 17:09:31', NULL, 22.00, 2);
INSERT INTO `checi_order` VALUES (39, '1742038943460', 4, 4, 27.50, 1, 1, '7', '2025-03-15', '2025-03-15 19:42:23', '2025-03-15 19:42:23', NULL, 55.00, 2);
INSERT INTO `checi_order` VALUES (40, '1742038974143', 4, 4, 27.50, 1, 1, '4', '2025-03-15', '2025-03-15 19:42:54', '2025-03-15 19:42:54', NULL, 55.00, 2);
INSERT INTO `checi_order` VALUES (41, '1742127316394', 3, 4, 27.00, 2, 1, '4', '2025-03-16', '2025-03-16 20:20:00', '2025-03-16 20:20:00', '已退款', 55.00, 2);
INSERT INTO `checi_order` VALUES (42, '1742130487672', 4, 6, 2.00, 1, 1, '6', '2025-03-16', '2025-03-16 21:09:14', '2025-03-16 21:09:14', NULL, 2.00, 0);
INSERT INTO `checi_order` VALUES (44, '1742139168104', 4, 7, 5.00, 1, 1, '7', '2025-03-16', '2025-03-16 23:33:38', '2025-03-16 23:33:38', NULL, 10.00, 2);
INSERT INTO `checi_order` VALUES (45, '1742139864073', 1, 7, 55.00, 1, 1, '5,6', '2025-03-16', '2025-03-16 23:45:11', '2025-03-16 23:45:11', NULL, 55.00, 2);
INSERT INTO `checi_order` VALUES (46, '1742187503658', 8, 4, 431.00, 1, 1, '5', '2025-03-17', '2025-03-17 12:58:48', '2025-03-17 12:58:48', NULL, 862.00, 2);
INSERT INTO `checi_order` VALUES (47, '1742187642384', 6, 4, 800.00, 1, 4, '7,20', '2025-03-17', '2025-03-17 13:01:11', '2025-03-17 13:01:11', NULL, 800.00, 2);
INSERT INTO `checi_order` VALUES (48, '1742188173347', 7, 4, 729.00, 1, 1, '8', '2025-03-17', '2025-03-17 13:10:01', '2025-03-17 13:10:01', NULL, 1458.00, 2);
INSERT INTO `checi_order` VALUES (49, '1742188489190', 6, 6, 2648.00, 1, 1, '5,7,8,9', '2025-03-17', '2025-03-17 13:15:10', '2025-03-17 13:15:10', NULL, 662.00, 0);
INSERT INTO `checi_order` VALUES (50, '1742207903856', 7, 6, 1458.00, 1, 1, '9', '2025-03-17', '2025-03-17 18:39:06', '2025-03-17 18:39:06', NULL, 1458.00, 0);
INSERT INTO `checi_order` VALUES (51, '1742642282503', 8, 4, 61.00, 1, 1, '5', '2025-03-22', '2025-03-22 19:18:27', '2025-03-22 19:18:27', NULL, 123.00, 2);
INSERT INTO `checi_order` VALUES (52, '1742643658466', 11, 4, 1.00, 1, 1, '4', '2025-03-22', '2025-03-22 19:41:20', '2025-03-22 19:41:20', NULL, 2.00, 2);
INSERT INTO `checi_order` VALUES (53, '1742188489192', 6, 6, 9990.00, 1, 1, '10', '2025-03-17', '2025-03-17 13:15:10', '2025-03-17 13:15:10', NULL, 662.00, 0);

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, '轮播图1', 'http://localhost:8080/huochpiaodingpiao/upload/1742139989684.png');
INSERT INTO `config` VALUES (2, '轮播图2', 'http://localhost:8080/huochpiaodingpiao/upload/1742140007974.png');

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段',
  `dic_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段名',
  `code_index` int(0) NULL DEFAULT NULL COMMENT '编码',
  `index_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码名字  Search111 ',
  `super_id` int(0) NULL DEFAULT NULL COMMENT '父字段id',
  `beizhu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (103, 'shangxia_types', '上下架', 1, '上架', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (104, 'shangxia_types', '上下架', 2, '下架', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (105, 'checi_types', '火车类型', 1, '普速列车', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (106, 'checi_types', '火车类型', 2, '快速列车', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (107, 'checi_types', '火车类型', 3, '直达特快', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (108, 'checi_order_types', '订单类型', 1, '已支付', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (109, 'checi_order_types', '订单类型', 2, '退款', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (110, 'checi_order_types', '订单类型', 3, '已使用', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (111, 'sex_types', '性别类型', 1, '男', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (112, 'sex_types', '性别类型', 2, '女', NULL, NULL, '2022-03-30 14:23:47');
INSERT INTO `dictionary` VALUES (113, 'checi_types', '火车类型', 4, '特快列车', NULL, '', '2025-03-16 21:47:37');
INSERT INTO `dictionary` VALUES (114, 'checi_types', '火车类型', 5, '高铁', NULL, '', '2025-03-16 21:47:51');
INSERT INTO `dictionary` VALUES (115, 'checi_types', '火车类型', 6, '动车', NULL, '', '2025-03-16 21:48:00');
INSERT INTO `dictionary` VALUES (116, 'checi_types', '火车类型', 7, '高速动车', NULL, '', '2025-03-16 21:48:10');

-- ----------------------------
-- Table structure for liuyan
-- ----------------------------
DROP TABLE IF EXISTS `liuyan`;
CREATE TABLE `liuyan`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `yonghu_id` int(0) NULL DEFAULT NULL COMMENT '会员',
  `liuyan_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言标题  Search111 ',
  `liuyan_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '留言内容',
  `insert_time` timestamp(0) NULL DEFAULT NULL COMMENT '留言时间',
  `reply_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '回复时间',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间 show2 nameShow',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '留言版' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of liuyan
-- ----------------------------
INSERT INTO `liuyan` VALUES (1, 3, '留言标题1', '留言内容1', '2022-03-30 14:23:57', '回复信息1', '2022-03-30 14:23:57', '2022-03-30 14:23:57');
INSERT INTO `liuyan` VALUES (2, 3, '留言标题2', '留言内容2', '2022-03-30 14:23:57', '回复信息2', '2022-03-30 14:23:57', '2022-03-30 14:23:57');
INSERT INTO `liuyan` VALUES (3, 3, '留言标题3', '留言内容3', '2022-03-30 14:23:57', '回复信息3', '2022-03-30 14:23:57', '2022-03-30 14:23:57');
INSERT INTO `liuyan` VALUES (4, 1, '留言标题4', '留言内容4', '2022-03-30 14:23:57', '回复信息4', '2022-03-30 14:23:57', '2022-03-30 14:23:57');
INSERT INTO `liuyan` VALUES (5, 2, '留言标题5', '留言内容5', '2022-03-30 14:23:57', '回复信息5', '2022-03-30 14:23:57', '2022-03-30 14:23:57');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `expiratedtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'token表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, 1, 'a1', 'yonghu', '会员', 'zu9ixbcrl5fqn03xwvl1zqco2p5gm6zs', '2022-03-30 14:15:23', '2025-03-17 19:32:42');
INSERT INTO `token` VALUES (2, 1, 'admin', 'users', '管理员', '5oi3d61l26zvcp18j9ev0296wxnuawau', '2022-03-30 14:31:01', '2025-03-22 20:22:23');
INSERT INTO `token` VALUES (3, 4, '1', 'yonghu', '会员', 'oxg31f24f7l8hc5ho0nfdjj1g276k832', '2025-03-14 21:26:29', '2025-03-22 20:25:36');
INSERT INTO `token` VALUES (4, 6, '123', 'yonghu', '会员', '9lj62trqaf9o5i0xrr9m63jit5ksnzs5', '2025-03-16 20:28:40', '2025-03-17 19:39:20');
INSERT INTO `token` VALUES (5, 7, '2', 'yonghu', '会员', '87tyldc7l6139758e4e2lcihmlf4ybum', '2025-03-16 23:22:25', '2025-03-17 00:48:05');
INSERT INTO `token` VALUES (6, 8, '1111', 'yonghu', '会员', '16211a9n5g0q1cmyub4jp4d95dxerlww', '2025-03-17 19:10:22', '2025-03-18 16:20:02');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', '管理员', '2022-05-01 00:00:00');

-- ----------------------------
-- Table structure for yonghu
-- ----------------------------
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `yonghu_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员姓名 Search111 ',
  `yonghu_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `yonghu_phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员手机号',
  `yonghu_id_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员身份证号 ',
  `sex_types` int(0) NULL DEFAULT NULL COMMENT '性别 Search111 ',
  `new_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额 ',
  `yonghu_delete` int(0) NULL DEFAULT 1 COMMENT '假删',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `zhengjian` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户证件',
  `zhekou` int(0) NULL DEFAULT 0 COMMENT '折扣',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yonghu
-- ----------------------------
INSERT INTO `yonghu` VALUES (1, 'a1', '123456', '会员姓名1', 'http://localhost:8080/huochpiaodingpiao/upload/yonghu1.jpg', '17703786901', '410224199610232001', 2, 77459.32, 1, '2022-03-30 14:23:57', NULL, 0);
INSERT INTO `yonghu` VALUES (2, 'a2', '123456', '会员姓名2', 'http://localhost:8080/huochpiaodingpiao/upload/yonghu2.jpg', '17703786902', '410224199610232002', 2, 834.76, 1, '2022-03-30 14:23:57', NULL, 3);
INSERT INTO `yonghu` VALUES (3, 'a3', '123456', '会员姓名3', 'http://localhost:8080/huochpiaodingpiao/upload/yonghu3.jpg', '17703786903', '410224199610232003', 1, 436.53, 1, '2022-03-30 14:23:57', NULL, 4);
INSERT INTO `yonghu` VALUES (4, '1', '1', '666', NULL, '15332222222', '110222222222222', 2, 21219166.19, 1, '2025-03-14 16:44:13', 'http://localhost:8080/huochpiaodingpiao/upload/1741941817065.png', 2);
INSERT INTO `yonghu` VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, 0);
INSERT INTO `yonghu` VALUES (6, '123', '123', '刘博宇', 'http://localhost:8080/huochpiaodingpiao/upload/1742128164213.jpg', '18279085380', '360502200209306034', 1, 1892.00, 1, '2025-03-16 20:28:36', NULL, 0);
INSERT INTO `yonghu` VALUES (7, '2', '2', '777', 'http://localhost:8080/huochpiaodingpiao/upload/1742138604991.png', '15779592391', '360502200209306037', 1, 495.00, 1, '2025-03-16 23:22:19', ' http://baaviv.natappfree.cc/huochpiaodingpiao/upload/1742138523912.png', 2);
INSERT INTO `yonghu` VALUES (8, '1111', '1111', '1235', NULL, '18279085360', '360502200209306039', 1, 0.00, 1, '2025-03-17 19:10:14', 'http://izzx45.natappfree.cc/huochpiaodingpiao/upload/1742209802813.jpg', 4);

-- ----------------------------
-- Table structure for zhekou
-- ----------------------------
DROP TABLE IF EXISTS `zhekou`;
CREATE TABLE `zhekou`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `piaozhong` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '票种',
  `zhekou` float(255, 2) NULL DEFAULT NULL COMMENT '折扣',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zhekou
-- ----------------------------
INSERT INTO `zhekou` VALUES (2, '残疾人5折', 0.50);
INSERT INTO `zhekou` VALUES (3, '学生 75 折', 0.75);
INSERT INTO `zhekou` VALUES (4, '军人5折', 0.50);

SET FOREIGN_KEY_CHECKS = 1;
