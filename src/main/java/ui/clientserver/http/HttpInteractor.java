package ui.clientserver.http;

import ui.Interactor;
import ui.exception.UIException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean hasRead() {
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

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
                if (!Arrays.equals(endOfBytes, lastFourBytes)) {
                    continue;
                }

                int contentLength = getContentLength(bytes);
                for (int contentIdx = 0; contentIdx < contentLength; contentIdx++) {
                    i++;
                    bytes[i] = (byte) inputStream.read();
                }
                break;
            }

            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UIException(e);
        }
    }

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

    private int getContentLength(byte[] bytes) {
        byte[] contentLengthByte = new byte[]{67, 111, 110, 116, 101, 110, 116, 45, 76, 101, 110, 103, 116, 104, 58, 32};

        for (int startIdx = 0; startIdx < bytes.length; startIdx++) {
            if (!isMatch(bytes, contentLengthByte, startIdx)) {
                continue;
            }

            int numberIdx = startIdx + contentLengthByte.length;
            List<Byte> byteList = new ArrayList<>();
            while (bytes[numberIdx] != 13) {
                byteList.add(bytes[numberIdx]);
                numberIdx++;
            }
            byte[] numBytes = new byte[byteList.size()];
            for (int i = 0; i < byteList.size(); i++) {
                numBytes[i] = byteList.get(i);
            }
            String stringNumber = new String(numBytes, StandardCharsets.UTF_8);
            return Integer.parseInt(stringNumber);
        }

        return 0;
    }

    private boolean isMatch(byte[] bytes, byte[] contentLengthByte, int startIdx) {
        int curIdx = startIdx;
        for (byte b : contentLengthByte) {
            if (curIdx >= bytes.length) {
                return false;
            }

            if (bytes[curIdx] != b) {
                return false;
            }
            curIdx++;
        }
        return true;
    }
}
