package com.madukubah.temankatalogfilm.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.madukubah.temankatalogfilm.database.FavoriteTabel.COLUMN_BACKDROP;
import static com.madukubah.temankatalogfilm.database.FavoriteTabel.COLUMN_OVERVIEW;
import static com.madukubah.temankatalogfilm.database.FavoriteTabel.COLUMN_POSTER;
import static com.madukubah.temankatalogfilm.database.FavoriteTabel.COLUMN_RELEASE_DATE;
import static com.madukubah.temankatalogfilm.database.FavoriteTabel.COLUMN_TITLE;
import static com.madukubah.temankatalogfilm.database.FavoriteTabel.COLUMN_VOTE;
import static com.madukubah.temankatalogfilm.provider.DatabaseContract.getColumnDouble;
import static com.madukubah.temankatalogfilm.provider.DatabaseContract.getColumnInt;
import static com.madukubah.temankatalogfilm.provider.DatabaseContract.getColumnString;

public class Movie implements Parcelable {
    @SerializedName("id")private int id;
    @SerializedName("title")private String title;
    @SerializedName("original_title")private String original_title;
    @SerializedName("original_language")private String original_language;
    @SerializedName("overview")private String overview;

    @SerializedName("poster_path")private String poster_path;
    @SerializedName("backdrop_path")private String backdrop_path;

    @SerializedName("vote_count")private int vote_count;
    @SerializedName("vote_average")private double vote_average;
    @SerializedName("popularity")private double popularity;

    @SerializedName("adult")private boolean adult;
    @SerializedName("genre_ids")private List<Integer> genre_ids;

    @SerializedName("release_date")private String release_date;
//    getter setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
//    getter setter

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.original_title);
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
        dest.writeInt(this.vote_count);
        dest.writeDouble(this.vote_average);
        dest.writeDouble(this.popularity);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeList(this.genre_ids);
        dest.writeString(this.release_date);
    }

    public Movie() {
    }

    public Movie(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, COLUMN_TITLE);
        this.backdrop_path = getColumnString(cursor, COLUMN_BACKDROP);
        this.poster_path = getColumnString(cursor, COLUMN_POSTER);
        this.release_date = getColumnString(cursor, COLUMN_RELEASE_DATE);
        this.vote_average = getColumnDouble(cursor, COLUMN_VOTE);
        this.overview = getColumnString(cursor, COLUMN_OVERVIEW);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.vote_count = in.readInt();
        this.vote_average = in.readDouble();
        this.popularity = in.readDouble();
        this.adult = in.readByte() != 0;
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.release_date = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
