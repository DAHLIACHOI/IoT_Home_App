package com.example.iotapp.view.ElecVideo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.iotapp.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ShowVideo extends AppCompatActivity {
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer youtubePlayer;

    private static String API_KEY;
    private static String videoId;
    private static String title;
    private static String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);

        Intent intent = getIntent();
        API_KEY = intent.getStringExtra("API_KEY");
        videoId = intent.getStringExtra("videoId");
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");

        initPlayer();   //플레이어 초기화

        TextView titleText = findViewById(R.id.youtubeViewTitleText);
        titleText.setText(title);
        TextView descriptionText = findViewById(R.id.youtubeViewDescText);
        descriptionText.setText(description);
    }

    //영상 재생 또는 일시정지
    private void playOrPause() {
        if (youtubePlayer != null) {
            if (youtubePlayer.isPlaying()) { //재생 중이면
                youtubePlayer.pause();  //일시정지
            } else {    //일시정지 상태면
                youtubePlayer.play();   //재생
            }
        }
    }

    //플레이어 초기화
    private void initPlayer() {
        AlertDialog.Builder helpPopup = new AlertDialog.Builder(this);
        AlertDialog ad = helpPopup.create();
        ad.setTitle("영상 로딩 중");
        ad.setMessage("잠시만 기다려주세요.");
        ad.setCancelable(false);

        youTubePlayerView = findViewById(R.id.youtubeView);
        youTubePlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                youtubePlayer = player;
                youtubePlayer.cueVideo(videoId);    //썸네일 이미지 로드하는 메소드

//                player.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
//                    @Override
//                    public void onPlaying() {   //play() 또는 사용자 작업으로 재생이 시작될 때
//                    }
//
//                    @Override
//                    public void onPaused() {    //pause() 또는 사용자 작업으로 재생이 일시중지될 때
//                    }
//
//                    @Override
//                    public void onStopped() {   //일시중지 외의 이유로 재생이 중지될 때
//                        System.out.println("영상 정지됨");
//                    }
//
//                    @Override
//                    public void onBuffering(boolean isBuffering) {    //버퍼링이 시작되거나 종료될 때
//                        System.out.println("버퍼링");
//                    }
//
//                    @Override
//                    public void onSeekTo(int i) {   //사용자가 취소하거나 찾기 메소드를 호출하여 재생 위치에서 이동할 때 호출됩니다.
//                        System.out.println("시간 건너뛰기");
//                    }
//                });

                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                        ad.show();
                    }

                    @Override
                    public void onLoaded(String s) {
                        ad.dismiss();
                    }

                    @Override
                    public void onAdStarted() {
                        System.out.println("광고 시작");
                    }
                    @Override
                    public void onVideoStarted() {

                    }

                    @Override
                    public void onVideoEnded() {

                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {
                        System.out.println("onError: " + errorReason);
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }


    public void goToBack(View view) {   //뒤로가기 버튼 클릭 시
        finish();
    }
    @Override
    public void onBackPressed() {
        goToBack(null);
    }
}