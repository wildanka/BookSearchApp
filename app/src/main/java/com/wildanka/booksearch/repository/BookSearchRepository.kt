package com.wildanka.booksearch.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wildanka.booksearch.model.ApiService
import com.wildanka.booksearch.model.BookData
import com.wildanka.booksearch.model.BookFeedData
import com.wildanka.booksearch.util.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookSearchRepository {
    val TAG = "BookSearchRepo"
    private val apiService: ApiService = ApiClient.createService(ApiService::class.java)

    fun fetchSearchBookResult(query: String?): MutableLiveData<List<BookData>> {
        val resultDatas = MutableLiveData<List<BookData>>()
        val call = apiService.getBookSearchResult(query)
        call.enqueue(object : Callback<BookFeedData?> {
            override fun onFailure(call: Call<BookFeedData?>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<BookFeedData?>, response: Response<BookFeedData?>) {
                if (response.code() == 200) {
                    resultDatas.value = response.body()?.items
                    Log.e(TAG, resultDatas.value?.get(0)?.bookVolumeInfo?.title)
                }else{

                    Log.e(TAG, response.code().toString())
                }
            }
        })

        return resultDatas
    }
}