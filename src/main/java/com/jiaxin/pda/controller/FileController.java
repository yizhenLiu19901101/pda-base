package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.FileService;
import com.jiaxin.pda.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件控制器类
 * @author milo
 */
@RestController
@Api(value = "file",tags = {"file_controller"})
@RequestMapping("/file")
@Slf4j
public class FileController extends BaseController{

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @PostMapping("/uploadImage")
    public GeneralVo uploadImage(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file, @RequestHeader("token") String token) {
        try{
            log.info("上传图片");
            if(file.getSize() > Constant.TEN_MB){
                return new GeneralVo(ErrorListEnum.PIC_TOO_BIG,null);
            }else{
                String result = fileService.insertFile(file,getCurrentUserId(request,response));
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,result);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }
}
