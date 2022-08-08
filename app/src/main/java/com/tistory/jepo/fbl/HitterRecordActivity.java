
package com.tistory.jepo.fbl;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HitterRecordActivity extends AppCompatActivity implements OnHitterLongClickListener {

    private HitterRecordAdapter hitterRecordAdapter;
    private RecyclerView hitterAvgRecyclerView;

    private HitterRecordHRAdapter hitterRecordHRAdapter;
    private RecyclerView hitterHrRecyclerView;
    private ArrayList<Hitter> hitterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitter_record);

        hitterRecordAdapter = new HitterRecordAdapter(new HitterItemUtil(), this);
        hitterAvgRecyclerView = findViewById(R.id.hitterRecyclerView);
        hitterAvgRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        hitterAvgRecyclerView.setAdapter(hitterRecordAdapter);


        hitterRecordHRAdapter = new HitterRecordHRAdapter(new HitterItemUtil(), this);
        hitterHrRecyclerView = findViewById(R.id.hitter2RecyclerView);
        hitterHrRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        hitterHrRecyclerView.setAdapter(hitterRecordHRAdapter);


        //hitterList = new ArrayList<Hitter>();
        hitterList = DataManager.getInstance(getApplicationContext()).getHitterList();
        hitterRecordAdapter.submitList(hitterList);
        hitterRecordHRAdapter.submitList(hitterList);
        //loadHitter();
        hitterRecordAdapter.notifyDataSetChanged();
        hitterRecordHRAdapter.notifyDataSetChanged();
    }

    private void loadHitter() {
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

    @Override
    public void onLongClicked(Hitter player) {
        Intent intent = new Intent(this, Hitter_Edit.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", player.getName());
        HitterRecord record = player.getRecord();
        bundle.putInt("hit", record.getHitCount());
        bundle.putInt("countAtBat", record.getTotalCountAtBat());
        bundle.putInt("homerun", record.getHomerun());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hitterRecordAdapter.notifyDataSetChanged();
        hitterRecordHRAdapter.notifyDataSetChanged();
        DataManager.getInstance(getApplicationContext()).saveHitterData();

    }
}