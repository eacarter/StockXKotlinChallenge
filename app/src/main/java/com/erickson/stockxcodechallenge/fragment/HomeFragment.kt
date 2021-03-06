package com.erickson.stockxcodechallenge.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.erickson.stockxcodechallenge.R
import com.erickson.stockxcodechallenge.adapter.ListPostAdapter
import com.erickson.stockxcodechallenge.databinding.HomeFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : DaggerFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: HomeFragmentBinding

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        loadContent()

        binding.homeSwipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            loadContent()
            binding.homeSwipe.isRefreshing = false
        })

        return binding.root
    }
    fun loadContent(){
        viewModel.getList("").observe(viewLifecycleOwner, Observer {
            with(homeRecyclerView){
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this.context)
                adapter = ListPostAdapter(it.data.children)
            }
        })
    }
}
