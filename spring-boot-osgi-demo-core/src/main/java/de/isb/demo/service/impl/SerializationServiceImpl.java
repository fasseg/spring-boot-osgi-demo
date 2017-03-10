package de.isb.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.isb.demo.model.error.SerializationException;
import de.isb.demo.service.api.SerializationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class SerializationServiceImpl implements SerializationService {

    final ObjectMapper mapper = new ObjectMapper();

    @Value("${demo.application.json.pretty}")
    private boolean jsonPrettyPrint;

    @PostConstruct
    public void init() {
        if (jsonPrettyPrint) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
    }

    @Override
    public String toJson(Object obj) throws SerializationException {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void toJson(Object obj, OutputStream sink) throws SerializationException {
        try {
            mapper.writeValue(sink, obj);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public <T> T fromJson(InputStream src, Class<T> clazz) throws SerializationException {
        try {
            return mapper.readValue(src, clazz);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) throws SerializationException {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
