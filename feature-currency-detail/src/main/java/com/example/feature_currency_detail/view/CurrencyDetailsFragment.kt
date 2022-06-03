package com.example.feature_currency_detail.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.solver.state.helpers.VerticalChainReference
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.rv.FeedNews
import com.example.domain.entities.rv.HeadlineItem
import com.example.domain.entities.rv.currency_details.CurrencyDetails
import com.example.domain.entities.rv.Item
import com.example.domain.entities.rv.currency_details.DatePickerItem
import com.example.feature_currency_detail.R
import com.example.feature_currency_detail.databinding.FragmentCurrencyDetailsBinding
import com.example.feature_currency_detail.di.DetailFeatureComponentViewModel
import com.example.feature_currency_detail.rv.CurrencyMainDetailsFingerPrint
import com.example.feature_currency_detail.rv.CurrencyNewsFingerPrint
import com.example.feature_currency_detail.rv.CurrencyPricesChartFingerPrint
import com.example.feature_currency_detail.rv.DatePickerFingerPrint
import com.example.utils.navigation.NavCommand
import com.example.utils.navigation.NavCommands
import com.example.utils.navigation.navigate
import javax.inject.Inject
import com.example.utils.rv.adapter.BaseAdapter
import com.example.utils.rv.decoration.GroupVerticalItemDecoration
import com.example.utils.rv.decoration.HorizontalItemDecoration
import com.example.utils.rv.fingerprints.HeadlineFingerPrint

class CurrencyDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyDetailsBinding

    @Inject
    lateinit var viewModelFactory: CurrencyDetailsViewModelFactory
    private val datePicker = DatePickerItem()

    lateinit var viewModel: CurrencyDetailsViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<DetailFeatureComponentViewModel>().detailsFeatureComponent.inject(
            this
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyDetailsBinding.inflate(inflater, container, false)
        initRecyclerView()
        viewModel = viewModelFactory.create(CurrencyDetailsViewModel::class.java)
        viewModel.fetchCurrencyData(arguments?.get("currencyName").toString())
        subscribeOnViewModel()
        binding.arrowBack.setOnClickListener(View.OnClickListener {
            navigateToMainFragment()
        })
        viewModel.fetchChartData(
            id = arguments?.get("currencyName").toString(),
            days = "1",
            interval = "hourly"
        )
        return binding.root
    }

    private fun subscribeOnViewModel() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.coinSlug.text = viewModel.coinData.value!!.slug.toUpperCase()
                Glide.with(requireContext()).load(viewModel.coinData.value!!.imageUrl)
                    .into(binding.coinLogo)
                val coinInfo = viewModel.coinData.value
                val coinCharts = viewModel.coinChartData.value
                val data = listOf<Item>(
                    CurrencyDetails(
                        coinInfo!!.fullName,
                        coinCharts!!.prices[coinCharts!!.prices.size - 1][1]
                    )
                ) + listOf(datePicker) + coinCharts!! + listOf(HeadlineItem("News")) + viewModel.newsData.value!!
                (binding.detailsRecyclerView.adapter as BaseAdapter).submitList(data)
            }
        })
    }

    private fun navigateToMainFragment() {
        navigate(NavCommand(NavCommands.DeepLink(Uri.parse("currencyapp://main"), true, true)))
    }

    private fun initRecyclerView() {
        binding.detailsRecyclerView.adapter = BaseAdapter(
            listOf(
                CurrencyMainDetailsFingerPrint(),
                CurrencyPricesChartFingerPrint(),
                DatePickerFingerPrint(::onDateButtonISChecked),
                HeadlineFingerPrint(),
                CurrencyNewsFingerPrint()
            )
        )
        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.detailsRecyclerView.itemAnimator = null
        decorateRV(binding.detailsRecyclerView)
    }

    private fun onDateButtonISChecked(id: Int) {
        when (id) {
            R.id.hours_24_button -> {
                viewModel.dataState.postValue(false)
                viewModel.fetchChartData(
                    id = arguments?.get("currencyName").toString(),
                    days = "1",
                    interval = "hourly"
                )
            }
            R.id.days_7_button -> {
                viewModel.dataState.postValue(false)
                viewModel.fetchChartData(
                    id = arguments?.get("currencyName").toString(),
                    days = "7",
                    interval = "hourly"
                )
            }

            R.id.days_30_button -> {
                viewModel.dataState.postValue(false)
                viewModel.fetchChartData(
                    id = arguments?.get("currencyName").toString(),
                    days = "30",
                    interval = "hourly"
                )
            }

            R.id.days_90_button -> {
                viewModel.fetchChartData(
                    id = arguments?.get("currencyName").toString(),
                    days = "90",
                    interval = "hourly"
                )
            }
        }
    }
    private fun decorateRV(view:RecyclerView){
        view.addItemDecoration(HorizontalItemDecoration(R.layout.currency_main_details, 50))
        view.addItemDecoration(GroupVerticalItemDecoration(R.layout.date_picker_item, 0, 10))
        view.addItemDecoration(GroupVerticalItemDecoration(R.layout.currency_main_details, 0, 20))
        view.addItemDecoration(GroupVerticalItemDecoration(com.example.utils.R.layout.headline_item, 0, 70))
        view.addItemDecoration(HorizontalItemDecoration(com.example.utils.R.layout.headline_item, 50))
        view.addItemDecoration(HorizontalItemDecoration(R.layout.currency_news_item, 20))
        view.addItemDecoration(GroupVerticalItemDecoration(R.layout.currency_news_item, 10, 30))
    }
}