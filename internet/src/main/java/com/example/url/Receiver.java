package com.example.url;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class Receiver {
    public static void main(String[] args) throws IOException {
        //新建一个空的数据包
        byte[] datas = new byte[1024];
        DatagramPacket packet = new DatagramPacket(datas, datas.length);
        //创建套接字
        DatagramSocket socket = new DatagramSocket(18888);
        //接收数据
        socket.receive(packet);
        //打印数据
        System.out.print(new String(packet.getData(), 0, packet.getLength()));
        //关闭资源
        socket.close();
    }
}
