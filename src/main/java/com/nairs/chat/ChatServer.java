package com.nairs.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    Integer port = Constants.DEFAULT_PORT;

    public ChatServer(Integer port) {
        this.port = port;
    }

    public void start() throws Exception {
        System.out.println("Starting server......");
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Started...listening on port " + port);

        while (true) {
            try (Socket socket = serverSocket.accept()) {
                String clientName = socket.getInetAddress().getCanonicalHostName();
                DataInputStream streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

                boolean done = false;
                while (!done) {
                    String line = streamIn.readUTF();
                    System.out.println("From " + clientName + "::" + line);
                    done = line.equals(".bye");
                }
            }
        }
    }

    public static void main(String args[]) {
        Integer port = Constants.DEFAULT_PORT;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        ChatServer server = new ChatServer(port);
        try {
            server.start();
        } catch (Exception e) {
            System.out.println("Socket failed::" + e);
            e.printStackTrace();
        }
    }
}
