package com.demo.countrylistretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.countrylistretrofit.adapter.CountryListAdapter
import com.demo.countrylistretrofit.databinding.ActivityMainBinding
import com.demo.countrylistretrofit.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.countryListRecyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdapter(this)
        binding.countryListRecyclerview.adapter =recyclerAdapter

    }

    private fun initViewModel() {
            val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            it?.let {
                with(recyclerAdapter) {
                    setCountryList(it)
                    notifyDataSetChanged()
                }
            }
        })
        viewModel.makeAPICall()
    }
}