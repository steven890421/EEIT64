package com.example.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetJson {
	
    public String getHttpJson(String url,int comefrom){
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
//           
            connection.connect();
            //请求成功
            if(connection.getResponseCode()==200){
                InputStream is=connection.getInputStream();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                //10MB的缓存
                byte [] buffer=new byte[10485760];
                int len=0;
                while((len=is.read(buffer))!=-1){
                    baos.write(buffer, 0, len);
                }
                String jsonString=baos.toString();
                baos.close();
                is.close();
               
                return jsonString;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        return null;
    }


}

