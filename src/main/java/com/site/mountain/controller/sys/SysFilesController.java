package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.SysFiles;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SysFilesService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/file")
@Controller
public class SysFilesController {
    private final static Logger logger = LoggerFactory.getLogger(SysFilesController.class);

    @Autowired
    SysFilesService sysFilesService;

    @Autowired
    ConstantProperties constantProperties;

    @RequestMapping(value = "imgUpload", method = RequestMethod.POST)
    public void imgUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        //获取文件名
        String fileName = file.getOriginalFilename();
        String path = constantProperties.getImgUploadPath();
//        String path = "D:\\workspace\\idea\\mountain\\src\\main\\resources\\upload\\";
        String filePath = path + fileName;
        File saveFile = new File(filePath);
        boolean isCreateSuccess = saveFile.createNewFile();
        if (isCreateSuccess) {
            //写入文件
            file.transferTo(saveFile);
            jsonObject.put("status", 200);
            String coverUrl = constantProperties.getImgUrl() + "/" + fileName;
            jsonObject.put("imgName", fileName);
            jsonObject.put("imgUrl", coverUrl);
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("msg", "文件已存在");
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public void fileUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        //获取文件名
        String fileName = file.getOriginalFilename();
        String path = constantProperties.getFileUploadPath();
        String filePath = path + fileName;
        File saveFile = new File(filePath);
        boolean isCreateSuccess = saveFile.createNewFile();
        if (isCreateSuccess) {
            //写入文件
            file.transferTo(saveFile);
            jsonObject.put("status", 200);
            String coverUrl = constantProperties.getImgUrl() + "/" + fileName;
            jsonObject.put("name", fileName);
            jsonObject.put("url", coverUrl);
            SysFiles sysFiles = getSysFiles(file);
            sysFiles.setPath(fileName);
            Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();
            sysFiles.setCreatePerson(sysUser.getUserId());
            int flag = sysFilesService.insert(sysFiles);
            if (flag == 0) {
                jsonObject.put("status", 501);
                jsonObject.put("msg", "文件上传失败");
            }
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("msg", "文件已存在");
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SysFiles getSysFiles(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        long size = file.getSize();
        SysFiles sysFiles = new SysFiles();
        sysFiles.setFname(fileName);
        sysFiles.setSuffixName(suffix);
        sysFiles.setSize(size);
        return sysFiles;
    }


    @RequestMapping(value = "/queryList")
    @ResponseBody
    public Map<String, Object> queryList(@RequestBody SysFiles sysFiles) {
        PageInfo<SysFiles> pageInfo = sysFilesService.getFileList(sysFiles);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "fileDownLoad", method = RequestMethod.POST)
    public void fileDownLoad(@RequestBody SysFiles sysFiles, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String fileName = sysFiles.getFname();
        String path = constantProperties.getFileUploadPath() + fileName;
        File file = new File(path);

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream;charset=utf-8");
        try {
            if (!file.exists()) {
                logger.info("文件不存在");
                jsonObject.put("code", 20000);
                jsonObject.put("status", 500);
                jsonObject.put("data", "");
                jsonObject.put("msg", "文件不存在");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print(jsonObject.toJSONString());
            }
            response.addHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

            byte[] buffer = new byte[1024];
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject delete(@RequestBody SysFiles sysFiles,HttpServletRequest request,HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        int flag = sysFilesService.delete(sysFiles);
        if(flag==0){
            jsonObject.put("status", 500);
            jsonObject.put("msg", "删除失败");
        }else{
            jsonObject.put("status", 200);
            jsonObject.put("msg", "删除成功");
        }
        return jsonObject;
    }
}
