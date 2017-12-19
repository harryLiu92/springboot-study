CREATE TABLE `t_user_0` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `userAge` int(11) DEFAULT NULL,
  `userAddress` varchar(200) DEFAULT NULL,
  `company` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `userAge` int(11) DEFAULT NULL,
  `userAddress` varchar(200) DEFAULT NULL,
  `company` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `userAge` int(11) DEFAULT NULL,
  `userAddress` varchar(200) DEFAULT NULL,
  `company` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `userAge` int(11) DEFAULT NULL,
  `userAddress` varchar(200) DEFAULT NULL,
  `company` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

INSERT INTO `liuhao`.`t_user_0` (`id`, `userName`, `userAge`, `userAddress`, `company`, `userId`) VALUES ('1', 'summer', '30', 'shanghai', '1', '11');
INSERT INTO `liuhao`.`t_user_0` (`id`, `userName`, `userAge`, `userAddress`, `company`, `userId`) VALUES ('2', 'test2', '22', 'suzhou', '2', '15');
INSERT INTO `liuhao`.`t_user_0` (`id`, `userName`, `userAge`, `userAddress`, `company`, `userId`) VALUES ('3', 'test1', '29', 'some place', '1', '19');
INSERT INTO `liuhao`.`t_user_0` (`id`, `userName`, `userAge`, `userAddress`, `company`, `userId`) VALUES ('4', 'lu', '28', 'some place', '2', '23');
INSERT INTO `liuhao`.`t_user_0` (`id`, `userName`, `userAge`, `userAddress`, `company`, `userId`) VALUES ('5', 'xiaoxun', '27', 'nanjing', '1', '27');
INSERT INTO `liuhao`.`t_user_0` (`id`, `userName`, `userAge`, `userAddress`, `company`, `userId`) VALUES ('6', 'tlh', '29', 'some place', '2', '31');

INSERT INTO `liuhao`.`t_user_3` (`id`, `userName`, `userAge`, `userAddress`, `company`, `userId`) VALUES ('10', 'llll', '23', 'hh', '4', '99');
