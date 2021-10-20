package com.example.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.netty.ServerInboundHandler
 * 描      述 :
 * 创 建 时 间 : 2021/8/5 17:52
 *
 * @author :  张伟
 */
public class ServerInboundHandler extends ChannelInboundHandlerAdapter {


    private CopyOnWriteArraySet<ChannelHandlerContext> set = new CopyOnWriteArraySet();
    private ThreadLocal<ChannelHandlerContext> threadLocal = new ThreadLocal<>();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        System.out.println("服务端接收的消息：" + Utils.convertObject(msg));
//        ctx.flush();
//        ctx.write("in bound handler send");//传递到下一个handler
        ctx.write(Utils.convertToByteBuf("in bound handler send"));//传递到下一个handler
        ctx.writeAndFlush(Utils.convertToByteBuf("服务端收到"));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        set.add(ctx);
        threadLocal.set(ctx);
        ctx.writeAndFlush("收到".getBytes());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        set.remove(ctx);
        threadLocal.remove();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


}