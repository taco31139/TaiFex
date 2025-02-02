package com.taco.taifex.model

import android.os.Parcelable
import com.taco.taifex.domain.data.BwibbuAllRs
import com.taco.taifex.domain.data.StockDayAvgRs
import com.taco.taifex.domain.data.StockDayRs
import kotlinx.parcelize.Parcelize

@Parcelize
class TaifexModel (
    private val bwibbuRs: List<BwibbuAllRs>,
    private val stockDayRs: List<StockDayRs>,
    private val stockDayAvgRs: List<StockDayAvgRs>
) : Parcelable {

    private var _taifexList: List<TaifexData>? = null
    val taifexList: List<TaifexData>
        get() {
            if (_taifexList == null) {
                val bwibbuMap = bwibbuRs.associateBy { it.code }
                val stockDayMap = stockDayRs.associateBy { it.code }
                val stockDayAvgMap = stockDayAvgRs.associateBy { it.code }

                _taifexList = (bwibbuMap.keys + stockDayMap.keys + stockDayAvgMap.keys).distinct().map { code ->
                    responseToData(
                        bwibbu = bwibbuMap[code],
                        stockDay = stockDayMap[code],
                        stockDayAvg = stockDayAvgMap[code]
                    )
                }
            }
            return _taifexList!!
        }

    @Parcelize
    data class TaifexData(
        val code: String,
        val name: String,
        val pEratio: String,
        val dividendYield: String,
        val pBratio: String,
        val closingPrice: String,
        val monthlyAveragePrice: String,
        val tradeVolume: String,
        val tradeValue: String,
        val openingPrice: String,
        val highestPrice: String,
        val lowestPrice: String,
        val change: String,
        val transaction: String,
    ): Parcelable

    private fun responseToData(
        bwibbu: BwibbuAllRs?,
        stockDay: StockDayRs?,
        stockDayAvg: StockDayAvgRs?
    ): TaifexData {
        return TaifexData(
            code = bwibbu?.code ?: stockDay?.code ?: stockDayAvg?.code ?: "",
            name = bwibbu?.name ?: stockDay?.name ?: stockDayAvg?.name ?: "",
            pEratio = bwibbu?.pEratio ?: "",
            dividendYield = bwibbu?.dividendYield ?: "",
            pBratio = bwibbu?.pBratio ?: "",
            closingPrice = stockDay?.closingPrice ?: stockDayAvg?.closingPrice ?: "",
            monthlyAveragePrice = stockDayAvg?.monthlyAveragePrice ?: "",
            tradeVolume = stockDay?.tradeVolume ?: "",
            tradeValue = stockDay?.tradeValue ?: "",
            openingPrice = stockDay?.openingPrice ?: "",
            highestPrice = stockDay?.highestPrice ?: "",
            lowestPrice = stockDay?.lowestPrice ?: "",
            change = stockDay?.change ?: "",
            transaction = stockDay?.transaction ?: ""
        )
    }
}