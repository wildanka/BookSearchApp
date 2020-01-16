package com.wildanka.booksearch.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wildanka.booksearch.R
import com.wildanka.booksearch.model.BookData

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {
    private var booksData: List<BookData>? = null

    fun setupBooksData(booksData: List<BookData>?) {
        this.booksData = booksData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book_list, parent, false)
        return BooksViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return booksData?.size ?: 0
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val selectedBookData = booksData?.get(position)
        holder.bind(selectedBookData)
    }

    inner class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_book_title)
        private val tvAuthors = itemView.findViewById<TextView>(R.id.tv_author)
        private val rbRating = itemView.findViewById<RatingBar>(R.id.rb_book_rating)
        private val ivThumbnail = itemView.findViewById<ImageView>(R.id.iv_book_thumbnail)

        fun bind(bookData: BookData?) {
            tvTitle.text = bookData?.bookVolumeInfo?.title
            tvAuthors.text = bookData?.bookVolumeInfo?.authors?.joinToString()
            Glide.with(itemView.context).load(bookData?.bookVolumeInfo?.imageLinks?.thumbnail).into(ivThumbnail)
        }
    }
}