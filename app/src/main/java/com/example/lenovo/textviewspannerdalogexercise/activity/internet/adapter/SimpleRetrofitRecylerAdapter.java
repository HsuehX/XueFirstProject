package com.example.lenovo.textviewspannerdalogexercise.activity.internet.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.networkbymyself.simpleretrofit.bean.BeanSimpleRetrofit;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xue on 2018/8/29.
 */

public class SimpleRetrofitRecylerAdapter extends RecyclerView.Adapter<SimpleRetrofitRecylerAdapter.ViewHolderSimpleRetrofit> {
    private List<BeanSimpleRetrofit> mData = new ArrayList<>();
    private Context mContext;
    private String requestType;

    public SimpleRetrofitRecylerAdapter(Context context, List<BeanSimpleRetrofit> list, String type) {
        this.mData = list;
        this.mContext = context;
        requestType = type;
    }

    @Override
    public SimpleRetrofitRecylerAdapter.ViewHolderSimpleRetrofit onCreateViewHolder(ViewGroup parent, int viewType) {
        if ("Android".equals(requestType)) {
            return new ViewHolderSimpleRetrofit(View.inflate(mContext, R.layout.item_simple_retrofit, null));
        } else {
            return new ViewHolderSimpleRetrofit(View.inflate(mContext, R.layout.item_simple_retrofit_image, null));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolderSimpleRetrofit holder, int position) {
        if ("Android".equals(requestType)) {
            holder.title.setText(mData.get(position).getDesc());
            holder.subtitle.setText(mData.get(position).getPublishedAt().toLocaleString());
            if (mData.get(position).getImages() != null && mData.get(position).getImages().size() > 0) {
                holder.image.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(mData.get(position).getImages().get(0)).asBitmap().into(holder.image);
            } else {
                holder.image.setVisibility(View.GONE);
            }
        } else {
//            Glide.with(mContext).load(mData.get(position).getUrl()).asBitmap().into(holder.imageFuli);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolderSimpleRetrofit extends RecyclerView.ViewHolder {

        @Bind(R.id.gank_item_title)
        TextView title;

        @Bind(R.id.gank_item_subtitle)
        TextView subtitle;

        @Bind(R.id.gank_item_image)
        ImageView image;

//        // FULI fragment
//        @Bind(R.id.gank_item_image_fuli)
//        ImageView imageFuli;

//        @Optional
//        @InjectView(R.id.gank_item_title)
//        TextView title;
//
//        @Optional
//        @InjectView(R.id.gank_item_subtitle)
//        TextView subtitle;
//
//        @Optional
//        @InjectView(R.id.gank_item_image)
//        ImageView image;
//
//        // FULI fragment
//        @Optional
//        @InjectView(R.id.gank_item_image_fuli)
//        ImageView imageFuli;

        public ViewHolderSimpleRetrofit(View itemView) {
            super(itemView);
//            ButterKnife.inject(this, itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
