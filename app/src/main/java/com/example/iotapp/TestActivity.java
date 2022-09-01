package com.example.iotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.*;

public class TestActivity extends YouTubeBaseActivity { // YouTubeBaseActivity 상속받도록 해야 오류가 안남.
    YouTubePlayerView playerView;
    YouTubePlayer player;

    static String API_KEY ="AIzaSyDUJc25nZUJCDEbYj7K71GTMk9Zib0YMf4"; // 구글 콘솔사이트에서 발급받는 키
    static String videoId = "x3iy315chyc"; // 재생할 동영상의 id값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPlayer();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });
    } // onCreate

    public void initPlayer() {
        playerView = findViewById(R.id.playerView);
        // YouTubePlayerView 초기화하기
        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;
                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {}

                    @Override
                    public void onLoaded(String s) {
                        Log.e("PlayerView", "onLoaded 호출됨: " + s);
                        player.play(); // 동엿앙이 로딩되었으면 재생하기
                    }

                    @Override
                    public void onAdStarted() {}

                    @Override
                    public void onVideoStarted() {}

                    @Override
                    public void onVideoEnded() {}

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {}
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {}
        });
    } // initPlayer

    public void playVideo() {
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
//                player.cueVideo(videoId); // 여기에 있으면 동영상 재생이 안됨.
            }
            player.cueVideo(videoId);
        }
    }

} // class