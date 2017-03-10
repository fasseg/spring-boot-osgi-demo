package de.isb.demo.service.api;

import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationService {
    String toJson(Object obj);
    void toJson(Object obj, OutputStream sink);
    <T> T fromJson(InputStream src, Class<T> clazz);
    <T> T fromJson(String json, Class<T> clazz);
}
