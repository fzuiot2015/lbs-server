package edu.fzu.lbs.config;

import edu.fzu.lbs.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {

    private TraceService traceService;

    @Autowired
    public void setTraceService(TraceService traceService) {
        this.traceService = traceService;
    }

    @Scheduled(fixedRate = 10000)
    public void traceSchedule() {
        //TODO
    }
}
