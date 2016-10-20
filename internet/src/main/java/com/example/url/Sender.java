package com.example.url;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) throws IOException {
        //创建数据包
        byte[] datas = "醒过来~~~".getBytes();
        DatagramPacket packet = new DatagramPacket(datas, datas.length,
                InetAddress.getByName("192.168.1.204"),13848);
        DatagramSocket socket = new DatagramSocket();
        //发送数据
        socket.send(packet);

        //关闭资源
        socket.close();
    }
}
