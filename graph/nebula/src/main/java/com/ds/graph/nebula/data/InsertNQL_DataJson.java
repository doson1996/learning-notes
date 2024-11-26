package com.ds.graph.nebula.data;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2024/11/19
 * @description
 */
public class InsertNQL_DataJson {

    public static final Integer COUNT = 10;

    public static void main(String[] args) {
        // 借据
        String jsonStr = jjb();
        String tableName = "loan_dubil_dtl";
        String comment = "借据表";

        // 黑名单
        jsonStr = hmd();
        tableName = "black_list_detail";
        comment = "黑名单";

        // 账户状态异常
        jsonStr = zhztyc();
        tableName = "account_normal_detail";
        comment = "账户状态异常";

        // 负面舆情
        jsonStr = yq();
        tableName = "negative_news";
        comment = "负面舆情";

        // 历史执行
        jsonStr = lszx();
        tableName = "risk_zhixing_history";
        comment = "历史执行";

        // 历史失信
        jsonStr = lssx();
        tableName = "risk_shixin_history";
        comment = "历史失信";

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
        return "{\"eid\":\"重庆测试技术有限公司915000002024110502\",\"object_key\":\"数据主键\",\"lawlock_rs\":\"异常状态认定原因\",\"lawlock_dt\":\"异常状态开始时间\",\"cust_no\":\"客户号\",\"acct_no\":\"异常的账户号\",\"cust_name\":\"客户名称\"}\n";
    }

    // 黑名单
    private static String hmd() {
        return "{\"eid\":\"重庆测试技术有限公司915000002024110502\",\"object_key\":\"数据主键\",\"confirm_rs\":\"认定原因\",\"cust_no\":\"客户号\",\"confirm_dt\":\"认定时间\",\"cust_name\":\"客户名称\"}\n";
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
