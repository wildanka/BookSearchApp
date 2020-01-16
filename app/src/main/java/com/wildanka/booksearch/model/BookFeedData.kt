package com.wildanka.booksearch.model

import com.google.gson.annotations.SerializedName

data class BookFeedData(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("totalItems") var totalItems: String? = null,
    @SerializedName("items") var items: List<BookData>? = null
)

data class BookData(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("etag") var etag: String? = null,
    @SerializedName("selfLink") var selfLink: String? = null,
    @SerializedName("volumeInfo") var bookVolumeInfo: BookVolumeInfo? = null,
    @SerializedName("saleInfo") var bookSaleInfo: BookSaleInfo? = null
)

data class BookSaleInfo(
    @SerializedName("country") var country: String? = null,
    @SerializedName("saleability") var saleability: String? = null,
    @SerializedName("isEbook") var isEbook: Boolean? = null
)

//@SerializedName("readingModes") var readingModes: List<String>? = null,
data class BookVolumeInfo(
    @SerializedName("title") var title: String? = null,
    @SerializedName("authors") var authors: List<String>? = null,
    @SerializedName("publisher") var publisher: String? = null,
    @SerializedName("publishedDate") var publishedDate: String? = null,
    @SerializedName("industryIdentifiers") var industryIdentifiers: List<IndustryIdentifier>? = null,
    @SerializedName("pageCount") var pageCount: Long? = null,
    @SerializedName("printType") var printType: String? = null,
    @SerializedName("categories") var categories: List<String>? = null,
    @SerializedName("averageRating") var averageRating: Double? = null,
    @SerializedName("ratingsCount") var ratingsCount: Long? = null,
    @SerializedName("maturityRating") var maturityRating: String? = null,
    @SerializedName("imageLinks") var imageLinks: ImageLinks? = null,
    @SerializedName("language") var language: String? = null
)

data class ImageLinks(
    @SerializedName("smallThumbnail") var smallThumbnail: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null
)

data class IndustryIdentifier(
    @SerializedName("type") var type: String? = null,
    @SerializedName("identifier") var kind: String? = null
)
