package com.ds.es.chapter01;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * 创建文档
 * 全量更新
 *
 * @author ds
 * @date 2022/3/9 22:47
 */
public class CrcpCompanyDocCreate {

    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = getClient();

        // 创建索引1
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest("enterprise_data_gov");
//        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        IndexRequest request = new IndexRequest();

        //向es里存放数据  必须将数据转换为json格式

//        request.index("enterprise_data_gov").id("重庆测试公司915000002024110501");
        String json = "{\n" +
                "\t\"eid\": \"重庆测试公司915000002024110501\",\n" +
                "\t\"zczj\": \"2000万人民币元\",\n" +
                "\t\"dh\": \"暂无信息\",\n" +
                "\t\"dz\": \"重庆市江北区永平门xxx号\",\n" +
                "\t\"clrq\": \"1996-09-02\",\n" +
                "\t\"name\": \"重庆测试公司\",\n" +
                "\t\"fddbr\": \"张三\",\n" +
                "\t\"sjkzr\": \"李四\",\n" +
                "\t\"zt\": \"在营\"\n" +
                "}";
//        JSONObject jsonObject = JSONUtil.parseObj(json);
//        String jsonString = jsonObject.toJSONString(2);

        request.index("enterprise_data_gov").id("重庆测试技术有限公司915000002024110502");
        json = "{\n" +
                "\t\"enterprise_type\": \"股份有限公司(上市、国有控股)\",\n" +
                "\t\"capital\": \"1000\",\n" +
                "\t\"is_revocate\": \"false\",\n" +
                "\t\"is_listed\": \"true\",\n" +
                "\t\"entid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
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
                "\t\"eid\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
                "\t\"registered_address\": \"重庆市市场监督管理局\",\n" +
                "\t\"object_key\": \"8A80A8D54F2184BC8285A923F98DBE09\",\n" +
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
                "\t\"registered_code\": \"500000000008212\"\n" +
                "}";

        request.source(json, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        //关闭客户端
        client.close();
    }

    private static RestHighLevelClient getClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials("elastic", "Haizhi@2022")
        );

        RestClientBuilder builder = RestClient.builder(new HttpHost("192.168.1.238", 9200))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(builder);
    }

}
