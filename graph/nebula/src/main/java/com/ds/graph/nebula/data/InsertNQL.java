package com.ds.graph.nebula.data;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ds
 * @date 2024/11/19
 * @description
 */
public class InsertNQL {

    public static final Integer COUNT = 1;

    public static void main(String[] args) {
        // 基本信息
        String jsonStr = jbxx();
        String tableName = "Company";
        String comment = "测试";

        // 年报
        jsonStr = nb();
        tableName = "ads_company_ar_detail_dd";
        comment = "测试";

        // 黑名单
        jsonStr = hmd();
        tableName = "black_list_detail";

        // 账户状态异常
        jsonStr = zhztyc();
        tableName = "account_normal_detail";

        // 借据
        jsonStr = jj();
        tableName = "loan_dubil_dtl";

        // 账户状态异常
        jsonStr = qs();
        tableName = "entity_ads_edmp_qianshui_di";

        jsonStr = fmyq();
        tableName = "ads_edmp_dataplus_news_dd";

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Set<String> keySet = jsonObject.keySet();
        StringBuilder sb = new StringBuilder("CREATE tag `").append(tableName).append("` (");
        for (String key : keySet) {
            sb.append("`").append(key).append("` string NULL,");
        }
        String createTableNql = sb.substring(0, sb.length() - 1) + ")";
        System.out.println("createTableNql = " + createTableNql);

        StringBuilder sb1 = new StringBuilder("INSERT VERTEX ").append(tableName).append(" (");
        StringBuilder sb2 = new StringBuilder().append("(");
        for (String key : keySet) {
            sb1.append(key).append(",");
            sb2.append("\"").append(jsonObject.getString(key)).append("\",");
        }

        String str1 = sb1.substring(0, sb1.length() - 1) + ") VALUES " + "\"" + UUID.fastUUID() + "\"" + ":";
        String str2 = sb2.substring(0, sb2.length() - 1) + ");";

        String insertNql = str1 + str2;

        System.out.println(insertNql);

        System.exit(1);
        String createTable = "CREATE tag `%s` (`eid` string NOT NULL COMMENT \"企业id\", `name` string NULL COMMENT \"企业名\", `data_json` string NULL COMMENT \"数据\") COMMENT = \"%s\";";
        String createIndex = " CREATE TAG INDEX `%s_eid_name` on `%s`(`eid`(50), `name`(50));";
        createTable = String.format(createTable, tableName, comment);
        createIndex = String.format(createIndex, tableName, tableName);
//        System.out.println("createIndex = " + createTable);
//        System.out.println("createIndex = " + createIndex);

        NebulaRepo nebulaRepo = NebulaRepo.getNebulaRepo();
        try {
            nebulaRepo.executeUseDatabase(createTable);
            Thread.sleep(10000);
            nebulaRepo.executeUseDatabase(createIndex);
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println("e = " + e);
            System.exit(0);
        }

        List<String> nqlList = nqlList(COUNT, tableName, JSON.parseObject(jsonStr).toJSONString());

        try {
            for (String nql : nqlList) {
                try {
                    nebulaRepo.executeUseDatabase(nql);
                } catch (Exception e) {
                    System.out.println("e = " + e);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        } finally {
            nebulaRepo.close();
        }

    }

    private static List<String> nqlList(int i, String tableName, String json) {
        List<String> result = new ArrayList<>();
        for (int j = 1; j <= i; j++) {
            String id = tableName + j;
            String insertNql = "INSERT VERTEX %s(eid, name, data_json) VALUES \"%s\": (\"重庆测试技术有限公司915000002024110502\", \"重庆测试技术有限公司\",'%s');";
            insertNql = String.format(insertNql, tableName, id, json);
//            System.out.println("insertNql = " + insertNql);
            result.add(insertNql);
        }
        return result;
    }

    private static String a() {
        return "";
    }

    private static String fmyq() {
        return "{\n" +
                "\t\"area\": \"所属地区\",\n" +
                "\t\"eid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"object_key\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"keywords\": \"文件关键字\",\n" +
                "\t\"entid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"impact\": \"情感\",\n" +
                "\t\"all_cities\": \"重庆\",\n" +
                "\t\"mid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"title\": \"标题\",\n" +
                "\t\"all_provinces\": \"重庆\",\n" +
                "\t\"id\": \"id\",\n" +
                "\t\"etl_dt\": \"处理时间\",\n" +
                "\t\"event_time\": \"舆情发生时间\"\n" +
                "}";
    }

    private static String qs() {
        return "{\n" +
                "\t\"qnum\": \"915000002024110502\",\n" +
                "\t\"eid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"data_ddate\": \"2024-10-11\",\n" +
                "\t\"object_key\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"entid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"entname\": \"重庆测试技术有限公司\",\n" +
                "\t\"taxcate\": \"国税\",\n" +
                "\t\"mid\": \"1\",\n" +
                "\t\"personid\": \"1\",\n" +
                "\t\"id\": \"1\",\n" +
                "\t\"debt\": \"100000\",\n" +
                "\t\"taxtype\": \"城市维护建设税\"\n" +
                "}";
    }


    private static String jj() {
        return "{\n" +
                "\t\"eid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"payoff_dt\": \"结清日期\",\n" +
                "\t\"object_key\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"dubil_id\": \"001\",\n" +
                "\t\"oper_org_id\": \"1001\",\n" +
                "\t\"cert_id\": \"101\",\n" +
                "\t\"pay_back_type_self\": \"true\",\n" +
                "\t\"indv_ind\": \"公私标志\",\n" +
                "\t\"contr_id\": \"1000001\",\n" +
                "\t\"asset_tran_ind\": \"true\",\n" +
                "\t\"credit_code\": \"915000002024110502\",\n" +
                "\t\"cust_name\": \"重庆测试技术有限公司\",\n" +
                "\t\"distr_dt\": \"贷款发放日期\",\n" +
                "\t\"off_owe_int_bal\": 100000,\n" +
                "\t\"in_off_bs_cate_cd\": \"1\",\n" +
                "\t\"etl_dt\": \"数据日期\",\n" +
                "\t\"oper_org_name\": \"总行\",\n" +
                "\t\"cert_cate_cd\": \"证件类型代1码\",\n" +
                "\t\"company_id\": \"企业id\",\n" +
                "\t\"wrtoff_ind\": \"true\",\n" +
                "\t\"guarantee_type_name\": \"抵押\",\n" +
                "\t\"loan_amt\": 100000,\n" +
                "\t\"loan_bal\": 100000,\n" +
                "\t\"flzp_org_name\": \"总行\",\n" +
                "\t\"prod_name\": \"贷中贷\",\n" +
                "\t\"level5_class_cd\": \"正常\",\n" +
                "\t\"ovdue_bal\": 100000,\n" +
                "\t\"flzp_org_id\": \"1001\",\n" +
                "\t\"exec_int_rate\": \"3%\",\n" +
                "\t\"matr_dt\": \"到期日期\",\n" +
                "\t\"pay_back_type_selfhide\": \"true\",\n" +
                "\t\"ovdue_days\": 10,\n" +
                "\t\"cust_id\": \"10001\",\n" +
                "\t\"pay_back\": \"true\",\n" +
                "\t\"strip_line_cate_cd_new\": \"大中条线\",\n" +
                "\t\"pay_back_type_relate\": \"true\"\n" +
                "}";
    }

    private static String nb() {
        return "{\n" +
                "\t\"ANCHEYEAR\": \"2019\",\n" +
                "\t\"POSTALCODE\": \"40111\",\n" +
                "\t\"eid\": \"8a80a8d54f2184bc8285a923f98dbe09\",\n" +
                "\t\"DOM\": \"重庆市渝北区xxx号\",\n" +
                "\t\"object_key\": \"8a80a8d54f2184bc8285a923f98dbe09\",\n" +
                "\t\"entid\": \"8a80a8d54f2184bc8285a923f98dbe09\",\n" +
                "\t\"invested_companies\": \"[]\",\n" +
                "\t\"BUSST\": \"在营\",\n" +
                "\t\"has_equity\": \"否\",\n" +
                "\t\"UNISCID\": \"915000002024110502\",\n" +
                "\t\"EMAIL\": \"1@qq.com\",\n" +
                "\t\"edit_shareholding_change_infos\": \"[]\",\n" +
                "\t\"edit_change_infos\": \"[]\",\n" +
                "\t\"has_invest\": \"true\",\n" +
                "\t\"shareholder_information\": \"[]\",\n" +
                "\t\"enterprise_asset_status_information\": \"{\\\"id\\\": 1,\\\"entid\\\": \\\"8a80a8d54f2184bc8285a923f98dbe09\\\"\\\"ANCHEYEAR\\\": \\\"2019\\\",\\\"ASSGRO\\\": \\\"1000000\\\",\\\"TOTEQU\\\": \\\"1000000\\\",\\\"VENDINC\\\": \\\"1000000\\\",\\\"PROGRO\\\": \\\"1000000\\\",\\\"MAIBUSINC\\\": \\\"1000000\\\",\\\"NETINC\\\": \\\"1000000\\\",\\\"RATGRO\\\": \\\"1000000\\\",\\\"LIAGRO\\\": \\\"1000000\\\"}\",\n" +
                "\t\"ENTNAME\": \"企业名称\",\n" +
                "\t\"TEL\": \"企业联系电话\",\n" +
                "\t\"websites\": \"[]\",\n" +
                "\t\"out_guarantee_info\": \"[]\",\n" +
                "\t\"id\": \"1\",\n" +
                "\t\"etl_dt\": \"2024-11-01\",\n" +
                "\t\"EMPNUM\": 100\n" +
                "}";
    }

    private static String jbxx() {
        return "{\n" +
                "\t\"enterprise_type\": \"股份有限公司(上市、国有控股)\",\n" +
                "\t\"capital\": \"1000\",\n" +
                "\t\"is_revocate\": \"false\",\n" +
                "\t\"is_listed\": \"true\",\n" +
                "\t\"entid\": \"8a80a8d54f2184bc8285a923f98dbe09\",\n" +
                "\t\"suspect_status_count\": \"1\",\n" +
                "\t\"is_blacklist\": \"false\",\n" +
                "\t\"loss_status_count\": \"1\",\n" +
                "\t\"taxpayer_qualification\": \"增值税一般纳税人\",\n" +
                "\t\"is_cancel\": \"false\",\n" +
                "\t\"operation_startdate\": \"2002-07-02\",\n" +
                "\t\"patent_cn\": \"10\",\n" +
                "\t\"fxcd_max_ovdue_bal_12\": \"1000\",\n" +
                "\t\"province\": \"重庆\",\n" +
                "\t\"contact\": \"13012345678\",\n" +
                "\t\"hezhun_date\": \"2002-07-02\",\n" +
                "\t\"cancel_date\": \"-\",\n" +
                "\t\"ctime\": \"2023\",\n" +
                "\t\"revocation_date\": \"-\",\n" +
                "\t\"fxcd_max_ovdue_days\": \"10\",\n" +
                "\t\"fxcd_max_ovdue_days_his\": \"10\",\n" +
                "\t\"is_defendant\": \"true\",\n" +
                "\t\"period\": \"2002-07-02至长期\",\n" +
                "\t\"business_status\": \"在营（开业）\",\n" +
                "\t\"taxpayer_code\": \"915000002024110502\",\n" +
                "\t\"legal_man\": \"张三\",\n" +
                "\t\"is_abnomal_operation\": \"true\",\n" +
                "\t\"fxcd_max_ovdue_days_12\": \"100\",\n" +
                "\t\"business_scope\": \"许可项目：吸收公众存款；发放短期、中期和长期贷款；办理国内结算；办理票据承兑贴现；发行金融债券；代理发行、代理兑付、承销政府债券；买卖政府债券；从事同业拆借；提供信用证服务及担保；代理收付款项及代办保险业务；提供保管箱业务；信贷资产转让业务；办理地方财政周转金的委托贷款业务。外汇存款；外汇贷款；外币兑换；国际结算；结汇、售汇；同业外汇拆借；自营和代客买卖外汇；普通类衍生产品交易；买卖除股票以外的外币有价证券；资信调查、咨询、见证业务；开办信用卡业务；证券投资基金销售业务；办理帐务查询、网上转帐、代理业务、贷款业务、集团客户管理、理财服务、电子商务、客户服务、公共信息等网上银行业务；经中国银行业监督管理委员会批准的其他业务（依法须经批准的项目，经相关部门批准后方可开展经营活动，具体经营项目以相关部门批准文件或许可证件为准）\",\n" +
                "\t\"is_xiangao\": \"false\",\n" +
                "\t\"normal_status_count\": \"10\",\n" +
                "\t\"is_illegal_promise\": \"true\",\n" +
                "\t\"district\": \"渝北区\",\n" +
                "\t\"name\": \"重庆测试技术有限公司\",\n" +
                "\t\"is_ovtax\": \"true\",\n" +
                "\t\"unified_social_credit_code\": \"915000002024110502\",\n" +
                "\t\"follow_status_count\": \"12\",\n" +
                "\t\"eid\": \"8a80a8d54f2184bc8285a923f98dbe09\",\n" +
                "\t\"registered_address\": \"重庆市市场监督管理局\",\n" +
                "\t\"object_key\": \"8a80a8d54f2184bc8285a923f98dbe09\",\n" +
                "\t\"city\": \"重庆市\",\n" +
                "\t\"legal_man_id\": \"法人id\",\n" +
                "\t\"fxcd_max_ovdue_mths_12\": \"1\",\n" +
                "\t\"customer_level\": \"普惠级资产类\",\n" +
                "\t\"industry\": \"互联网信息服务\",\n" +
                "\t\"is_zhixing\": \"true\",\n" +
                "\t\"endowment_insurance\": \"100\",\n" +
                "\t\"is_shixin\": \"true\",\n" +
                "\t\"industry_label\": \"服装零售\",\n" +
                "\t\"secondary_status_count\": \"100\",\n" +
                "\t\"fxcd_max_ovdue_bal\": \"1000\",\n" +
                "\t\"period_from\": \"2002-07-02至长期\",\n" +
                "\t\"registered_capital_unit\": \"万元\",\n" +
                "\t\"stock_code\": \"601962\",\n" +
                "\t\"copyright_cn\": \"19\",\n" +
                "\t\"email\": \"1@qq.com\",\n" +
                "\t\"env_penalty_cn\": \"5\",\n" +
                "\t\"address\": \"重庆市江北区永平门xxx号\",\n" +
                "\t\"penalty_cn\": \"8\",\n" +
                "\t\"utime\": \"2024-11-21 10:11:22\",\n" +
                "\t\"fxcd_max_ovdue_days_nhis\": \"100\",\n" +
                "\t\"registered_capital\": \"1000\",\n" +
                "\t\"cust_code\": \"123456\",\n" +
                "\t\"entity_type\": \"实体类型\",\n" +
                "\t\"trademark_cn\": \"10\",\n" +
                "\t\"period_to\": \"2002-07-02至长期\",\n" +
                "\t\"registered_code\": \"500000000008212\",\n" +
                "\t\"his_ovdue_list\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"dubil_id\": \"1001\",\n" +
                "\t\t\t\"distr_dt\": \"2023-10-11\",\n" +
                "\t\t\t\"ovdue_dt\": \"2024-11-11\",\n" +
                "\t\t\t\"ovdue_amt\": \"100\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"his_apply_list\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"apply_time\": \"2023-10-11\",\n" +
                "\t\t\t\"apply_final_status\": \"成功\",\n" +
                "\t\t\t\"apply_tel\": \"13012345678\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }


    private static String lssx() {
        return "{\"FSX_LASJ\":\"立案时间\",\"eid\":\"重庆测试技术有限公司915000002024110502\",\"object_key\":\"数据主键\",\"FSX_ZCZXDW\":\"做出执行依据单位\",\"entid\":\"企业UID\",\"FSX_SF\":\"省份\",\"FSX_FBDATE\":\"发布时间\",\"data_ddate\":\"数据日期\",\"FSX_SXJTQX\":\"失信被执行人具体情形\",\"FSX_AH\":\"案号\",\"FSX_SXWS\":\"生效法律文书确定的义务\",\"FSX_MONEY\":\"执行标的\",\"FSX_ZXFYNAME\":\"执行法院名称\",\"FSX_LXQK\":\"被执行人履历情况\",\"FSX_NAME\":\"被执行人姓名/名称\",\"FSX_SFZH_ALL\":\"统计代码/证件号\"}\n";
    }

    /**
     * 历史执行
     *
     * @return
     */
    private static String lszx() {
        return "{\"eid\":\"重庆测试技术有限公司915000002024110502\",\"FSS_COURTNAME\":\"执行法院名称\",\"FSS_LASJ\":\"立案时间\",\"data_ddate\":\"数据日期\",\"FSS_OUTDATE\":\"移出时间\",\"object_key\":\"数据主键\",\"FSS_IDT\":\"插入时间\",\"entid\":\"企业UID\",\"FSS_STATUS\":\"状态\",\"FSS_NAME\":\"被执行人姓名/名称\",\"FSS_MONEY\":\"执行标的\",\"FSS_CASENO\":\"案号\"}\n";
    }

    /**
     * 负面舆情
     *
     * @return
     */
    private static String yq() {
        return "{\"area\":\"所属地区\",\"eid\":\"重庆测试技术有限公司915000002024110502\",\"all_provinces\":\"省份\",\"keywords\":\"文件关键字\",\"entid\":\"企业UID\",\"impact\":\"情感\",\"all_cities\":\"城市\",\"mid\":\"唯一键\",\"id\":\"id\",\"title\":\"标题\",\"etl_dt\":\"处理时间\"}\n";
    }

    // 账户状态异常
    private static String zhztyc() {
        return "{\n" +
                "\t\"eid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"object_key\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"lawlock_rs\": \"异常状态认定原因\",\n" +
                "\t\"lawlock_dt\": \"异常状态开始时间\",\n" +
                "\t\"cust_no\": \"c10002\",\n" +
                "\t\"acct_no\": \"a10002\",\n" +
                "\t\"cust_name\": \"重庆测试xxx公司\"\n" +
                "}";
    }

    // 黑名单
    private static String hmd() {
        return "{\n" +
                "\t\"eid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"object_key\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"system_flag\": \"信贷系统\",\n" +
                "\t\"confirm_rs\": \"认定原因\",\n" +
                "\t\"cust_no\": \"c10001\",\n" +
                "\t\"confirm_dt\": \"认定时间\",\n" +
                "\t\"cust_name\": \"张三\"\n" +
                "}";
    }

    /**
     * 借据表数据
     *
     * @return
     */
    private static String jjb() {
        return "{\n" +
                "\t\"eid\": \"重庆测试技术有限公司915000002024110502\",\n" +
                "\t\"payoff_dt\": \"2024-10-21\",\n" +
                "\t\"object_key\": \"数据主键\",\n" +
                "\t\"dubil_id\": \"123\",\n" +
                "\t\"oper_org_id\": \"经办机构号\",\n" +
                "\t\"cert_id\": \"证件编号\",\n" +
                "\t\"pay_back_type_self\": \"是否自身回流\",\n" +
                "\t\"indv_ind\": \"公私标志\",\n" +
                "\t\"contr_id\": \"合同编号\",\n" +
                "\t\"asset_tran_ind\": \"是否资产转让\",\n" +
                "\t\"credit_code\": \"915000002024110502\",\n" +
                "\t\"cust_name\": \"重庆测试技术有限公司\",\n" +
                "\t\"distr_dt\": \"2024-10-11\",\n" +
                "\t\"off_owe_int_bal\": \"表外欠息余额\",\n" +
                "\t\"in_off_bs_cate_cd\": \"表内外类型代码\",\n" +
                "\t\"etl_dt\": \"数据日期\",\n" +
                "\t\"oper_org_name\": \"经办机构名称\",\n" +
                "\t\"cert_cate_cd\": \"证件类型代码\",\n" +
                "\t\"company_id\": \"企业id\",\n" +
                "\t\"wrtoff_ind\": \"1\",\n" +
                "\t\"guarantee_type_name\": \"担保方式\",\n" +
                "\t\"loan_amt\": \"100000\",\n" +
                "\t\"loan_bal\": \"100000\",\n" +
                "\t\"flzp_org_name\": \"管户机构名称\",\n" +
                "\t\"prod_name\": \"贷款产品名称\",\n" +
                "\t\"level5_class_cd\": \"正常\",\n" +
                "\t\"ovdue_bal\": \"10000\",\n" +
                "\t\"flzp_org_id\": \"管户机构号\",\n" +
                "\t\"exec_int_rate\": \"执行利率\",\n" +
                "\t\"matr_dt\": \"2024-10-11\",\n" +
                "\t\"pay_back_type_selfhide\": \"是否隐性回流\",\n" +
                "\t\"ovdue_days\": \"10\",\n" +
                "\t\"cust_id\": \"客户编号\",\n" +
                "\t\"pay_back\": \"是否资金回流\",\n" +
                "\t\"strip_line_cate_cd_new\": \"条线标识\",\n" +
                "\t\"pay_back_type_relate\": \"是否关联方回流\"\n" +
                "}";
    }

}
