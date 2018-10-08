CREATE TABLE `user_customer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openId` varchar(100) NOT NULL COMMENT '微信用户公开id',
  `nickName` varchar(100) NOT NULL COMMENT '姓名',
  `avatarUrl` varchar(500) NOT NULL COMMENT '头像',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别：0-男,1-女',
  `status` tinyint(2) DEFAULT '1' COMMENT '1-开启，0-关闭',
  `province` varchar(100) DEFAULT NULL COMMENT '省会',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_customer_uk_openId` (`openId`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='消费者';

drop table if exists merchant;
CREATE TABLE `merchant` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '商户名',
  `logo` varchar(500) NOT NULL COMMENT '图像',
  `score` int(11) DEFAULT 10000 COMMENT '商户分数',
  `level` int(11) DEFAULT NULL COMMENT '商户等级',
  `desc` varchar(1000) DEFAULT NULL COMMENT '商户描述',
  `status` tinyint(2) DEFAULT '-1' COMMENT '默认：-1禁用，0停业，1开业',
  `province` varchar(100) DEFAULT NULL COMMENT '省会',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `address` varchar(1000) DEFAULT NULL COMMENT '地址',
  `longitude` varchar(100) NOT NULL COMMENT '纬度',
  `latitude` varchar(100) NOT NULL COMMENT '经度',
  `beginOpenTime` datetime DEFAULT NULL COMMENT '每日开始营业时间',
  `endOpenTime` datetime DEFAULT NULL COMMENT '每日结束营业时间',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `merchant_status` (`status`),
  KEY `merchant_longitude` (`longitude`),
  KEY `merchant_latitude` (`latitude`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='商户表';

drop table if exists goods_catalogue_config;
CREATE TABLE `goods_catalogue_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='商品分类配置模板';

drop table if exists goods_catalogue;
CREATE TABLE `goods_catalogue` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchantid` int(11) DEFAULT NULL COMMENT '商户id',
  `name` varchar(50) NOT NULL COMMENT '商品分类名称',
  `status` tinyint(2) DEFAULT '0' COMMENT '1-上架，0-下架',
  `order` int(11) DEFAULT NULL COMMENT '排序',
  `groupOrder` int(11) NOT NULL COMMENT '分组排序',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='商品分类';

drop table if exists goods;
CREATE TABLE `goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `desc` varchar(100) NOT NULL COMMENT '说明',
  `sellingPrice` decimal(11,2) NOT NULL COMMENT '售价',
  `costPrice` decimal(11,2) NOT NULL COMMENT '成本价',
  `discount` decimal(11,2) NOT NULL COMMENT '折扣',
  `discountPrice` decimal(11,2) NOT NULL COMMENT '折扣价格',
  `imageUrl` varchar(500) NOT NULL COMMENT '商品图像',
  `status` tinyint(2) DEFAULT '0' COMMENT '0-停售,1-在售,9-已售完',
  `sales` int(11) DEFAULT '0' COMMENT '销售额',
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `delivery` int(11) DEFAULT '0' COMMENT '配送方式:0-到店自提,1-急速送货到家,2-送货到家',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `goods_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='普通商品';

drop table if exists groupon;
CREATE TABLE `groupon` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `desc` varchar(100) NOT NULL COMMENT '说明',
  `sellingPrice` decimal(11,2) NOT NULL COMMENT '售价',
  `costPrice` decimal(11,2) NOT NULL COMMENT '成本价',
  `discount` decimal(11,2) NOT NULL COMMENT '折扣',
  `grouponPrice` decimal(11,2) NOT NULL COMMENT '团购价格',
  `imageUrl` varchar(500) NOT NULL COMMENT '商品图像',
  `status` tinyint(2) DEFAULT '0' COMMENT '0-停售,1-在售,9-已售完',
  `sales` int(11) DEFAULT '0' COMMENT '销售额',
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `delivery` int(11) DEFAULT '0' COMMENT '配送方式:0-到店自提,1-急速送货到家,2-送货到家',
  `minNumber` int(11) DEFAULT '0' COMMENT '团购最低人数',
  `isSeckill` tinyint(2) DEFAULT '0' COMMENT '是否秒杀,0-否,1-是',
  `beginSeckill` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `endSeckill` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `groupon_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='团购商品';