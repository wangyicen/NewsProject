package com.example.multThread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MutDownload {

	static int x = 0;
	static int start = 0;
	static int end = 0;
	public static void main(String[] args) throws Exception{

		//准备地址和要开启多少线程
		final String path = "http://183.131.55.15/mp3.9ku.com/9kutuiguang.exe";
		//第一次连接服务器,为了获取数据的长度
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(3000);
		conn.setRequestMethod("GET");//默认get

		if(conn.getResponseCode() == 200){
			//先获取文件的总大小
			int size = conn.getContentLength();
			//获取要下载的文件名
			String fileName = path.substring(path.lastIndexOf("/") + 1);
			final File file = new File(fileName);
			//切分成三份 1000  334    1200  400
			final int block = size % 3 == 0 ? size / 3 : size / 3 + 1;
			conn.disconnect();
			for(x = 0; x < 3; x++){//0 1 2    0    335
				//计算当前线程应该下载的start 和 end
				//1000  0   335
				start = x * block ;
				end = (x+1) * block -1;
				Runnable r = new Runnable(){
					public void run() {
						try {
							//每个线程每次都要连接服务器
							HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
							conn.setConnectTimeout(3000);
							conn.setRequestMethod("GET");

							//告诉浏览器要下载的数据范围
							//setRequestProperty是在请求头中设置属性
							conn.setRequestProperty("Range", "bytes="+start+"-"+end);
							if(conn.getResponseCode() >= 200 && conn.getResponseCode() < 300){
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
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					};
				};

				new Thread(r).start();
			}
		}

	}

}
