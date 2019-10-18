package com.jiaxin.pda.swagger.note;

/**
 * 用户注释类
 * @author milo
 */
public class UserNote {
    public static final String REGISTER_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"userName\": \"用户名称\",\n" +
            "    \"password\": \"密码\"\n" +
            "}\n"+
            "```";

    public static final String REGISTER_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"userName\": \"green\",\n" +
            "    \"password\": \"123456\"\n" +
            "}\n" +
            "```";

    public static final String UPDATE_USER_NAME_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"用户ID\",\n" +
            "    \"userName\": \"用户名称\",\n" +
            "    \"reversion\": \"版本\"\n" +
            "}\n"+
            "```";

    public static final String UPDATE_USER_NAME_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"23\",\n" +
            "    \"userName\": \"gray\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";
    public static final String UPDATE_USER_PASSWORD_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"用户ID\",\n" +
            "    \"password\": \"密码\",\n" +
            "    \"reversion\": \"版本\"\n" +
            "}\n"+
            "```";

    public static final String UPDATE_USER_PASSWORD_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"23\",\n" +
            "    \"password\": \"23454\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";

    public static final String DELETE_USER_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"用户ID\",\n" +
            "    \"reversion\": \"版本\"\n" +
            "}\n"+
            "```";

    public static final String DELETE_USER_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"23\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";

    public static final String USER_EXIT_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"userToken\": \"用户token\"\n" +
            "}\n"+
            "```";

    public static final String USER_EXIT_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"userToken\": \"234\"\n" +
            "}\n" +
            "```";
    public static final String QUERY_BY_PAGE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"current\": \"页码\",\n" +
            "    \"offset\": \"每页记录数\"\n" +
            "}\n"+
            "```";

    public static final String QUERY_BY_PAGE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"current\": 1,\n" +
            "    \"offset\": 10\n" +
            "}\n" +
            "```";
    public static final String GIVE_USER_ROLE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"userId\": \"用户ID\",\n" +
            "    \"roleId\": \"角色ID\"\n" +
            "}\n"+
            "```";

    public static final String GIVE_USER_ROLE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"userId\": \"23\",\n" +
            "    \"roleId\": \"344\"\n" +
            "}\n" +
            "```";
}
