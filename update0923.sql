/*
SQLyog Ultimate v8.32 
MySQL - 5.7.21 : Database - db_bmpower
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_bmpower` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `db_bmpower`;

/*Table structure for table `tb_power` */

DROP TABLE IF EXISTS `tb_power`;

CREATE TABLE `tb_power` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称，用于显示',
  `alias_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限别名，英文名',
  `parent_id` int(11) DEFAULT NULL COMMENT '所属父级权限编号',
  `action_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '接口请求路径，或者页面路由',
  `state` int(11) DEFAULT NULL COMMENT '启用状态，1启用，0未启用',
  `type` int(11) DEFAULT NULL COMMENT '类型，0表示项目，1菜单目录，2接口功能',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序',
  `project_id` int(11) DEFAULT NULL COMMENT '所属项目编号',
  `add_time` datetime DEFAULT NULL,
  `add_user_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `opt_time` datetime DEFAULT NULL,
  `opt_user_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='细粒度权限';

/*Data for the table `tb_power` */

insert  into `tb_power`(`id`,`name`,`alias_name`,`parent_id`,`action_url`,`state`,`type`,`sort_num`,`project_id`,`add_time`,`add_user_code`,`opt_time`,`opt_user_code`,`user_code`) values (1,'自动化项目','auto-project',0,NULL,NULL,0,NULL,16,NULL,'D1234',NULL,'D1234','D1234'),(2,'数据库管理','database-module',1,'database',1,1,1,16,'2018-09-23 01:02:46','D1234','2018-09-23 03:17:55','D1234','D1234'),(3,'项目管理','project-module',1,'project',1,1,2,16,'2018-09-23 01:03:14','D1234','2018-09-23 03:18:06','D1234','D1234'),(4,'系统管理','system-module',1,'dependency',1,1,3,16,'2018-09-23 01:03:42','D1234','2018-09-23 03:18:31','D1234','D1234'),(5,'日志管理','logger-module',1,'logger',1,1,4,16,'2018-09-23 01:04:16','D1234','2018-09-23 03:18:42','D1234','D1234'),(6,'权限管理','power-module',1,'power',1,1,5,16,'2018-09-23 01:04:45','D1234','2018-09-23 03:18:50','D1234','D1234'),(7,'数据库创建','database-create',2,'database/add.html',1,1,1,16,'2018-09-23 01:05:36','D1234','2018-09-23 02:29:24','D1234','D1234'),(8,'数据库列表','database-list',2,'database/list.html',1,1,2,16,'2018-09-23 01:05:57','D1234','2018-09-23 02:29:17','D1234','D1234'),(9,'创建项目','project-create',3,'project/add.html',1,1,1,16,'2018-09-23 01:06:37','D1234','2018-09-23 02:29:08','D1234','D1234'),(10,'项目列表','project-list',3,'project/list.html',1,1,2,16,'2018-09-23 01:07:02','D1234','2018-09-23 02:29:38','D1234','D1234'),(11,'依赖管理','dependency-manage',4,'dependency/list.html',1,1,1,16,'2018-09-23 01:07:37','D1234','2018-09-23 02:30:39','D1234','D1234'),(12,'权限设置','power-module',6,'power/list.html',1,1,1,16,'2018-09-23 01:08:07','D1234','2018-09-23 02:31:28','D1234','D1234'),(13,'查看日志','logger-search',5,'logger/list.html',1,1,1,16,'2018-09-23 01:10:06','D1234','2018-09-23 02:31:10','D1234','D1234');

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限组名',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `add_time` datetime DEFAULT NULL,
  `add_user_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `opt_time` datetime DEFAULT NULL,
  `opt_user_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL COMMENT '所属项目',
  `user_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限祖、角色';

/*Data for the table `tb_role` */

insert  into `tb_role`(`id`,`name`,`remark`,`add_time`,`add_user_code`,`opt_time`,`opt_user_code`,`project_id`,`user_code`) values (1,'最高权限组','此权限组拥有项目所有权限','2018-09-23 01:13:27','D1234','2018-09-23 01:13:27','D1234',16,'D1234'),(2,'数据库权限组','自权限组只有数据库管理模块','2018-09-23 04:00:55','D1234','2018-09-23 04:00:55','D1234',16,'D1234');

/*Table structure for table `tb_role_power_ref` */

DROP TABLE IF EXISTS `tb_role_power_ref`;

CREATE TABLE `tb_role_power_ref` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `power_id` int(11) NOT NULL COMMENT '权限id',
  `add_time` datetime DEFAULT NULL,
  `add_user_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`role_id`,`power_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色与权限的映射关系';

/*Data for the table `tb_role_power_ref` */

insert  into `tb_role_power_ref`(`role_id`,`power_id`,`add_time`,`add_user_code`) values (1,1,'2018-09-23 01:13:35','D1234'),(1,2,'2018-09-23 01:13:35','D1234'),(1,3,'2018-09-23 01:13:35','D1234'),(1,4,'2018-09-23 01:13:35','D1234'),(1,5,'2018-09-23 01:13:35','D1234'),(1,6,'2018-09-23 01:13:35','D1234'),(1,7,'2018-09-23 01:13:35','D1234'),(1,8,'2018-09-23 01:13:35','D1234'),(1,9,'2018-09-23 01:13:35','D1234'),(1,10,'2018-09-23 01:13:35','D1234'),(1,11,'2018-09-23 01:13:35','D1234'),(1,12,'2018-09-23 01:13:35','D1234'),(1,13,'2018-09-23 01:13:35','D1234'),(2,1,'2018-09-23 04:01:24','D1234'),(2,2,'2018-09-23 04:01:12','D1234'),(2,7,'2018-09-23 04:01:12','D1234'),(2,8,'2018-09-23 04:01:12','D1234');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
