package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class XMLUtils {
    private static final XmlMapper xmlMapper = new XmlMapper();

    /**
     * Сериализует экземпляр запроса (Request) в текстовый формат обмена данными XML
     *
     * @param request экземпляр Request
     * @return строка, экземпляр Request в формате XML
     * @throws JsonProcessingException для всех проблем, возникающих при обработке (генерации)
     *                                 содержимого XML, которые не являются чистыми проблемами ввода-вывода.
     */
    public static String toXML(Request request) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(request);
    }

    /**
     * Сериализует экземпляр ответа (Response) в текстовый формат обмена данными XML
     *
     * @param response экземпляр Response
     * @return строка, экземпляр Response в формате XML
     * @throws JsonProcessingException для всех проблем, возникающих при обработке (генерации)
     *                                 содержимого XML, которые не являются чистыми проблемами ввода-вывода.
     */
    public static String toXML(Response response) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(response);
    }

    /**
     * Десериализует входную строку в формате XML в экземпляр запроса (Request)
     *
     * @param xmlContent строка содержащая экземпляр Request в формате XML
     * @return экземпляр Response
     * @throws JsonProcessingException для всех проблем, возникающих при обработке (синтаксическом анализе)
     *                                 содержимого XML, которые не являются чистыми проблемами ввода-вывода.
     */
    public static Request XMLtoRequest(String xmlContent) throws JsonProcessingException {
        JsonNode jsonNode = xmlMapper.readTree(xmlContent);
        return new Request(jsonNode.get("atmNumber").asText());
    }

    /**
     * Десериализует входную строку в формате XML в экземпляр ответа (Response)
     *
     * @param xmlContent строка содержащая экземпляр Response в формате XML
     * @return экземпляр Response
     * @throws JsonProcessingException для всех проблем, возникающих при обработке (синтаксическом анализе)
     *                                 содержимого XML, которые не являются чистыми проблемами ввода-вывода.
     */
    public static Response XMLtoResponse(String xmlContent) throws JsonProcessingException {
        JsonNode jsonNode = xmlMapper.readTree(xmlContent);
        return new Response(jsonNode.get("statusCode").asText());
    }
}
