package edu.fzu.lbs.config;

import edu.fzu.lbs.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Schedule {

    private TraceService traceService;

    @Autowired
    public void setTraceService(TraceService traceService) {
        this.traceService = traceService;
    }

    /**
     * 定时任务
     *  秒 0-59   分钟 0-59  小时 0-23  日期 1-31  月份 1-12  星期 1-7
     *  *任意值    ?任意可能的值
     * @throws IOException
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void traceSchedule() throws IOException {
        System.out.println("traceService updateData");
        traceService.updateData();
    }
}
