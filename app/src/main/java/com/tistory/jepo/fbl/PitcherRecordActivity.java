package com.tistory.jepo.fbl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PitcherRecordActivity extends AppCompatActivity implements OnPitcherLongClickListener {

    private PitcherRecordAdapter pitcherRecordAdapter;
    private RecyclerView pitcherWinRecyclerView;

    private PitcherRecordEraAdapter pitcherRecordEraAdapter;
    private RecyclerView picherEraRecyclerView;
    private ArrayList<Pitcher> pitcherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picher_record);

        pitcherRecordAdapter = new PitcherRecordAdapter(new PitcherItemUtil(), this);
        pitcherWinRecyclerView = findViewById(R.id.picherRecyclerView);
        pitcherWinRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pitcherWinRecyclerView.setAdapter(pitcherRecordAdapter);


        pitcherRecordEraAdapter = new PitcherRecordEraAdapter(new PitcherItemUtil(), this);
        picherEraRecyclerView = findViewById(R.id.picher2RecyclerView);
        picherEraRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        picherEraRecyclerView.setAdapter(pitcherRecordEraAdapter);


        //pitcherList = new ArrayList<Pitcher>();
        pitcherList = DataManager.getInstance(getApplicationContext()).getPitcherList();
        pitcherRecordAdapter.submitList(pitcherList);
        pitcherRecordEraAdapter.submitList(pitcherList);
        //loadPitcher();
        pitcherRecordAdapter.notifyDataSetChanged();
    }

    private void loadPitcher() {
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

    @Override
    public void onLongClicked(Pitcher player) {
        Log.d("jepo", "player = " + player.getName());
        Intent intent = new Intent(this, Profile_Edit.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", player.getName());
        bundle.putInt("inning", player.getRecord().getInning());
        bundle.putInt("lost", player.getRecord().getLosePoint());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pitcherRecordAdapter.notifyDataSetChanged();
        pitcherRecordEraAdapter.notifyDataSetChanged();
        DataManager.getInstance(getApplicationContext()).savePitcherData();

    }
}