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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.rv.FeedNews
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentNewsFeedBinding
import com.example.feature_main.di.MainFeatureComponentViewModel
import com.example.feature_main.recycler_view.NewsFingerPrint
import com.example.feature_main.viewmodels.NewsFeedViewModel
import com.example.feature_main.viewmodels.NewsViewModelFactory
import com.example.utils.State
import com.example.utils.navigation.NavCommand
import com.example.utils.navigation.NavCommands
import com.example.utils.navigation.navigate
import com.example.utils.rv.adapter.BaseAdapter
import com.example.utils.rv.decoration.GroupVerticalItemDecoration
import com.example.utils.rv.decoration.HorizontalItemDecoration
import javax.inject.Inject

class NewsFeedFragment : Fragment() {
    private lateinit var binding:FragmentNewsFeedBinding

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory
    private lateinit var viewModel:NewsFeedViewModel

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<MainFeatureComponentViewModel>().mainFeatureComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        viewModel = viewModelFactory.create(NewsFeedViewModel::class.java)
        initRecyclerView()
        subscribeOnViewModel()
        viewModel.fetchNews()
        return binding.root
    }
    private fun subscribeOnViewModel(){
        viewModel.news.observe(viewLifecycleOwner, Observer {
            when(it){
                is State.ErrorState -> {
                    binding.errorImage.visibility = View.VISIBLE
                    binding.errorText.visibility = View.VISIBLE
                }
                is State.LoadingState -> {
                    binding.progressBar.visibility = View.VISIBLE

                }

                is State.NoItemsState -> {
                    binding.errorImage.visibility = View.VISIBLE
                    binding.noItemsFoundedText.visibility = View.VISIBLE
                }

                is State.SuccessfullyDownloaded<*> -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorImage.visibility = View.GONE
                    binding.noItemsFoundedText.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    (binding.newsFeedRecyclerView.adapter as BaseAdapter).submitList(it.data as List<FeedNews>)
                }
            }
        })
    }
    private fun initRecyclerView(){
        binding.newsFeedRecyclerView.adapter = BaseAdapter(listOf(NewsFingerPrint(::onNewsItemClicked)))
        binding.newsFeedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        decorateRecyclerView()
    }
    private fun decorateRecyclerView(){
        binding.newsFeedRecyclerView.addItemDecoration(GroupVerticalItemDecoration(R.layout.news_simple_item, 20, 30))
        binding.newsFeedRecyclerView.addItemDecoration(HorizontalItemDecoration(R.layout.news_simple_item, 20))
    }
    private fun onNewsItemClicked(item:FeedNews){
        val bundle = Bundle()
        bundle.putString("uri", item.newsUrl)
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    Uri.parse("currencyapp://news_details"),
                    true,
                    true
                ), args = bundle
            )
        )
    }
}