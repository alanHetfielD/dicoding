package com.madukubah.katalogfilm2.config.base;

public interface BaseView<T>
{
    void setPresenter( T presenter  );
    void showLoading();
    void hideLoading();
}