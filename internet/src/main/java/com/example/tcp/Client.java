package com.example.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("192.168.1.207"), 13377);

        OutputStream os = socket.getOutputStream();
        os.write("李小玉上天啦".getBytes());
        os.flush();
        socket.close();
    }
}
