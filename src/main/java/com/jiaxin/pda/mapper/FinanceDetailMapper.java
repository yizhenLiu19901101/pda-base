package com.jiaxin.pda.mapper;

import com.jiaxin.pda.entity.dto.FinanceDetailDto;
import com.jiaxin.pda.entity.vo.FinanceDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 财务详情映射接口
 * @author milo
 */
@Mapper
public interface FinanceDetailMapper {
    /**
     * 查询最大的ID
     * @return
     */
    int selectMaxUuid();

    /**
     * 插入财务数据
     * @param financeDetailVo
     * @return
     */
    int insertFinanceDetail(FinanceDetailVo financeDetailVo);

    /**
     * 修改财务数据
     * @param financeDetailVo
     * @return
     */
    int updateFinanceDetail(FinanceDetailVo financeDetailVo);

    /**
     * 删除财务数据
     * @param financeDetailVo
     * @return
     */
    int deleteFinanceDetail(FinanceDetailVo financeDetailVo);

    /**
     * 根据ID查询财务记录
     * @param id
     * @return
     */
    FinanceDetailVo queryFinanceDetailById(String id);

    /**
     * 按照条件分页查询财务对象
     * @param financeDetailDto
     * @return
     */
    List<FinanceDetailVo> queryFinanceDetailByPage(FinanceDetailDto financeDetailDto);

    /**
     * 按照用户ID查询财务明细
     * @param userId
     * @return
     */
    List<FinanceDetailVo> queryFinanceDetailByUserId(@Param("userId") Integer userId);

    /**
     * 按照用户ID查询财务统计数据
     * @param userId
     * @return
     */
    List<FinanceDetailVo> queryFinanceStatisticsByUserId(@Param("userId") Integer userId);

}
