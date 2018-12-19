DROP TABLE IF EXISTS `tbl_withdraw`;
CREATE TABLE `tbl_withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `txhash` varchar(64) NOT NULL,
  `address` varchar(32) NOT NULL,
  `amount` decimal(40,4) NOT NULL DEFAULT '0.000000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;