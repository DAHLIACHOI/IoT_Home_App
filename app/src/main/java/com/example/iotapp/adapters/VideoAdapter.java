package com.example.iotapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iotapp.R;
import com.example.iotapp.model.Video;
import com.example.iotapp.view.ElecVideo.VideoMenu;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

public class VideoAdapter extends BaseAdapter {

    private static String API_KEY = "AIzaSyDUJc25nZUJCDEbYj7K71GTMk9Zib0YMf4";

    VideoMenu videoMenu;
    Context mContext;
    LayoutInflater mLayoutInflater;

    ArrayList<Video> items = new ArrayList<Video>();    //영상 리스트

    public VideoAdapter(VideoMenu videoMenu, Context context) {
        this.videoMenu = videoMenu;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Video getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public ArrayList<Video> getVideos() { return items; }

    public void setItems(ArrayList<Video> items){
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.video_item, null);

        Video video = getItem(position);
        String videoId = video.getId();
        String title = video.getTitle();
        String description = video.getDescription();

        //유튜브 썸네일 불러오기
        YouTubeThumbnailView thumbnail = view.findViewById(R.id.videoThumbnailView);    //유튜브 썸네일 뷰
        thumbnail.initialize(API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(videoId);   //썸네일 불러오기
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        youTubeThumbnailLoader.release();
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(videoMenu, "영상 썸네일을 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //영상 정보 출력하기
        TextView videoTitleText = view.findViewById(R.id.videoTitleText);
        videoTitleText.setText(title);   //제목
        TextView videoDescriptionText = view.findViewById(R.id.videoDescriptionText);
        videoDescriptionText.setText(description);   //설명



        //각 아이템 클릭 시 영상 시청 화면으로 이동
        LinearLayout videoItemView = view.findViewById(R.id.videoItemView);
        videoItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, VideoMenu.class);
                //영상 정보 넘기기
                intent.putExtra("API_KEY", API_KEY);
                intent.putExtra("videoId", videoId);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                mContext.startActivity(intent); //영상 시청 화면으로 이동
            }
        });


        return view;
    }
}