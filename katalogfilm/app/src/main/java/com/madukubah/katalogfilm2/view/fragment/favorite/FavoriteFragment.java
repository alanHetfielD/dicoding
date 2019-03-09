package com.madukubah.katalogfilm2.view.fragment.favorite;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.madukubah.katalogfilm2.R;
import com.madukubah.katalogfilm2.adapter.MovieAdapter;
import com.madukubah.katalogfilm2.model.Movie;
import com.madukubah.katalogfilm2.view.activity.detail.DetailMovieActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.madukubah.katalogfilm2.provider.DatabaseContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    public FavoriteFragment() {
        // Required empty public constructor
    }

    public MovieAdapter adapter ;
    private Context context ;
    @BindView(R.id.toolbar_layout)
    Toolbar toolbar_layout;
    @BindView(R.id.rv_favorite)
    RecyclerView rv_favorite;
    @BindView(R.id.button_refresh)
    Button button_refresh;

    public static FavoriteFragment create()
    {
        return new FavoriteFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        setUp();
        new loadDataAsync().execute();

        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new loadDataAsync().execute();
            }
        });

        return view;
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

    private void setUp()
    {
        adapter = new MovieAdapter( this.context );
        rv_favorite.setLayoutManager(new LinearLayoutManager(this.context));
        rv_favorite.setAdapter(adapter);

        adapter.setMovieOnItemClickListener( new MovieAdapter.MovieOnItemClickListener(){
            @Override
            public void OnItemClickMovie(Movie item) {
                Intent i = new Intent(context ,DetailMovieActivity.class );
                i.putExtra(DetailMovieActivity.MOVIE, item );
                assert context != null;
                context.startActivity(i);
            }
        });
    }

    private class loadDataAsync extends AsyncTask<Void, Void, List<Movie>> {
        @Override
        protected List<Movie> doInBackground(Void... voids) {
            Cursor cursor = context.getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
            cursor.moveToFirst();
            ArrayList<Movie> arrayList = new ArrayList<>();
            Movie movie;
            if (cursor.getCount() > 0) {
                do {
                    movie = new Movie( cursor );
                    arrayList.add(movie);
                    cursor.moveToNext();

                } while (!cursor.isAfterLast());
            }
            cursor.close();
            return arrayList;
        }


        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);

            adapter.replaceAll(movies);
        }
    }

}
