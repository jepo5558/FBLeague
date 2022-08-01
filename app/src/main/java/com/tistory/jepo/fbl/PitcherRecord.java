package com.tistory.jepo.fbl;

import java.text.DecimalFormat;

public class PitcherRecord {
    private int win;
    private int lose;
    private int inning;
    private int losePoint;
    private String era;

    public String getEra() {
        float erafloat = 0.0F;
        if (inning > 0 ) {
            erafloat = (float)losePoint * 9 / inning;
            DecimalFormat frmt = new DecimalFormat();
            frmt.setMaximumFractionDigits(3);
            era = frmt.format(erafloat);
        }
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }



    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getInning() {
        return inning;
    }

    public void setInning(int inning) {
        this.inning = inning;
    }

    public int getLosePoint() {
        return losePoint;
    }

    public void setLosePoint(int losePoint) {
        this.losePoint = losePoint;
    }
}
