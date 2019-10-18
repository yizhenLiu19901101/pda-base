package com.jiaxin.pda.swagger.note;

/**
 * 财务接口注释类
 * @author milo
 */
public class FinanceDetailNote {
    public static final String DELETE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"财务详情ID\",\n" +
            "    \"reversion\": \"版本\"\n" +
            "}\n"+
            "```";

    public static final String DELETE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"222\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";

    public static final String INSERT_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"itemId\": \"字典项ID\",\n" +
            "    \"note\": \"备注\",\n" +
            "    \"costMoney\": \"花费金额\"\n" +
            "}\n"+
            "```";

    public static final String INSERT_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"itemId\": \"1\",\n" +
            "    \"note\": \"公交费\",\n" +
            "    \"costMoney\": \"1\"\n" +
            "}\n" +
            "```";

    public static final String UPDATE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"财务详情ID\",\n" +
            "    \"itemId\": \"字典项ID\",\n" +
            "    \"note\": \"备注\",\n" +
            "    \"costMoney\": \"花费金额\"\n" +
            "}\n"+
            "```";

    public static final String UPDATE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"112\",\n" +
            "    \"itemId\": \"1\",\n" +
            "    \"note\": \"公交费\",\n" +
            "    \"costMoney\": \"1\"\n" +
            "}\n" +
            "```";

    public static final String QUERY_BY_PAGE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"peopleId\": \"人员ID\",\n" +
            "    \"current\": \"页码\",\n" +
            "    \"offset\": \"页数\"\n" +
            "}\n"+
            "```";

    public static final String QUERY_BY_PAGE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"peopleId\": \"1\",\n" +
            "    \"current\": \"1\",\n" +
            "    \"offset\": \"10\"\n" +
            "}\n" +
            "```";
}
