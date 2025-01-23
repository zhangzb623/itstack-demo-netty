package org.itstack.demo.netty.bio.client;

import org.itstack.demo.netty.bio.ChannelAdapter;
import org.itstack.demo.netty.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 客户端处理器，继承自ChannelAdapter，用于处理客户端与服务器之间的通信。
 * 在连接建立后发送初始消息，并处理从服务器接收到的消息。
 */
public class BioClientHandler extends ChannelAdapter {

    /**
     * 构造函数，初始化客户端处理器。
     * @param socket  客户端的Socket连接
     * @param charset 字符集，用于编码和解码消息
     */
    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    /**
     * 当通道激活时调用，即与服务器建立连接后。
     * 打印本地地址，并向服务器发送初始消息。
     * @param ctx 通道处理器上下文
     */
    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("链接报告LocalAddress:" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("hi! 我是bugstack虫洞栈 BioClient to msg for you \r\n");
    }

    /**
     * 当从通道读取到数据时调用。
     * 打印接收到的消息，并向服务器发送确认消息。
     * @param ctx 通道处理器上下文
     * @param msg 从服务器接收到的消息
     */
    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        ctx.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }

}
