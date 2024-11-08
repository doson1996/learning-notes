package com.ds.graph.nebula.data.sj;

import com.ds.graph.nebula.data.NebulaRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2024/11/5
 * @description 工商基本信息
 */
public class D1GSJBXX {

    public static List<String> getNql() {
        List<String> result = new ArrayList<>();
        String nql1 = "CREATE tag `enterprise_data_gov` (`eid` string NOT NULL COMMENT \"企业id\", `name` string NULL COMMENT \"企业名\", `data_json` string NULL COMMENT \"数据\") COMMENT = \"企业基本信息\";\n";
        String nql2 = "CREATE TAG INDEX `enterprise_data_gov_eid_name` on `enterprise_data_gov`(`eid`(50), `name`(50)); \n";
        String nql3 = "INSERT VERTEX enterprise_data_gov(eid, name, data_json) VALUES \"enterprise_data_gov1\": (\"重庆测试技术有限公司915000002024110502\", \"重庆测试技术有限公司\",'{\n" +
                "\t\"cym\": \"重庆技术测试有限公司\",\n" +
                "\t\"zhztyc\": \"false\",\n" +
                "\t\"sfsx\": \"true\",\n" +
                "\t\"sfjd\": \"true\",\n" +
                "\t\"yqkh\": \"true\",\n" +
                "\t\"sfhmd\": \"true\",\n" +
                "\t\"sfjs\": \"true\",\n" +
                "\t\"sfbzx\": \"true\",\n" +
                "\t\"gpdm\": \"601962\",\n" +
                "\t\"clrq\": \"2002-07-02\",\n" +
                "\t\"gslx\": \"股份有限公司(上市、国有控股)\",\n" +
                "\t\"nsrzz\": \"增值税一般纳税人\",\n" +
                "\t\"qydz\": \"重庆市江北区永平门xxx号\",\n" +
                "\t\"jyfw\": \"许可项目：吸收公众存款；发放短期、中期和长期贷款；办理国内结算；办理票据承兑贴现；发行金融债券；代理发行、代理兑付、承销政府债券；买卖政府债券；从事同业拆借；提供信用证服务及担保；代理收付款项及代办保险业务；提供保管箱业务；信贷资产转让业务；办理地方财政周转金的委托贷款业务。外汇存款；外汇贷款；外币兑换；国际结算；结汇、售汇；同业外汇拆借；自营和代客买卖外汇；普通类衍生产品交易；买卖除股票以外的外币有价证券；资信调查、咨询、见证业务；开办信用卡业务；证券投资基金销售业务；办理帐务查询、网上转帐、代理业务、贷款业务、集团客户管理、理财服务、电子商务、客户服务、公共信息等网上银行业务；经中国银行业监督管理委员会批准的其他业务（依法须经批准的项目，经相关部门批准后方可开展经营活动，具体经营项目以相关部门批准文件或许可证件为准）\",\n" +
                "\t\"sf\": \"重庆\",\n" +
                "\t\"zch\": \"500000000008212\",\n" +
                "\t\"ssbk\": \"主板\",\n" +
                "\t\"ysskr\": \"李四\",\n" +
                "\t\"hzrq\": \"2023-02-23\",\n" +
                "\t\"lxdh\": \"13012345678\",\n" +
                "\t\"zczb\": \"1.000.00万人民币元\",\n" +
                "\t\"djjg\": \"重庆市市场监督管理局\",\n" +
                "\t\"tyshxydm\": \"915000002024110502\",\n" +
                "\t\"nsrsbh\": \"915000002024110502\",\n" +
                "\t\"zyhy\": \"互联网信息服务\",\n" +
                "\t\"fddbr\": \"张三\",\n" +
                "\t\"jyzt\": \"在营（开业）\",\n" +
                "\t\"dkye\": \"1000000\",\n" +
                "    \"dkje\": \"1000000\",\n" +
                "    \"hnhyhf\": \"金融业\",\n" +
                "    \"sjkzr\": \"李四\",\n" +
                "\t\"yyqx\": \"2002-07-02至长期\",\n" +
                "\t\"utime\": \"2024-11-05 22:06:55\"\n" +
                "}');";

        result.add(nql1);
        result.add(nql2);
        result.add(nql3);
        return result;
    }

}
