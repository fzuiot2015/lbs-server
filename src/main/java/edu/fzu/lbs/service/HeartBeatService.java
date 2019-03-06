package edu.fzu.lbs.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeartBeatService {

    private static final double THRESHOLD = 620.0;
    private long oldTime = 0;

    /**
     * 计算心率
     *
     * @param outputs 传感器输出
     * @return
     */
    public int getHeartBeat(List<Integer> outputs) {
        boolean belowThreshold = true;
        List<Double> beats = new ArrayList<>();

        for (Integer output : outputs) {
            if (output > THRESHOLD && belowThreshold) {
                if (oldTime == 0) {
                    oldTime = System.currentTimeMillis();
                } else {
                    double currentBPM = calculateBPM();
                    beats.add(currentBPM);
                }
                belowThreshold = false;
            } else if (output < THRESHOLD) {
                belowThreshold = true;
            }
        }

        double average = beats.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        return (int) average;
    }

    /**
     * 计算当前心率
     *
     * @return 瞬时心率数值
     */
    private double calculateBPM() {
        long newTime = System.currentTimeMillis();
        double diff = newTime - oldTime;
        oldTime = newTime;
        double currentBPM = 60000 / diff;
        return currentBPM;
    }

}
