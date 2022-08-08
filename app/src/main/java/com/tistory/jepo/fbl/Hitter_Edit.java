package com.tistory.jepo.fbl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Hitter_Edit extends AppCompatActivity implements View.OnClickListener {

    private TextView nameTextView;
    private EditText hitCountEditText;
    private EditText hrCountEditText;
    private EditText countAtBatEditText;
    private Button cancel;
    private Button done;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hitter_edit);
        init();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        int hitCount = bundle.getInt("hit");
        int countAtBat = bundle.getInt("countAtBat");
        int hrCount = bundle.getInt("homerun");

        nameTextView = findViewById(R.id.playerName);
        hitCountEditText = findViewById(R.id.hitCountEditText);
        hrCountEditText = findViewById(R.id.hrEditText);
        countAtBatEditText = findViewById(R.id.countAtBatEditText);

        nameTextView.setText(name);
        hitCountEditText.setHint(String.valueOf(hitCount));
        hrCountEditText.setHint(String.valueOf(hrCount));
        countAtBatEditText.setHint(String.valueOf(countAtBat));

        cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(this);
        done = findViewById(R.id.doneButton);
        done.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cancelButton) {
            finish();
        } else {
            updateRecord();
            finish();
        }
    }

    private void updateRecord() {
        dataManager = DataManager.getInstance(getApplicationContext());
        ArrayList<Hitter> list = dataManager.getHitterList();
        String playerName = nameTextView.getText().toString();
        int countAtBat = 0;
        int hrCount = 0;
        int hitcount = 0;

        if (!TextUtils.isEmpty(countAtBatEditText.getText().toString())) {
            countAtBat = Integer.parseInt(countAtBatEditText.getText().toString());
        }
        if (!TextUtils.isEmpty(hrCountEditText.getText().toString())) {
            hrCount = Integer.parseInt(hrCountEditText.getText().toString());
        }
        if (!TextUtils.isEmpty(hitCountEditText.getText().toString())) {
            hitcount = Integer.parseInt(hitCountEditText.getText().toString());
        }

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (playerName.equals(list.get(i).getName())) {
                    HitterRecord record = list.get(i).getRecord();
                    record.setTotalCountAtBat(countAtBat + record.getTotalCountAtBat());
                    record.setHitCount(hitcount + record.getHitCount());
                    record.setHomerun(hrCount + record.getHomerun());
                    break;
                } else {
                    continue;
                }
            }
        }
    }
}