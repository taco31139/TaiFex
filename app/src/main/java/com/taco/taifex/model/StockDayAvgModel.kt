package com.taco.taifex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.taco.taifex.domain.data.StockDayAvgRs
import kotlinx.parcelize.Parcelize

@Parcelize
class StockDayAvgModel (private val response: List<StockDayAvgRs>) : Parcelable {

    private var _stockDayAvgList: List<StockDayAvgData>? = null
    val stockDayAvgList: List<StockDayAvgData>
        get() {
            if (_stockDayAvgList == null) {
                _stockDayAvgList = response.map { responseToData(it) }
            }
            return _stockDayAvgList!!
        }

    @Parcelize
    data class StockDayAvgData(
        val code: String,
        val name: String,
        val closingPrice: String,
        val monthlyAveragePrice: String,
    ): Parcelable

    private fun responseToData(response: StockDayAvgRs): StockDayAvgData {
        return StockDayAvgData(
            code = response.code,
            name = response.name,
            closingPrice = response.closingPrice,
            monthlyAveragePrice = response.monthlyAveragePrice,
        )
    }
}