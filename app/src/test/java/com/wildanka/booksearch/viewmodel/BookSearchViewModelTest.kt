package com.wildanka.booksearch.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.example.livedatabuilder.util.getOrAwaitValue
import com.wildanka.booksearch.model.BookData
import com.wildanka.booksearch.model.BookVolumeInfo
import com.wildanka.booksearch.repository.BookSearchRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class BookSearchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var bookSearchViewModel: BookSearchViewModel

    @Mock
    private lateinit var bookSearchRepository: BookSearchRepository

    @Before
    fun setUp() {
        bookSearchRepository = mock(BookSearchRepository::class.java)
        bookSearchViewModel = BookSearchViewModel()
    }

    @Test
    fun getBookSearchResults() {
        val expectedSizez = 2
        val actual = bookSearchViewModel.getBookSearchResults("{keyword")?.getOrAwaitValue()
        Assert.assertNotNull(actual)
        Assert.assertTrue(
            "MovieList Data",
            actual?.size ?: 0 > expectedSizez
        )

        //region expected output
        val dummyBookData = BookData(
            "books#volume",
            "rSV1uwCy-vQC",
            "kYbzyi/OXzc",
            "\"https://www.googleapis.com/books/v1/volumes/rSV1uwCy-vQC\"",
            BookVolumeInfo(
                "Teknik Rahasia Keyword Google Pemula",
                null,
                "Elex Media Komputindo",
                null,
                null
            )
        )

        //create dummy data
        var dummyBookLiveData: LiveData<List<BookData>>? = null //construct dummy liveData to be returned later
        val dummyBookSearchResultList: MutableList<BookData> = mutableListOf() //list of BookData
        for (i in 0..5) dummyBookSearchResultList.add(dummyBookData)

        val dummyBookMutableList: List<BookData> = dummyBookSearchResultList //convert MutableList to List
        val dummyBookMutableLiveDataList: MutableLiveData<List<BookData>> = MutableLiveData() //construct MutableLiveData<List<BookData>>
        dummyBookMutableLiveDataList.value = dummyBookMutableList
        //endregion expected output
        val mockBookSearchViewModel = mock(BookSearchViewModel::class.java)

        //return make getBookSearchResults return the dummy data
        `when`(mockBookSearchViewModel.getBookSearchResults("{keyword")).thenReturn(dummyBookMutableLiveDataList)

        //verify onChanged livedata on getBookSearchResults method
        val observer: Observer<List<BookData>> = mock(Observer::class.java) as Observer<List<BookData>>
        mockBookSearchViewModel.getBookSearchResults("{keyword")?.observeForever(observer)
        verify(observer).onChanged(dummyBookSearchResultList)
    }


}