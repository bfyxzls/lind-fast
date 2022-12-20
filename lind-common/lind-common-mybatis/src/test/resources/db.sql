-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                         `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                         `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
                         `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
                         `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
                         `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_attribute
-- ----------------------------
DROP TABLE IF EXISTS `user_attribute`;
CREATE TABLE `user_attribute`  (
                                   `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                                   `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                   `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
                                   `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
                                   `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
                                   `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
                                   `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户ID',
                                   `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '属性名',
                                   `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '属性值',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;
