package com.madukubah.katalogfilm2.view.activity.detail;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.madukubah.katalogfilm2.BuildConfig;
import com.madukubah.katalogfilm2.R;
import com.madukubah.katalogfilm2.database.FavoriteHelper;
import com.madukubah.katalogfilm2.database.FavoriteTabel;
import com.madukubah.katalogfilm2.model.Movie;
import com.madukubah.katalogfilm2.widget.ImagesBannerWidget;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.madukubah.katalogfilm2.provider.DatabaseContract.CONTENT_URI;

public class DetailMovieActivity extends AppCompatActivity {
    //    declaration
    @BindView(R.id.circleImageView_poster)
    CircleImageView circleImageView_poster;
    @BindView(R.id.textView_title)
    TextView textView_title;
    @BindView(R.id.textView_tagline)
    TextView textView_tagline;
    @BindView(R.id.textView_rating)
    TextView textView_rating;
    @BindView(R.id.textView_duration)
    TextView textView_duration;
    @BindView(R.id.textView_language)
    TextView textView_language;
    @BindView(R.id.textView_genres)
    TextView textView_genres;
    @BindView(R.id.textView_release_date)
    TextView textView_release_date;
    @BindView(R.id.textView_overview)
    TextView textView_overview;
    @BindView(R.id.button_favorite)
    Button button_favorite;

    private FavoriteHelper favoriteHelper;
    private Boolean isFavorite = false;
    //    declaration
    public static final String MOVIE = "MOVIE";
    private Movie movie ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind( this);
        movie = getIntent().getParcelableExtra(MOVIE);
        setUp();
        isExist();
    }
    private void setUp()
    {
        if( movie != null )
        {
            textView_title.setText(movie.getTitle());
            textView_tagline.setText(movie.getTitle());
            textView_release_date.setText(movie.getRelease_date());
            textView_overview.setText(movie.getOverview());
            textView_rating.setText(""+movie.getVote_average());

            textView_genres.setText("-");

            textView_duration.setText("-");
            textView_language.setText( "EN" );

            Glide.with(this)
                    .load(BuildConfig.IMAGE + "w154" + movie.getBackdrop_path())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.placeholder)
                            .centerCrop()
                    )
                    .into(circleImageView_poster);
        }

        button_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) FavoriteRemove();
                else FavoriteSave();

                isFavorite = !isFavorite;
                favoriteSet();

//                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(DetailMovieActivity.this);
//                ComponentName thisWidget = new ComponentName(DetailMovieActivity.this, ImagesBannerWidget.class);
//                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
//                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view);
            }
        });
    }

    private void isExist() {
        favoriteHelper = new FavoriteHelper(this);
        favoriteHelper.open();

        Cursor cursor = getContentResolver().query(
                Uri.parse(CONTENT_URI + "/" + movie.getId()),
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;
            cursor.close();
        }
        favoriteSet();
    }

    private void favoriteSet() {
        if (isFavorite) button_favorite.setText(R.string._throw);
        else button_favorite.setText(R.string.save);
    }
    private void FavoriteSave() {
        //Log.d("TAG", "FavoriteSave: " + item.getId());
        ContentValues cv = new ContentValues();
        cv.put(FavoriteTabel.COLUMN_ID, movie.getId());
        cv.put(FavoriteTabel.COLUMN_TITLE, movie.getTitle());
        cv.put(FavoriteTabel.COLUMN_BACKDROP, movie.getBackdrop_path());
        cv.put(FavoriteTabel.COLUMN_POSTER, movie.getPoster_path());
        cv.put(FavoriteTabel.COLUMN_RELEASE_DATE, movie.getRelease_date());
        cv.put(FavoriteTabel.COLUMN_VOTE, movie.getVote_average());
        cv.put(FavoriteTabel.COLUMN_OVERVIEW, movie.getOverview());

        getContentResolver().insert(CONTENT_URI, cv);
        Toast.makeText(this, R.string.save_data, Toast.LENGTH_SHORT).show();
    }

    private void FavoriteRemove() {
        getContentResolver().delete(
                Uri.parse(CONTENT_URI + "/" + movie.getId()),
                null,
                null
        );
        Toast.makeText(this, R.string.delete_data, Toast.LENGTH_SHORT).show();
    }
}
