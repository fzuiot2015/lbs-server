package edu.fzu.lbs.controller;

import edu.fzu.lbs.config.exception.ResultEnum;
import edu.fzu.lbs.entity.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${lbs.upload-path}")
    private String webUploadPath;

    /**
     * 基于用户标识的头像上传
     *
     * @param file 图片
     * @return
     */
    @PostMapping(value = "/upload")
    public ResultDTO<String> fileUpload(@RequestParam MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResultDTO.error(ResultEnum.FORMAT_ERROR);
        }

/*
        if (!file.getContentType().contains("image")) {
            return ResultDTO.error(ResultEnum.FORMAT_ERROR);
        }
*/

/*        // 获取图片的文件名
        String fileName = file.getOriginalFilename();
        // 获取图片的扩展名
        String extensionName = fileName.substring(fileName.lastIndexOf('.'));*/

        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        String newFileName = System.currentTimeMillis() + ".jpg";

        String path = webUploadPath + newFileName;

        File dest = new File(path);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        // 上传到指定目录
        file.transferTo(dest);
        return new ResultDTO<>(newFileName);
    }

}
