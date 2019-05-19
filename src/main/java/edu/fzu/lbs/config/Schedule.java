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

    @Scheduled(cron = "0 20 22 * * ?")
    public void traceSchedule() throws IOException {
        System.out.println("test");
        traceService.updateData();
    }
}
