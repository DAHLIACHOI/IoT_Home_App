package com.example.iotapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Temp extends AppCompatActivity {
    TextView readText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        readText = findViewById(R.id.tempText);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Temperature");

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer value = dataSnapshot.getValue(Integer.class);
                readText.setText(value + "°C");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                readText.setText(("error : ") + error.toException());
            }
        });
    }

    public void goToHome(View view) {   //뒤로가기 버튼 클릭 시
         finish();   //현재 액티비티 없애기

    }

}
