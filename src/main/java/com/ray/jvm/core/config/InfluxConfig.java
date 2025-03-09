package com.ray.jvm.core.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class InfluxConfig {
    private String url;
    private String token;
    private String org;
    private String bucket;

    @Bean
    public InfluxDBClient influxClient() {
        return InfluxDBClientFactory.create(url, token.toCharArray(), org,bucket);
    }

}
