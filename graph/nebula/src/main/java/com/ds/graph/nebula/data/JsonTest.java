package com.ds.graph.nebula.data;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ds
 * @date 2024/11/19
 * @description
 */
public class JsonTest {
    public static void main(String[] args) {
        // 借据表
        String table = jjb();
        // 舆情
        table = yq();
        // 历史执行
        table = lszx();
        // 历史失信
        table = lssx();
        // 基本信息
        table = jbxx();
        //  变更
        table = bg();
        // 年报
        table = nb();

        // 黑名单
        table = hmd();
        // 账户状态异常
        table = zhztyc();
        // 借据
        table = jj();
        // 欠税
        table = qs();
        String[] lineArray = table.split("\n");
        JSONObject json = new JSONObject();
        for (String line : lineArray) {
            String[] obj = line.split("\t");
            String key = obj[0];
            String value = obj[1];
            json.put(key, value);
            String md5 = SecureUtil.md5("915000002024110502重庆测试技术有限公司").toUpperCase();
            json.put("eid", md5);
//            json.put("entid", md5);
            json.put("object_key", md5);
//            json.put("mid", UUID.fastUUID().toString().replace("-", ""));
        }

        System.out.println(json);
    }


    private static String a() {
        return "";
    }

    private static String qs() {
        return "object_key\t数据主键\n" +
                "eid\t关联查询主键\n" +
                "id\tid\n" +
                "mid\t唯一键\n" +
                "entid\t企业uid\n" +
                "personid\t个人uid\n" +
                "entname\t纳税人名称\n" +
                "qnum\t纳税人识别号\n" +
                "taxtype\t欠缴税种\n" +
                "taxcate\t种类1国税2地税\n" +
                "debt\t总欠税额\n" +
                "data_ddate\t数据日期";
    }


    private static String jj() {
        return "eid\t关联查询主键\n" +
                "object_key\t数据主键\n" +
                "cust_name\t客户名称\n" +
                "credit_code\t统一社会信用代码\n" +
                "company_id\t企业id\n" +
                "wrtoff_ind\t是否核销\n" +
                "indv_ind\t公私标志\n" +
                "loan_bal\t贷款余额\n" +
                "loan_amt\t贷款金额\n" +
                "matr_dt\t到期日期\n" +
                "distr_dt\t贷款发放日期\n" +
                "ovdue_days\t逾期天数\n" +
                "level5_class_cd\t五级分类（中文）\n" +
                "cust_id\t客户编号\n" +
                "contr_id\t合同编号\n" +
                "dubil_id\t借据编号\n" +
                "ovdue_bal\t逾期余额\n" +
                "off_owe_int_bal\t表外欠息余额\n" +
                "in_off_bs_cate_cd\t表内外类型代码\n" +
                "cert_cate_cd\t证件类型代码\n" +
                "cert_id\t证件编号\n" +
                "asset_tran_ind\t是否资产转让\n" +
                "payoff_dt\t结清日期\n" +
                "pay_back_type_self\t是否自身回流\n" +
                "pay_back_type_relate\t是否关联方回流\n" +
                "pay_back_type_selfhide\t是否隐性回流\n" +
                "pay_back\t是否资金回流\n" +
                "etl_dt\t数据日期\n" +
                "exec_int_rate\t执行利率\n" +
                "prod_name\t贷款产品名称\n" +
                "strip_line_cate_cd_new\t条线标识\n" +
                "flzp_org_id\t管户机构号\n" +
                "flzp_org_name\t管户机构名称\n" +
                "guarantee_type_name\t担保方式\n" +
                "oper_org_id\t经办机构号\n" +
                "oper_org_name\t经办机构名称";
    }

    private static String nbZc() {
        return "{\n" +
                "\t\"id\": 1,\n" +
                "\t\"entid\": \"8a80a8d54f2184bc8285a923f98dbe09\",\n" +
                "\t\"ANCHEYEAR\": \"2019\",\n" +
                "\t\"ASSGRO\": \"1000000\",\n" +
                "\t\"TOTEQU\": \"1000000\",\n" +
                "\t\"VENDINC\": \"1000000\",\n" +
                "\t\"PROGRO\": \"1000000\",\n" +
                "\t\"MAIBUSINC\": \"1000000\",\n" +
                "\t\"NETINC\": \"1000000\",\n" +
                "\t\"RATGRO\": \"1000000\",\n" +
                "\t\"LIAGRO\": \"1000000\"\n" +
                "}";
    }

    /**
     * 年报
     * @return
     */
    private static String nb() {
        return "object_key\t数据主键\n" +
                "eid\t关联查询主键\n" +
                "id\tid\n" +
                "entid\t企业UID\n" +
                "ANCHEYEAR\t年报年度\n" +
                "ENTNAME\t企业名称\n" +
                "UNISCID\t统一社会信用代码\n" +
                "BUSST\t经营状态\n" +
                "DOM\t企业通信地址\n" +
                "EMAIL\t电子邮箱\n" +
                "has_invest\t是否有投资信息或购买其他公司股权\n" +
                "POSTALCODE\t邮政编码\n" +
                "TEL\t企业联系电话\n" +
                "EMPNUM\t从业人数\n" +
                "has_equity\t是否发生股东股权转让\n" +
                "websites\t网站或网点JSON数据\n" +
                "shareholder_information\t股东信息JSON数据\n" +
                "invested_companies\t对外投资JSON数据\n" +
                "enterprise_asset_status_information\t企业资产状况JSON数据\n" +
                "edit_shareholding_change_infos\t股权变更JSON数据\n" +
                "edit_change_infos\t修改记录JSON数据\n" +
                "out_guarantee_info\t对外提供保证JSON数据\n" +
                "etl_dt\t处理时间";
    }



    /**
     * 变更
     *
     * @return
     */
    private static String bg() {
        return "entid\t企业UID\n" +
                "mid\t唯一键\n" +
                "ALTITEM\t变更事项\n" +
                "ALTBE\t变更前内容\n" +
                "ALTAF\t变更后内容\n" +
                "ALTDATE\t变更日期\n" +
                "etl_dt\t处理时间";
    }


    /**
     * 基本信息
     *
     * @return
     */
    private static String jbxx() {
        return "entid\t工商数据id\n" +
                "eid\teid\n" +
                "object_key\t数据主键\n" +
                "name\t企业名称\n" +
                "unified_social_credit_code\t统一社会信用代码\n" +
                "address\t注册地址\n" +
                "contact\t联系方式\n" +
                "cust_code\t客户号\n" +
                "business_scope\t经营范围\n" +
                "legal_man_id\t法人id\n" +
                "legal_man\t法人\n" +
                "stock_code\t股票代码\n" +
                "entity_type\t实体类型\n" +
                "capital\t注册资本\n" +
                "operation_startdate\t注册日期\n" +
                "business_status\t经营状态\n" +
                "enterprise_type\t工商类型\n" +
                "is_listed\t是否上市公司\n" +
                "province\t省份\n" +
                "city\t城市\n" +
                "industry\t行业（国标一级）\n" +
                "industry_label\t行业（国标四级）\n" +
                "registered_address\t注册工商局\n" +
                "registered_capital_unit\t注册资本金单位\n" +
                "utime\t数据更新时间\n" +
                "ctime\t数据创建时间\n" +
                "is_blacklist\t是否黑名单\n" +
                "customer_level\t行内客户等级\n" +
                "normal_status_count\t五级分类正常的借据笔数\n" +
                "follow_status_count\t五级分类关注的借据笔数\n" +
                "secondary_status_count\t五级分类次级的借据笔数\n" +
                "suspect_status_count\t五级分类可疑的借据笔数\n" +
                "loss_status_count\t五级分类损失的借据笔数\n" +
                "his_ovdue_list\t历史逾期明细\n" +
                "his_ovdue_list.dubil_id\t借据编号\n" +
                "his_ovdue_list.distr_dt\t贷款发放时间\n" +
                "his_ovdue_list.ovdue_dt\t贷款逾期时间\n" +
                "his_ovdue_list.ovdue_amt\t贷款逾期金额\n" +
                "his_apply_list\t申请明细信息\n" +
                "his_apply_list.apply_time\t申请时间\n" +
                "his_apply_list.apply_final_status\t申请结果\n" +
                "his_apply_list.apply_tel\t申请人联系方式\n" +
                "fxcd_max_ovdue_days_12\t近12个月贷款最大逾期天数\n" +
                "fxcd_max_ovdue_bal_12\t近12个月贷款最大逾期天数对应余额\n" +
                "fxcd_max_ovdue_mths_12\t近12个月贷款逾期金额>1000的月份数\n" +
                "fxcd_max_ovdue_days\t贷款当前最大逾期天数\n" +
                "fxcd_max_ovdue_bal\t贷款当前逾期金额\n" +
                "fxcd_max_ovdue_days_his\t未结清贷款历史最高逾期天数\n" +
                "fxcd_max_ovdue_days_nhis\t非当前逾期贷款历史最高逾期天数\n" +
                "patent_cn\t专利数量\n" +
                "copyright_cn\t软著数量\n" +
                "trademark_cn\t商标数量\n" +
                "penalty_cn\t行政处罚数\n" +
                "env_penalty_cn\t环保处罚数\n" +
                "is_abnomal_operation\t是否经营异常\n" +
                "is_illegal_promise\t是否严重违法失信\n" +
                "is_cancel\t是否注销\n" +
                "is_revocate\t是否吊销\n" +
                "is_ovtax\t是否存在欠税信息\n" +
                "is_shixin\t是否失信被执行人\n" +
                "is_zhixing\t是否被执行人\n" +
                "is_defendant\t是否存在为被告的案件\n" +
                "is_xiangao\t是否存在限高\n" +
                "registered_code\t注册号\n" +
                "registered_capital\t注册资本\n" +
                "hezhun_date\t核准日期\n" +
                "period\t经营日期范围\n" +
                "period_from\t营业期限自\n" +
                "period_to\t营业期限至\n" +
                "revocation_date\t吊销日期\n" +
                "cancel_date\t注销日期\n" +
                "district\t区域\n" +
                "endowment_insurance\t养老保险人数\n" +
                "email\t邮箱\n" +
                "taxpayer_qualification\t纳税人资质\n" +
                "taxpayer_code\t纳税人标识码";
    }

    /**
     * 历史失信
     *
     * @return
     */
    private static String lssx() {
        return "object_key\t数据主键\n" +
                "entid\t企业UID\n" +
                "FSX_SF\t省份\n" +
                "FSX_AH\t案号\n" +
                "FSX_NAME\t被执行人姓名/名称\n" +
                "FSX_FBDATE\t发布时间\n" +
                "FSX_ZXFYNAME\t执行法院名称\n" +
                "FSX_SFZH_ALL\t统计代码/证件号\n" +
                "FSX_LXQK\t被执行人履历情况\n" +
                "FSX_SXWS\t生效法律文书确定的义务\n" +
                "FSX_LASJ\t立案时间\n" +
                "FSX_ZCZXDW\t做出执行依据单位\n" +
                "FSX_SXJTQX\t失信被执行人具体情形\n" +
                "FSX_MONEY\t执行标的\n" +
                "data_ddate\t数据日期";
    }

    private static String lszx() {
        return "object_key\t数据主键\n" +
                "entid\t企业UID\n" +
                "FSS_CASENO\t案号\n" +
                "FSS_LASJ\t立案时间\n" +
                "FSS_STATUS\t状态\n" +
                "FSS_NAME\t被执行人姓名/名称\n" +
                "FSS_COURTNAME\t执行法院名称\n" +
                "FSS_IDT\t插入时间\n" +
                "FSS_MONEY\t执行标的\n" +
                "FSS_OUTDATE\t移出时间\n" +
                "data_ddate\t数据日期";
    }


    /**
     * 舆情
     *
     * @return
     */
    private static String yq() {
        return "id\tid\n" +
                "mid\t唯一键\n" +
                "entid\t企业UID\n" +
                "title\t标题\n" +
                "area\t所属地区\n" +
                "impact\t情感\n" +
                "keywords\t文件关键字\n" +
                "all_cities\t城市\n" +
                "all_provinces\t省份\n" +
                "etl_dt\t处理时间";
    }


    // 账户状态异常
    private static String zhztyc() {
        return "eid\t企业的唯一id\n" +
                "object_key\t数据主键\n" +
                "lawlock_dt\t异常状态开始时间\n" +
                "lawlock_rs\t异常状态认定原因\n" +
                "cust_no\t客户号\n" +
                "cust_name\t客户名称\n" +
                "acct_no\t异常的账户号";
    }


    /**
     * 黑名单
     *
     * @return
     */
    private static String hmd() {
        return "eid\t企业的唯一id\n" +
                "object_key\t数据主键\n" +
                "confirm_dt\t认定时间\n" +
                "confirm_rs\t认定原因\n" +
                "cust_no\t客户号\n" +
                "cust_name\t客户名称\n" +
                "system_flag\t来源系统";
    }

    /**
     * 借据表
     *
     * @return
     */
    private static String jjb() {
        String str = "object_key\t数据主键\n" +
                "cust_name\t客户名称\n" +
                "credit_code\t统一社会信用代码\n" +
                "company_id\t企业id\n" +
                "wrtoff_ind\t是否核销\n" +
                "indv_ind\t公私标志\n" +
                "loan_bal\t贷款余额\n" +
                "loan_amt\t贷款金额\n" +
                "matr_dt\t到期日期\n" +
                "distr_dt\t贷款发放日期\n" +
                "ovdue_days\t逾期天数\n" +
                "level5_class_cd\t五级分类（中文）\n" +
                "cust_id\t客户编号\n" +
                "contr_id\t合同编号\n" +
                "dubil_id\t借据编号\n" +
                "ovdue_bal\t逾期余额\n" +
                "off_owe_int_bal\t表外欠息余额\n" +
                "in_off_bs_cate_cd\t表内外类型代码\n" +
                "cert_cate_cd\t证件类型代码\n" +
                "cert_id\t证件编号\n" +
                "asset_tran_ind\t是否资产转让\n" +
                "payoff_dt\t结清日期\n" +
                "pay_back_type_self\t是否自身回流\n" +
                "pay_back_type_relate\t是否关联方回流\n" +
                "pay_back_type_selfhide\t是否隐性回流\n" +
                "pay_back\t是否资金回流\n" +
                "etl_dt\t数据日期\n" +
                "exec_int_rate\t执行利率\n" +
                "prod_name\t贷款产品名称\n" +
                "strip_line_cate_cd_new\t条线标识\n" +
                "flzp_org_id\t管户机构号\n" +
                "flzp_org_name\t管户机构名称\n" +
                "guarantee_type_name\t担保方式\n" +
                "oper_org_id\t经办机构号\n" +
                "oper_org_name\t经办机构名称";
        return str;
    }

}
