package com.ds.graph.nebula.data.sj;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2024/11/5
 * @description 股东信息
 */
public class D2GDXX {

    public static List<String> getNql() {
        List<String> result = new ArrayList<>();
        String nql1 = "CREATE tag `shareholders` (`eid` string NOT NULL COMMENT \"企业id\", `name` string NULL COMMENT \"企业名\", `data_json` string NULL COMMENT \"数据\") COMMENT = \"股东信息\";\n";
        String nql2 = "CREATE TAG INDEX `shareholders_eid_name` on `shareholders`(`eid`(50), `name`(50)); \n";
        String nql3 = "INSERT VERTEX shareholders(eid, name, data_json) VALUES \"shareholders1\": (\"重庆测试技术有限公司915000002024110502\", \"重庆测试技术有限公司\",'{\n" +
                "\t\"gdmc\": \"重庆测试集团\",\n" +
                "\t\"rjje\": \"1000.00万元\",\n" +
                "\t\"sjje\": \"500.00万元\",\n" +
                "\t\"gdlx\": \"企业法人\",\n" +
                "\t\"rjsj\": \"2020-10-23\",\n" +
                "\t\"rjcgbl\": \"50%\",\n" +
                "\t\"sjsj\": \"2021-02-19\"\n" +
                "}');";

        String nql4 = "INSERT VERTEX shareholders(eid, name, data_json) VALUES \"shareholders2\": (\"重庆测试技术有限公司915000002024110502\", \"重庆测试技术有限公司\",'{\n" +
                "\t\"gdmc\": \"重庆测试集团子公司1\",\n" +
                "\t\"rjje\": \"200.00万元\",\n" +
                "\t\"sjje\": \"200.00万元\",\n" +
                "\t\"gdlx\": \"企业法人\",\n" +
                "\t\"rjsj\": \"2019-07-21\",\n" +
                "\t\"rjcgbl\": \"20%\",\n" +
                "\t\"sjsj\": \"2019-08-19\"\n" +
                "}')";

        String nql5 = "INSERT VERTEX shareholders(eid, name, data_json) VALUES \"shareholders3\": (\"重庆测试技术有限公司915000002024110502\", \"重庆测试技术有限公司\",'{\n" +
                "\t\"gdmc\": \"重庆测试集团子公司2\",\n" +
                "\t\"rjje\": \"300.00万元\",\n" +
                "\t\"sjje\": \"300.00万元\",\n" +
                "\t\"gdlx\": \"企业法人\",\n" +
                "\t\"rjsj\": \"2018-04-21\",\n" +
                "\t\"rjcgbl\": \"30%\",\n" +
                "\t\"sjsj\": \"2018-06-29\"\n" +
                "}')";

        result.add(nql1);
        result.add(nql2);
        result.add(nql3);
        result.add(nql4);
        result.add(nql5);
        return result;
    }

}
