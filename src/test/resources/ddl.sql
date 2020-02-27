CREATE TABLE `a_t_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(36) NOT NULL COMMENT '用户名',
  `nick_name` varchar(255) NOT NULL COMMENT '昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `phone_number` varchar(11) NOT NULL COMMENT '手机号',
  `password` varchar(36) NOT NULL COMMENT '登录密码',
  `option_password` varchar(36) NOT NULL COMMENT '操作密码',
  `status` tinyint(4) NOT NULL COMMENT '账号状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '创建人',
  `expired_time` datetime COMMENT '截止登录时间',
  `logic` bit(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING HASH,
  KEY `phone_number` (`phone_number`) USING HASH,
  KEY `status` (`status`) USING BTREE,
  KEY `logic` (`logic`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统账号信息';
insert into a_t_users(`id`,`username`,`nick_name`,`phone_number`,`password`,`option_password`,`status`,`create_at`,`logic`)
values(0,'','系统初始化','','','',0,0,1);
insert into a_t_users(`id`,`username`,`nick_name`,`phone_number`,`password`,`option_password`,`status`,`create_at`)
values(1,'steven','傻蛋','13112345678','293f6338b046d65e437b7ca39ba90136','293f6338b046d65e437b7ca39ba90136',1,0);

CREATE TABLE `a_t_roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(36) NOT NULL COMMENT '角色名称',
  `role_tag` varchar(50) NOT NULL COMMENT '角色标签',
  `status` bit(1) NOT NULL DEFAULT 1 COMMENT '角色状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_tag` (`role_tag`) USING HASH,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统角色信息';
insert into a_t_roles(`id`,`role_name`,`role_tag`,`create_at`) values(1,'系统管理员','system_admin',0);

CREATE TABLE `a_t_menus` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '上级菜单id',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `menu_name` varchar(255) NOT NULL COMMENT '菜单名称',
  `menu_index` varchar(255) NOT NULL COMMENT '菜单索引',
  `menu_no` int NOT NULL DEFAULT 0 COMMENT '菜单顺序',
  `status` bit(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统菜单信息';
insert into a_t_menus(`id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(1,'控制台','console',1,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(2,1,'账号管理','account_manager',1,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(3,2,'添加账号','account_add',1,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(4,2,'账号查询','account_query',2,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(5,2,'账号授权','account_auth',3,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(6,1,'角色管理','role_manager',2,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(7,6,'添加角色','role_add',1,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(8,6,'角色查询','role_query',2,0);
-- insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(9,6,'角色修改','role_update',3,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(10,1,'菜单管理','menu_manager',3,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(11,10,'添加菜单','menu_add',1,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(12,10,'菜单查询','menu_query',2,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(13,10,'角色关联菜单','menu_auth',3,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(14,1,'权限管理','control_manager',4,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(15,14,'添加权限','control_add',1,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(16,14,'权限查询','control_query',2,0);
insert into a_t_menus(`id`,`parent_id`,`menu_name`,`menu_index`,`menu_no`,`create_at`) values(17,14,'角色关联权限','control_auth',3,0);


CREATE TABLE `a_t_controls` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单id',
  `button_index` varchar(255) NOT NULL COMMENT '按钮索引',
  `option_name` varchar(255) NOT NULL COMMENT '功能名称',
  `option_tag` varchar(255) NOT NULL COMMENT '功能权限标签',
  `need_password` bit(1) NOT NULL COMMENT '是否需要密码',
  `status` bit(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `warring_prompt` bit(1) NOT NULL DEFAULT 0 COMMENT '警告提示',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统功能信息';


CREATE TABLE `a_t_role_auth` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '系统账号id',
  `role_id` int(10) unsigned NOT NULL COMMENT '系统角色id',
  `status` bit(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '授权时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '授权人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_id` (`user_id`,`role_id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统菜单授权信息';
insert into a_t_role_auth(`user_id`,`role_id`,`create_at`) values(1,1,0);

CREATE TABLE `a_t_menu_auth` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单id',
  `role_id` int(10) unsigned NOT NULL COMMENT '系统角色id',
  `status` bit(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '授权时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '授权人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_role_id` (`menu_id`,`role_id`),
  KEY `menu_id` (`menu_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统菜单授权信息';
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,1,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,2,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,3,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,4,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,5,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,6,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,7,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,8,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,9,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,10,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,11,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,12,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,13,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,14,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,15,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,16,0);
insert into a_t_menu_auth(`role_id`,`menu_id`,`create_at`) values(1,17,0);

CREATE TABLE `a_t_control_auth` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单id',
  `control_id` int(10) unsigned NOT NULL COMMENT '功能id',
  `role_id` int(10) unsigned NOT NULL COMMENT '系统角色id',
  `status` bit(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '授权时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '授权人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `control_role_id` (`control_id`,`role_id`),
  KEY `menu_id` (`menu_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `control_id` (`control_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统功能授权信息';
