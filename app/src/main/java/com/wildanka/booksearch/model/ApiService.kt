package com.wildanka.booksearch.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("books/v1/volumes")
    fun getBookSearchResult(
        @Query("q") query: String?
    ): Call<BookFeedData>
}