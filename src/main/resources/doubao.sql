/*
 Navicat Premium Data Transfer
 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : doubao
 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bms_billboard
-- ----------------------------
DROP TABLE IF EXISTS `bms_billboard`;
CREATE TABLE `bms_billboard`  (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT '����',
                                  `content` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '����',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '����ʱ��',
                                  `show` tinyint(1) NULL DEFAULT NULL COMMENT '1��չʾ�У�0������',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4  COMMENT = 'ȫվ����' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_billboard
-- ----------------------------
INSERT INTO `bms_billboard` VALUES (2, 'R1.0 ��ʼ��ʵ�ֻ���ģʽ ,������Ҳ���õ����ҵ��۾��ˡ�', '2020-11-19 17:16:19', 0);
INSERT INTO `bms_billboard` VALUES (4, 'ϵͳ�Ѹ��������°�1.0.1', NULL, 1);


-- ----------------------------
-- Table structure for bms_follow
-- ----------------------------
DROP TABLE IF EXISTS `bms_follow`;
CREATE TABLE `bms_follow`  (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '����',
                               `parent_id` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '����ע��ID',
                               `follower_id` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '��ע��ID',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8mb4  COMMENT = '�û���ע' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_follow
-- ----------------------------
INSERT INTO `bms_follow` VALUES (65, '1329723594994229250', '1317498859501797378');
INSERT INTO `bms_follow` VALUES (85, '1332912847614083073', '1332636310897664002');
INSERT INTO `bms_follow` VALUES (129, '1349290158897311745', '1349618748226658305');

-- ----------------------------
-- Table structure for bms_post
-- ----------------------------
DROP TABLE IF EXISTS `bms_post`;
CREATE TABLE `bms_post`  (
                             `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '����',
                             `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '����',
                             `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'markdown����',
                             `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '����ID',
                             `comments` int NOT NULL DEFAULT 0 COMMENT '����ͳ��',
                             `collects` int NOT NULL DEFAULT 0 COMMENT '�ղ�ͳ��',
                             `view` int NOT NULL DEFAULT 0 COMMENT '���ͳ��',
                             `top` bit(1) NOT NULL DEFAULT b'0' COMMENT '�Ƿ��ö���1-�ǣ�0-��',
                             `essence` bit(1) NOT NULL DEFAULT b'0' COMMENT '�Ƿ�Ӿ���1-�ǣ�0-��',
                             `section_id` int NULL DEFAULT 0 COMMENT 'ר��ID',
                             `create_time` datetime NOT NULL COMMENT '����ʱ��',
                             `modify_time` datetime NULL DEFAULT NULL COMMENT '�޸�ʱ��',
                             UNIQUE INDEX `title`(`title`) USING BTREE,
                             INDEX `user_id`(`user_id`) USING BTREE,
                             INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '�����' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_post
-- ----------------------------
INSERT INTO `bms_post` VALUES ('1333447953558765569', '1', '12\n2\n\n', '1349290158897311745', 0, 0, 77, b'0', b'0', 0, '2020-12-01 00:29:01', '2020-12-03 23:56:51');
INSERT INTO `bms_post` VALUES ('1349362401438392322', '2021 ����������', '2021��`FLAG`\n\n1. ���ܽ���\n2. û�з���\n3. ���� :smile:\n\n', '1349290158897311745', 0, 0, 21, b'0', b'0', 0, '2021-01-13 22:27:21', '2021-01-14 17:30:13');
INSERT INTO `bms_post` VALUES ('1334481725322297346', 'hello��spring-security', ':hibiscus: spring-security\n\n', '1349290158897311745', 0, 0, 46, b'0', b'0', 0, '2020-12-03 20:56:51', NULL);
INSERT INTO `bms_post` VALUES ('1332650453142827009', '��������helloworld', '���ǵ�һƪŶ\n\n> hi :handshake: ���\n\n`hello world`\n\n:+1: �ܺ�\n', '1349290158897311745', 0, 0, 29, b'0', b'0', 1, '2020-11-28 19:40:02', '2020-11-28 19:46:39');
INSERT INTO `bms_post` VALUES ('1333432347031646209', '�����������˸�dark����', '�������ΪDark\n\n', '1349290158897311745', 0, 0, 6, b'0', b'0', 0, '2020-11-30 23:27:00', NULL);
INSERT INTO `bms_post` VALUES ('1333668258587750401', '�ٺ٣�����һ�°�', '��Һ�\n`Hello everyone!`\n\n\n\n', '1349290158897311745', 0, 0, 7, b'0', b'0', 0, '2020-12-01 15:04:26', '2020-12-01 16:49:14');
INSERT INTO `bms_post` VALUES ('1332682473151635458', '��Ҫ����', '2021 ���壡����\n\n', '1349290158897311745', 0, 0, 94, b'0', b'0', 2, '2020-11-28 21:47:16', '2020-11-30 19:40:22');
INSERT INTO `bms_post` VALUES ('1333695976536748034', '���°汾���ܣ�ͬ�����£�', '<p align=center>һ�����SpringBoot�������ǻ�����ϵͳ</p>\n\n<p align=center>\n<a href=\"https://github.com/1020317774/rhapsody-admin/stargazers\"><img alt=\"GitHub release\" src=\"https://img.shields.io/github/release/1020317774/rhapsody-admin?style=flat-square\"></a>\n<a href=\"https://github.com/1020317774/rhapsody-admin/blob/main/LICENSE\"><img alt=\"GitHub license\" src=\"https://img.shields.io/github/license/1020317774/rhapsody-admin\"></a>\n</p>\n\n### Hi there :wave:\n\n<!--\n**1020317774/1020317774** is a :sparkles: _special_ :sparkles: repository because its `README.md` (this file) appears on your GitHub profile.\n\nHere are some ideas to get you started:\n\n- :telescope: I��m currently working on ...\n- :seedling: I��m currently learning ...\n- :dancers: I��m looking to collaborate on ...\n- :thinking: I��m looking for help with ...\n- :speech_balloon: Ask me about ...\n- :mailbox: How to reach me: ...\n- :smile: Pronouns: ...\n- :zap: Fun fact: ...\n-->\n\n[![1020317774\'s github stats](https://github-readme-stats.vercel.app/api?username=1020317774&show_icons=true&count_private=true)](https://github.com/1020317774)\n\n[![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=1020317774&layout=compact)](https://github.com/1020317774)\n---------\n\n> ���ߣ���һ��\n> github��[https://github.com/1020317774](https://github.com/1020317774)\n\n## ����ջ\n\n- [x] SpringBoot 2.X\n- [x] Mysql 8.X\n- [x] Mybatis\n- [x] MybatisPlus\n- [x] Redis\n- [x] Jwt\n- [x] FastJson\n- [x] Hutool\n- [x] Lombok\n- [ ] ElasticSearch\n\n����\n\n## ��װָ��\n\n- ��¡\n\n```java\ngit clone https://github.com/1020317774/rhapsody-admin.git\n```\n\n- �޸�`application.properties`ѡ�񻷾�\n- �޸Ķ໷�������е�redis���������ݿ�\n- ����`BootApplication`\n- ����[`http://127.0.0.1:10000`](http://127.0.0.1:10000)\n\n', '1349290158897311745', 0, 0, 45, b'1', b'1', 0, '2020-12-01 16:54:34', '2020-12-01 17:05:00');
INSERT INTO `bms_post` VALUES ('1349631541260595202', 'Ȩ�޲��� OK', '1. ���� ok\n2. �޸� ok\n3. ɾ�� ok\n\n', '1349290158897311745', 0, 0, 17, b'0', b'0', 0, '2021-01-14 16:16:49', '2021-01-14 16:18:53');
INSERT INTO `bms_post` VALUES ('1333676096156528641', '����', '����\n\n', '1349290158897311745', 0, 0, 38, b'0', b'0', 0, '2020-12-01 15:35:34', NULL);
INSERT INTO `bms_post` VALUES ('1332681213400817665', '�ۺϲ�ѯ��ͳ��', '* [x] SQL��\n\n```sql\nSELECT s.*,\nCOUNT(t.id) AS topics\nFROM section s\nLEFT JOIN topic t\nON s.id = t.section_id\nGROUP BY s.title\n```\n\n', '1349290158897311745', 0, 0, 55, b'0', b'0', 1, '2020-11-28 21:42:16', '2020-11-29 15:00:42');
INSERT INTO `bms_post` VALUES ('1335149981733449729', '��ƵǶ��', ':+1:\n\n[https://www.bilibili.com/video/BV1w64y1f7w3](https://www.bilibili.com/video/BV1w64y1f7w3)\n\n[1](https://www.bilibili.com/video/BV1tp4y1x72w)\n\n```\n.vditor-reset pre > code\n```\n\n```\npublic class HelloWorld {\n\npublic static void main(String[] args) {\n    System.out.println(\"Hello World!\");\n}\n}\n```\n\n', '1349290158897311745', 0, 0, 41, b'0', b'0', 0, '2020-12-05 17:12:16', '2021-01-14 13:06:16');


-- ----------------------------
-- Table structure for bms_comment
-- ----------------------------
DROP TABLE IF EXISTS `bms_comment`;
CREATE TABLE `bms_comment`  (
                                `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '����',
                                `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '����',
                                `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '����ID',
                                `topic_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'topic_id',
                                `create_time` datetime NOT NULL COMMENT '����ʱ��',
                                `modify_time` datetime NULL DEFAULT NULL COMMENT '�޸�ʱ��',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '���۱�' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for bms_tag
-- ----------------------------
DROP TABLE IF EXISTS `bms_tag`;
CREATE TABLE `bms_tag`  (
                            `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '��ǩID',
                            `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '��ǩ',
                            `topic_count` int NOT NULL DEFAULT 0 COMMENT '��������',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '��ǩ��' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_tag
-- ----------------------------
INSERT INTO `bms_tag` VALUES ('1332650453377708034', 'java', 1);
INSERT INTO `bms_tag` VALUES ('1332681213568589825', 'css', 1);
INSERT INTO `bms_tag` VALUES ('1332681213631504385', 'mongodb', 1);
INSERT INTO `bms_tag` VALUES ('1332682473218744321', 'python', 1);
INSERT INTO `bms_tag` VALUES ('1332913064463794178', 'vue', 2);
INSERT INTO `bms_tag` VALUES ('1332913064530903041', 'react', 1);
INSERT INTO `bms_tag` VALUES ('1333432347107143681', 'node', 1);
INSERT INTO `bms_tag` VALUES ('1333432347107143682', 'mysql', 1);
INSERT INTO `bms_tag` VALUES ('1333447953697177602', 'flask', 2);
INSERT INTO `bms_tag` VALUES ('1333676096320106498', 'spring', 1);
INSERT INTO `bms_tag` VALUES ('1333695976742268930', 'django', 1);
INSERT INTO `bms_tag` VALUES ('1334481725519429634', 'security', 1);
INSERT INTO `bms_tag` VALUES ('1349362401597775874', 'tensorflow', 1);
INSERT INTO `bms_tag` VALUES ('1349631541306732545', 'pytorch', 1);


-- ----------------------------
-- Table structure for bms_post_tag
-- ----------------------------
DROP TABLE IF EXISTS `bms_post_tag`;
CREATE TABLE `bms_post_tag`  (
                                 `id` int NOT NULL AUTO_INCREMENT COMMENT '����',
                                 `tag_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '��ǩID',
                                 `topic_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '����ID',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `tag_id`(`tag_id`) USING BTREE,
                                 INDEX `topic_id`(`topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '����-��ǩ �м��' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_post_tag
-- ----------------------------
INSERT INTO `bms_post_tag` VALUES (36, '1332650453377708034', '1332650453142827009');
INSERT INTO `bms_post_tag` VALUES (37, '1332681213568589825', '1332681213400817665');
INSERT INTO `bms_post_tag` VALUES (38, '1332681213631504385', '1332681213400817665');
INSERT INTO `bms_post_tag` VALUES (39, '1332682473218744321', '1332682473151635458');
INSERT INTO `bms_post_tag` VALUES (40, '1332913064463794178', '1332913064396685314');
INSERT INTO `bms_post_tag` VALUES (41, '1332913064530903041', '1332913064396685314');
INSERT INTO `bms_post_tag` VALUES (42, '1333432347107143681', '1333432347031646209');
INSERT INTO `bms_post_tag` VALUES (43, '1333432347107143682', '1333432347031646209');
INSERT INTO `bms_post_tag` VALUES (44, '1333447953697177602', '1333447953558765569');
INSERT INTO `bms_post_tag` VALUES (45, '1332913064463794178', '1333668258587750401');
INSERT INTO `bms_post_tag` VALUES (46, '1333676096320106498', '1333676096156528641');
INSERT INTO `bms_post_tag` VALUES (47, '1333695976742268930', '1333695976536748034');
INSERT INTO `bms_post_tag` VALUES (48, '1334481725519429634', '1334481725322297346');
INSERT INTO `bms_post_tag` VALUES (49, '1333447953697177602', '1335149981733449729');
INSERT INTO `bms_post_tag` VALUES (50, '1349362401597775874', '1349362401438392322');
INSERT INTO `bms_post_tag` VALUES (51, '1349631541306732545', '1349631541260595202');

-- ----------------------------
-- Table structure for bms_promotion
-- ----------------------------
DROP TABLE IF EXISTS `bms_promotion`;
CREATE TABLE `bms_promotion`  (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT '����',
                                  `title` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '������',
                                  `link` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '�������',
                                  `description` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '˵��',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4  COMMENT = '����ƹ��' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_promotion
-- ----------------------------
INSERT INTO `bms_promotion` VALUES (1, '������ͷ��', 'https://juejin.cn/', '������ͷ��');
INSERT INTO `bms_promotion` VALUES (2, '���������', 'https://juejin.cn/', '���������');
INSERT INTO `bms_promotion` VALUES (3, '���', 'https://juejin.cn/', '���');


-- ----------------------------
-- Table structure for bms_tip
-- ----------------------------
DROP TABLE IF EXISTS `bms_tip`;
CREATE TABLE `bms_tip`  (
                            `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '����',
                            `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '����',
                            `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '����',
                            `type` tinyint NOT NULL COMMENT '1��ʹ�ã�0������',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24864 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'ÿ������' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_tip
-- ----------------------------
INSERT INTO `bms_tip` VALUES (1, '��ﱳ���⣬������֪ʶ��', '����', 1);
INSERT INTO `bms_tip` VALUES (2, 'δ��������ʲô�������������ĵ��ˡ�', '����', 1);
INSERT INTO `bms_tip` VALUES (3, 'һ���˵��ǻ۲����ã������˵��ǻ��ò��ꡣ', '����', 1);
INSERT INTO `bms_tip` VALUES (4, 'ʮ��ָͷ����סʮ������', '����', 1);
INSERT INTO `bms_tip` VALUES (5, '�Բ����ߣ��в�����', 'ī��', 1);
INSERT INTO `bms_tip` VALUES (6, '��Ԯ���ǣ�������ۣ�������֮������������ϯ֮�¡�', '����Ԫ', 1);
INSERT INTO `bms_tip` VALUES (7, '���´󶼰��������õ�ϰ��֮�ڡ�', '������', 1);
INSERT INTO `bms_tip` VALUES (8, '���в�����������ˡ��', '�����顷', 1);
INSERT INTO `bms_tip` VALUES (9, '������Ω˳', '����������', 1);
INSERT INTO `bms_tip` VALUES (10, '����������Ӧ��˵�滰�������ǻ���ô����', '��˹�����˹��', 1);
INSERT INTO `bms_tip` VALUES (11, 'ʱ����һ�вƸ������ĲƸ���', '�°¸���˹��', 1);
INSERT INTO `bms_tip` VALUES (12, '�����²˵�', '����', 1);
INSERT INTO `bms_tip` VALUES (13, '��������±��˷��У�Ů�˾����ᱣ�����������ࡣ', '����ʲ����', 1);
INSERT INTO `bms_tip` VALUES (14, '���Ǵ�ů����ʱ����������Ц��', '����', 1);
INSERT INTO `bms_tip` VALUES (15, 'ϣ���Ǽ��͵Ĺ��ȣ����������д���Я�����ǣ��˿��Ե�������֮�á�', '����', 1);
INSERT INTO `bms_tip` VALUES (18, '�������Ҹ��������ڶ��氮��ϣ����', '����', 1);
INSERT INTO `bms_tip` VALUES (19, '�������ڱ�����ȫ���ֵ��ļ��ɣ�����һ�ж������������С�', '���˼', 1);
INSERT INTO `bms_tip` VALUES (20, '�ڿ�ѧ��û��ƽ̹�Ĵ����ֻ�в�η�Ϳ����Ŷ���ɽ·�ʵǵ��ˣ�����ϣ���ﵽ��ԵĶ��㡣', '���˼', 1);
INSERT INTO `bms_tip` VALUES (21, '���������·Զ', '�ɹ�', 1);
INSERT INTO `bms_tip` VALUES (22, '��������ˮ������ˮ�ճɵ�', '����', 1);


-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
                             `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '�û�ID',
                             `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '�û���',
                             `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�û��ǳ�',
                             `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '����',
                             `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ͷ��',
                             `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '����',
                             `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�ֻ�',
                             `score` int NOT NULL DEFAULT 0 COMMENT '����',
                             `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'token',
                             `bio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '���˼��',
                             `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '�Ƿ񼤻1���ǣ�0����',
                             `status` bit(1) NULL DEFAULT b'1' COMMENT '״̬��1��ʹ�ã�0��ͣ��',
                             `role_id` int NULL DEFAULT NULL COMMENT '�û���ɫ',
                             `create_time` datetime NOT NULL COMMENT '����ʱ��',
                             `modify_time` datetime NULL DEFAULT NULL COMMENT '�޸�ʱ��',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `user_name`(`username`) USING BTREE,
                             INDEX `user_email`(`email`) USING BTREE,
                             INDEX `user_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '�û���' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES ('1349290158897311745', 'admin', 'admin', '$2a$10$8qx711TBg/2hxfL7N.sxf.0ROMhR/iuPhQx33IFqGd7PLgt5nGJTO', 'https://s3.ax1x.com/2020/12/01/DfHNo4.jpg', '23456@qq.com', NULL, 2, '', '����ְҵ��', b'1', b'1', NULL, '2021-01-13 17:40:17', NULL);
INSERT INTO `ums_user` VALUES ('1349618748226658305', 'zhangsan', 'zhangsan', '$2a$10$7K3yYv8sMV5Xsc2facXTcuyDo8JQ4FJHvjZ7qtWYcJdei3Q6Fvqdm', 'https://s3.ax1x.com/2020/12/01/DfHNo4.jpg', '23456@qq.com', NULL, 0, '', '����ְҵ��', b'1', b'1', NULL, '2021-01-14 15:25:59', NULL);

SET FOREIGN_KEY_CHECKS = 1;
13
src/main/resources/application-dev.yaml
@@ -0,0 +1,13 @@
server:
  port: 8000

web:
  domain: http://localhost

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/doubao?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=GMT%2B8
    type: com.zaxxer.hikari.HikariDataSource
 13
src/main/resources/application-prod.yaml
@@ -0,0 +1,13 @@
server:
  port: 8088

web:
  domain: http://localhost

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/doubao?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=GMT%2B8
    type: com.zaxxer.hikari.HikariDataSource