package com.ray.jvm.service;

import com.ray.jvm.common.exception.RuntimeException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 描述：sql查询服务类接口
 */
public interface QueryDataService {
    int getCount(String sql);

    Map<String, Object> getData(String sql, int start, int limit);

    void export(String sql, int start, int limit, HttpServletResponse response) throws RuntimeException;
}
