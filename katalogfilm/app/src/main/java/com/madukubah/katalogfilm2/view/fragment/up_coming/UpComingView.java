package com.madukubah.katalogfilm2.view.fragment.up_coming;

import com.madukubah.katalogfilm2.config.base.BasePresenter;
import com.madukubah.katalogfilm2.config.base.BaseView;
import com.madukubah.katalogfilm2.model.Movie;
import com.madukubah.katalogfilm2.view.fragment.now_playing.NowPlayingView;

import java.util.List;

public interface UpComingView {
    interface Presenter extends BasePresenter
    {
        void loaddata(  );
        void onloadData( List<Movie> item );
    }

    interface MView
            extends BaseView<NowPlayingView.Presenter>
    {
        void onItemMovieClick( Movie item );
    }
}
