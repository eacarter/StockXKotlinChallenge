package com.erickson.stockxcodechallenge.manager

import com.erickson.stockxcodechallenge.models.Base
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService{
    @GET("{subreddit}.json")
    suspend fun getHome(@Path("subreddit", encoded = true) sub: String): Response<Base>
}