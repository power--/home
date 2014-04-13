Drop table  if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  create_time datetime,
  archived tinyint(1) default '0',
  trusted tinyint(1) default '0'
);

Drop table  if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

Drop table  if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

Drop table  if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

Drop table  if exists oauth_code;
create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

Drop table  if exists gp_user;
CREATE TABLE `gp_user` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `createTime` datetime ,
  `email` varchar(255),
  `password` varchar(255) not null,
  `phone` varchar(255),
  `username` varchar(255) not null unique,
  `defaultUser` tinyint(1) default '0',
  `lastLoginTime` datetime ,
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

