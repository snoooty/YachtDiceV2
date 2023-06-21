package com.example.yachtdicev2.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoSendDataHandler extends ChannelInboundHandlerAdapter {

    String TAG = "EchoSendDataHandler";

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        String sendData = "";

        ByteBuf msgBuffer = Unpooled.buffer();
        msgBuffer.writeBytes(sendData.getBytes());

    }
}
