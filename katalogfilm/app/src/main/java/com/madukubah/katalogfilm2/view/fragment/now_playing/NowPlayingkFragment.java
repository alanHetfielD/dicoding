package com.madukubah.katalogfilm2.view.fragment.now_playing;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.madukubah.katalogfilm2.R;
import com.madukubah.katalogfilm2.model.Movie;
import com.madukubah.katalogfilm2.view.activity.detail.DetailMovieActivity;
import com.madukubah.katalogfilm2.view.activity.main.MainActivity;
import com.madukubah.katalogfilm2.view.activity.setting.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingkFragment
        extends
            Fragment
        implements
           NowPlayingView.MView
{


    public NowPlayingkFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.toolbar_layout)
    Toolbar toolbar_layout;
    @BindView(R.id.rv_movie)
    RecyclerView rv_movie;
    @BindView(R.id.progressBar_movie)
    ProgressBar progressBar_movie;
    @BindView(R.id.tv_empty)
    TextView tv_empty;

    NowPlayingPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playingk, container, false);
        ButterKnife.bind( this, view );
        this.presenter = new NowPlayingPresenter( this );
        this.presenter.start();

        progressBar_movie.setVisibility( View.GONE );
        tv_empty.setVisibility( View.GONE );

        rv_movie.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_movie.setAdapter( this.presenter.adapter );

        if( savedInstanceState != null ){
            List<Movie> movies = savedInstanceState.getParcelableArrayList("data");
            this.presenter.adapter.replaceAll(movies);
        }else{
            this.presenter.loaddata();
        }


        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) presenter.adapter.getAll());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
        if(getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_layout);
        }
        toolbar_layout.setTitleTextColor( getResources().getColor(R.color.colorWhite) );
    }

    public static NowPlayingkFragment create()
    {
        return new NowPlayingkFragment();
    }

    @Override
    public void onItemMovieClick(Movie item) {
        Context context = getContext();
        Intent i = new Intent(context ,DetailMovieActivity.class );
        i.putExtra(DetailMovieActivity.MOVIE, item );
        assert context != null;
        context.startActivity(i);
    }

    @Override
    public void setPresenter(NowPlayingView.Presenter presenter) {
        this.presenter = (NowPlayingPresenter) presenter;
    }

    @Override
    public void showLoading() {
        progressBar_movie.setVisibility( View.VISIBLE );
//        tv_empty.setVisibility( View.VISIBLE );
    }

    @Override
    public void hideLoading() {
        progressBar_movie.setVisibility( View.GONE );
        tv_empty.setVisibility( View.GONE );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.clear();
        inflater.inflate(R.menu.setting, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.locale) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        if (item.getItemId() == R.id.reminder) {
            Intent intent = new Intent(getContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
