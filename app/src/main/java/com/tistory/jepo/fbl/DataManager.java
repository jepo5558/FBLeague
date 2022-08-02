package com.tistory.jepo.fbl;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class DataManager {
    private ArrayList<Pitcher> pitcherList;
    private ArrayList<Hitter> hitterList;

    private SharedPreferences preferences;
    private Context context;

    private DataManager(Context c) {
        context = c;
        pitcherList = new ArrayList<Pitcher>();
        initPitcherInfo();
        hitterList = new ArrayList<Hitter>();
        initHitterInfo();
    }

    public void loadData () {
        preferences = context.getSharedPreferences("Record", Context.MODE_PRIVATE);


    }

    public void savdeData() {
        SharedPreferences preferences = context.getSharedPreferences( "Record" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

/*
* https://codechacha.com/ko/sharedpref_arraylist/
        editor.putString(KEY, VALUE);
        editor.commit();
* */

    }

    private static class LazyHolder {
        public static final DataManager INSTANCE = new DataManager();
    }

    public static DataManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public ArrayList<Pitcher> getPitcherList() {
        return pitcherList;
    }

    public ArrayList<Hitter> getHitterList() {
        return hitterList;
    }

    private void initPitcherInfo() {
        Pitcher pitcher1 = new Pitcher();
        pitcher1.setName("Phillip Lee");
        PitcherRecord record1 = new PitcherRecord();
        record1.setInning(31);
        record1.setWin(10);
        record1.setLose(2);
        record1.setLosePoint(10);
        pitcher1.setRecord(record1);
        pitcherList.add(pitcher1);

        Pitcher pitcher2 = new Pitcher();
        pitcher2.setName("Jack Lee");
        PitcherRecord record2 = new PitcherRecord();
        record2.setInning(29);
        record2.setWin(2);
        record2.setLose(8);
        record2.setLosePoint(48);
        pitcher2.setRecord(record2);
        pitcherList.add(pitcher2);

        Pitcher pitcher3 = new Pitcher();
        pitcher3.setName("Kevin Lee");
        PitcherRecord record3 = new PitcherRecord();
        record3.setInning(10);
        record3.setWin(0);
        record3.setLose(3);
        record3.setLosePoint(15);
        pitcher3.setRecord(record3);
        pitcherList.add(pitcher3);
    }

    private void initHitterInfo() {
        Hitter hitter1 = new Hitter();
        hitter1.setName("Phillip Lee");
        HitterRecord record1 = new HitterRecord();
        record1.setHitCount(33);
        record1.setTotalCountAtBat(80);
        record1.setHomerun(8);
        hitter1.setRecord(record1);
        hitterList.add(hitter1);

        Hitter hitter2 = new Hitter();
        hitter2.setName("Jack Lee");
        HitterRecord record2 = new HitterRecord();
        record2.setHomerun(5);
        record2.setHitCount(7);
        record2.setTotalCountAtBat(16);
        hitter2.setRecord(record2);
        hitterList.add(hitter2);

        Hitter hitter3 = new Hitter();
        hitter3.setName("Kevin Lee");
        HitterRecord record3 = new HitterRecord();
        record3.setTotalCountAtBat(82);
        record3.setHitCount(30);
        record3.setHomerun(3);
        hitter3.setRecord(record3);
        hitterList.add(hitter3);
    }

}
