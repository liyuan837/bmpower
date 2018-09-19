/*
SQLyog Ultimate v8.32 
MySQL - 5.6.33-log : Database - db_bmpower
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

/*Data for the table `tb_power` */

insert  into `tb_power`(`id`,`name`,`alias_name`,`parent_id`,`action_url`,`state`,`type`,`sort_num`,`project_id`,`add_time`,`add_user_code`,`opt_time`,`opt_user_code`,`user_code`) values (1,'日志管理','logger-module',0,'',1,1,1,8,'2018-09-19 14:06:31','D1234','2018-09-19 14:06:31','D1234',NULL),(2,'数据库管理','database-module',0,'',1,1,2,8,'2018-09-19 17:41:40','D1234','2018-09-19 17:41:40','D1234','D1234'),(3,'美女管理','beauty-module',0,'',1,1,1,9,'2018-09-19 17:42:11','D1234','2018-09-19 17:42:11','D1234','D1234'),(4,'创建数据库','database-add',2,'database/add',1,2,1,8,'2018-09-19 17:44:02','D1234','2018-09-19 17:44:02','D1234','D1234'),(5,'更新数据库','database-add',2,'database/add',1,2,2,8,'2018-09-19 17:44:18','D1234','2018-09-19 17:44:18','D1234','D1234');

/*Data for the table `tb_role` */

insert  into `tb_role`(`id`,`name`,`remark`,`add_time`,`add_user_code`,`opt_time`,`opt_user_code`,`project_id`,`user_code`) values (1,'最高权限组','此权限组拥有该项目最高权限','2018-09-19 17:58:19','D1234','2018-09-19 17:58:19','D1234',8,NULL);

/*Data for the table `tb_role_power_ref` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
