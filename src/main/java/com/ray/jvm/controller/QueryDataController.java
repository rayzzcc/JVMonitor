package com.ray.jvm.controller;

import com.ray.jvm.common.exception.RuntimeException;
import com.ray.jvm.common.response.Response;
import com.ray.jvm.common.response.ResponseHelper;
import com.ray.jvm.service.QueryDataService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：查询数据Controller
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE							PERSON          REASON
 *  1    2018/12/1                     80002946          Create
 * ****************************************************************************
 * </pre>
 *
 * @author 80002946
 * @since 1.0
 */
@RestController()
public class QueryDataController {
    @Autowired
    private QueryDataService queryDataService;

    @RequestMapping(value = "queryData/list")
    public Response list(String sql, int start, int limit) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (StringUtils.isNotBlank(sql) && sql.toUpperCase().indexOf("SELECT") > -1) {
                int total = queryDataService.getCount(sql);
                Map<String, Object> dataList = queryDataService.getData(sql, start, limit);
                resultMap.put("total", total);
                resultMap.put("dataList", dataList);
                System.out.println(dataList.get("datas"));
            }else{
                return ResponseHelper.buildFail("查询失败！");
            }
        } catch (Exception e) {
            return ResponseHelper.buildFail("查询失败！");
        }
        return ResponseHelper.buildOk(resultMap);
    }

    @RequestMapping(value="queryData/export")
    public void export(String sql, int start, int limit, HttpServletResponse response) throws RuntimeException {
        try {
            if (StringUtils.isNotBlank(sql) && sql.toUpperCase().indexOf("SELECT") > -1) {
                queryDataService.export(sql, start, limit, response);
            }else{
                throw new RuntimeException("导出失败！");
            }
        } catch (Exception e) {
            throw new RuntimeException("导出失败！");
        }
    }
}
