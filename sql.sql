CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `is_admin` bit(1) NOT NULL DEFAULT b'0',
  `e_mail` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(100) NOT NULL,
  `bookAuthors` varchar(100) NOT NULL,
  `annotation` varchar(5000) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `bookNumber` int(11) NOT NULL DEFAULT '1',
  `availableBookNumber` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `blockedusers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blockedUserId` int(11) NOT NULL,
  PRIMARY KEY (`id`,`blockedUserId`),
  KEY `blokedUserId_idx` (`blockedUserId`),
  CONSTRAINT `blokedUserId` FOREIGN KEY (`blockedUserId`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `bookorderstory` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `date` date NOT NULL,
  `isReplace` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`orderId`),
  KEY `bookId_idx` (`bookId`),
  KEY `userId_idx` (`userId`),
  CONSTRAINT `bookId` FOREIGN KEY (`bookId`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

CREATE TABLE `needsendoutbooks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userIdNS` int(11) NOT NULL,
  `bookIdNS` int(11) NOT NULL,
  `isinreadingroom` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `bookIdNS_idx` (`bookIdNS`),
  KEY `userIdNS_idx` (`userIdNS`),
  CONSTRAINT `bookIdNS` FOREIGN KEY (`bookIdNS`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userIdNS` FOREIGN KEY (`userIdNS`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

CREATE TABLE `readingroombooks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userIdRR` int(11) NOT NULL,
  `bookIdRR` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bookIdRR_idx` (`bookIdRR`),
  KEY `userIdRR_idx` (`userIdRR`),
  CONSTRAINT `bookIdRR` FOREIGN KEY (`bookIdRR`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userIdRR` FOREIGN KEY (`userIdRR`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

CREATE TABLE `takenbooks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userIdTB` int(11) NOT NULL,
  `bookIdTB` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bookId_idx` (`bookIdTB`),
  KEY `userIdTB_idx` (`userIdTB`),
  CONSTRAINT `bookIdTB` FOREIGN KEY (`bookIdTB`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userIdTB` FOREIGN KEY (`userIdTB`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

CREATE TABLE `userlikedbooks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userIdLB` int(11) NOT NULL,
  `bookIdLB` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bookIdLB_idx` (`bookIdLB`),
  KEY `userIdLB_idx` (`userIdLB`),
  CONSTRAINT `bookIdLB` FOREIGN KEY (`bookIdLB`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userIdLB` FOREIGN KEY (`userIdLB`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8;








