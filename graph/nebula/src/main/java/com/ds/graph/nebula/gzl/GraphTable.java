package com.ds.graph.nebula.gzl;

/**
 * @author ds
 * @date 2025/1/15
 * @description 图平台表名
 */
public class GraphTable {
    public static void main(String[] args) {
        // 图平台表
        String str = graph();
        // 图平台nacos表
        str = nacos();
        // 风铃服务
        str = iapServer();

        String[] split = str.split("\n");
        for (String s : split) {
            String[] split1 = s.split("\t");
            System.out.println(split1[0] + "(" + split1[1] + ")");
        }

    }

    private static String iapServer() {
        return "account\t用户服务\n" +
                "bank\t实时服务\n" +
                "follow\t关注服务\n" +
                "search\t搜索服务";
    }

    private static String nacos() {
        return "config_info\t配置信息表\n" +
                "config_info_aggr\t配置信息聚合表\n" +
                "config_info_beta\t配置信息测试版表\n" +
                "config_info_tag\t配置信息标签表\n" +
                "config_tags_relation\t配置信息标签关联表\n" +
                "group_capacity\t集群、各Group容量信息表\n" +
                "his_config_info\t历史配置信息表\n" +
                "permissions\t权限信息\n" +
                "roles\t角色信息\n" +
                "tenant_capacity\t租户容量信息表\n" +
                "tenant_info\t租户信息\n" +
                "users\t用户信息";
    }

    private static String graph() {
        return "ac_card\t档案管理卡片表\n" +
                "ac_card_base\t档案管理基本信息卡片详情表\n" +
                "ac_card_common\t档案管理普通卡片详情表\n" +
                "ac_card_quote\t档案管理引用卡片详情表\n" +
                "ac_card_related\t档案管理关系卡片详情表\n" +
                "ac_card_statistic\t档案管理统计卡片详情表\n" +
                "ac_card_statistic_conf\t档案管理统计卡片时间区间表\n" +
                "ac_section\t档案管理栏目表\n" +
                "afl_alg\t算法表\n" +
                "afl_alg_config\t自定义算法配置表\n" +
                "afl_alg_usage\t算法使用记录\n" +
                "afl_alg_version\t算法版本表\n" +
                "afl_alg_version_history\t算法版本历史表\n" +
                "afl_dag\t工作流dag表\n" +
                "afl_dag_instance\tdag实例表\n" +
                "afl_file\t文件表\n" +
                "afl_flow\t工作流定义表\n" +
                "afl_flow_auth\t工作流授权表\n" +
                "afl_flow_import_task\t场景导入任务\n" +
                "afl_flow_sub\t子工作流定义表\n" +
                "afl_import_task\t基于文件的挖掘导入任务\n" +
                "afl_item\titem表\n" +
                "afl_item_group\titem组表\n" +
                "afl_item_param\t组件栏目参数表\n" +
                "afl_model\t工作流模型表\n" +
                "afl_schedule\t调度配置表\n" +
                "afl_schedule_audit\t调度审核表\n" +
                "afl_schedule_instance\t调度实例表\n" +
                "afl_template\t场景工作流模板\n" +
                "app_count_field_config\t应用族谱统计字段配置表\n" +
                "app_group_tag_dict\t族谱标签表\n" +
                "app_info\t应用表\n" +
                "app_role\t应用授权表\n" +
                "app_schema\t应用表关联表\n" +
                "app_tag_field_config\t应用族谱标识字段配置表\n" +
                "app_template\t应用模版表\n" +
                "app_template_schema\t模版字段关联表\n" +
                "csp_item\t组件实例表\n" +
                "csp_item_file\t门户Logo文件\n" +
                "csp_item_param\t组件实例参数表\n" +
                "csp_item_relation\t组件父子关系\n" +
                "csp_page_item\t门户页面配置\n" +
                "csp_portal\t门户实例表\n" +
                "csp_portal_item\t门户组件配置\n" +
                "csp_portal_page\t页面实例表\n" +
                "csp_portal_page_item\t门户页面组件配置\n" +
                "csp_portal_topic\t门户主题关系\n" +
                "dc_data_operate_record\t数据源数据操作记录\n" +
                "dc_data_source\t外部数据源表\n" +
                "dc_dict_group\t检索字典配置表\n" +
                "dc_dict_item\t检索字典项表\n" +
                "dc_env\t环境表\n" +
                "dc_env_file\t环境配置文件表\n" +
                "dc_graph\t图实例表\n" +
                "dc_graph_designator\t图域名指定人表\n" +
                "dc_graph_store\t图存储库关系表\n" +
                "dc_schema\t实体/边元数据表\n" +
                "dc_schema_field\t实体/边的字段元数据表\n" +
                "dc_store\t数据源配置表\n" +
                "dc_store_designator\t数据源指定人表\n" +
                "dc_store_param\t数据源参数配置表\n" +
                "dc_style\t本体模式可视化配置\n" +
                "dc_tags\t标签表\n" +
                "dc_tags_group\t标签分组表\n" +
                "dc_task\t任务表数据接入\n" +
                "dc_task_condition\t任务表数据接入数据筛选、高级设置\n" +
                "dc_task_error\t任务错误表数据接入\n" +
                "dc_task_format\t任务元数据映射kafka格式解析\n" +
                "dc_task_instance\t任务实例表数据接入\n" +
                "dc_task_meta\t任务元数据映射数据接入\n" +
                "dc_vertex_edge\t实体与边关联表\n" +
                "ds_project_store\t项目与数据源\n" +
                "ds_query_statement\t数据服务我的查询\n" +
                "ds_response_template\t数据服务结果模版\n" +
                "ds_service\t数据服务\n" +
                "ds_service_error_code\t数据服务服务错误码\n" +
                "gap_roster\t名单管理表\n" +
                "ga_entity_history\t图析添加实体导入历史记录表\n" +
                "ga_entity_history_group\t图析添加实体导入历史记录分组详情表\n" +
                "hive_sheet\t工作表管理Hive表管理目录树信息表\n" +
                "hive_sheet_account\t工作表管理工作表统计信息\n" +
                "hive_sheet_column\t工作表管理工作表字段信息表\n" +
                "hive_sheet_count\t工作表管理工作表趋势变化信息表\n" +
                "hive_sheet_history\t工作表管理工作表字段历史信息表\n" +
                "is_history_view\t浏览历史记录\n" +
                "is_semantic_tag\t智能搜索语义标签表\n" +
                "is_service\t智能搜索服务\n" +
                "is_view_field\t智能搜索字段展示层级\n" +
                "kam_analysis_text\t警情研判历史文本分析\n" +
                "kam_call_group\t通话分组表对话单所属信息分组\n" +
                "kam_call_record_detail\t话单所属详情表\n" +
                "kam_call_record_info\t话单所属信息表\n" +
                "kam_multi_visual_app\t警情挖掘专题应用表\n" +
                "kam_occurrence_police\t警情研判共现预警\n" +
                "kam_police_info_type\t警情研判异常预警类型\n" +
                "kam_special_identity\t警情研判异常预警特殊身份\n" +
                "kam_trans_group\t交易分组表\n" +
                "kam_trans_record_detail\t交易记录详情表\n" +
                "kam_trans_record_info\t交易记录所属信息表\n" +
                "kap_lab\t个人库信息表\n" +
                "kap_lab_excel_template\t个人库excel导出模版表\n" +
                "kap_lab_rule\t个人库构建规则表\n" +
                "kap_lab_server\t个人库服务器信息表Gremlinerver\n" +
                "kip_tag\t自定义指标基础信息表\n" +
                "kip_tag_allocation\t自定义指标分配信息表\n" +
                "kip_tag_batch_calc\t自定义指标分批计算表\n" +
                "kip_tag_calc_hist\t自定义指标计算历史表\n" +
                "kip_tag_dependency\t自定义指标依赖信息表\n" +
                "kip_tag_edge_amt\t生产环境关系数据量记录表\n" +
                "kip_tag_write_history\t自定义指标回写历史表\n" +
                "kpp_entity_href_config\t实体对象内链拓展配置表\n" +
                "kpp_group_schema\t智慧搜索检索标签与表关联表\n" +
                "kpp_module_used\t最近使用模块表\n" +
                "kpp_schema_count\t统计表\n" +
                "kpp_search_config\t智慧搜索配置表\n" +
                "kpp_search_group\t智慧搜索检索标签表\n" +
                "kpp_search_schema_field\t智慧检索元数据配置表\n" +
                "kpp_tag\t标签表\n" +
                "kpp_tag_field\t标签字段表\n" +
                "kpp_tag_history\t浏览记录表\n" +
                "kpp_tag_schema\t标签检索标签字典组与表关联表\n" +
                "oss_file\t对象存储服务表\n" +
                "ph_catalog\t图析快照目录表\n" +
                "ph_info\t图析快照信息表\n" +
                "ph_share\t图析快照分享表\n" +
                "pm_event_config\t事件配置表\n" +
                "pm_gdb_search_history\t图析查询历史\n" +
                "pm_hyperlink\t详情超链接\n" +
                "pm_hyperlink_data\t详情超链接数据\n" +
                "pm_org\t项目组织\n" +
                "pm_org_level\t项目组织层级\n" +
                "pm_org_user\t项目组织与成员\n" +
                "pm_project\t项目\n" +
                "pm_project_config\t项目其他配置表\n" +
                "pm_project_graph\t项目与图实例\n" +
                "pm_project_schema\t项目与表\n" +
                "pm_role\t项目角色\n" +
                "pm_role_resource\t项目角色功能权限\n" +
                "pm_role_rule\t项目角色数据权限规则\n" +
                "pm_role_schema\t项目角色数据权限\n" +
                "pm_role_search_depth\t项目角色数据权限搜索深度\n" +
                "pm_search_detail_config\t图查询实体关系详情配置\n" +
                "pm_style\t可视化配置\n" +
                "pm_style_rule\t可视化配置规则\n" +
                "pm_timeline\t时间轴配置表\n" +
                "pm_user_role\t项目用户与项目角色\n" +
                "sm_aggregation_rule\t关系聚合规则\n" +
                "sm_group\t分组\n" +
                "sm_permission_mapping\t服务发布权限映射\n" +
                "sm_rule\t图查询规则\n" +
                "sm_search\t我的查询\n" +
                "sm_service\t我的服务\n" +
                "sm_service_access_log\t服务访问日志表\n" +
                "sm_service_import\t服务导入记录表\n" +
                "sm_service_permission\t我的服务权限\n" +
                "sm_sync_history\t服务注册历史表\n" +
                "sm_template\t图查询模板\n" +
                "sm_template_rule\t图查询模板与规则\n" +
                "so_entity_list\t离线图查询任务实体名单excel文件解析出来的实体表名和对应的主键信息表\n" +
                "so_entity_list_allocate\t离线图查询任务实体名单excel文件解析出来的实体表名分配信息表\n" +
                "so_entity_list_config\t离线图线上名单配置表\n" +
                "so_excel_config\t离线图查询EXCEL导入上限配置表\n" +
                "so_search_rule\t项目图搜索过滤规则定义配置表(图可视化查询用)\n" +
                "so_sub_task\t离线图查询子任务表\n" +
                "so_task\t离线图查询任务表\n" +
                "so_task_result\t离线图查询任务表\n" +
                "sub_entity\t订阅实体\n" +
                "sub_group\t订阅分组\n" +
                "sub_message\t订阅消息\n" +
                "sub_user_message\t订阅用户消息\n" +
                "sys_config\t系统配置\n" +
                "sys_dict\t数据字典\n" +
                "sys_file\t系统文件（统一管理）\n" +
                "sys_license\tlicense数据\n" +
                "sys_license_resource\tlicense资源与系统资源关联表\n" +
                "sys_mapping_table\t权限映射表\n" +
                "sys_mapping_value\t权限映射数据值\n" +
                "sys_openapi_secret\tOpenAPI用户密钥表\n" +
                "sys_resource\t资源\n" +
                "sys_role\t角色\n" +
                "sys_role_resource\t角色与资源\n" +
                "sys_update_history\t更新历史表\n" +
                "sys_user\t用户\n" +
                "sys_user_config\t用户配置\n" +
                "sys_user_info_field\t用户信息字段表\n" +
                "sys_user_operation_log\t用户操作日志\n" +
                "sys_user_role\t用户与角色\n" +
                "tr_task_error\t大数据平台错误数据记录\n" +
                "tr_task_instance\t大数据平台任务实例表";
    }

}
