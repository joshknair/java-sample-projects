package com.nairs.chat;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

class SocketDemo {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("joshnair.com", 80);

        InputStream is = s.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        System.out.println(dis.readInt());

        s.close();
    }
}