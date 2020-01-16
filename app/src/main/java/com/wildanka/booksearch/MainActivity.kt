package com.wildanka.booksearch

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wildanka.booksearch.view.BooksAdapter
import com.wildanka.booksearch.viewmodel.BookSearchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val rvBookSearchResults = findViewById<RecyclerView>(R.id.rv_book_search_results)
        val adapter = BooksAdapter()
        rvBookSearchResults.layoutManager = LinearLayoutManager(this)
        rvBookSearchResults.adapter = adapter

        val viewModel: BookSearchViewModel = ViewModelProviders.of(this).get(BookSearchViewModel::class.java)
        viewModel.getBookSearchResults("{keyword")?.observe(this, Observer {
            if (it != null) {
                Log.e("TES", it[0].bookVolumeInfo?.title!!)
                adapter.setupBooksData(it)
            }else{
                Log.e("TES", "NULL BRADAH")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
