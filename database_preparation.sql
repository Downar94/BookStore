USE `bookstore`;

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street_name` varchar(40),
  `city` varchar(30),
  `country` varchar(30),
  `post_code` varchar(25),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `contact` (`street_name`,`city`, `country`, `post_code`)
VALUES 
(null, null, null, null),
(null, null, null, null),
(null, null, null, null);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `account_status` varchar(20),
  `contact_id` int(11),
  PRIMARY KEY (`id`),
  
  CONSTRAINT `FK_CONTACT_05` FOREIGN KEY (`contact_id`) 
  REFERENCES `contact` (`id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `user` (`username`,`password`, `first_name`, `last_name`, `email`, `account_status`, `contact_id`)
VALUES 
('harry','$2a$12$Y5hGiN1Om5ynaTTpxxzpQOCM847qJ4ZvE1oDoAKcl1TfVCAU/Q3o.','Harry', 'Potter', 'harrypotter@mail.com', null, 1),
('mario','$2a$12$pl/piK/7Z6OCstwQVwJkvetsDOaV98YcBAunUzbosXxGel1kNltCu','Super', 'Mario', 'supermario@mail.com', null, 2),
('dolores','$2a$12$IyiSJY/wCKRvZOBtNKtdB.BywPBjIJNFt25/KND3dTP7F7yk77W9W','Dolores', 'Madrigal', 'doloresmadrigal@mail.com', null, 3);

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_ADMIN');


DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(3, 1),
(3, 2)