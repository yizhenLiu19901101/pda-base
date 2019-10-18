package com.jiaxin.pda.swagger.note;

/**
 * 菜单注释类
 * @author milo
 */
public class MenuNote {
    public static final String INSERT_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"menuName\": \"菜单名称\",\n" +
            "    \"parentMenuId\": \"父菜单ID\",\n" +
            "    \"menuLevel\": \"菜单层级\"\n" +
            "}\n"+
            "```";

    public static final String INSERT_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"menuName\": \"看板\",\n" +
            "    \"parentMenuId\": \"23\",\n" +
            "    \"menuLevel\": 1\n" +
            "}\n" +
            "```";

    public static final String DELETE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"菜单ID\",\n" +
            "    \"reversion\": \"版本\"\n" +
            "}\n"+
            "```";

    public static final String DELETE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"23\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";

    public static final String UPDATE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"菜单ID\",\n" +
            "    \"menuName\": \"看板\",\n" +
            "    \"reversion\": \"版本\"\n" +
            "}\n"+
            "```";

    public static final String UPDATE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"23\",\n" +
            "    \"menuName\": \"小看板\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";

    public static final String QUERY_BY_PAGE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"current\": \"当前页码\",\n" +
            "    \"offset\": \"每页显示记录数\"\n" +
            "}\n"+
            "```";

    public static final String QUERY_BY_PAGE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"current\": 1,\n" +
            "    \"offset\": 20\n" +
            "}\n" +
            "```";
}
