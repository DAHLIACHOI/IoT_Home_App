package com.example.iotapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Money extends AppCompatActivity {

   // final TextView todayDate = (TextView) findViewById(R.id.todayDate);

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
        setContentView(R.layout.activity_money);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView todayDate = (TextView) findViewById(R.id.todayDate);
        //TextView monthDate = (TextView) findViewById(R.id.monthDate);
        //TextView text02 = (TextView) findViewById(R.id.text02);

        long now = System.currentTimeMillis(); // 1970년 1월 1일부터 몇 밀리세컨드가 지났는지를 반환함
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년MM월dd일");//형식 지정
        String getTime1 = simpleDateFormat.format(date);

        todayDate.setText(getTime1);

        //현재 월만 가져오기
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());

        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);

        //현재 월의 마지막 일 가져오기
        Calendar cal = Calendar.getInstance(); //캘린더 인스턴스 생성
        cal.set(Integer.parseInt(year), Integer.parseInt(month)-1, 1); //캘린더에 기준이 되는 날짜(년, 월, 일) 입력, 0이 1월로 잡히기 때문에 월 -1
        //따라서 월이 11(12월)을 넘어가면 년도도 알아서 넘어감

        //text02.setText(month+"월 예상사용량(요금)");
        //monthDate.setText(year+"년"+month+"월1일~"+cal.getActualMaximum(Calendar.DAY_OF_MONTH)+"일");
    }

    public void goToHome(View view) {   //뒤로가기 버튼 클릭 시
        finish();   //현재 액티비티 없애기
    }

}