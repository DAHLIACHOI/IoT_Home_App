package com.example.iotapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Mypage extends AppCompatActivity {

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
        setContentView(R.layout.activity_mypage);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void goToHome(View view) {   //뒤로가기 버튼 클릭 시
        finish();   //현재 액티비티 없애기
    }

}