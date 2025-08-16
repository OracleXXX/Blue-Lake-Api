-- ----------------------------
-- Role Based Admin Control
-- ----------------------------
DROP TABLE IF EXISTS `business`;
create table business
(
    id             int unsigned not null auto_increment primary key comment '主键Id',
    `name`         varchar(32)  NOT NULL DEFAULT '' COMMENT '业务名称',
    `display_name` varchar(64)  NOT NULL DEFAULT '' COMMENT '业务展示名称',
    `description`  varchar(128) NOT NULL DEFAULT '' COMMENT '业务描述',
    create_time    int          not null default 0 comment '创建时间',
    update_time    int          not null default 0 comment '更新时间',
    is_delete      tinyint      not null default 0 comment '是否软删除（0：正常；1：已删除）'
) comment '业务表';

DROP TABLE IF EXISTS `employee`;
create table employee
(
    id            int unsigned            not null auto_increment primary key comment '主键Id',
    email         varchar(256) default '' not null comment '登录邮箱',
    `employee_id` VARCHAR(64)             NOT NULL DEFAULT '' COMMENT '用户uid',
    nickname      varchar(256) default '' not null comment '昵称',
    `password`    varchar(256) default '' not null comment '密码',
    create_time   int          default 0  not null comment '创建时间',
    update_time   int          default 0  not null comment '更新时间',
    is_delete     tinyint      default 0  not null comment '是否软删除（0：正常；1：已删除）'
) comment '员工表';

DROP TABLE IF EXISTS `employee_role`;
create table employee_role
(
    id            int unsigned not null auto_increment primary key comment '主键Id',
    `employee_id` VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
    role_id       int          not null default 0 comment '角色id',
    create_time   int          not null default 0 comment '创建时间',
    update_time   int          not null default 0 comment '更新时间',
    is_delete     tinyint      not null default 0 comment '是否软删除（0：正常；1：已删除）'
) comment '员工和角色关系表';

DROP TABLE IF EXISTS `permission`;
create table permission
(
    id            int unsigned not null auto_increment primary key comment '主键Id',
    `action`      varchar(256) not null default '' comment '权限操作',
    business_id   int          not null default 0 comment '业务id',
    `description` varchar(256) not null default '' comment '权限描述',
    display_name  varchar(256) not null default '' comment '权限名称',
    sort_num      int          not null default 100 comment '排序',
    create_time   int          not null default 0 comment '创建时间',
    update_time   int          not null default 0 comment '更新时间',
    is_delete     tinyint      not null default 0 comment '是否软删除（0：正常；1：已删除）'
) comment '权限表';

DROP TABLE IF EXISTS `role`;
create table role
(
    id            int unsigned not null auto_increment primary key comment '主键Id',
    `description` varchar(256) not null default '' comment '角色描述',
    display_name  varchar(256) not null default '' comment '角色展示名称',
    `level`       int          not null default 1000 comment '角色等级',
    `name`        varchar(256) not null default '' comment '角色后端代码',
    create_time   int          not null default 0 comment '创建时间',
    update_time   int          not null default 0 comment '更新时间',
    is_delete     tinyint      not null default 0 comment '是否软删除（0：正常；1：已删除）'
) comment '角色表';

DROP TABLE IF EXISTS `role_permission`;
create table role_permission
(
    id            int unsigned not null auto_increment primary key comment '主键Id',
    permission_id int          not null default 0 comment '关联权限id',
    role_id       int          not null default 0 comment '关联角色id',
    create_time   int          not null default 0 comment '创建时间',
    update_time   int          not null default 0 comment '更新时间',
    is_delete     tinyint      not null default 0 comment '是否软删除（0：正常；1：已删除）'
) comment '角色和权限关系表';
