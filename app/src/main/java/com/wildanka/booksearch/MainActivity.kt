package com.wildanka.booksearch

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wildanka.booksearch.view.BooksAdapter
import com.wildanka.booksearch.viewmodel.BookSearchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: BookSearchViewModel
    private lateinit var adapter: BooksAdapter
    private lateinit var pbLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val rvBookSearchResults = findViewById<RecyclerView>(R.id.rv_book_search_results)
        pbLoading = findViewById<ProgressBar>(R.id.pb_search)
        adapter = BooksAdapter()
        rvBookSearchResults.layoutManager = LinearLayoutManager(this)
        rvBookSearchResults.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(BookSearchViewModel::class.java)
        searchBookData("{keyword")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            editText.hint = "Cari Buku..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query!!.isNotEmpty()) {
                        val searchQuery = query.toLowerCase()
//                        Toast.makeText(this@MainActivity, "Melakukan Pencarian : $searchQuery", Toast.LENGTH_SHORT).show()
                        searchBookData(searchQuery)
                    } else {
                        searchBookData("{keyword")
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        val searchQuery = newText.toLowerCase()
                        searchBookData(searchQuery)
                    } else {
                        searchBookData("{keyword")
                    }
                    return true
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun searchBookData(keyword: String) {
        pbLoading.visibility = View.VISIBLE
        viewModel.getBookSearchResults(keyword)?.observe(this, Observer {
            pbLoading.visibility = View.INVISIBLE
            if (it != null) {
                adapter.setupBooksData(it)
            } else {
                Toast.makeText(this@MainActivity, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
        })
    }

    //Press Back Again to Exit
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.back_twice_to_exit), Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}
