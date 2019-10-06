create database articlemanagement character set utf8;
use articlemanagement;

CREATE TABLE users (
  user_id int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  username varchar(20) NOT NULL unique COMMENT '登录账号',
  userpass varchar(20) NOT NULL COMMENT '登录密码',
  nickname varchar(20) NOT NULL COMMENT '用户昵称',
  birthday varchar(30) DEFAULT NULL COMMENT '出生日期',
  gender varchar(3) DEFAULT NULL COMMENT '用户性别',
  phone varchar(13) DEFAULT NULL COMMENT '联系方式',
  email varchar(30) DEFAULT NULL COMMENT '邮箱',
  address varchar(30) DEFAULT NULL COMMENT '地址',
  createTime datetime NOT NULL COMMENT '创建时间',
  updateTime datetime DEFAULT NULL COMMENT '最后修改时间',
  lastLogin datetime DEFAULT NULL COMMENT '最后登录时间',
  user_status int(2) NOT NULL COMMENT '用户状态 0 正常 1 锁定 2管理员',
  remark text COMMENT '个人简介',
  PRIMARY KEY (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

insert into users(username,userpass,nickname,createTime,user_status,remark) values('Cooper','Cooper','Cooper',NOW(),2,'超级管理员');

CREATE TABLE article (
  article_id int(11) NOT NULL AUTO_INCREMENT COMMENT '文章编号',
  author_id int(11) NOT NULL COMMENT '作者编号',
  article_topic varchar(30) DEFAULT NULL COMMENT '文章题目',
  article_summary varchar(200) DEFAULT NULL COMMENT '摘要',
  article_content text COMMENT '文章内容',
  article_date datetime NOT NULL COMMENT '创建时间',
  article_status int(2) NOT NULL COMMENT '文章状态 0 正常 1 违规',
  PRIMARY KEY (article_id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE reply (
  reply_id int(11) NOT NULL AUTO_INCREMENT COMMENT '回复编号',
  reply_grade int(11) NOT NULL COMMENT '回复等级 0 留言 0以上指向被回复信息ID',
  article_id int(11) NOT NULL  COMMENT '文章编号',
  users_id int(11) NOT NULL  COMMENT '回复人编号',
  for_users_id int(11) NOT NULL  COMMENT '被回复人编号',
  reply_content text NOT NULL COMMENT '回复内容',
  reply_date datetime NOT NULL COMMENT '回复时间',
  reply_status int(2) NOT NULL COMMENT '回复状态 0 正常 1 违规',
  PRIMARY KEY (reply_id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

ALTER TABLE article
ADD FOREIGN KEY(author_id) REFERENCES users(user_id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reply
ADD FOREIGN KEY(article_id) REFERENCES article(article_id)
ON DELETE CASCADE ON UPDATE CASCADE;