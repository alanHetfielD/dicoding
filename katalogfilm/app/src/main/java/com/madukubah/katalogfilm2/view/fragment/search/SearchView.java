package com.madukubah.katalogfilm2.view.fragment.search;

import com.madukubah.katalogfilm2.config.base.BasePresenter;
import com.madukubah.katalogfilm2.config.base.BaseView;
import com.madukubah.katalogfilm2.model.Movie;

import java.util.List;

public interface SearchView {
    interface Presenter extends BasePresenter
    {
        void loaddata( String query );
        void onloadData( List<Movie> item );
    }

    interface MView
            extends BaseView<Presenter>
    {
        void onItemMovieClick( Movie item );
    }
}
