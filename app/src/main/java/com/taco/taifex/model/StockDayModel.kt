package com.taco.taifex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.taco.taifex.domain.data.StockDayRs
import kotlinx.parcelize.Parcelize

@Parcelize
class StockDayModel(private val response: List<StockDayRs>) : Parcelable {

    private var _stockDayList: List<StockDayData>? = null
    val stockDayList: List<StockDayData>
        get() {
            if (_stockDayList == null) {
                _stockDayList = response.map { responseToData(it) }
            }
            return _stockDayList!!
        }

    @Parcelize
    data class StockDayData(
        val code: String,
        val name: String,
        val tradeVolume: String,
        val tradeValue: String,
        val openingPrice: String,
        val highestPrice: String,
        val lowestPrice: String,
        val closingPrice: String,
        val change: String,
        val transaction: String,
    ): Parcelable

    private fun responseToData(response: StockDayRs): StockDayData {
        return StockDayData(
            code = response.code,
            name = response.name,
            tradeVolume = response.tradeVolume,
            tradeValue = response.tradeValue,
            openingPrice = response.openingPrice,
            highestPrice = response.highestPrice,
            lowestPrice = response.lowestPrice,
            closingPrice = response.closingPrice,
            change = response.change,
            transaction = response.transaction,
        )
    }
}