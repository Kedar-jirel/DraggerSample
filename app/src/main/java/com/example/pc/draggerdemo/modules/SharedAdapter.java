package com.example.pc.draggerdemo.modules;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pc.draggerdemo.R;
import com.example.pc.draggerdemo.extras.Constants;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContent;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContentData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZUserActionStandard;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by sanjay on 4/26/2017.
 */

public class SharedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIDEO_TYPE = 0, TEXT_TYPE = 1, IMAGE_TYPE = 2;
    ArrayList<NewsResponseContent> mObjectArrayList = new ArrayList<>();
    int mObjectListSize = 0;
    Activity mActivity;
    Context mContext;
    private MediaController mediaControls;

    public SharedAdapter(Activity mActivity, Context context) {
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
            switch ((mObjectArrayList.get(position).getType())) {
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
        if (mObjectArrayList.get(position).getData() instanceof NewsResponseContentData) {
            NewsResponseContentData mImageContent = (NewsResponseContentData) mObjectArrayList.get(position).getData();
            holder.mImageTitleView.setText(mImageContent.getTitle());
            holder.mImageDescriptionView.setText(mImageContent.getDescription());
            Picasso.with(mActivity).load(mImageContent.getUrl()).into(holder.mImageComtentView);
        }
    }


    private void configureVideoViewHolder(final VideoViewHolder holder, int position) {
        NewsResponseContentData video = (NewsResponseContentData) mObjectArrayList.get(position).getData();
        holder.mTitleView.setText(video.getTitle());
        holder.mDescriptionView.setText(video.getDescription());

        holder.mVideoView.setUp(video.getUrl(), JZVideoPlayerStandard.SCREEN_LAYOUT_LIST, "My Public Video");
        Glide.with(mActivity).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(holder.mVideoView.thumbImageView);
        JZVideoPlayer.setJzUserAction(new MyUserActionStandard());

    }


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

        @BindView(R.id.video_view)
        public JZVideoPlayerStandard mVideoView;
        @BindView(R.id.layout_video_viewholder_title)
        TextView mTitleView;
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


    class MyUserActionStandard implements JZUserActionStandard {

        @Override
        public void onEvent(int type, String url, int screen, Object... objects) {
            switch (type) {
                case JZUserAction.ON_CLICK_START_ICON:
                    Log.i("USER_EVENT", "ON_CLICK_START_ICON" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_CLICK_START_ERROR:
                    Log.i("USER_EVENT", "ON_CLICK_START_ERROR" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_CLICK_START_AUTO_COMPLETE:
                    Log.i("USER_EVENT", "ON_CLICK_START_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_CLICK_PAUSE:
                    Log.i("USER_EVENT", "ON_CLICK_PAUSE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_CLICK_RESUME:
                    Log.i("USER_EVENT", "ON_CLICK_RESUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_SEEK_POSITION:
                    Log.i("USER_EVENT", "ON_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_AUTO_COMPLETE:
                    Log.i("USER_EVENT", "ON_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_ENTER_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_ENTER_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_QUIT_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_QUIT_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_ENTER_TINYSCREEN:
                    Log.i("USER_EVENT", "ON_ENTER_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_QUIT_TINYSCREEN:
                    Log.i("USER_EVENT", "ON_QUIT_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_TOUCH_SCREEN_SEEK_VOLUME:
                    Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_VOLUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_TOUCH_SCREEN_SEEK_POSITION:
                    Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;

                case JZUserActionStandard.ON_CLICK_START_THUMB:
                    Log.i("USER_EVENT", "ON_CLICK_START_THUMB" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserActionStandard.ON_CLICK_BLANK:
                    Log.i("USER_EVENT", "ON_CLICK_BLANK" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                default:
                    Log.i("USER_EVENT", "unknow");
                    break;
            }
        }
    }
}
