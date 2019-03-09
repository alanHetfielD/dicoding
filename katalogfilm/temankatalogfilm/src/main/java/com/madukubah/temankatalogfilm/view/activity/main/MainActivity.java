package com.madukubah.temankatalogfilm.view.activity.main;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.madukubah.temankatalogfilm.R;
import com.madukubah.temankatalogfilm.adapter.MovieAdapter;
import com.madukubah.temankatalogfilm.model.Movie;
import com.madukubah.temankatalogfilm.view.activity.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.madukubah.temankatalogfilm.provider.DatabaseContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity {
    public MovieAdapter adapter ;

    @BindView(R.id.rv_favorite)
    RecyclerView rv_favorite;
    @BindView(R.id.button_refresh)
    Button button_refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUp();
        new loadDataAsync().execute();

        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new loadDataAsync().execute();
            }
        });
    }

    private void setUp()
    {
        adapter = new MovieAdapter( this);
        rv_favorite.setLayoutManager(new LinearLayoutManager(this));
        rv_favorite.setAdapter(adapter);

        adapter.setMovieOnItemClickListener( new MovieAdapter.MovieOnItemClickListener(){
            @Override
            public void OnItemClickMovie(Movie item) {
                Intent i = new Intent(MainActivity.this ,DetailActivity.class );
                i.putExtra(DetailActivity.MOVIE, item );

                startActivity(i);
            }
        });
    }

    private class loadDataAsync extends AsyncTask<Void, Void, List<Movie>> {
        @Override
        protected List<Movie> doInBackground(Void... voids) {
            Cursor cursor = getContentResolver().query(
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
