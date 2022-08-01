package com.tistory.jepo.fbl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView batImageView;
    private ImageView gloveImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI () {
        batImageView = findViewById(R.id.batImageView);
        gloveImageView = findViewById(R.id.gloveImageView);

        batImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHitterRecord();
            }
        });


        gloveImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPicherRecord();
            }
        });
    }

    private void goPicherRecord() {
        Intent intent = new Intent(this, PitcherRecordActivity.class);
        startActivity(intent);

    }

    private void goHitterRecord() {
        Intent intent = new Intent(this, HitterRecordActivity.class);
        startActivity(intent);
    }
}