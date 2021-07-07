package com.jiaxin.pda.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 数据字典实体类
 * @author milo
 * @since 2021-07-07
 */
@TableName("tb_dictionary")
@Data
public class DictionaryPO {
    /**
     * 主键
     */
    private String id;
    private Integer uuid;
    /**
     * 字典项名称
     */
    private String itemName;
    /**
     * 字典类型ID
     */
    private Integer dictionaryTypeId;
    /**
     * 序号
     */
    private Integer orderNo;
    /**
     * 状态，1代表删除，0代表未删除
     */
    private Integer deleteFlag;
    /**
     * 版本
     */
    private Integer reversion;
    /**
     * 创建人
     */
    private Integer createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改人
     */
    private Integer updatedBy;
    /**
     * 修改时间
     */
    private Date updatedTime;
}
