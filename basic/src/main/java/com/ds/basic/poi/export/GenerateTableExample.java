package com.ds.basic.poi.export;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Table;

/**
 * @author ds
 * @date 2025/5/14
 * @description
 */
public class GenerateTableExample {
    public static void main(String[] args) throws Exception {
        // 创建一个新的文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 开始插入表格
        Table table = builder.startTable();


        // 插入主表头行
        insertMainHeaderRow(builder);

        // 插入子表头行
        insertSubHeaderRow(builder);

        // 插入数据行
        insertDataRows(builder);

        // 结束表格
        builder.endTable();

        // 保存文档
        doc.save("output/ComplexTableExample.docx");
    }

    private static void insertMainHeaderRow(DocumentBuilder builder) {
        // 插入主表头行
        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("年度");

        builder.insertCell();
        builder.getCellFormat().setWidth(140.0);
        builder.write("2021年");

        builder.insertCell();
        builder.getCellFormat().setWidth(140.0);
        builder.write("2022年");

        builder.insertCell();
        builder.getCellFormat().setWidth(140.0);
        builder.write("2023年");

        builder.insertCell();
        builder.getCellFormat().setWidth(140.0);
        builder.write("2024年9月");

        builder.insertCell();
        builder.getCellFormat().setWidth(100.0);
        builder.write("科目附注");

        builder.endRow();
    }

    private static void insertSubHeaderRow(DocumentBuilder builder) {
        // 插入子表头行
        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("");

        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("合并");

        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("本部");

        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("合并");

        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("本部");

        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("合并");

        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("本部");

        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("合并");

        builder.insertCell();
        builder.getCellFormat().setWidth(70.0);
        builder.write("本部");

        builder.insertCell();
        builder.getCellFormat().setWidth(100.0);
        builder.write("");

        builder.endRow();
    }

    private static void insertDataRows(DocumentBuilder builder) {
        String[][] data = {
                {"项目", "1372154", "1080201", "1495170", "1318335", "1511503", "1303398", "1535585", "1364072", ""},
                {"总资产", "235842", "312747", "242810", "445157", "183647", "372302", "127965", "214521", ""},
                {"流动资产合计", "15520", "9510", "67973", "62083", "28908", "25199", "26045", "24137", "23年末受限货币资金：16933万元为美元债保证金，2400万元为定期存款已质押，23万元为信用保证金。"},
                {"货币资金", "0", "0", "0", "0", "0", "0", "0", "0", ""},
                {"交易性金融资产", "0", "0", "0", "0", "50", "0", "0", "0", ""},
                {"应收票据", "0", "0", "0", "0", "0", "0", "0", "0", ""},
                {"应收账款", "8835", "1361", "10138", "1614", "9856", "2141", "11868", "4461", "陕西化建工程750万元（工程款），西安未央城市建设公司509万元、延安干部培训学院南泥湾分院222万元、黄陵县外事办公室209万元、延安市接待处150万元（餐费及住宿费）。"},
                {"其他应收款", "8929", "269872", "43566", "348457", "28992", "313887", "18797", "166053", "中铁建金融租赁4292万元、平安国际租赁1250万元、上实融资租赁1050万元、基石京信（天津）融资租赁950万元，（金融租赁保证金）延安城投1254万元（往来款）。下降幅度较大主要为往来款收回。"},
                {"坏账准备", "318", "69", "147", "39", "157", "80", "/", "/", ""},
                {"预付账款", "83906", "29310", "80125", "29313", "70931", "28055", "26488", "16141", "陕西建工第二建设42825万元、延安西北局革命旧址5112万元、陕西建工第十三建设1697万元（工程款）、陕西黄陵国家森林公园4912万元土地征迁款、延安市建龙工程材料2478万元（材料款）。"},
                {"存货", "39024", "219", "33599", "164", "37442", "146", "36619", "142", "原材料204万元、库存商品4320万元、低值易耗品429万元、工程施工3979万元、开发产品98509万元。"},
                {"1年内到期非流动资产", "", "", "", "", "", "", "", "", ""}
        };

        for (String[] row : data) {
            builder.insertCell();
            builder.write(row[0]);

            for (int i = 1; i < row.length; i++) {
                builder.insertCell();
                builder.write(row[i]);
            }

            builder.endRow();
        }
    }
}
