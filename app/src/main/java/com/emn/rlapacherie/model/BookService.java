package com.emn.rlapacherie.model;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface BookService {

    @GET("books")
    Call<List<Book>> listBooks();

}
