package com.madukubah.katalogfilm2.model.response;

import com.google.gson.annotations.SerializedName;
import com.madukubah.katalogfilm2.model.Movie;

import java.util.List;

public class MovieResponse {
    @SerializedName("page")private int page;
    @SerializedName("total_results")private int total_results;
    @SerializedName("total_pages")private int total_pages;
    @SerializedName("results")private List<Movie> results;

//    GETTER SETTER
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
//    GETTER SETTER

}
