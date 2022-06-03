package com.example.feature_currency_detail.rv

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.rv.currency_details.CurrencyChart
import com.example.domain.entities.rv.Item
import com.example.feature_currency_detail.R
import com.example.feature_currency_detail.databinding.ChartItemBinding
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.fingerprints.ItemFingerPrint
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class CurrencyPricesChartFingerPrint: ItemFingerPrint<ChartItemBinding, CurrencyChart> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is CurrencyChart
    }

    override fun getLayoutId(): Int {
        return R.layout.chart_item
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ChartItemBinding, CurrencyChart> {
        return ChartViewHolder(ChartItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<CurrencyChart> {
        return diffUtil
    }
    private val diffUtil = object : DiffUtil.ItemCallback<CurrencyChart>()  {
        override fun areItemsTheSame(oldItem: CurrencyChart, newItem: CurrencyChart): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CurrencyChart, newItem: CurrencyChart): Boolean {
            return oldItem.prices == newItem.prices
        }

    }
    class ChartViewHolder(val binding: ChartItemBinding): BaseViewHolder<ChartItemBinding, CurrencyChart>(binding){
        override fun onBind(item: CurrencyChart) {
            super.onBind(item)
            val prices = mutableListOf<Entry>()
            item.prices.forEach {
                prices.add(Entry(it[0].toFloat(), it[1].toFloat()))
            }
            binding.currencyPriceChart.apply {
                val lineDataSet = LineDataSet(prices, "chart")
                styleDataSet(lineDataSet, context)
                data = LineData(lineDataSet)
                styleChart(this)
                invalidate()
            }
        }
        private fun styleDataSet(lineDataSet: LineDataSet, context: Context){
            lineDataSet.color = ContextCompat.getColor(context, com.example.utils.R.color.primary_dark)
            lineDataSet.setDrawValues(false)
            lineDataSet.lineWidth = 2f
            lineDataSet.setDrawCircles(false)
            lineDataSet.isHighlightEnabled = false
            lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            lineDataSet.setDrawFilled(true)
            lineDataSet.setDrawHighlightIndicators(false)
            lineDataSet.fillDrawable = ContextCompat.getDrawable(context, R.drawable.chart_gradient)
        }

        private fun styleChart(lineChart: LineChart){
            lineChart.apply {
                axisRight.isEnabled = false
                xAxis.setDrawGridLines(false)
                xAxis.setDrawAxisLine(false)
                xAxis.isEnabled = false
                axisLeft.setDrawGridLines(false)
                axisLeft.setDrawAxisLine(false)
                legend.isEnabled = false
                description = null
            }
        }
    }
}