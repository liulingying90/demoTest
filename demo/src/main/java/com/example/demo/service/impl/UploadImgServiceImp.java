package com.example.demo.service.impl;

import com.example.demo.utils.MyX509TrustManager;
import com.example.demo.utils.TrustAnyHostnameVerifier;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

public class UploadImgServiceImp {

    public static void importForBatchAddDetailsPost(String requestUrl,String apiKey,String apiSecret,String filePath){
        File image=new File(filePath);
        String BOUNDARY="------WebKitFormBoundaryAl9CIOBJ1jfQWTl8";
        String end="\r\n";
        URL url;
        try{
            url=new URL(requestUrl);
            SSLContext sc=SSLContext.getInstance("SSL");
            sc.init(null,new TrustManager[]{new MyX509TrustManager()},new java.security.SecureRandom());
            HttpsURLConnection urlConnection=(HttpsURLConnection)url.openConnection();
            urlConnection.setSSLSocketFactory(sc.getSocketFactory());
            urlConnection.setHostnameVerifier(new TrustAnyHostnameVerifier());
            urlConnection.setUseCaches(false);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Connection","Keep-Alive");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
           // urlConnection.setRequestProperty("Uuser-Agent","Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
            urlConnection.setRequestProperty("Charset","UTF-8");
            urlConnection.setRequestProperty("Content-Type","multipart/form-data;boundary="+"------WebKitFormBoundaryAl9CIOBJ1jfQWTl8");
            urlConnection.connect();

            StringBuilder contentBody1=new StringBuilder();
            StringBuilder contentBody2=new StringBuilder();
            String boundary=BOUNDARY+"\r\n";
            DataOutputStream out=new DataOutputStream(urlConnection.getOutputStream());
            byte[] end_data=("------WebKitFormBoundaryAl9CIOBJ1jfQWTl8--".getBytes());

            //第一部分参数图片
            contentBody1.append(boundary);
            contentBody1.append("Content-Disposition: form-data; name=\"image\"; filename=\"image2.jpg\"" + end);
            contentBody1.append(end);
            out.write(contentBody1.toString().getBytes());

            DataInputStream dis=new DataInputStream(new FileInputStream(image));
            int len=0;
            byte[] bufferOut=new byte[1024];
            while((len=dis.read(bufferOut))!=-1){
                out.write(bufferOut,0,len);
            }
            out.write("\r\n".getBytes());
            dis.close();


            //第二部分参数
            contentBody2.append(boundary);
            contentBody2.append("Content-Disposition: form-data; name=\"api_key\""+ "\r\n");
            contentBody2.append(end);
            contentBody2.append(apiKey+end);
            contentBody2.append(boundary);
            contentBody2.append("Content-Disposition: form-data; name=\"api_secret\""+ "\r\n");
            contentBody2.append(end);
            contentBody2.append(apiSecret+end);
            contentBody2.append(boundary);
            contentBody2.append("Content-Disposition: form-data; name=\"return_portrait\""+ "\r\n");
            contentBody2.append(end);
            contentBody2.append("1"+end);


            out.write(contentBody2.toString().getBytes());
            out.write(end_data);
            out.flush();


            //从服务器获取内容
            InputStream inputStream=urlConnection.getInputStream();
            InputStreamReader  reader=new InputStreamReader(inputStream);
            BufferedReader in=new BufferedReader(reader);
            String strResponse=in.readLine();
            System.out.println("response"+strResponse);


        }catch (Exception e){
         e.printStackTrace();
        }

    }
/*
    public static void main(String args[]){
        String url="https://api.megvii.com/faceid/v3/ocridcard";
        String apiKey="hcbGIc3pfJA3_4ss0pO6T23lEjCwhpff";
        String apiSecret="eT-aC6LR3vQeOMGMj1V2Wf1ipHvF5TpL";
        String filePath="D:/img/image2.jpg";
        importForBatchAddDetailsPost(url,apiKey,apiSecret,filePath);
    }*/
}
