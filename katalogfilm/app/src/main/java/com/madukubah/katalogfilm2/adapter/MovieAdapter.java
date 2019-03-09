package com.madukubah.katalogfilm2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.madukubah.katalogfilm2.BuildConfig;
import com.madukubah.katalogfilm2.R;
import com.madukubah.katalogfilm2.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter
        extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private Context context ;
    private List<Movie> item = new ArrayList<>();
    public MovieAdapter( Context context )
    {
        this.context = context;
    }
    public void replaceAll(List<Movie> items) {
        item.clear();
        item = items;
        notifyDataSetChanged();
    }
    public List<Movie> getAll( ) {

        return item ;

    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                LayoutInflater.from(this.context).inflate(R.layout.item_card_view_movie, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(item.get( position ) );
    }

    @Override
    public int getItemCount() {
        return this.item.size();
    }


//    ViewHolder
    public class MovieViewHolder
        extends RecyclerView.ViewHolder
    {
        ImageView img_item_photo;
        TextView tv_title;
        TextView tv_overview;

        public MovieViewHolder(View itemView) {
            super(itemView);
        }
        public void bind(final Movie item)
        {
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_overview = itemView.findViewById(R.id.tv_overview);
            img_item_photo = itemView.findViewById(R.id.img_item_photo);

            tv_title.setText( item.getTitle() );
            String s = item.getOverview();
            if(item.getOverview().length() >= 100)
            {
                s = item.getOverview().substring(1, 90);
                s += "...";
            }
            tv_overview.setText( s );
            Glide.with(itemView.getContext())
                    .load(BuildConfig.IMAGE + "w154" + item.getPoster_path())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.placeholder)
                            .centerCrop()
                    )
                    .into(img_item_photo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getOnOptionDialogListener().OnItemClickMovie(item);
                }
            });
        }
    }

    MovieOnItemClickListener movieOnItemClickListener;

    public interface MovieOnItemClickListener {
        void OnItemClickMovie(Movie item);
    }

    public MovieOnItemClickListener getOnOptionDialogListener() {
        return movieOnItemClickListener;
    }

    public void setMovieOnItemClickListener(MovieOnItemClickListener movieOnItemClickListener) {
        this.movieOnItemClickListener = movieOnItemClickListener;
    }
}
