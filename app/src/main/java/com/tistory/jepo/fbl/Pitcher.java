package com.tistory.jepo.fbl;

public class Pitcher extends Player {
    private PitcherRecord record;

    public PitcherRecord getRecord() {
        return record;
    }

    public void setRecord(PitcherRecord record) {
        this.record = record;
    }
}
