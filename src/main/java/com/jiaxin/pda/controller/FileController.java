package com.jiaxin.pda.controller;

import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.entity.vo.UserVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.FileService;
import com.jiaxin.pda.service.UserService;
import com.jiaxin.pda.util.JWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/**
 * 文件控制器类
 * @author milo
 */
@RestController
@Api(value = "file",tags = {"file_controller"})
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @PutMapping("/uploadImage")
    public GeneralVo uploadImage(@RequestParam("file") MultipartFile file, @RequestHeader("token") String token) {
        String id = JWT.unsign(token,String.class);
        UserVo userVo = userService.findUserById(id);
        try{
            String result = fileService.insertFile(file,userVo.getUserId());
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,result);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }
}
