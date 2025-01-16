package com.ds.graph.nebula.gzl;

/**
 * @author ds
 * @date 2025/1/15
 * @description 风铃表名
 */
public class IapTable {
    public static void main(String[] args) {
        // 风铃表
        String str = iapTable();

        String[] split = str.split("\n");
        for (String s : split) {
            String[] split1 = s.split("\t");
            System.out.println(split1[0] + "(" + split1[1] + ")");
        }

    }

    private static String iapTable() {
        return "AML_BLACK_GRAY_RISK_CUST\t反洗钱风险客户\n" +
                "BATCH_DOWNLOAD_LOG\t批量下载日志\n" +
                "BATCH_DOWNLOAD_TASK\t批量下载任务\n" +
                "BATCH_UPLOAD_LOG\t批量上传日志\n" +
                "BEMEFICIARY\t受益人\n" +
                "BIGDATA_USER\t大数据用户\n" +
                "BRANCH_USER_OPE\t分支机构操作记录\n" +
                "BROWSING_HISTORY\t搜索历史\n" +
                "BUSINESS_CUSTOM_BELONG\t业务管户\n" +
                "CATEGORIES_CREDIT_STATUS\t五级分类授信状态表\n" +
                "CERT_TYPE_CD\t证件类型码表\n" +
                "CLOUD_MANAGER_OFFLINE\t云管家离线统计\n" +
                "CLOUD_MAMAGER_REALTIME\t云管家实时统计\n" +
                "COMPANY_BUSINESS_STATUS\t企业经营状态码表\n" +
                "COMPANY_FOR_FLUSH\t待刷新的企业列表\n" +
                "COMPANY_FOR_FLUSH_DEAL_DATA\t刷新企业数据详情\n" +
                "COMPANY_MAPPING_ECIF_ID\t公司映射ecif客户编码表\n" +
                "CREDIT_GROUP_MEMBER\t信贷集团派系表\n" +
                "CREDIT_GROUP_MEMBER_ENTITY\t信贷集团派系成员表\n" +
                "CUSTOM_BELONG\t管户数据\n" +
                "C_PAR_CORP_FIN_INDEX_INFO\t客户财报信息表\n" +
                "DATA_AUTH_CONFIG\t数据角色配置表\n" +
                "EXCHANGE_RATE\t汇率表\n" +
                "FILE_DOWNLOAD_TEMPLATE\t文件下载模板\n" +
                "FLZP_IO_ORG_INF_MAPP\t信贷机构信息\n" +
                "FOLLOW_ITEM\t关注详情\n" +
                "FOLLOW_LIST\t关注列表\n" +
                "FXCD_DEFLT\t风险传导\n" +
                "FXCD_DEFLT_TMP\t风险传导临时表\n" +
                "HR_DIM_HR_USER\t人力资源用户信息表\n" +
                "HR_DIM_HR_USER_YESTODAY\t人力资源用户前一日信息表\n" +
                "HR_DIM_ORG_CHANGE_INF\t人力资源机构变更信息表\n" +
                "HR_DIM_QUIT\t人力资源用户信息表\n" +
                "HR_DIM_XD_USER\t人力资源信贷用户信息表\n" +
                "HR_RESET_USER_ROLES\t人力资源重置用户角色为游客\n" +
                "HR_TO_CREDIT\t人力资源信贷映射表\n" +
                "HZ_USER_RESOURCE\t用户权限表\n" +
                "INDUSTRY_SEARCH_RECORD\t行业搜索记录\n" +
                "INDUSTRY_STATISTICS\t行业统计\n" +
                "INNER_COMPANY\t行内企业\n" +
                "MACRO_STORE\t宏观事件记录表\n" +
                "MARKET_EVENT_SETTING\t营销事件推送设置表\n" +
                "MODULE_FIELD\t模块字段\n" +
                "M_CREDIT_INFO\t行内授信客户表\n" +
                "M_ORG_INF\t机构表\n" +
                "NEW_REGISTERED_COMPANY\t新注册企业表\n" +
                "NOTIFICATION\t事件通知\n" +
                "N_P_SHAREHOLDER\t法人配偶信息表\n" +
                "OFFLINE_COUNT_BLOCKCHAIN\t区块链埋点统计结果表\n" +
                "OFFLINE_COUNT_BS_TASK\t个人贷款智能审批埋点统计结果表\n" +
                "OFFLINE_COUNT_OUT_SERVICE_TASK\t反洗钱对外提供服务埋点统计结果表\n" +
                "OFFLINE_COUNT_REPORT_TASK\t风险报告埋点统计结果表\n" +
                "OFFLINE_COUNT_STANDARD_TASK\t标准数据导出埋点统计结果表\n" +
                "OFFLINE_COUNT_TASK\t离线任务统计结果表\n" +
                "OFFLINE_COUNT_USER_TASK\t用户操作埋点统计结果表\n" +
                "OPERATECHECK_LOG\t管理员复核日志记录\n" +
                "ORG_AREA_CODE\t机构区域代码\n" +
                "O_L_COMPANY_INVEST\t同企业法人股东处理表\n" +
                "O_L_COMPANY_INVEST_DEAL\t同企业法人股东详情表\n" +
                "O_L_PERSON_INVEST\t同自然人股东\n" +
                "O_L_PERSON_INVEST_DEAL\t同自然人股东详情表\n" +
                "O_L_POSITION\t同法定代表人\n" +
                "O_L_POSITION_DEAL\t同法定代表人详情表\n" +
                "PERSON_MERGE_GROUP\t自然人融合表\n" +
                "PROFILE_ENTERPRISE_MAPPING\t族谱AB表控制\n" +
                "PWD_CONFIG\t密码配置\n" +
                "P_PAR_ZSTP_COMPANY_RELATE\t行内企业外部关联\n" +
                "P_PAR_ZSTP_CUST_BELONG\t客户所属\n" +
                "P_PAR_ZSTP_CUST_INFO\t客户信息\n" +
                "P_PAR_ZSTP_CUST_LABEL\t客户标签\n" +
                "P_PAR_ZSTP_TRAN_CORP\t客户集团交易\n" +
                "RISK_EXCEL_ODEL_INFO\t风险管理部导出模型\n" +
                "RISK_MODEL_USER_RELATION\t风险管理部-模型与人关系表\n" +
                "RISK_REPORT_CONFIG\t企业报告配置表\n" +
                "ROLE_RESOURCES_RELATION\t角色权限关联表\n" +
                "SYSTEM_CONFIG\t系统配置表\n" +
                "SYSTEM_PUBLISH_RECORD\t系统发布记录\n" +
                "T48_INDUSTRY_AVERAGE_INFO\t行业平均信息\n" +
                "TAG_CATEGORY\t客户标签\n" +
                "TASK\t导出任务表\n" +
                "TYPE_QUERY\t导出任务类型\n" +
                "USER_AUTH_MAPPING\t用户权限映射表\n" +
                "USER_BLACKLIST\t用户黑名单表\n" +
                "USER_CHANGE_ROLE_LOG\t用户变更角色日志表\n" +
                "USER_CONFIG\t用户配置表\n" +
                "USER_DATA_ROLE\t用户数据角色\n" +
                "USER_DATA_ROLE_RELATION\t用户数据角色关联\n" +
                "USER_GROUP\t用户分组\n" +
                "USER_HELP_FILE\t用户帮助文件\n" +
                "USER_HELP_QA\t用户帮助QA\n" +
                "USER_HELP_WORD\t用户帮助词典\n" +
                "USER_IMPORT_LOG\t用户导入功能日志记录表\n" +
                "USER_ROLES\t用户角色表\n" +
                "USER_ROLE_RELATION\t用户角色关联表\n" +
                "USER_TEMPLATE\t用户模板\n" +
                "USER_TEMPLATE_DELETED\t用户模板删除记录\n" +
                "USER_WHITELIST\t用户白名单表\n" +
                "YBI_AUTH_COMPANY_INFO\t银保监日终授权表\n" +
                "YBI_EMBEZZLE\t银保监资金挪用表\n" +
                "YBI_RECYCLING\t银保监资金回流表";
    }

}
