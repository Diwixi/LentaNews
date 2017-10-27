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

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.ViewHolder> {

    private List<News> newsList = new ArrayList<>();
    private int width = 0;

    public void setData(List<News> list, int width){
        newsList = list;
        this.width = width;
    }

    @Override
    public LinearAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linear_news, parent, false);
        return new LinearAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LinearAdapter.ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.description.setText(news.getDescription());
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
        TextView title;
        TextView description;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_linear_news);
            title = itemView.findViewById(R.id.title_linear_news);
            description = itemView.findViewById(R.id.description_linear_news);
        }
    }
}
