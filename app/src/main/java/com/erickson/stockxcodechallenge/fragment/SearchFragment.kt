package com.erickson.stockxcodechallenge.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.erickson.stockxcodechallenge.R
import com.erickson.stockxcodechallenge.adapter.ListPostAdapter
import com.erickson.stockxcodechallenge.databinding.SearchFragmentBinding
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_second.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: SearchFragmentBinding

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        var text: String? = arguments?.getString("sub", "")

        loadContent("/r/"+text)

        Log.d("Search", text)

        binding.searchSwipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            loadContent(text.toString())
            binding.searchSwipe.isRefreshing = false
        })

        return binding.root
    }
    fun loadContent(string: String){
        viewModel.getList(string).observe(viewLifecycleOwner, Observer {
            with(searchRecyclerView){
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this.context)
                adapter = ListPostAdapter(it.data.children)
            }
        })
    }
}
