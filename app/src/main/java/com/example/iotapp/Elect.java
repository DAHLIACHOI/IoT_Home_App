package com.example.iotapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Elect extends AppCompatActivity {

    private ProgressBar progressBar01, progressBar02;
    private TextView progressText01, progressText02;
    int i, j = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elect);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar01 = findViewById(R.id.progress_bar01);
        progressText01 = findViewById(R.id.progress_text01);

        progressBar02 = findViewById(R.id.progress_bar02);
        progressText02 = findViewById(R.id.progress_text02);

        final Handler handler01 = new Handler();
        final Handler handler02 = new Handler();
        handler01.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (i <= 100) {
                    progressText01.setText(""+i);
                    progressBar01.setProgress(i);
                    i++;
                    handler01.postDelayed(this, 300);
                } else {
                    handler01.removeCallbacks(this);
                }
            }
        }, 300);

        handler02.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (j <= 20) {
                    progressText02.setText(""+j);
                    progressBar02.setProgress(j);
                    j++;
                    handler02.postDelayed(this, 300);
                } else {
                    handler02.removeCallbacks(this);
                }
            }
        }, 300);
    }

    public void goToHome(View view) {   //뒤로가기 버튼 클릭 시
        finish();   //현재 액티비티 없애기
    }


}