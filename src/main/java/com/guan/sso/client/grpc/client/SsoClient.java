package com.guan.sso.client.grpc.client;

import com.guan.sso.client.grpc.stub.SsoServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

public class SsoClient {

    private Channel channel;

    public SsoClient(String address, int port) {
        channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
    }

    public SsoServiceGrpc.SsoServiceBlockingStub getSsoServiceBlockingStub() {
        return SsoServiceGrpc.newBlockingStub(channel);
    }

}
