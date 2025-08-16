create table test_data
(
    id          int unsigned auto_increment comment '业务ID'
        primary key,
    name        varchar(32) default '' not null comment '业务名称',
    update_time int         default 0  not null comment 'timestamp of update time',
    create_time int         default 0  not null comment 'timestamp of create time',
    is_delete   tinyint     default 0  not null comment '是否软删除（0：正常；1：已删除）'
) comment '测试表';

