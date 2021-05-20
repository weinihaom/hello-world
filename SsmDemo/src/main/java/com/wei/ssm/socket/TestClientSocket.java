package com.wei.ssm.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClientSocket {
    static Socket client = null;

    public static void main(String[] args) {
        try {
            client = new Socket("127.0.0.1", 12345);
            System.out.println("client start send!");
            // 客服端发送测试消息
            Writer writer = new OutputStreamWriter(client.getOutputStream(), "UTF-8");
            writer.write("测试消息！");
            writer.flush();
            client.shutdownOutput();
            Reader reader = new InputStreamReader(client.getInputStream());
            // 接受服务端的返回值
            String res = readerAccept(reader);
            System.out.println("返回消息：" + res);
            reader.close();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
