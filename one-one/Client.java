package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 客户端 connect
 */
public class Client {
    public static void main(String[] args) throws IOException {

        String newLine = "client";
        String inTemp = null;

        //指定要连接的服务端的端口号和IP地址
        int port = 3000;
        byte ipAddress[] = {127, 0, 0 , 1};
        //服务端ip地址编码
        InetAddress ip = InetAddress.getByAddress(ipAddress);

        //创建套接字，指定要连接的服务端的IP地址和Port端口号
        Socket socket = new Socket(ip, port);

        //创建三个流，系统输入流BufferedReader systemIn，socket输入流BufferedReader socketIn，socket输出流PrintWriter socketOut;
        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());

        while(!newLine.equals("bye")){
            //从终端输入客户端要发送的信息
            System.out.println("Client:");
            inTemp = systemIn.readLine();
            //通过套接字， 将信息发送给服务端
            socketOut.println(inTemp);
            socketOut.flush();
            //读取服务端发送来的信息
            newLine = socketIn.readLine();
            System.out.println("Server:" + "\n" + newLine);
        }
        //关闭连接
        systemIn.close();
        socketIn.close();
        socketOut.close();
        socket.close();
        System.out.println("连接已关闭");
    }
}
