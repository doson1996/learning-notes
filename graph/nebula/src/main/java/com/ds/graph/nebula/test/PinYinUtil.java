package com.ds.graph.nebula.test;

import com.alibaba.fastjson.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author ds
 * @date 2024/11/5
 * @description
 */
public class PinYinUtil {

    public static void main(String[] args) {
        // 工商-基本信息   主营行业(zyhy)
        String str = "曾用名、账户状态异常、逾期客户、是否结算、是否授信、是否建档、管户机构、是否黑名单、是否失信、是否被执行、统一社会信用代码、经营状态、法定代表人、成立日期、公司类型、登记机关、企业地址、纳税人识别号、经营范围、上市板块、股票代码、注册号、核准日期、省份、营业期限、注册资本、纳税人资质、疑似实控人、贷款余额、主营行业、联系电话、是否结算、是否授信、是否建档、管户机构、是否黑名单、是否失信、是否被执行";

        // es工商
//        str = "eid、name、法定代表人、实际控制人、电话、注册资金、地址、成立日期、状态";

        // 工商-股东
        str = "股东名称、股东类型、认缴金额、认缴时间、实缴金额、实缴时间、认缴持股比例";

        // 工商-变更
        str = "变更时间、变更项、变更前、变更后";

        // 工商-年报-基本信息    本年度是否股权转让(gqzr)、是否有投资信息或购买其他公司股权(gsgq)
        str = "统一社会信用代码、经营状体、联系电话、电子邮箱、邮政编码、从业人数、企业通信地址、本年度是否股权转让(gqzr)、是否有投资信息或购买其他公司股权(gsgq)";

        // 工商-年报-网站或网店信息
        str = "类型、名称、网站";

        // 工商-年报-股东
        str = "股东、认缴出资额、认缴出资时间、认缴出资方式、实缴出资额、实缴出资实缴、实缴出资方式";

        // 工商-年报-对外投资信息
        str = "被投资企业名称、统一社会信用代码";

        // 工商-年报-企业资产状况          营业总收入中主营业务收入(ywsr)
        str = "资产总额、所有者权益合计、销售总额、利润总额、营业总收入中主营业务收入(ywsr)、净利润、纳税总额、负债总额";

        // 工商-年报-股权变更
        str = "股东、变更前股权比例、变更后股权比例、股权变更日期";

        // 工商-年报-对外担保
        str = "债权人、债务人、主债权种类、主债权数额、履行债务期限、保证时间、保证方式";

        // 风险-行政处罚
        str = "处罚时间、发布时间、处罚编号、处罚结果、处罚部门、原因、处罚详情";

        // 风险-欠税公告
        str = "欠税项目、法人、纳税人编号";

        // 风险-执行人信息 被执行人
        str = "立案时间、案件编号、执行金额、法院";

        // 风险-开庭公告(详情)
        str = "开庭日期、案件编号、案由、身份、原告、被告、法院/审判庭/法庭(ft)、审判长/主审人/法官(fg)";

        // 风险-法院公告(详情)
//        str = "公告时间、案件编号、公告类型、身份、原告、被告、法院、公告内容";

        // 风险-法院判决(详情)
        str = "案件名称、判决日期、身份、案由、案件编号、案件类型、原告、被告、文书摘要、判决结果";
        str = "案件编号、发布日期、涉案金额、被执行人履行情况(lxqk)、被执行人姓名、执行依据文号、组织机构代码、省份、立案时间、执行法院、作出依据单位、生效法律文书确定的义务(yy)、失信被执行人行为具体情形(qx)";

        String[] split = str.split("、");
        JSONObject jsonObject = new JSONObject();
        for (String s : split) {
            String pinyinInitial = getPinyinInitial(s);
            jsonObject.put(pinyinInitial, s);
            System.out.println(s + " = " + pinyinInitial);
        }

        System.out.println(jsonObject);

    }

    public static String getPinyinInitial(String name) {
        // 创建格式化对象
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //设置声调格式
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 放置输入结果
        StringBuilder result = new StringBuilder();
        // 字符数组
        char[] charArray = name.toCharArray();
        // 遍历字符
        for (char c : charArray) {
            // 中文会被变成拼音首字母，非中文会被直接拼接在结果字符串中
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                String[] pinyinArray = new String[0];
                try {
                    pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                if (pinyinArray != null) {
                    result.append(pinyinArray[0].charAt(0));
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
