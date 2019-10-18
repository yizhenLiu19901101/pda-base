package com.jiaxin.pda.swagger.note;

/**
 * 字典注释类
 * @author milo
 */
public class DictionaryNote {
    public static final String TYPE_INSERT_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"typeName\": \"数据项类型名称\"\n" +
            "}\n"+
            "```";

    public static final String TYPE_INSERT_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"typeName\": \"交通\"\n" +
            "}\n" +
            "```";

    public static final String TYPE_UPDATE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"数据项类型ID\",\n" +
            "    \"typeName\": \"数据项类型名称\",\n" +
            "    \"reversion\": \"版本\"\n" +
            "}\n"+
            "```";

    public static final String TYPE_UPDATE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"12\",\n" +
            "    \"typeName\": \"餐饮\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";

    public static final String TYPE_DELETE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"数据项类型ID\",\n" +
            "    \"reversion\": \"版本\"\n" +
            "}\n"+
            "```";

    public static final String TYPE_DELETE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"12\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";

    public static final String TYPE_QUERY_BY_PAGE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"current\": \"页码\",\n" +
            "    \"offset\": \"每页显示记录数\"\n" +
            "}\n"+
            "```";

    public static final String TYPE_QUERY_BY_PAGE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"typeName\": \"交通\"\n" +
            "}\n" +
            "```";

    public static final String ITEM_INSERT_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"itemName\": \"数据项名称\",\n" +
            "    \"orderNo\": \"数据项次序\",\n" +
            "    \"dictionaryTypeId\": \"数据项类型ID\"\n" +
            "}\n"+
            "```";

    public static final String ITEM_INSERT_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"itemName\": \"公交车\",\n" +
            "    \"orderNo\": 1,\n" +
            "    \"dictionaryTypeId\": \"234\"\n" +
            "}\n" +
            "```";
    public static final String ITEM_UPDATE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"数据项ID\",\n" +
            "    \"reversion\": \"版本\",\n" +
            "    \"itemName\": \"数据项名称\",\n" +
            "    \"orderNo\": \"数据项次序\",\n" +
            "    \"dictionaryTypeId\": \"数据项类型ID\"\n" +
            "}\n"+
            "```";

    public static final String ITEM_UPDATE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"12\",\n" +
            "    \"reversion\": 1,\n" +
            "    \"itemName\": \"公交车\",\n" +
            "    \"orderNo\": 1,\n" +
            "    \"dictionaryTypeId\": \"234\"\n" +
            "}\n" +
            "```";
    public static final String ITEM_DELETE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"数据项ID\",\n" +
            "    \"reversion\": \"版本\"\n" +
            "}\n"+
            "```";

    public static final String ITEM_DELETE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"12\",\n" +
            "    \"reversion\": 1\n" +
            "}\n" +
            "```";
    public static final String ITEM_QUERY_BY_PAGE_NOTE = "入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"current\": \"页码\",\n" +
            "    \"offset\": \"每页显示记录数\"\n" +
            "}\n"+
            "```";

    public static final String ITEM_QUERY_BY_PAGE_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"typeName\": \"交通\"\n" +
            "}\n" +
            "```";

}
