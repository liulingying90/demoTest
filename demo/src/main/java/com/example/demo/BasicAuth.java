package com.example.demo;

import org.apache.commons.codec.binary.Base64;
import sun.net.www.http.HttpClient;

public class BasicAuth {
    private static final String URL="url";
    private static final String APP_KEY="key";
    private static final String SECRET_KEY="secret";

    /**
     * 构造Basic Auth认证头信息
     * @return
     */
    private String getHeader(){
        String auth=APP_KEY+":"+SECRET_KEY;
        byte[] encodedAuth= Base64.decodeBase64(auth.getBytes());
        String authHeader="Basic"+new String(encodedAuth);
        return authHeader;
    }

    public void HttpPostData(){
 /*       try{
            HttpClient httpClient=new DefaultHttpClient();
        }*/
    }
}
