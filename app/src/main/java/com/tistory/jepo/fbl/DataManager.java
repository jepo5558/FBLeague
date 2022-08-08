package com.tistory.jepo.fbl;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataManager {
    private ArrayList<Pitcher> pitcherList;
    private ArrayList<Hitter> hitterList;

    private SharedPreferences preferences;
    private static Context context;

    private DataManager() {
        pitcherList = new ArrayList<Pitcher>();
        hitterList = new ArrayList<Hitter>();

        if (isInit()) {
            initPitcherInfo();
            initHitterInfo();
        } else {
            loadPitcherData();
            loadHitterData();
        }
    }

    private boolean isInit() {
        preferences = context.getSharedPreferences("PitcherRecord", Context.MODE_PRIVATE);
        String result = preferences.getString("유건", "");
        if ("".equals(result))
            return true;
        else
            return false;
    }

    // name-inning-win-lose-losepoint
    public void loadPitcherData() {
        pitcherList.clear();
        preferences = context.getSharedPreferences("PitcherRecord", Context.MODE_PRIVATE);

        String record = preferences.getString("유건", "");
        String[] result = record.split("-");
        Pitcher pitcher = new Pitcher();
        pitcher.setName(result[0]); // name
        PitcherRecord pitcherRecord = new PitcherRecord();
        pitcherRecord.setInning(Integer.parseInt(result[1])); // inning
        pitcherRecord.setWin(Integer.parseInt(result[2])); // win
        pitcherRecord.setLose(Integer.parseInt(result[3])); // lose
        pitcherRecord.setLosePoint(Integer.parseInt(result[4])); // lose point
        pitcherList.add(pitcher);

        String record2 = preferences.getString("도현", "");
        String[] result2 = record2.split("-");
        Pitcher pitcher2 = new Pitcher();
        pitcher2.setName(result[0]); // name
        PitcherRecord pitcherRecord2 = new PitcherRecord();
        pitcherRecord2.setInning(Integer.parseInt(result[1])); // inning
        pitcherRecord2.setWin(Integer.parseInt(result[2])); // win
        pitcherRecord2.setLose(Integer.parseInt(result[3])); // lose
        pitcherRecord2.setLosePoint(Integer.parseInt(result[4])); // lose point
        pitcherList.add(pitcher2);

        String record3 = preferences.getString("재호", "");
        String[] result3 = record3.split("-");
        Pitcher pitcher3 = new Pitcher();
        pitcher3.setName(result[0]); // name
        PitcherRecord pitcherRecord3 = new PitcherRecord();
        pitcherRecord3.setInning(Integer.parseInt(result[1])); // inning
        pitcherRecord3.setWin(Integer.parseInt(result[2])); // win
        pitcherRecord3.setLose(Integer.parseInt(result[3])); // lose
        pitcherRecord3.setLosePoint(Integer.parseInt(result[4])); // lose point
        pitcherList.add(pitcher3);

    }

    // name-hitcount-atbat-homerun
    public void loadHitterData() {
        hitterList.clear();
        preferences = context.getSharedPreferences("HitterRecord", Context.MODE_PRIVATE);

        String record = preferences.getString("유건", "");
        String[] result = record.split("-");
        Hitter hitter = new Hitter();
        hitter.setName(result[0]); // name
        HitterRecord hitterRecord = new HitterRecord();
        hitterRecord.setHitCount(Integer.parseInt(result[1])); // hit count
        hitterRecord.setTotalCountAtBat(Integer.parseInt(result[2])); // at bat
        hitterRecord.setHomerun(Integer.parseInt(result[3])); // homerun
        hitterList.add(hitter);

        String record2 = preferences.getString("도현", "");
        String[] result2 = record2.split("-");
        Hitter hitter2 = new Hitter();
        hitter2.setName(result[0]); // name
        HitterRecord hitterRecord2 = new HitterRecord();
        hitterRecord2.setHitCount(Integer.parseInt(result[1])); // hit count
        hitterRecord2.setTotalCountAtBat(Integer.parseInt(result[2])); // at bat
        hitterRecord2.setHomerun(Integer.parseInt(result[3])); // homerun
        hitterList.add(hitter2);

        String record3 = preferences.getString("재호", "");
        String[] result3 = record3.split("-");
        Hitter hitter3 = new Hitter();
        hitter3.setName(result[0]); // name
        HitterRecord hitterRecord3 = new HitterRecord();
        hitterRecord3.setHitCount(Integer.parseInt(result[1])); // hit count
        hitterRecord3.setTotalCountAtBat(Integer.parseInt(result[2])); // at bat
        hitterRecord3.setHomerun(Integer.parseInt(result[3])); // homerun
        hitterList.add(hitter3);

    }


    public void savePitcherData() {
        Collections.sort(pitcherList, new Comparator<Pitcher>() {
            @Override
            public int compare(Pitcher pitcher, Pitcher t1) {
                return Integer.compare(pitcher.getRecord().getWin(), t1.getRecord().getWin());
            }
        });
        Collections.reverse(pitcherList);

        SharedPreferences preferences = context.getSharedPreferences("PitcherRecord", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < pitcherList.size(); i++) {
            Pitcher pitcher = pitcherList.get(i);
            PitcherRecord record = pitcher.getRecord();

            String pitcherRecord = null;
            StringBuilder recordBuilder = new StringBuilder();

            pitcherRecord = recordBuilder.append(pitcher.getName()).append('-').append(record.getInning()).append('-')
                    .append(record.getWin()).append('-').append(record.getLose()).append('-')
                    .append(record.getLosePoint()).toString();
            // name-inning-win-lose-losepoint
            editor.putString(pitcher.getName(), pitcherRecord);
            editor.commit();
        }
    }

    public void saveHitterData() {
        Collections.sort(hitterList, new Comparator<Hitter>() {
            @Override
            public int compare(Hitter hitter, Hitter t1) {
                return Float.compare(Float.parseFloat(hitter.getRecord().getAvg()), Float.parseFloat(t1.getRecord().getAvg()));
            }
        });
        Collections.reverse(hitterList);

        SharedPreferences preferences = context.getSharedPreferences("HitterRecord", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < hitterList.size(); i++) {
            Hitter hitter = hitterList.get(i);
            HitterRecord record = hitter.getRecord();

            String hitterRecord = null;
            StringBuilder recordBuilder = new StringBuilder();

            hitterRecord = recordBuilder.append(hitter.getName()).append('-').append(record.getHitCount()).append('-')
                    .append(record.getTotalCountAtBat()).append('-').append(record.getHomerun()).toString();
            // name-hitcount-atbat-homerun
            editor.putString(hitter.getName(), hitterRecord);
            editor.commit();
        }
    }

    private static class LazyHolder {
        public static final DataManager INSTANCE = new DataManager();
    }

    public static DataManager getInstance(Context c) {
        context = c;
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
        pitcher1.setPhoto(context.getDrawable(R.drawable.phillip));
        PitcherRecord record1 = new PitcherRecord();
        record1.setInning(31);
        record1.setWin(10);
        record1.setLose(2);
        record1.setLosePoint(10);
        pitcher1.setRecord(record1);
        pitcherList.add(pitcher1);

        Pitcher pitcher2 = new Pitcher();
        pitcher2.setName("Jack Lee");
        pitcher2.setPhoto(context.getDrawable(R.drawable.jack));
        PitcherRecord record2 = new PitcherRecord();
        record2.setInning(29);
        record2.setWin(2);
        record2.setLose(8);
        record2.setLosePoint(48);
        pitcher2.setRecord(record2);
        pitcherList.add(pitcher2);

        Pitcher pitcher3 = new Pitcher();
        pitcher3.setName("Kevin Lee");
        pitcher3.setPhoto(context.getDrawable(R.drawable.kevin));
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
        hitter1.setPhoto(context.getDrawable(R.drawable.phillip));
        HitterRecord record1 = new HitterRecord();
        record1.setHitCount(33);
        record1.setTotalCountAtBat(80);
        record1.setHomerun(8);
        hitter1.setRecord(record1);
        hitterList.add(hitter1);

        Hitter hitter2 = new Hitter();
        hitter2.setName("Jack Lee");
        hitter2.setPhoto(context.getDrawable(R.drawable.jack));
        HitterRecord record2 = new HitterRecord();
        record2.setHomerun(5);
        record2.setHitCount(7);
        record2.setTotalCountAtBat(16);
        hitter2.setRecord(record2);
        hitterList.add(hitter2);

        Hitter hitter3 = new Hitter();
        hitter3.setName("Kevin Lee");
        hitter3.setPhoto(context.getDrawable(R.drawable.kevin));
        HitterRecord record3 = new HitterRecord();
        record3.setTotalCountAtBat(82);
        record3.setHitCount(30);
        record3.setHomerun(3);
        hitter3.setRecord(record3);
        hitterList.add(hitter3);
    }

}
