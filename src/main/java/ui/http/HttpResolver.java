package ui.http;

import game.dto.InferResult;
import ui.Resolver;
import ui.exception.UIException;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HttpResolver implements Resolver {

    @Override
    public Progress resolveStartOrEnd(String startOrEnd) {
        return null;
    }

    @Override
    public List<Integer> resolveNumbers(String numbers) {
        return null;
    }

    @Override
    public String toGameMessage(InferResult inferResult) {
        return null;
    }

    @Override
    public String toGameMessage(String message) {
        return null;
    }

    @Override
    public String startMessage() {
        try {
            ResponseBuilder responseBuilder = new ResponseBuilder();
            InputStream indexStream = this.getClass().getResourceAsStream("/http/index.html");
            if (indexStream == null) {
                throw new UIException("index.html 파일이 존재하지 않습니다.");
            }
            return responseBuilder.ok()
                    .addHeader("Content-Type", "text/html; charset=utf-8")
                    .body(new String(indexStream.readAllBytes(), StandardCharsets.UTF_8))
                    .build();
        } catch (Exception e) {
            throw new UIException(e);
        }
    }

    @Override
    public String victoryMessage() {
        return null;
    }
}
