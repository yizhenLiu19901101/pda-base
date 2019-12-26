package com.jiaxin.pda.service;

import com.jiaxin.pda.entity.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件业务接口
 * @author milo
 *
 */
public interface FileService {

    /**
     * 插入字典项
     * @param fileVo 字典对象
     * @param userId 用户ID
     * @return 返回结果
     */
    String insertFile(MultipartFile fileVo,int userId);
}
