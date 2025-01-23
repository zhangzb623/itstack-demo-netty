package org.itstack.demo.netty.bio.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 微信公众号：bugstack虫洞栈 | 专注原创技术专题案例，以最易学习编程的方式分享知识，让萌新、小白、大牛都能有所收获。目前已完成的专题有；Netty4.x从入门到实战、用Java实现JVM、基于JavaAgent的全链路监控等，其他更多专题还在排兵布阵中。
 * 论坛：http://bugstack.cn
 * Create by 付政委 on @2019
 */
public class BioClient {

    /**
     * 程序的入口点
     *
     * @param args 命令行参数，本例中未使用
     */
    public static void main(String[] args) {
        try {
            // 创建一个Socket连接到本地主机的指定端口
            Socket socket = new Socket("127.0.0.1", 7397);
            // 打印客户端启动成功的信息
            System.out.println("itstack-demo-netty bio client start done. {关注公众号：bugstack虫洞栈 | 欢迎关注&获取源码}");
            // 实例化BioClientHandler进行后续的通信处理
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("utf-8"));
            // 启动BioClientHandler
            bioClientHandler.start();
        } catch (IOException e) {
            // 打印异常信息，通常在实际应用中需要进行更精细的异常处理
            e.printStackTrace();
        }
    }

}
