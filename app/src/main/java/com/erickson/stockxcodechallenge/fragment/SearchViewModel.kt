package com.erickson.stockxcodechallenge.fragment


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erickson.stockxcodechallenge.manager.PostManager
import com.erickson.stockxcodechallenge.models.Base
import com.erickson.stockxcodechallenge.models.Children
import com.google.gson.JsonObject
import org.json.JSONObject
import javax.inject.Inject

class SearchViewModel @Inject constructor(val postManager: PostManager): ViewModel() {

    fun getList(subreddit: String): MediatorLiveData<Base> {
        return postManager.getPost(subreddit)
    }
}