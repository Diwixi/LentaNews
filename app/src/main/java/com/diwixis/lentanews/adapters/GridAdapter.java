package com.diwixis.lentanews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diwixis.lentaapi.objects.News;
import com.diwixis.lentanews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diwixis on 26.10.2017.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<News> newsList = new ArrayList<>();
    private int width = 0;

    public void setData(List<News> list, int width){
        newsList = list;
        this.width = width;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.text.setText(news.getTitle());
        if (news.getEnclosure() != null) {
            Glide.with(holder.image.getContext())
                    .load(news.getUrl())
                    .override(width, width)
                    .centerCrop()
                    .into(holder.image);
        } else {
            Glide.with(holder.image.getContext())
                    .load(R.drawable.lenta_default)
                    .override(width, width)
                    .centerCrop()
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_grid_news);
            text = itemView.findViewById(R.id.title_grid_news);
        }
    }
}
