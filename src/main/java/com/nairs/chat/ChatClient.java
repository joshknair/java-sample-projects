package com.nairs.chat;

import com.sun.tools.jdeprscan.scan.Scan;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatClient {
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;

    public ChatClient(String serverName, int serverPort) {
        System.out.println("Establishing connection. Please wait ...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            start();
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
        String line = "";
        while (!line.equals(".bye")) {
            try {
                Scanner sc = new Scanner(System.in);
                line = sc.nextLine();
                System.out.println("Sending " + line);
                streamOut.writeUTF(line);
                streamOut.flush();
            } catch (IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
            }
        }
    }

    public void start() throws IOException {
        console = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
    }

    public void stop() {
        try {
            if (console != null) console.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
    }

    public static void main(String args[]) {
        ChatClient client = null;
        if (args.length == 0)
            client = new ChatClient(Constants.DEFAULT_HOST, Constants.DEFAULT_PORT);
        else
            client = new ChatClient(args[0], Integer.parseInt(args[1]));
    }
}