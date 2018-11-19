package com.example.demo;

import com.example.demo.model.EmailTO;
import com.example.demo.model.People;
import com.example.demo.response.DownLoadResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class FaceTest {
    //    public static void main(String[] args) throws Exception{
//        File file=new File("D:/img/image2.jpg");
//        byte[] buff=getBytesFromFile(file);
//        String url="https://api.megvii.com/faceid/v3/ocridcard";
//        HashMap<String,String> map=new HashMap<>();
//        HashMap<String,byte[]> byteMap=new HashMap<>();
//        map.put("api_key","hcbGIc3pfJA3_4ss0pO6T23lEjCwhpff");
//        map.put("api_secret","eT-aC6LR3vQeOMGMj1V2Wf1ipHvF5TpL");
//        map.put("return_portrait","1");
//
//        byteMap.put("image",buff);
//        try{
//            byte[] bacd=post(url,map,byteMap);
//            String str=new String(bacd);
//            System.out.println(str);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    private final static int CONNECT_TIME_OUT=30000;
//    private final static int READ_OUT_TIME=50000;
//    private static String boundaryString=getBoundary();
//    public static byte[] post(String url,HashMap<String,String> map,HashMap<String,byte[]> fileMap) throws Exception{
//        HttpURLConnection conne;
//        URL url1=new URL(url);
//        conne=(HttpURLConnection)url1.openConnection();
//        conne.setDoOutput(true);
//        conne.setUseCaches(false);
//        conne.setRequestMethod("POST");
//        conne.setConnectTimeout(CONNECT_TIME_OUT);
//        conne.setReadTimeout(READ_OUT_TIME);
//        conne.setRequestProperty("accept","*/*");
//        conne.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundaryStrfing);
//        conne.setRequestProperty("connection","Keep-Alive");
//        conne.setRequestProperty("user-agent","Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
//        DataOutputStream obos=new DataOutputStream(conne.getOutputStream());
//        Iterator iter=map.entrySet().iterator();
//        while(iter.hasNext()){
//            Map.Entry<String,String> entry=(Map.Entry)iter.next();
//            String key=entry.getKey();
//            String value=entry.getValue();
//            obos.writeBytes("--"+boundaryString+"\r\n");
//            obos.writeBytes("Content-Disposition: form-data; name=\"" + key+ "\"\r\n");
//            obos.writeBytes("\r\n");
//            obos.writeBytes(value+"\r\n");
//        }
//
//        if(fileMap!=null && fileMap.size()>0){
//            Iterator fileIter=fileMap.entrySet().iterator();
//            while(fileIter.hasNext()){
//                Map.Entry<String,byte[]> fileEntry=(Map.Entry)fileIter.next();
//                obos.writeBytes("--" + boundaryString + "\r\n");
//                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey() + "\"; filename=\"" + encode(" ") + "\"\r\n");
//                obos.writeBytes("\r\n");
//                obos.write(fileEntry.getValue());
//                obos.writeBytes("\r\n");
//            }
//        }
//        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
//        obos.writeBytes("\r\n");
//        obos.flush();
//        obos.close();
//
//        InputStream ins = null;
//        int code = conne.getResponseCode();
//        try{
//            if(code == 200){
//                ins = conne.getInputStream();
//            }else{
//                ins = conne.getErrorStream();
//            }
//        }catch (SSLException e){
//            e.printStackTrace();
//            return new byte[0];
//        }
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] buff = new byte[4096];
//        int len;
//        while((len = ins.read(buff)) != -1){
//            baos.write(buff, 0, len);
//        }
//        byte[] bytes = baos.toByteArray();
//        ins.close();
//        return bytes;
//
//    }
//
//    private static String getBoundary() {
//        StringBuilder sb = new StringBuilder();
//        Random random = new Random();
//        for(int i = 0; i < 32; ++i) {
//            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
//        }
//        return sb.toString();
//    }
//
//    public static byte[] getBytesFromFile(File f) {
//        if (f == null) {
//            return null;
//        }
//        try {
//            FileInputStream stream = new FileInputStream(f);
//            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
//            byte[] b = new byte[1000];
//            int n;
//            while ((n = stream.read(b)) != -1)
//                out.write(b, 0, n);
//            stream.close();
//            out.close();
//            return out.toByteArray();
//        } catch (IOException e) {
//        }
//        return null;
//    }
   // public static void main(String[] args) {
       /* RestTemplate post请求
        OutputStream outputStream=null;
        InputStream inputStream=null;
        try {
        RestTemplate restTemplate=new RestTemplate();
        String url="http://localhost:8080/downLoadFile";
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<DownLoadResponse> response=restTemplate.exchange(url,HttpMethod.POST,new HttpEntity<byte[]>(headers),DownLoadResponse.class);
        DownLoadResponse resp=response.getBody();
        inputStream=new ByteArrayInputStream(resp.getFile());
        File file=new File("D:/2.txt");
        if(!file.exists()){

                file.createNewFile();

        }

        outputStream=new FileOutputStream(file);
        int len=0;
        byte[] buf=new byte[1024];
        while((len=inputStream.read(buf))!=-1){
            outputStream.write(buf,0,len);
        }
        outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/

       //HttpUrlConnection post请求
        /*try {
            URL httpUrl=new URL("http://localhost:8080/downLoadFile");
            HttpURLConnection httpCon=(HttpURLConnection)httpUrl.openConnection();
            httpCon.setRequestMethod("POST");
            httpCon.setConnectTimeout(10000);
            httpCon.setDoOutput(true);
            String data="username=aaa&password=111";
            OutputStream out=httpCon.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();

            int responseCode=httpCon.getResponseCode();
            if(responseCode==200){
                InputStream is=httpCon.getInputStream();
                BufferedReader in=new BufferedReader(new InputStreamReader(is));
                String line;
                String result="";
                while((line=in.readLine())!=null){
                    result=result+line;
                }
                System.out.println("DownLoadResponse:"+result);
                httpCon.getResponseMessage();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
   /*     StringBuilder orderNoStr = new StringBuilder();
        orderNoStr.append("1,2,3,4,5,");
        System.out.println(orderNoStr.substring(0,orderNoStr.length()-1));*/
  /*      List<Long> orderList=new ArrayList<Long>();
        if(null!=orderList && orderList.size()!=0){
            System.out.println("aaaa");
        }
*/


    //}


}
