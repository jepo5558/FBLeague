package com.tistory.jepo.fbl;

import java.text.DecimalFormat;

public class HitterRecord {
    private String avg;
    private int homerun;
    private int totalCountAtBat; // 타석수

    public String getAvg() {
        float avgFloat = 0.0f;
        if (totalCountAtBat > 0) {
            avgFloat = (float) hitCount / totalCountAtBat;
            DecimalFormat frmt = new DecimalFormat();
            frmt.setMaximumFractionDigits(3);
            avg = frmt.format(avgFloat);
        }

        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public int getHomerun() {
        return homerun;
    }

    public void setHomerun(int homerun) {
        this.homerun = homerun;
    }

    public int getTotalCountAtBat() {
        return totalCountAtBat;
    }

    public void setTotalCountAtBat(int totalCountAtBat) {
        this.totalCountAtBat = totalCountAtBat;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    private int hitCount;

}
