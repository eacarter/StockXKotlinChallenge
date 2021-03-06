package com.erickson.stockxcodechallenge.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erickson.stockxcodechallenge.MainViewModel
import com.erickson.stockxcodechallenge.fragment.HomeViewModel
import com.erickson.stockxcodechallenge.fragment.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    protected abstract fun homeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    protected abstract fun searchViewModel(searchViewModel: SearchViewModel): ViewModel


}