package com.wei.ssm.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServerSocket {
    private static ServerSocket server;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(12345);
            System.out.println("server is started!");
            while (true) {
                Socket client = server.accept();
                if (client != null) {
                    Reader reader = new InputStreamReader(client.getInputStream());
                    Writer writer = new OutputStreamWriter(client.getOutputStream());
                    String res = readerAccept(reader);
                    System.out.println("server: "+res);
                    writer.write("已收到消息" + res);
                    writer.flush();
                    writer.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String readerAccept(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int a = 0;
        char[] buffer = new char[1024];
        while ((a = reader.read(buffer)) >= 0) {
            sb.append(buffer, 0, a);
        }
        return sb.toString();
    }
}
