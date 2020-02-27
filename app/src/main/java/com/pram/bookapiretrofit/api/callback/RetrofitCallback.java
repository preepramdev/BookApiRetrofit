package com.pram.bookapiretrofit.api.callback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Custom CallBack From retrofit2 CallBack
 */
public interface RetrofitCallback<T> {
    void onResponse(Call<T> call, Response<T> response);

    void onFailure(Call<T> call, Throwable t);
}
