package com.pram.bookapiretrofit.api.controller;

import com.pram.bookapiretrofit.api.callback.RetrofitCallback;
import com.pram.bookapiretrofit.api.manager.HttpManager;
import com.pram.bookapiretrofit.api.service.BookApiService;
import com.pram.bookapiretrofit.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookApiController {
    BookApiService bookApiService;

    public BookApiController() {
        bookApiService = HttpManager.getInstance().getService();
    }

    public void getBooks(RetrofitCallback callback) {
        Call<List<Book>> call = bookApiService.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public void getBook(int bookId, RetrofitCallback callback) {
        Call<Book> call = bookApiService.getBook(bookId);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public void createBook(Book book, RetrofitCallback callback) {
        Call<Book> call = bookApiService.createBook(book);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public void updatePutBook(Book book, RetrofitCallback callback) {
        Call<Book> call = bookApiService.putBook(book.getId(), book);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public void updatePatchBook(Book book, RetrofitCallback callback) {
        Call<Book> call = bookApiService.patchBook(book.getId(), book);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public void removeBook(int bookId, RetrofitCallback callback) {
        Call<Void> call = bookApiService.removeBook(bookId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
