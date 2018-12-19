DROP TABLE IF EXISTS `tbl_match`;
CREATE TABLE `tbl_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(10) NOT NULL,
  `txhash` varchar(64) NOT NULL,
  `gameid` varchar(20) NOT NULL,
  `hteamid` varchar(20) NOT NULL,
  `vteamid` varchar(20) NOT NULL,
  `state` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;