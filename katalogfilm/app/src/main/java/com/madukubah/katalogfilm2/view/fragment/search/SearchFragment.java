package com.madukubah.katalogfilm2.view.fragment.search;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.madukubah.katalogfilm2.R;
import com.madukubah.katalogfilm2.model.Movie;
import com.madukubah.katalogfilm2.view.activity.detail.DetailMovieActivity;
import com.madukubah.katalogfilm2.view.activity.setting.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment
        extends
        Fragment
        implements
        SearchView.MView {


    public SearchFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.search_toolbar_layout)
    Toolbar toolbar_layout;
    @BindView(R.id.search_rv_movie)
    RecyclerView rv_movie;
    @BindView(R.id.search_progressBar_movie)
    ProgressBar progressBar_movie;
    @BindView(R.id.search_tv_empty)
    TextView tv_empty;
    @BindView(R.id.search_buttonCari)
    Button search_buttonCari;
    @BindView(R.id.search_editTextCari)
    EditText search_editTextCari;

    SearchPresenter presenter;
    public static final String SearchFragmentLoad= "SearchFragmentLoad";
    public SharedPreferences pref;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new SearchPresenter(this);
        this.presenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
//        this.presenter = new SearchPresenter(this);
//        this.presenter.start();

        progressBar_movie.setVisibility(View.GONE);
        tv_empty.setVisibility(View.GONE);

        rv_movie.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_movie.setAdapter(this.presenter.adapter);


        search_buttonCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = search_editTextCari.getText().toString().trim();
                if (query.equals("")) {
                    query = "a";
                }
                presenter.loaddata(query);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = getContext().getSharedPreferences(SearchFragmentLoad, MODE_PRIVATE);

        if( savedInstanceState != null ){
            String query = savedInstanceState.getString("query","a" );
            search_editTextCari.setText(query);
            List<Movie> movies = savedInstanceState.getParcelableArrayList("data");
            this.presenter.adapter.replaceAll(movies);
        }else{
            String query = search_editTextCari.getText().toString().trim();
            if (query.equals("")) {
                query = "a";
            }
            if( pref.getBoolean("isload", true) ){
                query = pref.getString("query", "a");
                search_editTextCari.setText(query);
            }
            presenter.loaddata(query);
        }

        pref.edit().putBoolean("isload", false).commit();
        pref.edit().putString("query", "").commit();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) presenter.adapter.getAll());
        String query = search_editTextCari.getText().toString().trim();
        if (query.equals("")) {
            query = "a";
        }
        outState.putString("query", query );
    }



    @Override
    public void onDetach() {
        super.onDetach();
        String query = search_editTextCari.getText().toString().trim();
        if (query.equals("")) {
            query = "a";
        }
        pref.edit().putBoolean("isload", true).commit();
        pref.edit().putString("query", query ).commit();
//        Toast.makeText(getContext(), "onDetach", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_layout);
        }
        toolbar_layout.setTitleTextColor(getResources().getColor(R.color.colorWhite));
    }

    public static SearchFragment create() {
        return new SearchFragment();
    }

    @Override
    public void onItemMovieClick(Movie item) {
        Context context = getContext();
        Intent i = new Intent(context, DetailMovieActivity.class);
        i.putExtra(DetailMovieActivity.MOVIE, item);
        assert context != null;
        context.startActivity(i);
    }

    @Override
    public void setPresenter(SearchView.Presenter presenter) {
        this.presenter = (SearchPresenter) presenter;
    }

    @Override
    public void showLoading() {
        progressBar_movie.setVisibility(View.VISIBLE);
//        tv_empty.setVisibility( View.VISIBLE );
    }

    @Override
    public void hideLoading() {
        progressBar_movie.setVisibility(View.GONE);
        tv_empty.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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
