package org.itstack.demo.netty.bio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 博客：http://itstack.org
 * 论坛：http://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛获取学习源码｝
 * Create by fuzhengwei on 2019/9/30
 */
public class BioServer extends Thread {

    private ServerSocket serverSocket = null;

    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
        bioServer.start();
    }

    @Override
    public void run() {
        try {
            // 创建一个新的ServerSocket实例
            serverSocket = new ServerSocket();
            // 绑定服务器到指定的端口7397
            serverSocket.bind(new InetSocketAddress(7397));
            // 输出服务器启动成功的消息
            System.out.println("itstack-demo-netty bio server start done. {关注公众号：bugstack虫洞栈 | 欢迎关注&获取源码}");
            // 进入无限循环，等待客户端连接
            while (true) {
                // 接受客户端连接请求
                Socket socket = serverSocket.accept();
                // 创建一个新的BioServerHandler线程来处理客户端请求
                BioServerHandler handler = new BioServerHandler(socket, Charset.forName("utf-8"));
                // 启动处理线程
                handler.start();
            }
        } catch (IOException e) {
            // 捕获并打印IO异常
            e.printStackTrace();
        }
    }
}
