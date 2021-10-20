package com.example.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.netty.Utils
 * 描      述 :
 * 创 建 时 间 : 2021/8/6 11:48
 *
 * @author :  张伟
 */
public class Utils {

    public static String convertObject(Object msg){
        ByteBuf buf = (ByteBuf) msg;
//        String result = buf.toString(CharsetUtil.UTF_8);
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        return new String(data).trim();
    }

//    public static ByteBuf convertString(String msg){
//        return Unpooled.wrappedBuffer(msg.getBytes());
//    }

    public static ByteBuf convertToByteBuf(String msg){
//        ByteBuf buf = (ByteBuf) msg;
//        ByteBuf res = Unpooled.wrappedBuffer(new String("塔台收到!塔台收到!信息如下, 请确认 " + buf.toString(CharsetUtil.UTF_8)).getBytes());
        ByteBuf res = Unpooled.wrappedBuffer(new String(msg).getBytes());
        return res;
    }
}