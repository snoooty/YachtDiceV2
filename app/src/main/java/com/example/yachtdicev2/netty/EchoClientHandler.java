package com.example.yachtdicev2.netty;

import android.util.Log;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    String userName;

    public EchoClientHandler(String userName){
        this.userName = userName;
    }
    String TAG = "EchoClientHandler";

    @Override
    public void channelActive(ChannelHandlerContext ctx) { // (1)
        String sendMessage = userName;
//        String sendMessage = "Hellow Netty";

        ByteBuf msgBuffer = Unpooled.buffer();
        msgBuffer.writeBytes(sendMessage.getBytes());

        StringBuilder builder = new StringBuilder();
        builder.append("Client name 전송[");
        builder.append(sendMessage);
        builder.append("]");

        ctx.writeAndFlush(msgBuffer);  // (2) 중요
        Log.e(TAG,"서버로 보낸 메세지 : " + builder.toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (3)
        String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset()); // (4)

        StringBuilder builder = new StringBuilder();
        builder.append("Client 수신한 name[");
        builder.append(readMessage);
        builder.append("]");

        Log.e(TAG,"서버에서 받은 메세지 :" + builder.toString());

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)   { // (5)
        ctx.close(); // (6)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)   {
        cause.printStackTrace();
        ctx.close();
    }

}
