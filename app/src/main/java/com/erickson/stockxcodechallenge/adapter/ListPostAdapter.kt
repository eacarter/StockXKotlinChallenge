package com.erickson.stockxcodechallenge.adapter

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.erickson.stockxcodechallenge.R
import com.erickson.stockxcodechallenge.fragment.WebFragment
import com.erickson.stockxcodechallenge.models.Children
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_reddit.view.*

class ListPostAdapter (val post: List<Children>):
    RecyclerView.Adapter<ListPostAdapter.ListPostViewHolder>(), View.OnClickListener {


    class ListPostViewHolder(val v1: View) : RecyclerView.ViewHolder(v1)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reddit, parent,false)
        return ListPostViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return post.size
    }

    override fun onBindViewHolder(holder: ListPostViewHolder, position: Int) {

        holder.v1.item_title.text = post[position].data.title
        if(post[position].data.thumbnail == "default" || post[position].data.thumbnail == ""){
            holder.v1.item_image.visibility = View.GONE
        }
        else{
            Picasso
                .get()
                .load(post[position].data.thumbnail)
                .into(holder.v1.item_image)
        }
        holder.v1.item_user.text = "u/" + post[position].data.author_fullname
        holder.v1.item_votes.text = post[position].data.subreddit_name_prefixed

        holder.v1.item.setOnClickListener(View.OnClickListener {
            val activity : AppCompatActivity = it?.context as AppCompatActivity
            val fragment = WebFragment()
            val bundle = Bundle()

            bundle.putString("url", post[position].data.url)

            fragment.arguments = bundle

            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack("")
                .commit()
        })

    }

    override fun onClick(v: View?) {

    }


}