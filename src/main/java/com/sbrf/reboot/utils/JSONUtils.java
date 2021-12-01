package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class JSONUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Сериализует экземпляр запроса (Request) в текстовый формат обмена данными JSON
     *
     * @param request экземпляр запроса
     * @return строка, экземпляр Request в формате JSON
     * @throws JsonProcessingException для всех проблем, возникающих при обработке (генерации)
     *                                 содержимого JSON, которые не являются чистыми проблемами ввода-вывода.
     */
    public static String toJSON(Request request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }

    /**
     * Сериализует экземпляр ответа (Response) в текстовый формат обмена данными JSON
     *
     * @param response экземпляр ответа
     * @return строка, экземпляр Response в формате JSON
     * @throws JsonProcessingException для всех проблем, возникающих при обработке (генерации)
     *                                 содержимого JSON, которые не являются чистыми проблемами ввода-вывода.
     */
    public static String toJSON(Response response) throws JsonProcessingException {
        return objectMapper.writeValueAsString(response);
    }

    /**
     * Десериализует входную строку в формате JSON в экземпляр запроса (Request)
     *
     * @param jsonContent содержимое в формате JSON
     * @return экземпляр Request
     * @throws JsonProcessingException для всех проблем, возникающих при обработке (синтаксическом анализе)
     *                                 содержимого JSON, которые не являются чистыми проблемами ввода-вывода.
     */
    public static Request JSONtoRequest(String jsonContent) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonContent);
        return new Request(jsonNode.get("atmNumber").asText());
    }

    /**
     * Десериализует входную строку в формате JSON в экземпляр ответа (Response)
     *
     * @param jsonContent содержимое в формате JSON
     * @return экземпляр Response
     * @throws JsonProcessingException для всех проблем, возникающих при обработке (синтаксическом анализе)
     *                                 содержимого JSON, которые не являются чистыми проблемами ввода-вывода.
     */
    public static Response JSONtoResponse(String jsonContent) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonContent);
        return new Response(jsonNode.get("statusCode").asText());
    }
}
