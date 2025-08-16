-- 建议：CREATE DATABASE your_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- 登录/操作审计（可选）
CREATE TABLE `audit_log` (
                             `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                             `employee_id`   INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '操作人',
                             `action`        VARCHAR(64)  NOT NULL DEFAULT '' COMMENT 'login/logout/create/update/delete等',
                             `resource`      VARCHAR(128) NOT NULL DEFAULT '' COMMENT '资源标识或表名',
                             `resource_id`   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '资源主键',
                             `ip`            VARCHAR(64)  NOT NULL DEFAULT '' COMMENT 'IP',
                             `ua`            VARCHAR(256) NOT NULL DEFAULT '' COMMENT 'User-Agent',
                             `content`       JSON         NULL COMMENT '变更摘要或参数',
                             `create_time`   INT          NOT NULL DEFAULT 0,
                             `update_time`   INT          NOT NULL DEFAULT 0,
                             `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                             KEY `idx_audit_emp_time`(`employee_id`,`create_time`),
                             KEY `idx_audit_paging`(`is_delete`,`create_time`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审计日志';

-- 通用文件存储
CREATE TABLE `file_store` (
                              `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                              `file_name`     VARCHAR(256) NOT NULL DEFAULT '' COMMENT '原文件名',
                              `file_ext`      VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '扩展名',
                              `mime_type`     VARCHAR(128) NOT NULL DEFAULT '' COMMENT 'MIME',
                              `file_size`     BIGINT       NOT NULL DEFAULT 0 COMMENT '字节',
                              `storage_path`  VARCHAR(512) NOT NULL DEFAULT '' COMMENT '存储路径/URL',
                              `uploader_id`   INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '上传人',
                              `create_time`   INT          NOT NULL DEFAULT 0,
                              `update_time`   INT          NOT NULL DEFAULT 0,
                              `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                              KEY `idx_file_uploader_time`(`uploader_id`,`create_time`),
                              KEY `idx_file_paging`(`is_delete`,`create_time`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件库';

-- =====================================================================
-- 销售管理：支撑素材（企业简介/项目案例/项目简介/培训/模板/报价）
-- =====================================================================

-- 企业简介（单页/可多版本保留最新启用）
CREATE TABLE `company_intro` (
                                 `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                 `title`         VARCHAR(256) NOT NULL DEFAULT '' COMMENT '标题',
                                 `cover_file_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '背景图片 file_store.id',
                                 `content`       MEDIUMTEXT   NULL COMMENT '富文本/Markdown',
                                 `enabled`       TINYINT      NOT NULL DEFAULT 1 COMMENT '是否启用',
                                 `create_time`   INT          NOT NULL DEFAULT 0,
                                 `update_time`   INT          NOT NULL DEFAULT 0,
                                 `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                 KEY `idx_intro_enabled_time`(`enabled`,`create_time`),
                                 KEY `idx_intro_paging`(`is_delete`,`create_time`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业简介';

-- 企业简介附件（多文件）
CREATE TABLE `company_intro_file` (
                                      `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                      `intro_id`      INT UNSIGNED NOT NULL,
                                      `file_id`       INT UNSIGNED NOT NULL,
                                      `create_time`   INT          NOT NULL DEFAULT 0,
                                      `update_time`   INT          NOT NULL DEFAULT 0,
                                      `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                      UNIQUE KEY `uk_intro_file`(`intro_id`,`file_id`),
                                      KEY `idx_introfile_paging`(`is_delete`,`create_time`,`id`),
                                      CONSTRAINT `fk_introfile_intro` FOREIGN KEY (`intro_id`) REFERENCES `company_intro`(`id`),
                                      CONSTRAINT `fk_introfile_file`  FOREIGN KEY (`file_id`)  REFERENCES `file_store`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业简介-附件';

-- 项目案例
CREATE TABLE `project_case` (
                                `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                `name`          VARCHAR(256) NOT NULL DEFAULT '' COMMENT '项目名称',
                                `contract_file` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '项目合同 file_store.id',
                                `notice_file`   INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '公示文件 file_store.id',
                                `other_files`   JSON         NULL COMMENT '其他文件ID列表',
                                `creator_id`    INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
                                `create_time`   INT          NOT NULL DEFAULT 0,
                                `update_time`   INT          NOT NULL DEFAULT 0,
                                `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                KEY `idx_case_name`(`name`),
                                KEY `idx_case_creator_time`(`creator_id`,`create_time`),
                                KEY `idx_case_paging`(`is_delete`,`create_time`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目案例';

-- 机构字典：部委/处室
CREATE TABLE `ministry` (
                            `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                            `name`          VARCHAR(128) NOT NULL DEFAULT '' COMMENT '所属部委',
                            `code`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '编码',
                            `create_time`   INT          NOT NULL DEFAULT 0,
                            `update_time`   INT          NOT NULL DEFAULT 0,
                            `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                            UNIQUE KEY `uk_ministry_code`(`code`),
                            KEY `idx_ministry_paging`(`is_delete`,`create_time`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部委字典';

CREATE TABLE `office` (
                          `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                          `ministry_id`   INT UNSIGNED NOT NULL,
                          `name`          VARCHAR(128) NOT NULL DEFAULT '' COMMENT '所属处室',
                          `code`          VARCHAR(64)  NOT NULL DEFAULT '',
                          `create_time`   INT          NOT NULL DEFAULT 0,
                          `update_time`   INT          NOT NULL DEFAULT 0,
                          `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                          UNIQUE KEY `uk_office_code`(`code`),
                          KEY `idx_office_ministry`(`ministry_id`),
                          KEY `idx_office_paging`(`is_delete`,`create_time`,`id`),
                          CONSTRAINT `fk_office_ministry` FOREIGN KEY (`ministry_id`) REFERENCES `ministry`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='处室字典';

-- 项目简介（类型与政策解析）
CREATE TABLE `project_brief` (
                                 `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                 `name`          VARCHAR(256) NOT NULL DEFAULT '' COMMENT '项目名称',
                                 `ministry_id`   INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属部委',
                                 `office_id`     INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属处室',
                                 `ppt_file_id`   INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '项目解析PPT',
                                 `policy_link`   VARCHAR(512) NOT NULL DEFAULT '' COMMENT '政策解析链接',
                                 `creator_id`    INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
                                 `create_time`   INT          NOT NULL DEFAULT 0,
                                 `update_time`   INT          NOT NULL DEFAULT 0,
                                 `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                 KEY `idx_brief_name`(`name`),
                                 KEY `idx_brief_org`(`ministry_id`,`office_id`),
                                 KEY `idx_brief_paging`(`is_delete`,`create_time`,`id`),
                                 CONSTRAINT `fk_brief_ministry` FOREIGN KEY (`ministry_id`) REFERENCES `ministry`(`id`),
                                 CONSTRAINT `fk_brief_office`   FOREIGN KEY (`office_id`)   REFERENCES `office`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目简介/政策解析';

-- 培训/模板/报价 分类与文件
CREATE TABLE `material_category` (
                                     `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                     `type`          TINYINT      NOT NULL DEFAULT 1 COMMENT '1培训 2模板 3报价',
                                     `name`          VARCHAR(128) NOT NULL DEFAULT '' COMMENT '标签页名称',
                                     `order_no`      INT          NOT NULL DEFAULT 0,
                                     `create_time`   INT          NOT NULL DEFAULT 0,
                                     `update_time`   INT          NOT NULL DEFAULT 0,
                                     `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                     KEY `idx_mcat_type_order`(`type`,`order_no`),
                                     KEY `idx_mcat_paging`(`is_delete`,`create_time`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='素材分类(培训/模板/报价)';

CREATE TABLE `material_file` (
                                 `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                 `category_id`   INT UNSIGNED NOT NULL,
                                 `title`         VARCHAR(256) NOT NULL DEFAULT '' COMMENT '标题',
                                 `desc_text`     VARCHAR(512) NOT NULL DEFAULT '' COMMENT '描述',
                                 `file_id`       INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'file_store.id',
                                 `creator_id`    INT UNSIGNED NOT NULL DEFAULT 0,
                                 `create_time`   INT          NOT NULL DEFAULT 0,
                                 `update_time`   INT          NOT NULL DEFAULT 0,
                                 `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                 KEY `idx_mfile_cat_time`(`category_id`,`create_time`),
                                 KEY `idx_mfile_paging`(`is_delete`,`create_time`,`id`),
                                 CONSTRAINT `fk_mfile_cat`  FOREIGN KEY (`category_id`) REFERENCES `material_category`(`id`),
                                 CONSTRAINT `fk_mfile_file` FOREIGN KEY (`file_id`)      REFERENCES `file_store`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='素材文件';

-- =====================================================================
-- 销售管理：客户商机 / 跟进 / 资源
-- =====================================================================

-- 商机
CREATE TABLE `opportunity` (
                               `id`              INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                               `uuid`            VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                               `company_name`    VARCHAR(256) NOT NULL DEFAULT '' COMMENT '公司名称',
                               `referrer_name`   VARCHAR(128) NOT NULL DEFAULT '' COMMENT '引荐人姓名',
                               `referrer_title`  VARCHAR(128) NOT NULL DEFAULT '' COMMENT '引荐人职位',
                               `referrer_contact`VARCHAR(256) NOT NULL DEFAULT '' COMMENT '引荐人联系方式',
                               `intended_brief_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '意向项目(project_brief.id)',
                               `status`          TINYINT      NOT NULL DEFAULT 1 COMMENT '1待跟进 2跟进中 3已放弃 4已完成',
                               `owner_id`        INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '负责人(创建后可变更)',
                               `creator_id`      INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
                               `create_time`     INT          NOT NULL DEFAULT 0,
                               `update_time`     INT          NOT NULL DEFAULT 0,
                               `is_delete`       TINYINT      NOT NULL DEFAULT 0,
                               KEY `idx_opp_status`(`status`,`create_time`),
                               KEY `idx_opp_owner`(`owner_id`,`status`),
                               KEY `idx_opp_paging`(`is_delete`,`create_time`,`id`),
                               CONSTRAINT `fk_opp_brief` FOREIGN KEY (`intended_brief_id`) REFERENCES `project_brief`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户商机';

-- 商机协助人（多对多）
CREATE TABLE `opportunity_assistant` (
                                         `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                         `opportunity_id`INT UNSIGNED NOT NULL,
                                         `assistant_id`  INT UNSIGNED NOT NULL,
                                         `create_time`   INT          NOT NULL DEFAULT 0,
                                         `update_time`   INT          NOT NULL DEFAULT 0,
                                         `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                         UNIQUE KEY `uk_opp_assist`(`opportunity_id`,`assistant_id`),
                                         KEY `idx_oa_opp`(`opportunity_id`),
                                         KEY `idx_oa_assist`(`assistant_id`),
                                         KEY `idx_oa_paging`(`is_delete`,`create_time`,`id`),
                                         CONSTRAINT `fk_oa_opp` FOREIGN KEY (`opportunity_id`) REFERENCES `opportunity`(`id`),
                                         CONSTRAINT `fk_oa_emp` FOREIGN KEY (`assistant_id`)   REFERENCES `employee`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商机-协助人';

-- 跟进（负责人或协助人更新；需与商机状态同步由业务层保障）
CREATE TABLE `followup` (
                            `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                            `opportunity_id`INT UNSIGNED NOT NULL,
                            `actor_id`      INT UNSIGNED NOT NULL COMMENT '更新人',
                            `status`        TINYINT      NOT NULL DEFAULT 1 COMMENT '同opportunity.status',
                            `note`          TEXT         NULL COMMENT '说明',
                            `next_action_at`INT          NOT NULL DEFAULT 0 COMMENT '下次跟进时间',
                            `create_time`   INT          NOT NULL DEFAULT 0,
                            `update_time`   INT          NOT NULL DEFAULT 0,
                            `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                            KEY `idx_fu_opp_time`(`opportunity_id`,`create_time`),
                            KEY `idx_fu_actor`(`actor_id`),
                            KEY `idx_fu_paging`(`is_delete`,`create_time`,`id`),
                            CONSTRAINT `fk_fu_opp` FOREIGN KEY (`opportunity_id`) REFERENCES `opportunity`(`id`),
                            CONSTRAINT `fk_fu_emp` FOREIGN KEY (`actor_id`)       REFERENCES `employee`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户跟进';

-- 资源客户（双方资源/外部联系资源）
CREATE TABLE `customer_resource_pool` (
                                          `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                          `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                          `name`          VARCHAR(256) NOT NULL DEFAULT '' COMMENT '资源名称',
                                          `type`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '类型标签',
                                          `contact`       VARCHAR(256) NOT NULL DEFAULT '' COMMENT '联系方式/链接',
                                          `owner_id`      INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '录入人',
                                          `remark`        VARCHAR(512) NOT NULL DEFAULT '' COMMENT '备注',
                                          `create_time`   INT          NOT NULL DEFAULT 0,
                                          `update_time`   INT          NOT NULL DEFAULT 0,
                                          `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                          KEY `idx_crp_owner_time`(`owner_id`,`create_time`),
                                          KEY `idx_crp_paging`(`is_delete`,`create_time`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户资源/外部联系池';

-- =====================================================================
-- 客户管理：客户档案/文件/申报/分析/评估/资源/人员/项目清单
-- =====================================================================

CREATE TABLE `customer` (
                            `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                            `name`          VARCHAR(256) NOT NULL DEFAULT '' COMMENT '企业名称',
                            `region`        VARCHAR(128) NOT NULL DEFAULT '' COMMENT '地域',
                            `industry`      VARCHAR(128) NOT NULL DEFAULT '' COMMENT '领域/行业',
                            `nature`        VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '性质（民营/国企/…）',
                            `level`         VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '等级（潜在/意向/成交）',
                            `scale`         VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '规模',
                            `owner_id`      INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '负责人',
                            `creator_id`    INT UNSIGNED NOT NULL DEFAULT 0,
                            `remark`        VARCHAR(512) NOT NULL DEFAULT '' COMMENT '备注',
                            `create_time`   INT          NOT NULL DEFAULT 0,
                            `update_time`   INT          NOT NULL DEFAULT 0,
                            `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                            UNIQUE KEY `uk_customer_name`(`name`),
                            KEY `idx_customer_owner`(`owner_id`,`level`),
                            KEY `idx_customer_filters`(`region`,`industry`,`nature`,`level`,`scale`),
                            KEY `idx_customer_paging`(`is_delete`,`create_time`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户基本信息';

-- 客户企业文件（多文件）
CREATE TABLE `customer_file` (
                                 `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                 `customer_id`   INT UNSIGNED NOT NULL,
                                 `file_id`       INT UNSIGNED NOT NULL,
                                 `tag`           VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '分类标签，如营业执照/财务报表等',
                                 `create_time`   INT          NOT NULL DEFAULT 0,
                                 `update_time`   INT          NOT NULL DEFAULT 0,
                                 `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                 KEY `idx_cfile_customer`(`customer_id`,`tag`),
                                 KEY `idx_cfile_paging`(`is_delete`,`create_time`,`id`),
                                 CONSTRAINT `fk_cfile_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`),
                                 CONSTRAINT `fk_cfile_file`     FOREIGN KEY (`file_id`)     REFERENCES `file_store`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户-文件';

-- 客户数据模型（“数据模型”标签内容）
CREATE TABLE `customer_datamodel_item` (
                                           `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                           `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                           `customer_id`   INT UNSIGNED NOT NULL,
                                           `model_key`     VARCHAR(128) NOT NULL DEFAULT '' COMMENT '模型字段Key',
                                           `model_value`   VARCHAR(512) NOT NULL DEFAULT '' COMMENT '值（或短文本）',
                                           `extra_json`    JSON         NULL COMMENT '扩展/校验信息',
                                           `create_time`   INT          NOT NULL DEFAULT 0,
                                           `update_time`   INT          NOT NULL DEFAULT 0,
                                           `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                           UNIQUE KEY `uk_cdm_k`(`customer_id`,`model_key`),
                                           KEY `idx_cdm_paging`(`is_delete`,`create_time`,`id`),
                                           CONSTRAINT `fk_cdm_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户-数据模型条目';

-- 申报项目（客户维度的具体申请）
CREATE TABLE `customer_project` (
                                    `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                    `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                    `customer_id`   INT UNSIGNED NOT NULL,
                                    `brief_id`      INT UNSIGNED NOT NULL COMMENT '对应项目类型 project_brief.id',
                                    `status`        TINYINT      NOT NULL DEFAULT 0 COMMENT '0草稿 1进行中 2已提交 3通过 4未通过 5放弃',
                                    `owner_id`      INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '负责人',
                                    `score`         DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '评估得分（快照）',
                                    `ai_report_id`  INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'AI生成报告 file_store.id',
                                    `create_time`   INT          NOT NULL DEFAULT 0,
                                    `update_time`   INT          NOT NULL DEFAULT 0,
                                    `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                    KEY `idx_cproj_customer`(`customer_id`,`status`),
                                    KEY `idx_cproj_owner`(`owner_id`,`status`),
                                    KEY `idx_cproj_paging`(`is_delete`,`create_time`,`id`),
                                    CONSTRAINT `fk_cproj_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`),
                                    CONSTRAINT `fk_cproj_brief`    FOREIGN KEY (`brief_id`)   REFERENCES `project_brief`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户-申报项目';

-- 客户项目-表单文件与模板填报（可多文件、多类型）
CREATE TABLE `customer_project_file` (
                                         `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                         `customer_project_id` INT UNSIGNED NOT NULL,
                                         `file_id`       INT UNSIGNED NOT NULL,
                                         `file_tag`      VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '类型：模板/表格/证明/其他',
                                         `create_time`   INT          NOT NULL DEFAULT 0,
                                         `update_time`   INT          NOT NULL DEFAULT 0,
                                         `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                         KEY `idx_cpf_proj`(`customer_project_id`,`file_tag`),
                                         KEY `idx_cpf_paging`(`is_delete`,`create_time`,`id`),
                                         CONSTRAINT `fk_cpf_proj` FOREIGN KEY (`customer_project_id`) REFERENCES `customer_project`(`id`),
                                         CONSTRAINT `fk_cpf_file` FOREIGN KEY (`file_id`)           REFERENCES `file_store`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户-申报项目文件';

-- 客户分析（四级树状思维导图：存结构JSON+快照图片文件可选）
CREATE TABLE `customer_analysis` (
                                     `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                     `customer_id`   INT UNSIGNED NOT NULL,
                                     `graph_json`    JSON         NULL COMMENT '思维导图数据(四级树)',
                                     `snapshot_file` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '生成图快照 file_store.id',
                                     `generated`     TINYINT      NOT NULL DEFAULT 0 COMMENT '是否已生成',
                                     `create_time`   INT          NOT NULL DEFAULT 0,
                                     `update_time`   INT          NOT NULL DEFAULT 0,
                                     `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                     KEY `idx_can_customer_time`(`customer_id`,`create_time`),
                                     KEY `idx_can_paging`(`is_delete`,`create_time`,`id`),
                                     CONSTRAINT `fk_can_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户-企业分析';

-- 项目评估（评分维度可扩展）
CREATE TABLE `project_evaluation` (
                                      `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                      `customer_project_id` INT UNSIGNED NOT NULL,
                                      `total_score`   DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '综合得分',
                                      `detail_json`   JSON          NULL COMMENT '各维度得分与备注',
                                      `create_time`   INT           NOT NULL DEFAULT 0,
                                      `update_time`   INT           NOT NULL DEFAULT 0,
                                      `is_delete`     TINYINT       NOT NULL DEFAULT 0,
                                      KEY `idx_pe_proj_time`(`customer_project_id`,`create_time`),
                                      KEY `idx_pe_paging`(`is_delete`,`create_time`,`id`),
                                      CONSTRAINT `fk_pe_proj` FOREIGN KEY (`customer_project_id`) REFERENCES `customer_project`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目评估';

-- 客户-双方资源（客户自有或平台资源映射）
CREATE TABLE `customer_resource` (
                                     `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                     `customer_id`   INT UNSIGNED NOT NULL,
                                     `resource_name` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '资源名称',
                                     `resource_type` VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '资源类型',
                                     `link_or_contact` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '链接/联系方式',
                                     `remark`        VARCHAR(512) NOT NULL DEFAULT '' COMMENT '备注',
                                     `create_time`   INT          NOT NULL DEFAULT 0,
                                     `update_time`   INT          NOT NULL DEFAULT 0,
                                     `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                     KEY `idx_cr_customer_time`(`customer_id`,`create_time`),
                                     KEY `idx_cr_paging`(`is_delete`,`create_time`,`id`),
                                     CONSTRAINT `fk_cr_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户-企业资源';

-- 客户-人员信息（可模板导出）
CREATE TABLE `customer_person` (
                                   `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                   `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                   `customer_id`   INT UNSIGNED NOT NULL,
                                   `name`          VARCHAR(128) NOT NULL DEFAULT '' COMMENT '姓名',
                                   `title`         VARCHAR(128) NOT NULL DEFAULT '' COMMENT '职位',
                                   `phone`         VARCHAR(64)  NOT NULL DEFAULT '',
                                   `email`         VARCHAR(256) NOT NULL DEFAULT '',
                                   `remark`        VARCHAR(512) NOT NULL DEFAULT '',
                                   `create_time`   INT          NOT NULL DEFAULT 0,
                                   `update_time`   INT          NOT NULL DEFAULT 0,
                                   `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                   KEY `idx_cperson_customer`(`customer_id`,`name`),
                                   KEY `idx_cperson_paging`(`is_delete`,`create_time`,`id`),
                                   CONSTRAINT `fk_cperson_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户-人员信息';

-- 项目清单（用于全局筛选展示；如与 project_brief 合并可视需求裁剪）
CREATE TABLE `project_catalog` (
                                   `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                   `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                   `name`          VARCHAR(256) NOT NULL DEFAULT '' COMMENT '项目名称',
                                   `tag`           VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '标签/类型',
                                   `brief_id`      INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联 project_brief.id',
                                   `create_time`   INT          NOT NULL DEFAULT 0,
                                   `update_time`   INT          NOT NULL DEFAULT 0,
                                   `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                   KEY `idx_pcat_tag_name`(`tag`,`name`),
                                   KEY `idx_pcat_paging`(`is_delete`,`create_time`,`id`),
                                   CONSTRAINT `fk_pcat_brief` FOREIGN KEY (`brief_id`) REFERENCES `project_brief`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目清单（筛选展示）';

-- =====================================================================
-- 模型文件（数据/分析/政策/素材）
-- =====================================================================

CREATE TABLE `model_repo` (
                              `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                              `type`          TINYINT      NOT NULL DEFAULT 1 COMMENT '1数据模型 2分析模型 3政策模型 4模型素材',
                              `name`          VARCHAR(256) NOT NULL DEFAULT '' COMMENT '名称',
                              `desc_text`     VARCHAR(512) NOT NULL DEFAULT '' COMMENT '说明',
                              `file_id`       INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联文件',
                              `link_url`      VARCHAR(512) NOT NULL DEFAULT '' COMMENT '外部链接(可选)',
                              `creator_id`    INT UNSIGNED NOT NULL DEFAULT 0,
                              `create_time`   INT          NOT NULL DEFAULT 0,
                              `update_time`   INT          NOT NULL DEFAULT 0,
                              `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                              KEY `idx_mrepo_type_time`(`type`,`create_time`),
                              KEY `idx_mrepo_paging`(`is_delete`,`create_time`,`id`),
                              CONSTRAINT `fk_mrepo_file` FOREIGN KEY (`file_id`) REFERENCES `file_store`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模型文件库';

-- =====================================================================
-- AI 智能（生成报告/内容）
-- =====================================================================

CREATE TABLE `ai_task` (
                           `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                           `task_type`     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '任务类型：report/gen_text等',
                           `prompt`        MEDIUMTEXT   NULL COMMENT '提示词/配置',
                           `status`        TINYINT      NOT NULL DEFAULT 0 COMMENT '0排队 1进行中 2完成 3失败',
                           `result_file_id`INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '生成结果文件（如报告）',
                           `error_msg`     VARCHAR(512) NOT NULL DEFAULT '' COMMENT '失败信息',
                           `creator_id`    INT UNSIGNED NOT NULL DEFAULT 0,
                           `create_time`   INT          NOT NULL DEFAULT 0,
                           `update_time`   INT          NOT NULL DEFAULT 0,
                           `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                           KEY `idx_aitask_status_time`(`status`,`create_time`),
                           KEY `idx_aitask_paging`(`is_delete`,`create_time`,`id`),
                           CONSTRAINT `fk_aitask_file` FOREIGN KEY (`result_file_id`) REFERENCES `file_store`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI 任务';

-- =====================================================================
-- 资源链接
-- =====================================================================

CREATE TABLE `resource_link` (
                                 `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `uuid`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户uid',
                                 `title`         VARCHAR(256) NOT NULL DEFAULT '' COMMENT '标题',
                                 `link_url`      VARCHAR(512) NOT NULL DEFAULT '' COMMENT '链接',
                                 `category`      VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '分类',
                                 `order_no`      INT          NOT NULL DEFAULT 0,
                                 `create_time`   INT          NOT NULL DEFAULT 0,
                                 `update_time`   INT          NOT NULL DEFAULT 0,
                                 `is_delete`     TINYINT      NOT NULL DEFAULT 0,
                                 KEY `idx_rl_category_order`(`category`,`order_no`),
                                 KEY `idx_rl_paging`(`is_delete`,`create_time`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源链接';
