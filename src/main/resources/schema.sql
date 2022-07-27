DROP TABLE IF EXISTS store_delivery_location;
DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS owner;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS `option`;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS menu_group;

 DROP TABLE IF EXISTS address;
 DROP TABLE IF EXISTS delivery_location;

CREATE TABLE  IF NOT EXISTS `delivery_location` (
    `townCode` varchar(45) NOT NULL,
    `status` varchar(10) NOT NULL
);

CREATE TABLE  IF NOT EXISTS `address` (
     `townCode` varchar(10)  DEFAULT NULL,
     `cityName` varchar(40)  DEFAULT NULL,
     `cityCountyName` varchar(40)  DEFAULT NULL,
     `townName` varchar(40)  DEFAULT NULL,
     `roadNameCode` varchar(12)  DEFAULT NULL,
     `roadName` varchar(80)  DEFAULT NULL,
     `undergroundStatus` varchar(1)  DEFAULT NULL,
     `buildingNum` varchar(40)  DEFAULT NULL,
     `buildingSideNum` varchar(10)  DEFAULT NULL,
     `zipCode` varchar(5)  DEFAULT NULL,
     `buildingManagementNum` varchar(25)  NOT NULL,
     `buildingNameForCity` varchar(45)  DEFAULT NULL,
     `buildingUseClassification` varchar(100)  DEFAULT NULL,
     `administrativeTownCode` varchar(45)  DEFAULT NULL,
     `administrativeTownName` varchar(45)  DEFAULT NULL,
     `groundFloorNumber` varchar(40)  DEFAULT NULL,
     `undergroundFloorNumber` varchar(40)  DEFAULT NULL,
     `classificationApartmentBuildings` varchar(1)  DEFAULT NULL,
     `buildingCnt` varchar(40)  DEFAULT NULL,
     `detailBuildingName` varchar(100)  DEFAULT NULL,
     `BuildingNameChangeHistory` varchar(1000)  DEFAULT NULL,
     `BuildingNameChangeHistoryDetail` varchar(1000)  DEFAULT NULL,
     `livingStatus` varchar(1)  DEFAULT NULL,
     `buildingCenterXCoordinate` varchar(40)  DEFAULT NULL,
     `buildingCenterYCoordinate` varchar(40)  DEFAULT NULL,
     `exitXCoordinate` varchar(40)  DEFAULT NULL,
     `exitYCoordinate` varchar(40)  DEFAULT NULL,
     `cityNameEng` varchar(45)  DEFAULT NULL,
     `cityCountryNameEng` varchar(45)  DEFAULT NULL,
     `townNameEng` varchar(45)  DEFAULT NULL,
     `roadNameEng` varchar(80)  DEFAULT NULL,
     `townMobileClassification` varchar(45)  DEFAULT NULL,
     `mobileReasonCode` varchar(45)  DEFAULT NULL,
     PRIMARY KEY (`buildingManagementNum`)
);



CREATE TABLE users (
        `id` int NOT NULL AUTO_INCREMENT,
        `name` varchar(45) NOT NULL,
        `password` varchar(300) NOT NULL,
        `phone` varchar(45) NOT NULL,
        `email` varchar(45) NOT NULL,
        `image` varchar(200) DEFAULT 'https://i.ibb.co/5Fhs8FJ/2022-01-27-180757.jpg',
         PRIMARY KEY (`id`)
);

CREATE TABLE `store_delivery_location` (
       `townCode` varchar(45) NOT NULL,
       `storeId` int NOT NULL,
       `id` int NOT NULL AUTO_INCREMENT,
       PRIMARY KEY (`id`)
);



CREATE TABLE `owner` (
     `id` int NOT NULL AUTO_INCREMENT,
     `name` varchar(45) NOT NULL,
     `email` varchar(45) NOT NULL,
     `password` varchar(200) NOT NULL,
     `phone` varchar(45) NOT NULL,
     `createdAt` datetime default CURRENT_TIMESTAMP,
     `CompanyRegistrationNumber` varchar(45),
     PRIMARY KEY (`id`)
);


CREATE TABLE `store` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `phone` varchar(45) NOT NULL,
    `ownerId` int NOT NULL,
    `openStatus` varchar(45) NOT NULL,
    `category` varchar(45) NOT NULL,
    `buildingManagementNum` varchar(45) NOT NULL,
    `address` varchar(45) NOT NULL,
    `createdAt` datetime default CURRENT_TIMESTAMP ,
    `imageUrl` varchar(200) DEFAULT 'https://i.ibb.co/L970RtB/2022-01-26-095629.jpg',
    `minimumOrder` int DEFAULT '0',
    `deliveryTip` int DEFAULT '0',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`ownerId`) REFERENCES owner(`id`),
    FOREIGN KEY (`buildingManagementNum`) REFERENCES address(`buildingManagementNum`)
);

CREATE TABLE `menu_group` (
    `id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(45) NOT NULL,
    `description` varchar(100) DEFAULT NULL,
    `storeId` int DEFAULT NULL,
     PRIMARY KEY (`id`)
);

CREATE TABLE `menu` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `groupMenuId` int NOT NULL,
    `price` int NOT NULL,
    `description` varchar(100) DEFAULT NULL,
    `photo` varchar(200) DEFAULT NULL,
    `status` varchar(45) DEFAULT NULL,
    `storeId` int DEFAULT NULL,
     PRIMARY KEY (`id`),
     FOREIGN KEY (`groupMenuId`) REFERENCES menu_group(`id`)
);

CREATE TABLE `option` (
     `id` int NOT NULL AUTO_INCREMENT,
     `name` varchar(45) NOT NULL,
     `menuId` int NOT NULL,
     `price` int DEFAULT NULL,
      PRIMARY KEY (`id`),
      FOREIGN KEY (`menuId`) REFERENCES menu(`id`)
);

