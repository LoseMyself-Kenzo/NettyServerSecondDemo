package com.zpf;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
/**
 * Created by LoseMyself on 2017/2/28.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel e) throws Exception {

        System.out.println("报告");
        System.out.println("信息:有一客户端连接到本服务端");
        System.out.println("IP:"+e.localAddress().getHostName());
        System.out.println("Port:"+e.localAddress().getPort());
        System.out.println("报告完毕");

        // 在管道中添加接受数据的方法
        e.pipeline().addLast(new MyServerHanlder());
    }
}
