package com.feicui.edu.news.activity;

import android.os.Bundle;

import com.feicui.edu.news.R;
import com.feicui.edu.news.base.activity.BaseActivity;
import com.feicui.edu.news.base.utils.LogUtils;
import com.feicui.edu.news.base.utils.NetUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* new Thread(){
            @Override
            public void run() {
                InputStream is = null;
                HttpURLConnection conn = null;
                super.run();
                try {
                    conn = (HttpURLConnection) new URL(NetUtils.BASE_PATH +
                            "news_list?ver=1&subid=1&dir=1&nid=1id&stamp=20140321&cnt=20").openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(3000);

                    if (conn.getResponseCode() == 200){
                        is = conn.getInputStream();
                        int x;
                        byte[] datas = new byte[1024];
                        while ((x = is.read(datas)) != -1){
                            LogUtils.i("news", new String(datas, 0, x));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (is != null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null){
                        conn.disconnect();
                    }
                }
            }
        }.start();*/

        /*获取新闻数据DefaultHttpClient和HttpGet*/

        new Thread(){
            @Override
            public void run() {
                super.run();
//                发送请求的客户端对象
                NetUtils.httpGet(NetUtils.BASE_PATH +
                        "news_list?ver="+ NetUtils.VERSION +"&subid=1&dir=1&nid=1id&stamp=20140321&cnt=20");
            }
        }.start();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
