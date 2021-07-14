package com.example.demo.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.TestNettyClient
 * 描      述 :
 * 创 建 时 间 : 2021/7/9 16:32
 *
 * @author :  张伟
 */
public class TestNettyClient {

    private String address;
    private int port;

    public TestNettyClient(String address, int port){
        this.address = address;
        this.port = port;
    }

    public void hello() {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());

        try {
            Channel channel = bootstrap.connect(address, port).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                String msg = reader.readLine();
                if (msg == null){
                    continue;
                }
                channel.writeAndFlush(msg + "\r\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringDecoder());
//        pipeline.addLast("handler", new TestNettyClient("localhost", 8080));
    }
}