package com.example.xusen.coolweather.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xusen on 2016/12/18.
 */

public class HttpUtil {
    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try{
                    URL url=new URL(address);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(in);
                    String response;
                    char []cha=new char[1024];
                    int len = isr.read(cha);
                    response=new String(cha,0,len);
//                    byte b[]=new byte[4096];
//                    int len=0;
//                    int temp=0;
//                    while((temp=in.read())!=-1){
//                        b[len]=(byte)temp;
//                        len++;
//                    }
//                    String response=new String(b);

//                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response=new StringBuilder();
//                    String line;
//                    while((line=reader.readLine())!=null){
//                        response.append(line);
//                        Log.d("MainActivity","qqqq:"+response.toString());
//                    }
                    Log.d("MainActivity",response);
                    if(listener!=null){
                        //回调onFinish方法
                        listener.onFinish(response);
                    }
                }catch (Exception e){
                    if(listener!=null){
                        //回调onError()方法
                        listener.onError(e);
                    }
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
