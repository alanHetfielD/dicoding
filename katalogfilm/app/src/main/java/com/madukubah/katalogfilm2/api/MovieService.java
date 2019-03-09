package com.madukubah.katalogfilm2.api;

import com.madukubah.katalogfilm2.model.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("search/movie")
    Call<MovieResponse> getSearchMovie(
            @Query("query") String query
    );
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(
    );
    @GET("movie/upcoming")
    Call<MovieResponse> getUpComing(
    );
}
