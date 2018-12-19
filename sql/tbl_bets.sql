DROP TABLE IF EXISTS `tbl_bets`;
CREATE TABLE `tbl_bets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `txhash` varchar(64) NOT NULL,
  `address` varchar(32) NOT NULL,
  `HorV` varchar(10) NOT NULL,
  `gameID` varchar(20) NOT NULL DEFAULT 0,
  `amount` decimal(40,4) NOT NULL DEFAULT '0.000000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;