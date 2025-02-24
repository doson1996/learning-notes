package com.ds.db.td;

/**
 * @author ds
 * @date 2025/1/23
 * @description
 */
public class SQLGen {
    public static void main(String[] args) {
        // 担保链圈全景图
        String str = getads_risk_guarantee_dd();
        // 担保链圈管户机构贷款余额总量
        str = ads_risk_guarantee_org_dd();

        String[] splitStr = str.split("\n");
        for (String s : splitStr) {
            String[] splitFiled = s.split("\t");
            String sql = "`" + splitFiled[0] + "` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '" + splitFiled[1] + "',";
            System.out.println(sql);
        }
    }

    public static String ads_risk_guarantee_org_dd() {
        return "org_name\t管户机构\n" +
                "credit_balance\t贷款余额总量";
    }

    public static String getads_risk_guarantee_dd() {
        return "group_count\t担保圈链群体数量\n" +
                "credit_company_count\t担保圈链授信客户数量\n" +
                "company_count\t授信客户数量\n" +
                "credit_company_pro\t担保圈链授信客户比例\n" +
                "credit_balance\t担保圈链贷款余额总量\n" +
                "balance\t授信客户贷款金额总量\n" +
                "credit_balance_pro\t担保全量贷款余额占比\n" +
                "mutual_company_count\t互保授信企业数\n" +
                "mutual_balance\t互保授信企业贷款总额\n" +
                "circle_company_count\t担保圈授信企业数\n" +
                "circle_balance\t担保圈授信企业贷款总额\n" +
                "one_2_many_company_count\t一对多授信企业数\n" +
                "one_2_many_balance\t一对多授信企业贷款总额\n" +
                "many_2_one_company_count\t多对一授信企业数\n" +
                "many_2_one_balance\t多对一授信企业贷款总额";
    }
}
