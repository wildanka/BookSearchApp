package com.wildanka.booksearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildanka.booksearch.model.BookData
import com.wildanka.booksearch.repository.BookSearchRepository
import retrofit2.http.Query

class BookSearchViewModel : ViewModel(){
    private var repo = BookSearchRepository()
    private var bookSearchResults : LiveData<List<BookData>>? = null
    fun getBookSearchResults(query: String?) : LiveData<List<BookData>>?{
        loadBookSearchResult(query)
        return bookSearchResults
    }

    private fun loadBookSearchResult(query: String?){
        bookSearchResults = repo.fetchSearchBookResult(query)
    }
}