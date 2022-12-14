package com.example.iotapp.view.ElecVideo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.iotapp.R;
import com.example.iotapp.adapters.VideoAdapter;
import com.example.iotapp.model.Video;
import com.google.android.youtube.player.YouTubeBaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class VideoMenu extends YouTubeBaseActivity{
    ListView videoListView;
    private ArrayList<Video> videoList = new ArrayList<Video>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_menu);

        videoListView = findViewById(R.id.videoListView);

        String jsonString = getJsonString();    //json 파일 읽기
        jsonParsing(jsonString);    //json 파싱 -> ArrayList에 담기

        //어댑터를 이용해서 리스트뷰에 데이터 넘김
        VideoAdapter adapter = new VideoAdapter(this, this);
        adapter.setItems(videoList);
        videoListView.setAdapter(adapter);   //어댑터 등록
    }

    //테스트 문항 json 파일 읽어오기
    private String getJsonString() {
        String json = "";

        try {
            InputStream is = getAssets().open("videos.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return json;
    }

    private void jsonParsing(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray videoArray = jsonObject.getJSONArray("Videos");

            for (int i = 0; i < videoArray.length(); i++) {
                JSONObject videoObject = videoArray.getJSONObject(i);
                String id = videoObject.getString("id");
                String title = videoObject.getString("title");
                String description = videoObject.getString("description");
                Video video = new Video(id, title, description);

                videoList.add(video);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void goToHome(View view) {   //뒤로가기 버튼 클릭 시
        finish();   //현재 액티비티 없애기
    }
}

