package com.taco.taifex.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.taco.taifex.common.*
import com.taco.taifex.databinding.DialogTaifexDataBinding
import com.taco.taifex.databinding.ItemTaifexDataBinding
import com.taco.taifex.model.TaifexModel

class TaifexAdapter(
    private var taifexList: List<TaifexModel.TaifexData>
) : RecyclerView.Adapter<TaifexAdapter.TaifexViewHolder>() {

    class TaifexViewHolder(val binding: ItemTaifexDataBinding) : RecyclerView.ViewHolder(binding.root)
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaifexViewHolder {
        val binding = ItemTaifexDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return TaifexViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaifexViewHolder, position: Int) {
        val item = taifexList[position]

        holder.binding.apply {
            tvCode.text = item.code
            tvName.text = item.name
            tvOpenPrice.text = item.openingPrice
            tvClosePrice.text = item.closingPrice
            tvHighestPrice.text = item.highestPrice
            tvLowestPrice.text = item.lowestPrice
            tvChange.text = item.change
            tvMonthlyAvgPrice.text = item.monthlyAveragePrice
            tvTransaction.text = item.transaction
            tvTradeVolume.text = item.tradeVolume
            tvTradeValue.text = item.tradeValue.toTWDFormat()
        }

        val closePrice = item.closingPrice.toCurrencyAmount() ?: 0.0
        val monthlyAvgPrice = item.monthlyAveragePrice.toCurrencyAmount() ?: 0.0
        if (closePrice > monthlyAvgPrice) {
            holder.binding.tvClosePrice.setTextColorRed()
        } else {
            holder.binding.tvClosePrice.setTextColorGreen()
        }

        val change = item.change.toCurrencyAmount() ?: 0.0
        if (change > 0) {
            holder.binding.tvChange.setTextColorRed()
        } else {
            holder.binding.tvChange.setTextColorGreen()
        }

        holder.itemView.setOnClickListener {
            showDialog(item)
        }
    }

    override fun getItemCount(): Int {
        return taifexList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newTaifexList: List<TaifexModel.TaifexData>) {
        taifexList = newTaifexList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortDataAscending(comparator: Comparator<TaifexModel.TaifexData>) {
        taifexList = taifexList.sortedWith(comparator)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortDataDescending(comparator: Comparator<TaifexModel.TaifexData>) {
        taifexList = taifexList.sortedWith(comparator.reversed())
        notifyDataSetChanged()
    }

    private fun showDialog(item: TaifexModel.TaifexData) {
        val dialogBinding = DialogTaifexDataBinding.inflate(LayoutInflater.from(context)).apply {
            tvDialogCode.text = item.code
            tvDialogName.text = item.name
            tvPEratio.text = item.pEratio
            tvDividendYield.text = item.dividendYield
            tvPBratio.text = item.pBratio
        }

        AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
}