package com.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端 ServerSocket, bind, accept
 */
public class Server {
    public static void main(String[] args) throws IOException {
        String newLine = "server";
        String inTemp = null;

        String readLine = null;
        String serverout = null;

        //指定服务器端的端口号
         int port = 3000;
        //直接创建服务器套接字
        ServerSocket serverSocket = new ServerSocket(port);

        //accept监听接收连接，并返回一个已连接套接字Socket, 如果没有请求连接，则会阻塞在此处
        Socket socket = serverSocket.accept();
        System.out.println("已建立连接");
        //创建三个流，系统输入流BufferedReader systemIn，socket输入流BufferedReader socketIn，socket输出流PrintWriter socketOut;
        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());

        while(!newLine.equals("bye")){
            //读取客户端传来的数据
            newLine = socketIn.readLine();
            System.out.println("Client:" + "\n" + newLine);
            //服务端发送数据
            System.out.println("Servver:");
            serverout = systemIn.readLine();
            //通过套接字发送给客户端
            socketOut.println(serverout);
            //赶快刷新使Client收到，也可以换成socketOut.println(readline, ture)
            socketOut.flush();
        }
        systemIn.close();
        socketIn.close();
        socketOut.close();
        socket.close();
        serverSocket.close();
        System.out.println("连接已关闭！");
    }
}
