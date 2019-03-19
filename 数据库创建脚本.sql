drop database if exists yootk ;
create database yootk character set UTF8 ;
use yootk ;
drop table if exists user ;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(32) NOT NULL,
  `power` int(11) default 1,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into `user`(`username`,password,`power`) values ('yootk','hello',10);

CREATE TABLE `book` (
  `id` int(11) NOT NULL auto_increment,
  bookName varchar(50) default NULL,
  authorName varchar(50) default NULL,
  bookNumber int ,
  booksType varchar(20),
  bookConcern varchar(55),
  languageType char(30) default NULL, 
  money double,
  remark text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;