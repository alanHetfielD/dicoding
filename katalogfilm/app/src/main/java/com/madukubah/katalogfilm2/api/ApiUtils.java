package com.madukubah.katalogfilm2.api;

import com.madukubah.katalogfilm2.BuildConfig;

public class ApiUtils {

    public static MovieService movieService()
    {
        return RetrofitClient
                .getClient(BuildConfig.END_POINT)
                .create(MovieService.class);
    }
}
