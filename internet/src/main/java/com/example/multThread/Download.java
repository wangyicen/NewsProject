package com.example.multThread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class Download {

    private static int start = 0;
    private static int end = 0;

    public static void main(String[] args) throws IOException {
//        String path = "http://www.9ku.com/down/68772.htm";
        String path = "http://183.131.55.16/mp3.9ku.com/9kutuiguang.exe";
        HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(3000);
        if (conn.getResponseCode() == 200){
            //获取文件大小
            int size = conn.getContentLength();
//            获取文件的名称
            String fileName = path.substring(path.lastIndexOf("/") + 1);

            File file = new File(fileName);
//            计算每个线程应该的下载量，开始start，结束end
            int block = size % 3 == 0 ? size / 3 : size / 3 + 1;
//            断开连接
            conn.disconnect();
            for (int x = 0; x < 3; x++) {
                start = x * block;
                end = (x + 1) *block - 1;
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection conn = null;
                        try {
                            conn = (HttpURLConnection) new URL(path).openConnection();
                            conn.setRequestMethod("GET");
                            conn.setConnectTimeout(3000);

//                            下载内容的大小范围
                            conn.setRequestProperty("Range", "bytes:" + start + "-" + end);
                            System.out.print("bytes:" + start + "-" + end);
                            if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300){
                                //下载
                                InputStream is = conn.getInputStream();
                                byte[] datas = new byte[1024];
                                int x;
                                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                                //seek函数用于定位从哪里开始
                                raf.seek(start);
                                while((x = is.read(datas))!=-1){
                                    raf.write(datas,0,x);
                                }
                                //断开和服务器的连接
                                conn.disconnect();
                                is.close();
                                raf.close();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                };

                new Thread(runnable).start();


            }



        }



    }

}
