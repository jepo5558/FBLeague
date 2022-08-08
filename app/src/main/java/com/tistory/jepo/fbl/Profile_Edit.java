package com.tistory.jepo.fbl;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Profile_Edit extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);
        init();
    }

    private TextView playerNameTextView;
    private TextView inningEditText;
    private TextView eraEditText;
    private CheckBox checkBox;
    private Button cancelButton;
    private Button doneButton;
    private DataManager dataManager;

    private void init() {
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        int inning = bundle.getInt("inning");
        int lostPoint = bundle.getInt("lost");
        playerNameTextView = findViewById(R.id.playerName);
        inningEditText = findViewById(R.id.inningEditText);
        eraEditText = findViewById(R.id.eraEditText);

        checkBox = findViewById(R.id.winCheckBox);

        playerNameTextView.setText(name);
        inningEditText.setHint(String.valueOf(inning));
        eraEditText.setHint(String.valueOf(lostPoint));
        cancelButton = findViewById(R.id.cancelButton);
        doneButton = findViewById(R.id.doneButton);

        cancelButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
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
        ArrayList<Pitcher> list = dataManager.getPitcherList();
        String playerName = playerNameTextView.getText().toString();
        int inningCount = 0;
        int era = 0;

        try {
            if (!TextUtils.isEmpty(inningEditText.getText().toString())) {
                inningCount = Integer.parseInt(inningEditText.getText().toString());
            }
            if (!TextUtils.isEmpty(eraEditText.getText().toString().toString())) {
                era = Integer.parseInt(eraEditText.getText().toString().toString());
            }
        } catch(NumberFormatException e) {
            Log.e("jepo", e.toString());
        }

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (playerName.equals(list.get(i).getName())) {
                    PitcherRecord record = list.get(i).getRecord();
                    record.setInning(inningCount + record.getInning());
                    record.setLosePoint(era + record.getLosePoint());
                    if (checkBox.isChecked()) {
                        record.setWin(record.getWin() + 1);
                    }
                    break;
                } else {
                    continue;
                }
            }
        }
    }
}