package com.example.booklibrary.tools.data

//@Parcelize
//data class DataBookItem(
//    val authors: List<String>,
//    val categories: List<String>,
//    val isbn: String,
//    val longDescription: String,
//    val pageCount: Int,
////    val publishedDate: PublishedDate,
//    val shortDescription: String,
//    val status: String,
//    val thumbnailUrl: String,
//    val title: String
//): Parcelable

data class DataBookItem(
    val result: ArrayList<Result>
) {
    data class Result(

        val authors: ArrayList<String>,
        val categories: ArrayList<String>,
        val isbn: String,
        val longDescription: String,
        val pageCount: String,
        val publishedDate: PublishedDate,
//        val shortDescription: String,
        val status: String,
        val thumbnailUrl: String,
        val title: String

    )
}