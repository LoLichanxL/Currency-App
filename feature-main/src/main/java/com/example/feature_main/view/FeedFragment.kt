package com.example.feature_main.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.*
import com.example.domain.entities.rv.*
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentMainFeedBinding
import com.example.feature_main.di.MainFeatureComponentViewModel
import com.example.feature_main.recycler_view.*
import com.example.feature_main.viewmodels.FeedViewModel
import com.example.feature_main.viewmodels.FeedViewModelFactory
import com.example.utils.State
import com.example.utils.navigation.NavCommand
import com.example.utils.navigation.NavCommands
import com.example.utils.navigation.navigate
import com.example.utils.rv.adapter.BaseAdapter
import com.example.utils.rv.decoration.GroupVerticalItemDecoration
import com.example.utils.rv.decoration.HorizontalItemDecoration
import com.example.utils.rv.fingerprints.HeadlineFingerPrint
import javax.inject.Inject

class FeedFragment : Fragment() {
    private lateinit var binding: FragmentMainFeedBinding

    private lateinit var viewModel: FeedViewModel

    @Inject
    lateinit var viewModelFactory: FeedViewModelFactory

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<MainFeatureComponentViewModel>().mainFeatureComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FeedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainFeedBinding.inflate(inflater, container, false)
        initRecyclerView()
        subscribeOnViewModel()
        fetchData()
        return binding.root
    }

    private fun onListItemClicked(currency: CryptoCurrency) {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    Uri.parse("currencyapp://currency_details/${currency.id}"),
                    true,
                    true
                )
            )
        )
    }
    private fun featureItemClicked(feauture: Feature){
        when(feauture){
            is Feature.Compare -> {

            }
            is Feature.Convert -> {
            }
            is Feature.WatchList -> {

            }
        }
    }
    private fun fetchData() {
        viewModel.fetchCurrencies()
        viewModel.fetchNews()
    }

    private fun subscribeOnViewModel() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val data = listOf<Item>(FeatureItem()) + listOf<Item>(HeadlineItem("Currencies")) +
                        listOf(CurrenciesRecyclerViewItem((viewModel.currencies.value as State.SuccessfullyDownloaded<CryptoCurrency>).data))  +
                        listOf<Item>(HeadlineItem("Latest news")) + (viewModel.news.value as State.SuccessfullyDownloaded<FeedNews>).data.subList(0, 10)
                (binding.currenciesFeedRecyclerView.adapter as BaseAdapter).submitList(data)
            }
        })
    }

    private fun initRecyclerView() {
        binding.currenciesFeedRecyclerView.adapter = BaseAdapter(
            listOf(
                CurrenciesRecyclerViewFingerPrint(
                    ::onListItemClicked,
                ),
                NewsFingerPrint(::onNewsItemClicked),
                HeadlineFingerPrint(),
                PageHeaderFingerPrint(::featureItemClicked)
            )
        )
        decorateRecyclerView(binding.currenciesFeedRecyclerView)
        binding.currenciesFeedRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun decorateRecyclerView(view:RecyclerView){
        view.addItemDecoration(HorizontalItemDecoration(R.layout.features_item, 40))
        view.addItemDecoration(HorizontalItemDecoration(com.example.utils.R.layout.headline_item, 40))
        view.addItemDecoration(GroupVerticalItemDecoration(R.layout.features_item, 20, 40))
        view.addItemDecoration(GroupVerticalItemDecoration(com.example.utils.R.layout.headline_item, 20, 40))
        view.addItemDecoration(GroupVerticalItemDecoration(R.layout.news_simple_item, 20, 20))
        view.addItemDecoration(HorizontalItemDecoration(R.layout.news_simple_item, 20))

    }
    private fun onNewsItemClicked(item:FeedNews){
        val bundle = Bundle()
        bundle.putString("uri", item.newsUrl)
        navigate(NavCommand(NavCommands.DeepLink(Uri.parse("currencyapp://news_details"), true, false), args = bundle))
    }
}