package com.ds.graph.nebula.gzl;

/**
 * @author ds
 * @date 2025/1/15
 * @description 金租表名
 */
public class JZTable {
    public static void main(String[] args) {
        // 金租表名
        String str = jzTable();

        String[] split = str.split("\n");
        for (String s : split) {
            String[] split1 = s.split("\t");
            System.out.println(split1[0] + "(" + split1[1] + ")");
        }

    }

    private static String jzTable() {
        return "ADMIN_ROLE\t角色表\n" +
                "DES_FIN_DATA\t行内财报基础数据表\n" +
                "DES_FIN_ENTITY\t内部企业信息表\n" +
                "DES_FIN_FACTOR_TRIGGER_LEVEL\t指标触发等级表\n" +
                "DES_FIN_MASTER\t行内财报时间表\n" +
                "DES_FIN_MATTER\t行内财报事项表\n" +
                "DES_LARGE_LEVEL\t大类触发异常等级表\n" +
                "DES_MODEL_FACTOR_TRIGGER_RUE\t行内模型敞口阈值表\n" +
                "DES_MODEL_LARGE_SHOW\t大类展示对应指标\n" +
                "LDES_KODEL_LARGE_TYFT\t大类类型\n" +
                "FIN_DATA\t上市财报数据\n" +
                "FIN_DATA_WIND\t上市财报WIND数据\n" +
                "FIN_ENTITY\t上市主体表\n" +
                "FIN_ENTITY_LEVEL\t主体等级表\n" +
                "FIN_EVENT_SCORE\t主体分类得分表\n" +
                "FIN_FACTOR_RULE\t重点指标触发表\n" +
                "FIN_FACTOR_RULE_SCORE\t重点指标得分表\n" +
                "FIN_FACTOR_TRICCER_LEVEL\t指标触发等级表\n" +
                "FIN_FACTOR_VALUE\t指标值表\n" +
                "FIN_FACTOR_WIND_AVG\t平均值表\n" +
                "FIN_LARGE_LEVEL\t大类初始等级\n" +
                "FIN_MASTER\t财报日期表\n" +
                "FIN_SUBCLASS_LEVEL\t小类等级表\n" +
                "MODEL_EVENT_COUNT_SCORE\t触发数量对应得分表\n" +
                "MODEL_EVENT_SCORE_WEIGHT\t得分权重表\n" +
                "MODEL_FACTOR_COUNT_SCORE\t重点指标得分表\n" +
                "MODEL_FACTOR_DICT\t指标表\n" +
                "MODEL_FACTOR_EXPLAIN_CODE\t指标话术表\n" +
                "MODEL_FACTOR_EXFLAIN_CODE_NEW\t金租指标话术表\n" +
                "MODEL_FACTOR_FIN_DICT\t指标对应科目表\n" +
                "MODEL_FACTOR_FORMULA\t指标公式表\n" +
                "MODEL_FACTOR_LEVEL_RULE\t重点指标阈值表\n" +
                "MODEL_FACTOR_SMALL\t科自过小值表\n" +
                "MODEL_FACTOR_TRICCER_RULE\t指标触发值表\n" +
                "MODEL_INFO\t模型表\n" +
                "MODEL_LARGE_EVENT\t大类表\n" +
                "MODEL_LARGE_FORMULA\t大类公式表\n" +
                "MODEL_LARCE_FARAM_VALUE\t大类公式参数表\n" +
                "MODEL_LARGE_RESULT_VALUE\t大类结果阈值表\n" +
                "MODEL_LEVEL_SCORE_ITEM\t主体等级划分表\n" +
                "MODEL_MASTER\t模型表\n" +
                "MODEL_SUBCLASS_DESC\t小类话木表\n" +
                "MODEL_SUBCLASS_EVENT\t小类表\n" +
                "MODEL_SUBCLASS_FACTOR\t指标小类对应表\n" +
                "MODEL_SUBCLASS_FORMULA\t小类公式表\n" +
                "JZ_DES_FIN_MATTEF\t金租上传财报事项表\n" +
                "JZ_OP_LOG\t金租接口调用记录表\n" +
                "JZ_PRINT_DES_FIN_DATA\t金租上传主体财报基础数据表\n" +
                "JZ_PRINT_DES_FIN_DATA_SPOT\t金租上传主体财报科目数据表\n" +
                "JZ_PRINT_DES_FIN_ENTITY\t金租内部上传企业信息\n" +
                "JZ_PRINT_DES_FIN_MASTER\t金租上传企业财报时间表\n" +
                "JZ_PRINT_DES_FIN_SPOT_MASTER\t金租上传主体财报科目事件表\n" +
                "JZ_SYS_CONFIG\t金租调用方配置表\n" +
                "JZ_TOKEN_RECORD\t金租token记录表\n" +
                "JZ_USER_ANT\t金租用户操作记录表";
    }

}
