CREATE TABLE HISTRNSDTL
(
  `HISDTLID` int(11) NOT NULL AUTO_INCREMENT,
  `HISHDRID` int(11) NOT NULL,
  `HISDTLITMID` int(11) NOT NULL,
  `HISDTLQTY` int(4) DEFAULT NULL,
  `HISDTLPRICE` int(4) DEFAULT NULL,
  `HISDTLRESPON` int(11) NOT NULL,
  `HISTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
PRIMARY KEY (HISDTLID),
FOREIGN KEY (HISDTLITMID) REFERENCES ITMGNL(ITMID),
FOREIGN KEY (HISHDRID) REFERENCES HISTRNSHDR(HISHDRID)
)