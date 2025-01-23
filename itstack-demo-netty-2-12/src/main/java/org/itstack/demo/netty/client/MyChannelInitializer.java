package org.itstack.demo.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;

import java.nio.charset.Charset;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛获取学习源码｝
 * Create by fuzhengwei on 2019
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //流量整形
        channel.pipeline().addLast(new ChannelTrafficShapingHandler(10, 10));
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringDecoder(Charset.forName("utf-8")));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringEncoder(Charset.forName("utf-8")));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }

}
