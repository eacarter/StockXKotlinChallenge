package com.erickson.stockxcodechallenge

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.erickson.stockxcodechallenge.databinding.MainActivityBinding
import com.erickson.stockxcodechallenge.fragment.SearchFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector

import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector{

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: MainActivityBinding

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    override fun onStart() {
        super.onStart()

        binding.fab.setOnClickListener { view ->
            if(!binding.homeSearch.text.isEmpty()) {
                val bundle = Bundle()

                bundle.putString("sub", binding.homeSearch.text.toString())

                val fragment = SearchFragment()
                fragment.arguments = bundle

                this.supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment, "second")
                    .addToBackStack(null)
                    .commit()

                Snackbar.make(view, "/r/"+ binding.homeSearch.text, Snackbar.LENGTH_LONG)
                    .show()

                binding.homeSearch.text.clear()

               val input: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                input.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
