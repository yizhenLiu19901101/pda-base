package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.vo.FileVo;
import com.jiaxin.pda.mapper.FileMapper;
import com.jiaxin.pda.service.FileService;
import com.jiaxin.pda.util.GenerateUtil;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

/**
 * 字典业务实现类
 * @author milo
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper mapper;
    @Value("${uploadFile.prefix}")
    private String uploadFilePrefix;
    @Override
    public String insertFile(MultipartFile fileVo,int userId) {
        try {
                String wholePath = uploadFilePrefix+File.separator+"images";
                File folder = new File(wholePath);
                if (!folder.exists()){
                    folder.mkdirs();
                }
                if(folder.canWrite()) {
                    String fileName = fileVo.getOriginalFilename();
                    /* 过滤下,只有表单项中类型为图片的才保存 */
                    InputStream inStream = fileVo.getInputStream();
                    byte[] b = new byte[1024];
                    int rb = inStream.read(b);
                    File file = new File(wholePath, fileName);
                    OutputStream outStream = new FileOutputStream(file);
                    while (rb != -1) {
                        outStream.write(b);
                        rb = inStream.read(b);
                    }
                    outStream.flush();
                    inStream.close();
                    outStream.close();
                    //插入文件对象
                    FileVo fileObject = new FileVo();
                    fileObject.setId(GenerateUtil.generateRandomString());
                    fileObject.setUuid(mapper.selectMaxUuid() + Constant.INCREASE_PACE);
                    fileObject.setFilePath(wholePath);
                    fileObject.setFileName(fileName);
                    fileObject.setDeleteFlag(false);
                    fileObject.setReversion(Constant.INIT_REVERSION);
                    fileObject.setCreatedTime(new Date());
                    fileObject.setCreatedBy(userId);
                    fileObject.setUpdatedBy(userId);
                    fileObject.setUpdatedTime(new Date());
                    int result = mapper.insertFile(fileObject);
                    if (Constant.OPERATE_SUCCESS == result) {
                        return wholePath + File.separator + fileName;
                    }
                }else{
                    System.out.println("没有写权限");
                }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            return null;
    }
}
