package ui.clientserver.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.dto.InferResult;
import ui.Progress;
import ui.clientserver.ClientServerResolver;
import ui.exception.UIException;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HttpResolver implements ClientServerResolver {
    private final ObjectMapper objectMapper;
    private final RequestParser requestParser;

    public HttpResolver(ObjectMapper objectMapper, RequestParser requestParser) {
        this.objectMapper = objectMapper;
        this.requestParser = requestParser;
    }

    @Override
    public Action resolveAction(String httpRequest) {
        Request request = requestParser.parse(httpRequest);
        return Action.valueOf(request.getUrl(), request.getMethod());
    }


    @Override
    public Progress resolveStartOrEnd(String startOrEnd) {
        try {
            Request request = requestParser.parse(startOrEnd);
            String body = request.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            ContinueQuestionRespDTO continueQuestionRespDTO = objectMapper.readValue(body, ContinueQuestionRespDTO.class);
            return continueQuestionRespDTO.getProgress();

        } catch (JsonProcessingException e) {
            throw new UIException(e);
        }
    }

    @Override
    public List<Integer> resolveNumbers(String httpNumbers) {
        String numbers = "";
        try {
            Request parse = requestParser.parse(httpNumbers);
            String body = parse.getBody();
            InferRequest inferRequest = objectMapper.readValue(body, InferRequest.class);

            numbers = inferRequest.getNumbers();
            return Arrays.stream(numbers.split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException ex) {
            throw new UIException("?????? ?????? ???????????? ?????????. ??????: " + numbers);
        } catch (JsonProcessingException e) {
            throw new UIException("??? ??? ?????? ????????? ??????????????????.");
        }
    }

    @Override
    public String toGameMessage(InferResult inferResult) {
        try {
            String message = getResultMessage(inferResult);
            InferResponse inferResponse = new InferResponse(message, inferResult.getVictory());
            String body = objectMapper.writeValueAsString(inferResponse);

            return Response
                    .builder()
                    .ok()
                    .addHeader("Content-Type", "application/json")
                    .body(body)
                    .build()
                    .toString();

        } catch (Exception e) {
            throw new UIException(e);
        }

    }

    private String getResultMessage(InferResult inferResult) {
        if (inferResult.getStrikeCnt() == 0 && inferResult.getBallCnt() == 0) {
            return "??????";
        }
        if (inferResult.getBallCnt() == 0) {
            return String.format("%d ???????????????", inferResult.getStrikeCnt());
        }

        if (inferResult.getStrikeCnt() == 0) {
            return String.format("%d ???", inferResult.getBallCnt());
        }

        return String.format("%d ??????????????? %d ??? ", inferResult.getStrikeCnt(), inferResult.getBallCnt());
    }

    public String toGameMessage(String message) {
        return Response
                .builder()
                .ok()
                .addHeader("Content-Type", "application/json")
                .body(String.format("{\"message\": \"%s\"}", message))
                .build()
                .toString();
    }

    @Override
    public String startMessage() {
        try {
            InputStream indexStream = this.getClass().getResourceAsStream("/http/index.html");
            if (indexStream == null) {
                throw new UIException("index.html ????????? ???????????? ????????????.");
            }
            return Response.builder()
                    .ok()
                    .addHeader("Content-Type", "text/html; charset=utf-8")
                    .body(new String(indexStream.readAllBytes(), StandardCharsets.UTF_8))
                    .build()
                    .toString();
        } catch (Exception e) {
            throw new UIException(e);
        }
    }
}
