package main.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POJOMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    private POJOMapper() {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }
}
