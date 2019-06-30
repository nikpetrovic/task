package com.nikpetrovic.task.utils;

import com.nikpetrovic.task.configs.IEXConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class HttpClient {
    private final IEXConfig iexConfig;

    public HttpClient(IEXConfig iexConfig) {
        this.iexConfig = iexConfig;
    }

    public IEXConfig getIexConfig() {
        return iexConfig;
    }

    public String get(String url) {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost(this.getIexConfig().getUrl()).setPath(url).addParameter("token",
                this.getIexConfig().getToken());
        try {
            URI uri = builder.build();
            return Request.Get(uri).execute().returnContent().asString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("Error on calling IEX endpoint.");
        }
    }
}
