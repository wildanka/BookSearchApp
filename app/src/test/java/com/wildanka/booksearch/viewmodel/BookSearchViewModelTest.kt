package com.wildanka.booksearch.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.example.livedatabuilder.util.getOrAwaitValue
import com.wildanka.booksearch.model.BookData
import com.wildanka.booksearch.model.BookFeedData
import com.wildanka.booksearch.repository.BookSearchRepository
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito

class BookSearchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var bookSearchViewModel: BookSearchViewModel

    @Mock
    private lateinit var bookSearchRepository: BookSearchRepository

    @Before
    fun setUp() {
        bookSearchRepository = Mockito.mock(BookSearchRepository::class.java)
        bookSearchViewModel = BookSearchViewModel()
    }

    @Test
    fun getBookSearchResults() {
        var expectedResult : MutableList<BookData> = mutableListOf<BookData>()
        val bookData1 = BookData(
            "books#volume",
            "iQmPNDIAskUC",
            "nsl8g+Hh21k"
        )
        expectedResult.add(bookData1)
        bookSearchViewModel.getBookSearchResults("")
        Assert.assertEquals(
            expectedResult.toString(),
            bookSearchViewModel.getBookSearchResults("")?.getOrAwaitValue()?.toString()
        )
    }
}