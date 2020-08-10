package com.erickson.stockxcodechallenge.manager

import android.app.Service
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.erickson.stockxcodechallenge.models.Base
import com.erickson.stockxcodechallenge.models.Children
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class PostManager @Inject constructor(){

    fun getPost(subreddit: String): MediatorLiveData<Base> {
        val post = MediatorLiveData<Base>()

        val service = RetrofitFactory.retrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getHome(subreddit)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        post.value = response.body()
                    } else {
                      Log.d("POST","Error: ${response.code()} ")

                    }
                } catch (e: HttpException) {
                    Log.d("POST","Exception ${e.message}")

                } catch (e: Throwable) {
                    Log.d("POST","Ooops: Something else went wrong")
                }
            }
        }

        return post
    }
}