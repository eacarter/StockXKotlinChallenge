package com.erickson.stockxcodechallenge.manager

import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    val Base_URL = "http://www.reddit.com"

    fun retrofitService(): RetrofitService{
        return Retrofit.Builder().
                baseUrl(Base_URL).
                client(okHttpClient()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(MoshiConverterFactory.create()).
                build().
                create(RetrofitService::class.java)
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}