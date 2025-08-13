create table test_data
(
    id     int unsigned auto_increment comment '业务ID'
        primary key,
    name   varchar(32) default '' not null comment '业务名称',
    is_del tinyint     default 0  not null comment '是否软删除（0：正常；1：已删除）'
)
    comment '测试表' collate = utf8mb4_unicode_ci
                     row_format = DYNAMIC;

