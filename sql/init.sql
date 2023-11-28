drop table if exists user;
create table user(
    id bigint unsigned not null auto_increment primary key comment '用户Id',
    nickname varchar(35) default null comment '用户昵称',
    open_id varchar(128) default null comment '用户openId',
    gender tinyint unsigned default null comment '用户性别: 1-男性、2-女性',
    phone varchar(20) default null comment '用户手机账号',
    email varchar(20) default null comment '用户邮箱',
    related_id bigint default null comment '关联id',
    related_status tinyint unsigned default 0 comment '关联状态:0-普通用户、1-正常关联、2-已解除关联、3-申请关联中、4-申请解除关联中）',
    status tinyint unsigned default 0 comment '用户状态： 0-正常使用',
    related_time datetime default null comment '关联时间',
    create_time datetime default now() comment '创建时间',
    update_time datetime default now()  comment '更新时间',
    last_login_time datetime default now() comment '最后一次登陆时间'
) comment '用户信息表';

drop table if exists message;
create table message(
    id bigint unsigned not null auto_increment primary key comment '信息id',
    send_user_id bigint not null comment '发送方Id',
    receive_user_id bigint not null comment '接收方Id',
    content text default null comment '文字内容',
    img varchar(512) default null comment '图片地址，如果多个图片用逗号隔开',
    status tinyint unsigned default 0 comment '信息状态：0-已发送、1-已读取、2-锁定中',
    create_time datetime default now() comment '创建时间',
    update_time datetime default null on update now() comment '更新时间',
    is_delete tinyint default 0 comment '是否删除，0-未删除、 1-已删除'
)comment '信息表';

drop table if exists notice;
create table notice(
    id bigint unsigned not null auto_increment primary key comment '通知id',
    notice_type tinyint unsigned default 0 comment '通知类型：0-系统消息、1-用户通知',
    status tinyint unsigned default 0 comment '通知状态: 0-正常',
    title varchar(40) default '通知' comment '消息标题',
    content varchar(128) not null comment '通知内容',
    user_id bigint unsigned not null comment '接受用户id',
    send_id bigint unsigned default null comment '发送方id，系统发送为null',
    create_time datetime default now() comment '创建时间',
    update_time datetime default now() on update now() comment '更新时间',
    is_delete tinyint default 0 comment '是否删除， 0-未删除、1-已删除'
)comment '通知消息表';