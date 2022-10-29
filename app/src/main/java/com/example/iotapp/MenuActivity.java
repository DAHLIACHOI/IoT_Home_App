package com.example.iotapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iotapp.view.ElecVideo.VideoMenu;

public class MenuActivity extends AppCompatActivity {
    private androidx.cardview.widget.CardView Elect, Control, Money, Temp, Youtube, MyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Elect = (androidx.cardview.widget.CardView) findViewById(R.id.elect);
        Control = (androidx.cardview.widget.CardView) findViewById(R.id.Control);
        Money = (androidx.cardview.widget.CardView) findViewById(R.id.Money);
        Temp = (androidx.cardview.widget.CardView) findViewById(R.id.Temp);
        Youtube = (androidx.cardview.widget.CardView) findViewById(R.id.Youtube);
        MyPage = (androidx.cardview.widget.CardView) findViewById(R.id.MyPage);

        Elect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ElectIntent = new Intent(MenuActivity.this, Elect.class);
                MenuActivity.this.startActivity(ElectIntent);
            }
        });

        Control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ControlIntent = new Intent(MenuActivity.this, Switch.class);
                MenuActivity.this.startActivity(ControlIntent);

            }
        });

        Money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent MoneyIntent = new Intent(MenuActivity.this, Money.class);
                MenuActivity.this.startActivity(MoneyIntent);
            }
        });

        Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent TempHumIntent = new Intent(MenuActivity.this, com.example.iotapp.Temp.class);
                MenuActivity.this.startActivity(TempHumIntent);

            }
        });

        Youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VideoMenuIntent = new Intent(MenuActivity.this, VideoMenu.class);
                MenuActivity.this.startActivity(VideoMenuIntent);
            }
        });

        MyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MyPageIntent = new Intent(MenuActivity.this, Hum.class);
                MenuActivity.this.startActivity(MyPageIntent);
            }
        });

    }
}