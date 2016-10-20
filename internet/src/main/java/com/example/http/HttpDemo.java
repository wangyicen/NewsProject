package com.example.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class HttpDemo {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setConnectTimeout(3000);

        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200){
            InputStream is = conn.getInputStream();
            byte[] arr = new byte[1024];
            int x;
            while ((x = is.read(arr)) != -1){
                System.out.print(new String(arr, 0, x));
            }
            is.close();
        }else {
            System.out.print("服务器连接失败");
        }

    }
}
