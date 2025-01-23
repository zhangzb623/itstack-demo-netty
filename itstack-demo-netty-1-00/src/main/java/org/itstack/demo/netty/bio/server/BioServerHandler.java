package org.itstack.demo.netty.bio.server;

import org.itstack.demo.netty.bio.ChannelAdapter;
import org.itstack.demo.netty.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 微信公众号：bugstack虫洞栈 | 专注原创技术专题案例，以最易学习编程的方式分享知识，让萌新、小白、大牛都能有所收获。目前已完成的专题有；Netty4.x从入门到实战、用Java实现JVM、基于JavaAgent的全链路监控等，其他更多专题还在排兵布阵中。
 * 论坛：http://bugstack.cn
 * Create by 付政委 on @2019
 */
public class BioServerHandler extends ChannelAdapter {

    /**
     * BioServerHandler类的构造函数
     *
     * @param socket 用于通信的套接字对象，代表了一个客户端与服务器之间的连接
     * @param charset 字符集，用于数据的编码和解码，确保数据在传输过程中的正确性
     */
    public BioServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    /**
     * 当通道激活时触发此方法
     * 该方法是处理连接建立时的逻辑
     *
     * @param ctx 通道处理器上下文，提供了访问通道、写入数据等功能
     */
    @Override
    public void channelActive(ChannelHandler ctx) {
        // 打印本地地址信息，用于日志记录和调试
        System.out.println("链接报告LocalAddress:" + ctx.socket().getLocalAddress());

        // 向客户端发送欢迎消息
        // 这里是业务逻辑的一部分，用于在建立连接后向客户端发送一条欢迎信息
        ctx.writeAndFlush("hi! 我是bugstack虫洞栈 BioServer to msg for you \r\n");
    }

    /**
     * 当通道读取到数据时，此方法被调用
     * 它接收上下文和消息作为参数，并在控制台打印接收到的消息
     * 之后，它向消息的发送方回复一个确认消息
     *
     * @param ctx 通道上下文，提供了与通道进行交互的能力
     * @param msg 接收到的消息对象
     */
    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        // 打印接收到消息的时间和内容
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        // 向发送方回复确认消息
        ctx.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }

}
