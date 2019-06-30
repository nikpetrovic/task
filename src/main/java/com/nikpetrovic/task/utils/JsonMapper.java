package com.nikpetrovic.task.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikpetrovic.task.dto.Logo;
import com.nikpetrovic.task.dto.StockQuote;

import java.io.IOException;

public class JsonMapper {
    private ObjectMapper objectMapper;

    private JsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static JsonMapper getMapper() {
        return new JsonMapper(new ObjectMapper());
    }

    private ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public StockQuote fromJsonToStockQuote(String json) {
        try {
            return this.getObjectMapper().readerFor(StockQuote.class).readValue(json);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Error while trying to convert json to StockQuote");
        }
    }

    public Double fromJsonToDouble(String json) {
        try {
            return this.getObjectMapper().readerFor(Double.class).readValue(json);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Error while trying to convert json to Double");
        }
    }

    public Logo fromJsonToLogo(String json) {
        try {
            return this.getObjectMapper().readerFor(Logo.class).readValue(json);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Error while trying to convert json to Logo");
        }
    }
}