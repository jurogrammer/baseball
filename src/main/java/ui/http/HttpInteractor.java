package ui.http;

import ui.Interactor;
import ui.exception.UIException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HttpInteractor implements Interactor {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public HttpInteractor() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean hasRead() {
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String read() {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            byte[] endOfBytes = new byte[]{13, 10, 13, 10};
            byte[] bytes = new byte[10000];
            int i = -1;
            while (true) {
                i++;
                bytes[i] = (byte) inputStream.read();
                if (i < 3) {
                    continue;
                }
                byte[] lastFourBytes = {bytes[i - 3], bytes[i - 2], bytes[i - 1], bytes[i]};
                if (Arrays.equals(endOfBytes, lastFourBytes)) {
                    break;
                }
            }
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UIException(e);
        }
    }

    @Override
    public void write(String content) {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UIException(e);
        }
    }
}
