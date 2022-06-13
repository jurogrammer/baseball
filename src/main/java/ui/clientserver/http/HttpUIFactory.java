package ui.clientserver.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import ui.Interactor;
import ui.clientserver.ClientServerResolver;
import ui.clientserver.ClientServerUIFactory;
import ui.exception.UIException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class HttpUIFactory implements ClientServerUIFactory {
    @Override
    public Interactor createInteractor() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080), 1);
            return new HttpInteractor(serverSocket);
        } catch (IOException e) {
            throw new UIException("server socket 생성에 실패했습니다.", e);
        }

    }

    @Override
    public ClientServerResolver createResolver() {
        return new HttpResolver(new ObjectMapper(), new RequestParser());
    }
}
