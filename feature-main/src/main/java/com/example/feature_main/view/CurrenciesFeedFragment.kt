package com.example.feature_main.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.rv.CryptoCurrency
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentCurrenciesFeedBinding
import com.example.feature_main.di.MainFeatureComponentViewModel
import com.example.feature_main.recycler_view.DefaultCurrencyFingerPrint
import com.example.feature_main.viewmodels.CurrenciesFeedViewModel
import com.example.feature_main.viewmodels.CurrenciesFeedViewModelFactory
import com.example.feature_main.viewmodels.FeedViewModel
import com.example.utils.State
import com.example.utils.rv.adapter.BaseAdapter
import com.example.utils.rv.decoration.GroupVerticalItemDecoration
import com.example.utils.rv.decoration.HorizontalItemDecoration
import javax.inject.Inject

class CurrenciesFeedFragment : Fragment() {
    @Inject
    lateinit var currenciesFeedViewModelFactory : CurrenciesFeedViewModelFactory
    private lateinit var viewModel:CurrenciesFeedViewModel
    private lateinit var binding: FragmentCurrenciesFeedBinding
    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<MainFeatureComponentViewModel>().mainFeatureComponent.inject(this)
        viewModel = ViewModelProvider(this, currenciesFeedViewModelFactory).get(CurrenciesFeedViewModel::class.java)
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrenciesFeedBinding.inflate(inflater, container, false)
        initRecyclerView()
        subscribeOnViewModel()
        viewModel.fetchAllCurrencies()
        return binding.root
    }
    private fun subscribeOnViewModel(){
        viewModel.currenciesState.observe(viewLifecycleOwner, Observer {
            when(it){
                is State.LoadingState -> {

                }
                is State.SuccessfullyDownloaded<*> -> {
                    (binding.currenciesRecyclerView.adapter as BaseAdapter).submitList(it.data as List<CryptoCurrency>)
                }
            }
        })
    }
    private fun initRecyclerView(){
        binding.currenciesRecyclerView.apply {
            adapter = BaseAdapter(listOf(DefaultCurrencyFingerPrint()))
            layoutManager = LinearLayoutManager(context)
            decorateRecyclerView(this)
        }
    }
    private fun decorateRecyclerView(view: RecyclerView){
        view.addItemDecoration(GroupVerticalItemDecoration(R.layout.main_currency_simple_item, 20, 30))
        view.addItemDecoration(HorizontalItemDecoration(R.layout.main_currency_simple_item, 25))

    }
}