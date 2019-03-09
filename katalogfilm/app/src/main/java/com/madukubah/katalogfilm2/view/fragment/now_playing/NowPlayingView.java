package com.madukubah.katalogfilm2.view.fragment.now_playing;

import com.madukubah.katalogfilm2.config.base.BasePresenter;
import com.madukubah.katalogfilm2.config.base.BaseView;
import com.madukubah.katalogfilm2.model.Movie;

import java.util.List;

public interface NowPlayingView {
    interface Presenter extends BasePresenter
    {
        void loaddata(  );
        void onloadData( List<Movie> item );
    }

    interface MView
            extends BaseView<Presenter>
    {
        void onItemMovieClick( Movie item );
    }
}
