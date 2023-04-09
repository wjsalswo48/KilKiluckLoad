package mz.sixsense.road.service;

import java.io.IOException;

public interface JSONService {
    String JSONRead(String url, String serviceKey, String page, String parseKeyword) throws IOException;
}
