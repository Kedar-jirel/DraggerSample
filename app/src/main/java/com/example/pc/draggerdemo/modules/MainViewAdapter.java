package com.example.pc.draggerdemo.modules;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.annotation.IntRange;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoControls;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.example.pc.draggerdemo.R;
import com.example.pc.draggerdemo.extras.Constants;
import com.example.pc.draggerdemo.mvp.model.NewsResponseContent;
import com.example.pc.draggerdemo.mvp.model.NewsResponseContentData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sanjay on 4/26/2017.
 */

public class MainViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIDEO_TYPE = 0, TEXT_TYPE = 1, IMAGE_TYPE = 2;
    ArrayList<NewsResponseContent> mObjectArrayList = new ArrayList<>();
    int mObjectListSize = 0;
    Activity mActivity;
    private MediaController mediaControls;
    Context mContext;

    public MainViewAdapter(Activity mActivity, Context context) {
        this.mActivity = mActivity;
        this.mContext = context;
        if (mediaControls == null) {
            mediaControls = new MediaController(mActivity);
        }
    }


    @Override
    public int getItemViewType(int position) {
        int TYPE = 0;
        if (mObjectArrayList.get(position) instanceof NewsResponseContent) {
            switch ((mObjectArrayList.get(position).getMType())) {
                case Constants.VIDEO:
                    TYPE = VIDEO_TYPE;
                    break;
                case Constants.IMAGE:
                    TYPE = IMAGE_TYPE;
                    break;
                case Constants.TEXT:
                    TYPE = TEXT_TYPE;
                    break;
                default:
                    /*do nothing
                    * */
                    break;
            }
        }

        return TYPE;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(mActivity);

        switch (viewType) {
            case VIDEO_TYPE:
                viewHolder = new VideoViewHolder(inflater.inflate(R.layout.layout_video_viewholder, parent, false));
                break;
            case IMAGE_TYPE:
                viewHolder = new ImageViewHolder(inflater.inflate(R.layout.layout_image_viewholder, parent, false));
                break;
            default:
                viewHolder = new SimpleViewHolder(inflater.inflate(R.layout.text_viewholder, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIDEO_TYPE:
                configureVideoViewHolder((VideoViewHolder) holder, position);
                break;
            case IMAGE_TYPE:
                configureViewImageViewHolder((ImageViewHolder) holder, position);
                break;
            default:
                configureDefaultViewHolder((SimpleViewHolder) holder, position);
                break;
        }
    }

    private void configureDefaultViewHolder(SimpleViewHolder holder, int position) {
    }

    private void configureViewImageViewHolder(ImageViewHolder holder, int position) {
        NewsResponseContentData mImageContent = (NewsResponseContentData) mObjectArrayList.get(position).getData();
        holder.mImageTitleView.setText(mImageContent.getMTitle());
        holder.mImageDescriptionView.setText(mImageContent.getMDescription());
        Picasso.with(mActivity).load(mImageContent.getMUrl()).into(holder.mImageComtentView);
    }


    private void configureVideoViewHolder(final VideoViewHolder holder, int position) {
        NewsResponseContentData video = (NewsResponseContentData) mObjectArrayList.get(position).getData();
        holder.mTitleView.setText(video.getMTitle());
        holder.mDescriptionView.setText(video.getMDescription());


        try {
            holder.mVideoView.setVideoURI(Uri.parse(video.getMUrl()));
            holder.mVideoView.setVolume(Float.parseFloat("0.50"));
            holder.mVideoView.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared() {
                    holder.mVideoView.start();

                }
            });

            holder.mVideoView.setControls(new VideoControls(mActivity) {
                @Override
                public void setPosition(@IntRange(from = 0L) long position) {

                }

                @Override
                public void setDuration(@IntRange(from = 0L) long duration) {

                }

                @Override
                public void updateProgress(@IntRange(from = 0L) long position, @IntRange(from = 0L) long duration, @IntRange(from = 0L, to = 100L) int bufferPercent) {

                }

                @Override
                protected int getLayoutResource() {
                    return 0;
                }

                @Override
                protected void animateVisibility(boolean toVisible) {

                }

                @Override
                protected void updateTextContainerVisibility() {

                }

                @Override
                public void showLoading(boolean initialLoad) {

                }

                @Override
                public void finishLoading() {
                    holder.mVideoView.start();
                }
            });

        } catch (Exception ioe) {
            //ignore
        }

//        try {
//            setMediaController(mActivity, holder.mVideoView, video.getmUrl());
//
//            holder.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                }
//            });
//        } catch (Exception e) {
//            // TODO: handle exception
//            Toast.makeText(mActivity, "Error connecting", Toast.LENGTH_SHORT).show();
//        }

    }

//
//    private void setMediaController(Activity mActivity, VideoView mVideoView, String videoUrl) {
//        MediaController mediaController = new MediaController(mActivity);
//        mediaController.setAnchorView(mVideoView);
//        Uri uri = Uri.parse(videoUrl);
//        mVideoView.setMediaController(mediaController);
//        mVideoView.setVideoURI(uri);
//        mVideoView.start();
//    }


    public void addAll(ArrayList<NewsResponseContent> commonObjectArrayList) {
        mObjectArrayList.addAll(commonObjectArrayList);
        notifyDataSetChanged();

    }

    public NewsResponseContent getItem(int position) {
        return mObjectArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return mObjectArrayList.size();
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_video_viewholder_title)
        TextView mTitleView;

        @BindView(R.id.video_view)
        public VideoView mVideoView;

        @BindView(R.id.layout_video_viewholder_description)
        TextView mDescriptionView;


        public VideoViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }


    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.layout_image_viewholder_title)
        TextView mImageTitleView;

        @BindView(R.id.layout_image_view_holder_image)
        ImageView mImageComtentView;

        @BindView(R.id.layout_image_viewholder_description)
        TextView mImageDescriptionView;

        public ImageViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }
    }

}
