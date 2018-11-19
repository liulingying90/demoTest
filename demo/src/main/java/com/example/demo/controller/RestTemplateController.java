package com.example.demo.controller;

import com.example.demo.response.DownLoadResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class RestTemplateController {

    @RequestMapping("/downLoadFile")
    public DownLoadResponse downLoadFile(HttpServletResponse response, HttpServletRequest request) {
        DownLoadResponse resp=new DownLoadResponse();
        try {
            FileInputStream fis = new FileInputStream(new File("D:/1.txt"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            resp = new DownLoadResponse();
            byte[] bts = new byte[1024];
            int len = -1;
            while ((len = fis.read(bts)) != -1) {
                bos.write(bts, 0, len);
            }
            byte[] fileByte=bos.toByteArray();
            resp.setName("张三");
            resp.setFile(fileByte);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return resp;

    }

}
