package com.zpf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.util.Date;


/**
 * Created by LoseMyself on 2017/2/28.
 */
public class MyServerHanlder extends ChannelHandlerAdapter{

    /*
    channel通道
    活跃的
    客户端主动连接服务端后,通道就会活跃.也就是客户端与服务端建立了通信通道并且可以传送数据
    */
    public void channelActive(ChannelHandlerContext ctx)throws Exception{
        System.out.println(ctx.channel().localAddress().toString()+" channelActive");
    }

    /*
    channel通道
    不活跃的
    当客户端断开服务端后,通道不活跃,即不可传输数据了
    */
    public void channelInactive(ChannelHandlerContext ctx)throws Exception{
        System.out.println(ctx.channel().localAddress().toString()+" channelInactive");
    }

    /*
	 * channelRead
	 *
	 * channel 通道
	 * Read    读
	 *
	 * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据
	 * 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
	 *
	 */
    public void channelIRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        ByteBuf buf = (ByteBuf)msg;
        byte[] msgByte = new byte[buf.readableBytes()];
        buf.readBytes(msgByte);
        System.out.println(new Date()+""+new String(msgByte,"UTF-8"));
    }

    /*
	 * channelReadComplete
	 *
	 * channel  通道
	 * Read     读取
	 * Complete 完成
	 *
	 * 在通道读取完成后会在这个方法里通知，对应可以做刷新操作
	 * ctx.flush()
	 *
	 */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /*
	 * exceptionCaught
	 *
	 * exception	异常
	 * Caught		抓住
	 *
	 * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
	 *
	 */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n"+cause.getMessage());
    }

}
