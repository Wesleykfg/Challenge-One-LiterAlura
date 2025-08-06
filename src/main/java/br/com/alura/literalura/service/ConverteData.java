package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteData implements IConverteData {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> className) {
        try{
            return mapper.readValue(json, className);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
