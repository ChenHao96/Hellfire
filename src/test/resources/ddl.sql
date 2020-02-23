CREATE TABLE `a_t_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(36) NOT NULL COMMENT '用户名',
  `nick_name` varchar(255) NOT NULL COMMENT '昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `phone_number` varchar(11) NOT NULL COMMENT '手机号',
  `password` varchar(36) NOT NULL COMMENT '登录密码',
  `option_password` varchar(36) NOT NULL COMMENT '操作密码',
  `status` tinyint(4) NOT NULL COMMENT '账号状态',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `expired_time` datetime COMMENT '截止登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING HASH,
  KEY `phone_number` (`phone_number`) USING HASH,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统账号信息';

CREATE TABLE `a_t_menus` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '上级菜单id',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `menu_name` varchar(255) NOT NULL COMMENT '菜单名称',
  `menu_index` varchar(255) NOT NULL COMMENT '菜单索引',
  `menu_no` int NOT NULL DEFAULT 0 COMMENT '菜单顺序',
  `status` bit(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统菜单信息';

CREATE TABLE `a_t_menu_auth` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单id',
  `user_id` int(10) unsigned NOT NULL COMMENT '系统账号id',
  `status` bit(1) NOT NULL COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '授权时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '授权人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_user_id` (`menu_id`,`user_id`),
  KEY `menu_id` (`menu_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统菜单授权信息';

CREATE TABLE `a_t_controls` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单id',
  `button_index` varchar(255) NOT NULL COMMENT '按钮索引',
  `authority_name` varchar(255) NOT NULL COMMENT '功能名称',
  `authority` varchar(255) NOT NULL COMMENT '功能链接',
  `need_password` bit(1) NOT NULL COMMENT '是否需要密码',
  `status` bit(1) NOT NULL COMMENT '状态',
  `warring_prompt` bit(1) NOT NULL COMMENT '警告提示',
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统功能信息';

CREATE TABLE `a_t_control_auth` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单id',
  `control_id` int(10) unsigned NOT NULL COMMENT '功能id',
  `user_id` int(10) unsigned NOT NULL COMMENT '系统账号id',
  `status` bit(1) NOT NULL COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '授权时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '授权人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `control_user_id` (`control_id`,`user_id`),
  KEY `menu_id` (`menu_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `control_id` (`control_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储系统功能授权信息';

--TODO: 字段控制表

--TODO：组织结构

CREATE TABLE `m_t_employees` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(255) NOT NULL COMMENT '员工编号',
  `name` varchar(30) NOT NULL COMMENT '员工名称',
  `photo` varchar(500) DEFAULT NULL COMMENT '员工相片',
  `sex` bit(1) NOT NULL COMMENT '员工性别',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '员工年龄',
  `birthday` date DEFAULT NULL COMMENT '员工生日',
  `status` tinyint(4) NOT NULL COMMENT '员工状态',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '登记时间',
  `create_at` int(10) unsigned NOT NULL COMMENT '登记人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`) USING HASH,
  KEY `sex` (`sex`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于存储员工基本信息';

