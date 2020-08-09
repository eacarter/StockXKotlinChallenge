package com.erickson.stockxcodechallenge.models

data class RedditPost(
    val author_fullname: String,
    val title: String,
    val subreddit_name_prefixed: String,
    val thumbnail: String,
    val url: String
    )