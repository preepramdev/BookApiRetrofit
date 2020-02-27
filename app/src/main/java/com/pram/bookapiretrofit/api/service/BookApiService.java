package com.pram.bookapiretrofit.api.service;

import com.pram.bookapiretrofit.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookApiService {

    @GET("books")
    Call<List<Book>> getBooks();

    @GET("books/{id}")
    Call<Book> getBook(@Path("id") int bookId);

    @POST("books")
    Call<Book> createBook(@Body Book book );

    @PUT("books/{id}") // you must add all field
    Call<Book> putBook(@Path("id") int bookId, @Body Book book);

    @PATCH("books/{id}") // you can add just some field
    Call<Book> patchBook(@Path("id") int bookId, @Body Book book);

    @DELETE("books/{id}")
    Call<Void> removeBook(@Path("id") int bookId);
}
