package com.example.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(25559);
        Socket socket = ss.accept();

        InputStream is = socket.getInputStream();
        byte[] arr = new byte[1024];
        int x;
        while ((x = is.read(arr)) != -1){
            System.out.print(new String(arr, 0, x));
        }

        ss.close();
    }
}
