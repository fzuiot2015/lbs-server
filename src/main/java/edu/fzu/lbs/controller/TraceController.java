package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 百度鹰眼轨迹追踪Controller
 */
@RestController
@RequestMapping("/trace")
public class TraceController {
    private TraceService traceService;

    @Autowired
    public void setTraceService(TraceService traceService) {
        this.traceService = traceService;
    }

    /**
     * 驾驶行为分析
     *
     * @param entityName entity名称，唯一标识
     * @return
     * @throws IOException
     */
    @RequestMapping("/behavior")
    public ResultDTO<String> behavior(String entityName) throws IOException {
        String behavior = traceService.behavior(entityName);
        return new ResultDTO<>(behavior);
    }

}
