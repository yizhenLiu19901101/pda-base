package com.jiaxin.pda.swagger.note;

/**
 * 角色注释类
 * @author milo
 */
public class RoleNote {
    public static final String INSERT_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"roleName\": \"角色名称\",\n" +
            "}\n"+
            "```";

    public static final String INSERT_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"menuName\": \"超级管理员\",\n" +
            "}\n" +
            "```";

    public static final String UPDATE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"角色ID\",\n" +
            "    \"roleName\": \"角色名称\",\n" +
            "    \"reversion\": \"角色版本\"\n" +
            "}\n"+
            "```";

    public static final String UPDATE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"23\",\n" +
            "    \"roleName\": \"模块管理员\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";

    public static final String DELETE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"角色ID\",\n" +
            "    \"reversion\": \"角色版本\"\n" +
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
    public static final String QUERY_BY_PAGE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"roleName\": \"角色名称\",\n" +
            "    \"current\": \"页码\",\n" +
            "    \"offset\": \"每页显示记录数\"\n" +
            "}\n"+
            "```";

    public static final String QUERY_BY_PAGE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"roleName\": \"超级管理员\",\n" +
            "    \"current\": 1,\n" +
            "    \"offset\": 10\n" +
            "}\n" +
            "```";
    public static final String GIVE_PRIVILEGE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"roleId\": \"角色ID\",\n" +
            "    \"menuID\": \"菜单ID\"\n" +
            "}\n"+
            "```";

    public static final String GIVE_PRIVILEGE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"roleId\": \"1\",\n" +
            "    \"menuID\": \"2\"\n" +
            "}\n" +
            "```";
}
