package com.example.demo.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.netty.Client
 * 描      述 :
 * 创 建 时 间 : 2021/8/5 17:52
 *
 * @author :  张伟
 */
@AllArgsConstructor
public class Client {

    private String address;
    private int port;
    private String message;

    public void run(){
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ClientInboundHandler());
//                            socketChannel.pipeline().addLast(new ClientOutboundHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(address, port).sync();
            future.channel().writeAndFlush(Unpooled.copiedBuffer(message.getBytes()));
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        String address = "172.16.23.125";
        int port = 8792;
        String message = "hello,server";
        new Client(address, port, message).run();
    }
}