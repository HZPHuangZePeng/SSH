/*
SQLyog v10.2 
MySQL - 5.5.27 : Database - restrant
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`restrant` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `restrant`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `LoginName` varchar(20) DEFAULT NULL,
  `LoginPwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`Id`,`LoginName`,`LoginPwd`) values (1,'admin','123456');

/*Table structure for table `meal` */

DROP TABLE IF EXISTS `meal`;

CREATE TABLE `meal` (
  `MealId` int(4) NOT NULL AUTO_INCREMENT,
  `MealSeriesId` int(4) DEFAULT NULL,
  `MealName` varchar(20) DEFAULT NULL,
  `MealSummarize` varchar(250) DEFAULT NULL,
  `MealDescription` varchar(250) DEFAULT NULL,
  `MealPrice` decimal(8,2) DEFAULT NULL,
  `MealImage` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MealId`),
  KEY `MealSeriesId` (`MealSeriesId`),
  CONSTRAINT `meal_ibfk_1` FOREIGN KEY (`MealSeriesId`) REFERENCES `mealseries` (`SeriesId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `meal` */

insert  into `meal`(`MealId`,`MealSeriesId`,`MealName`,`MealSummarize`,`MealDescription`,`MealPrice`,`MealImage`) values (1,1,'雪梨肉肘棒','味鲜香甜咸，肘棒不腻','味鲜香甜咸，肘棒不腻','10.00','01.jpg'),(2,1,'素锅烤鸭肉','颜色鲜艳，酷似鸭肉，鲜香不腻。','颜色鲜艳，酷似鸭肉，鲜香不腻。','20.00','02.jpg'),(3,1,'烤花肉揽桂鱼','味道特鲜，白中泛红，佐以姜末、香醋，尤胜一等。',' 烤花揽桂鱼，是运用孔府菜的传统独特工艺味，道特鲜，白中泛红，佐以姜末、香醋，尤胜一等。','15.00','03.jpg'),(4,1,'泰安肉三美豆腐','汤汁乳白而鲜，豆腐软滑，白菜鲜嫩，清淡爽口。','“泰安三美豆腐”是泰安风味名菜。泰安产的白菜、豆腐和泰山泉水，历来被誉为“泰安三美”。泰安白菜个儿大心实，质细无筋；泰安豆腐，浆细质纯，嫩而不老；泰山泉水，清甜爽口，杂质少','8.00','04.jpg'),(5,1,'落叶琵琶肉虾','外型美观，鲜香味美。',' 外型美观，鲜香味美。 ','14.00','05.jpg'),(6,1,'肉冬菜肉末','味鲜香适口，下饭便菜。','味鲜香适口，下饭便菜。','12.00','06.jpg'),(7,1,'糖醋红柿椒','色红美，味鲜香。','色红美，味鲜香。','8.00','07.jpg'),(8,2,'芹黄烧鱼条','色泽金黄润亮，鱼肉鲜嫩味香。','色泽金黄润亮，鱼肉鲜嫩味香。','15.00','08.jpg'),(9,2,'巴国玉米糕肉','风味浓、口感奇、品种多','风味浓、口感奇、品种多，信手拈来，皆为佳品。','13.00','09.jpg'),(10,2,'酥皮龙虾','色泽黄绿相衬，协调雅致，外酥内嫩，鲜香爽口','酥皮龙虾成菜配以西兰花和蒜薹花，使色泽更加丰富，口感多样，诱人食欲。','20.00','10.jpg'),(11,1,'香煎茄片','色泽红亮，口感似鱼 ','色泽红亮，口感似鱼 ','9.00','11.jpg'),(12,1,'金陵片皮鸭','此菜深红明亮，皮脆，肉滑，分两次上桌，滋味各异','此菜深红明亮，皮脆，肉滑，分两次上桌，滋味各异，原为江苏风味，传入广东有较长历史，在制法上已有不少变化，现已成为粤菜名品。','10.00','12.jpg');

/*Table structure for table `mealseries` */

DROP TABLE IF EXISTS `mealseries`;

CREATE TABLE `mealseries` (
  `SeriesId` int(4) NOT NULL AUTO_INCREMENT,
  `SeriesName` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`SeriesId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `mealseries` */

insert  into `mealseries`(`SeriesId`,`SeriesName`) values (1,'鲁菜'),(2,'川菜'),(3,'粤菜'),(4,'苏菜'),(5,'闽菜'),(6,'浙菜'),(7,'湘菜'),(8,'徽菜'),(9,'西餐'),(10,'西点'),(11,'药膳'),(12,'私房菜');

/*Table structure for table `orderdts` */

DROP TABLE IF EXISTS `orderdts`;

CREATE TABLE `orderdts` (
  `ODID` int(4) NOT NULL AUTO_INCREMENT,
  `OID` int(4) DEFAULT NULL,
  `MealId` int(4) DEFAULT NULL,
  `MealPrice` decimal(8,2) DEFAULT NULL,
  `MealCount` int(4) DEFAULT NULL,
  PRIMARY KEY (`ODID`),
  KEY `OID` (`OID`),
  KEY `MealId` (`MealId`),
  CONSTRAINT `orderdts_ibfk_1` FOREIGN KEY (`OID`) REFERENCES `orders` (`OID`),
  CONSTRAINT `orderdts_ibfk_2` FOREIGN KEY (`MealId`) REFERENCES `meal` (`MealId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `orderdts` */

insert  into `orderdts`(`ODID`,`OID`,`MealId`,`MealPrice`,`MealCount`) values (18,9,1,'10.00',1),(19,9,2,'20.00',1),(20,10,1,'10.00',2),(21,10,2,'20.00',3),(22,11,2,'20.00',2),(23,11,4,'8.00',1),(24,12,2,'20.00',3),(25,12,4,'8.00',1);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `OID` int(4) NOT NULL AUTO_INCREMENT,
  `UserId` int(4) DEFAULT NULL,
  `OrderTime` datetime DEFAULT NULL,
  `OrderState` varchar(20) DEFAULT NULL,
  `OrderPrice` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`OID`),
  KEY `UserId` (`UserId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`OID`,`UserId`,`OrderTime`,`OrderState`,`OrderPrice`) values (9,1,'2014-05-04 16:07:44','未处理','30.00'),(10,1,'2014-05-04 18:54:17','未处理','80.00'),(11,1,'2014-05-05 17:30:16','未处理','48.00'),(12,2,'2014-05-05 17:52:48','未处理','68.00');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `LoginName` varchar(20) DEFAULT NULL,
  `LoginPwd` varchar(20) DEFAULT NULL,
  `TrueName` varchar(20) DEFAULT NULL,
  `Email` varchar(20) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`Id`,`LoginName`,`LoginPwd`,`TrueName`,`Email`,`Phone`,`Address`) values (1,'zhangsan','123456','张三','user@163.com','13200000001','江苏南京X区X小区X栋001'),(2,'zs','zs','zs','zs','zs','zs'),(3,'lisi','lisi','lisi','lisi','lisi','lisi');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
