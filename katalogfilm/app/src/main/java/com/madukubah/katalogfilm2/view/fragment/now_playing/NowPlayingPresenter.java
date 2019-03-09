package com.madukubah.katalogfilm2.view.fragment.now_playing;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.madukubah.katalogfilm2.adapter.MovieAdapter;
import com.madukubah.katalogfilm2.api.ApiUtils;
import com.madukubah.katalogfilm2.api.MovieService;
import com.madukubah.katalogfilm2.model.Movie;
import com.madukubah.katalogfilm2.model.response.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingPresenter
    implements NowPlayingView.Presenter
{
    private  NowPlayingView.MView view;
    public MovieAdapter adapter ;
    private Context context ;
    private MovieService movieService;
    public NowPlayingPresenter( NowPlayingView.MView view )
    {
        this.view = view;
    }
    @Override
    public void loaddata() {
        this.view.showLoading();
        Log.d("AAA", "");
        Call<MovieResponse> call =   movieService.getNowPlaying();
        call.enqueue(new Callback<MovieResponse>(){
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    onloadData(response.body().getResults());
                }else{
                    loadFailed();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("AAA", "terjadi kesalahan"+ t);
            }
        });
    }

    @Override
    public void onloadData(List<Movie> item) {
        if( item.size() != 0 )
        {
            Log.d("AAA", item.get(0).getTitle());
            adapter.replaceAll( item );
        }

        adapter.notifyDataSetChanged();
        this.view.hideLoading();
    }

    private void loadFailed() {
        this.view.hideLoading();
    }

    @Override
    public void start() {
        context = ( (Fragment) this.view ).getContext();
        adapter = new MovieAdapter( this.context );

        adapter.setMovieOnItemClickListener( new MovieAdapter.MovieOnItemClickListener(){
            @Override
            public void OnItemClickMovie(Movie item) {
                view.onItemMovieClick(item);
            }
        });

        movieService = ApiUtils.movieService();
    }
}
