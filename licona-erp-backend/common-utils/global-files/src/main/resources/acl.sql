CREATE DATABASE IF NOT EXISTS licona_erp;

USE licona_erp;

#
# Structure for table "user"
#
CREATE TABLE IF NOT EXISTS `user`
(
    # 将UUID的32位的16进制数,每4位转成62进制后作为id
    `id`              char(8)     NOT NULL COMMENT '用户id',
    # 用户名最短8位,最长16位
    `username`        varchar(16) NOT NULL COMMENT '用户名',
    # 用户密码最短8位,最长16位,但SpringSecurity加密后为60位
    `password`        varchar(60) NOT NULL COMMENT '用户密码',
    # 用户昵称最长为12位
    `nick_name`       varchar(12)          DEFAULT NULL COMMENT '用户昵称',
    # @TODO 由于图片服务器暂未定,故暂定255
    `avatar`          varchar(255)         DEFAULT NULL COMMENT '用户头像',
    # 电子邮箱最长为254字符
    `email`           varchar(255)         DEFAULT NULL COMMENT '用户电子邮箱',
    # 最长电话号码为11位，但因为有时需要加上区号，因此设置为20位
    `phone_number`    varchar(20)          DEFAULT NULL COMMENT '用户电话号码',
    `status`          varchar(1)           DEFAULT 0 COMMENT '用户状态 0:正常 1:禁用',
    `last_login_time` datetime             DEFAULT NULL COMMENT '最近登录时间',
    # 更新时候用
    `version`         bigint(20)  NOT NULL DEFAULT 0 COMMENT '乐观锁',
    `is_deleted`      tinyint(1)  NOT NULL DEFAULT 0 COMMENT '逻辑删除 1:已删除 0:未删除',
    `created`         datetime             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated`         datetime             DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_uk_username` (`username`)
) ENGINE = InnoDB
    # most bytes four
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
INSERT INTO licona_erp.user (id, username, password, nick_name, avatar, email, phone_number, status, last_login_time,
                             version, is_deleted, created, updated)
VALUES ('fa45bdAg', 'superadmin', 'superadmin-password', '超管',
        'http://www.pw.qiniu.licona.club/licona-pw-default-boy-avatar.jpeg', 'superadmin@163.com', '13345677897', '0',
        null, 0, 0, '2021-05-08 19:02:57', '2021-05-08 19:17:36'),
       ('fa45bdbE', 'liconamoyi', 'liconamoyi-password', '普通用户',
        'http://www.pw.qiniu.licona.club/licona-pw-default-boy-avatar.jpeg', 'liconamoyi@163.com', '17693436385', '0',
        null, 1, 0, '2021-05-08 19:02:57', '2021-05-08 19:18:18'),
       ('fa45bdeR', 'useradmin', 'useradmin-password', '用户管理',
        'http://www.pw.qiniu.licona.club/licona-pw-default-boy-avatar.jpeg', 'useradmin@163.com', '13456787654', '0',
        null, 0, 0, '2021-05-09 17:55:01', null);

#
# Structure for table "role"
#
CREATE TABLE IF NOT EXISTS `role`
(
    # id:20210506174544629
    `id`         char(32)            NOT NULL COMMENT '角色id',
    `role_name`  varchar(32)         NOT NULL COMMENT '角色名称',
    `remark`     varchar(255)                 DEFAULT NULL COMMENT '备注',
    `version`    bigint(20)          NOT NULL DEFAULT 0 COMMENT '乐观锁',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 1:已删除 0:未删除',
    `created`    datetime                     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated`    datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '角色表';


INSERT INTO licona_erp.role (id, role_name, remark, version, is_deleted, created, updated)
VALUES ('5ad08aae36ad461a8f3862db37c63f1c', 'useradmin', '管理用户', 0, 0, '2021-05-09 17:44:47', null),
       ('a3546e0919c5490faf144b4c873d410a', 'admin', '管理所有数据', 0, 0, '2021-05-08 19:02:58', '2021-05-09 10:03:23'),
       ('cef1c5768c7340a19faa9239f2b0b149', 'normal', '普通用户', 0, 0, '2021-05-09 17:59:42', null);


#
# Structure for table "permission"
#
CREATE TABLE IF NOT EXISTS `permission`
(
    `id`               char(32)            NOT NULL COMMENT '权限id',
    `pid`              char(32)            NOT NULL COMMENT '所属上级',
    `name`             varchar(20)         NOT NULL COMMENT '权限名称',
    `type`             tinyint             NOT NULL COMMENT '类型 0:非菜单非按钮 1:菜单 2:按钮',
    `permission_value` varchar(50)                  DEFAULT NULL COMMENT '权限值',
    `interface_uri`    varchar(100)                 DEFAULT NULL,
    `path`             varchar(100)                 DEFAULT NULL COMMENT '访问路径',
    `component`        varchar(100)                 DEFAULT NULL COMMENT '组件路径',
    `icon`             varchar(50)                  DEFAULT NULL COMMENT '图标',
    `version`          bigint(20)                   DEFAULT 0 COMMENT '乐观锁',
    `is_deleted`       tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 1:已删除 0:未删除',
    `created`          datetime                     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated`          datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `pwd_permission_idx_pid` (`pid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';
INSERT INTO licona_erp.permission (id, pid, name, type, permission_value, interface_uri, path, component, icon, version,
                                   is_deleted, created, updated)
VALUES ('1', '0', '全部数据', 0, null, null, null, null, null, 1, 0, '2021-05-09 16:11:25', null),
       ('543aca77fe77493a865516a6b1e35fef', 'adc777f9666e4852b4a2681c84638f6e', '菜单管理', 1, null, null, '/menu',
        'views/acl/menu', null, 1, 0, '2021-05-08 19:02:58', '2021-05-11 09:51:38'),
       ('82160b8cbe3c4ce2bb32137ecc475536', 'adc777f9666e4852b4a2681c84638f6e', '用户管理', 1, null, null, '/user',
        'views/acl/user', null, 1, 0, '2021-05-08 19:02:58', '2021-05-11 09:51:38'),
       ('adc777f9666e4852b4a2681c84638f6e', '1', '权限管理', 1, null, '/pwb/acl/routes', '/acl', 'Layout', 'lock', 1, 0,
        '2021-05-08 19:02:58', '2021-05-10 18:57:38'),
       ('fe627631f3214d0286a2d58c4cd9334e', 'adc777f9666e4852b4a2681c84638f6e', '角色管理', 1, null, null, '/role',
        'views/acl/role', null, 1, 0, '2021-05-08 19:02:58', '2021-05-11 09:51:38');

#
# Structure for table "user_role"
#
CREATE TABLE IF NOT EXISTS `user_role`
(
    `id`         char(32)            NOT NULL COMMENT '主键id',
    `role_id`    char(32)            NOT NULL COMMENT '角色id',
    `user_id`    char(8)             NOT NULL COMMENT '用户id',
    `version`    bigint(20)                   DEFAULT 0 COMMENT '乐观锁',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 1:已删除 0:未删除',
    `created`    datetime                     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated`    datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_role_id` (`role_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
INSERT INTO licona_erp.user_role (id, role_id, user_id, version, is_deleted, created, updated)
VALUES ('254d03c34b364766bec0d713b2a4f8f2', 'cef1c5768c7340a19faa9239f2b0b149', 'fa45bdbE', 0, 0, '2021-05-09 18:01:41',
        null),
       ('406fcb9e95c6423b814259bbe42040ef', 'cef1c5768c7340a19faa9239f2b0b149', 'fa45bdeR', 0, 0, '2021-05-10 03:07:41',
        null),
       ('d309c7c311ac481ea81af19a6e0a683e', 'a3546e0919c5490faf144b4c873d410a', 'fa45bdAg', 0, 0, '2021-05-08 19:02:58',
        '2021-05-09 09:57:47'),
       ('f18a6521d4ae461aa9f0632f04c3deca', '5ad08aae36ad461a8f3862db37c63f1c', 'fa45bdeR', 0, 0, '2021-05-09 18:02:57',
        null);

#
# Structure for table "role_permission"
#
CREATE TABLE IF NOT EXISTS `role_permission`
(
    `id`            char(32)         NOT NULL COMMENT '主键id',
    `role_id`       char(32)         NOT NULL COMMENT '角色id',
    `permission_id` char(32)         NOT NULL COMMENT '权限id',
    `version`       bigint(20)                DEFAULT 0 COMMENT '乐观锁',
    `is_deleted`    tinyint unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 1:已删除 0:未删除',
    `created`       datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated`       datetime                  DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_role_id` (`role_id`),
    KEY `idx_permission_id` (`permission_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色权限表';
INSERT INTO licona_erp.role_permission (id, role_id, permission_id, version, is_deleted, created, updated)
VALUES ('02f79f80be9144508a1a4ca912d696ef', '5ad08aae36ad461a8f3862db37c63f1c', '82160b8cbe3c4ce2bb32137ecc475536', 0,
        0, '2021-05-09 10:06:17', null),
       ('4529f03e606e46ddbc96cb293266b345', '5ad08aae36ad461a8f3862db37c63f1c', 'adc777f9666e4852b4a2681c84638f6e', 0,
        0, '2021-05-09 11:08:33', '2021-05-09 14:36:43'),
       ('b8ccdfc46b31459bb58b4551d072c108', 'a3546e0919c5490faf144b4c873d410a', 'adc777f9666e4852b4a2681c84638f6e', 0,
        0, '2021-05-08 19:02:59', '2021-05-09 14:36:44');


