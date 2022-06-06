package ui.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.dto.InferResult;
import ui.Resolver;
import ui.exception.UIException;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HttpResolver {

    public Action resolveAction(String httpRequest) {
        RequestParser requestParser = getRequestParser();
        Request request = requestParser.parse(httpRequest);
        return Action.valueOf(request.getUrl(), request.getMethod());
    }


    public Resolver.Progress resolveStartOrEnd(String startOrEnd) {
        try {
            Request request = new RequestParser().parse(startOrEnd);
            String body = request.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            ContinueQuestionRespDTO continueQuestionRespDTO = objectMapper.readValue(body, ContinueQuestionRespDTO.class);
            return continueQuestionRespDTO.getProgress();

        } catch (JsonProcessingException e) {
            throw new UIException(e);
        }
    }

    public List<Integer> resolveNumbers(String httpNumbers) {
        String numbers = "";
        try {
            Request parse = new RequestParser().parse(httpNumbers);
            String body = parse.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            InferRequest inferRequest = objectMapper.readValue(body, InferRequest.class);

            numbers = inferRequest.getNumbers();
            return Arrays.stream(numbers.split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException ex) {
            throw new UIException("입력 값은 숫자여야 합니다. 입력: " + numbers);
        } catch (JsonProcessingException e) {
            throw new UIException("알 수 없는 에러가 발생했습니다.");
        }
    }

    public String toGameMessage(InferResult inferResult) {
        try {
            String message = getResultMessage(inferResult);
            InferResponse inferResponse = new InferResponse(message, inferResult.getVictory());
            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writeValueAsString(inferResponse);

            return new ResponseBuilder()
                    .ok()
                    .addHeader("Content-Type", "application/json")
                    .body(body)
                    .build();
        } catch (Exception e) {
            throw new UIException(e);
        }

    }

    private String getResultMessage(InferResult inferResult) {
        if (inferResult.getStrikeCnt() == 0 && inferResult.getBallCnt() == 0) {
            return "낫싱";
        }
        if (inferResult.getBallCnt() == 0) {
            return String.format("%d 스트라이크", inferResult.getStrikeCnt());
        }

        if (inferResult.getStrikeCnt() == 0) {
            return String.format("%d 볼", inferResult.getBallCnt());
        }

        return String.format("%d 스트라이크 %d 볼 ", inferResult.getStrikeCnt(), inferResult.getBallCnt());
    }

    public String toGameMessage(String message) {
        return new ResponseBuilder()
                .ok()
                .addHeader("Content-Type", "application/json")
                .body(String.format("{\"message\": \"%s\"}", message))
                .build();
    }

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

    private RequestParser getRequestParser() {
        return new RequestParser();
    }

}
