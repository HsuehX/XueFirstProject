package com.example.lenovo.textviewspannerdalogexercise.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.textviewspannerdalogexercise.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xue on 2018/8/23.
 */

public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.ViewHolder> {
    /**
     * 图片路径url
     */
    private List<String> mImageUrlList = new ArrayList<>();
    private Context mContext;
    //    public static final int MAX_NUMBER = 9;//最大选择图片的数量
    private OnPickerListener mOnPickerListener;

    private ImageLoader imageLoader;
//    private DisplayImageOptions

    public ShowImageAdapter(Context context) {
        this.mContext = context;
    }

    public ShowImageAdapter(Context context, List<String> imageUrlList) {
        this.mContext = context;
        this.mImageUrlList = imageUrlList;
        imageLoader = ImageLoader.getInstance();
        imageLoader.init((ImageLoaderConfiguration.createDefault(mContext)));
    }


    public void setImageUrlList(List<String> imageUrlList) {
        this.mImageUrlList = imageUrlList;
//        this.mImageUrlList.addAll(imageUrlList);
        this.notifyDataSetChanged();
//        imageLoader = ImageLoader.getInstance();
//        imageLoader.init((ImageLoaderConfiguration.createDefault(mContext)));
    }

    public void setOnPickerListener(OnPickerListener onPickerListener) {
        mOnPickerListener = onPickerListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_photo, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //判断位置显示的内容
        // 如果当前图片的数量是0，或者大于所选择图片的数量则显示一个带选择图片的image，同时隐藏删除按钮
//        if (mImageUrlList.size() == 0 || mImageUrlList.size() == position) {
//            holder.mImageViewDel.setVisibility(View.GONE);
//            holder.mImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),
//                    R.mipmap.ic_launcher));
//        } else {
//            //否则每个图片直接显示删除按钮，并加载显示图片
//            holder.mImageViewDel.setVisibility(View.VISIBLE);
//            //glide加载图片
//            Glide.with(mContext)
//                    .load(mImageUrlList.get(position))
//                    .placeholder(R.mipmap.ic_launcher)
//                    .into(holder.mImageView);
//
//        }

        //第一个是添加图片
        if (position == 0) {
            holder.mImageViewDel.setVisibility(View.GONE);
            holder.mImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                    R.mipmap.ic_launcher));
        } else {
            //否则每个图片直接显示删除按钮，并加载显示图片
            holder.mImageViewDel.setVisibility(View.VISIBLE);
            //glide加载图片
            Glide.with(mContext)
                    .load(mImageUrlList.get(position - 1))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.mImageView);

        }


    }

    @Override
    public int getItemCount() {
//        return mImageUrlList.size() < MAX_NUMBER ? mImageUrlList.size() + 1 : mImageUrlList.size();
        return mImageUrlList.size() + 1;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView, mImageViewDel;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewDel = (ImageView) itemView.findViewById(R.id.iv_del);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_pic);
            mImageViewDel.setOnClickListener(this);
            mImageView.setOnClickListener(this);
//            if (getLayoutPosition() == 0) {
//                return;
//            } else {
//                String imageUri = "drawable://" + R.drawable.awesome; // from drawables (only images, non-9patch)
//                imageLoader.displayImage(imageUri, mImageView);
//            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_pic:
                    mOnPickerListener.onPicker(getLayoutPosition());
                    break;
                case R.id.iv_del:
                    mImageUrlList.remove(getLayoutPosition() - 1);
                    notifyItemRemoved(getLayoutPosition());
                    break;
                default:
                    break;
            }

        }

    }

    /**
     * recyclerView设置的监听接口
     */
    public interface OnPickerListener {
        void onPicker(int position);
    }


}