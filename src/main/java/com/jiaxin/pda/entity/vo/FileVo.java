package com.jiaxin.pda.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件对象
 * @author milo
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class FileVo extends OperateVo{
    /**
     * 主键
     */
    private String id;

    /**
     * 用户ID
     */
    private int uuid;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private String filePath;
}
