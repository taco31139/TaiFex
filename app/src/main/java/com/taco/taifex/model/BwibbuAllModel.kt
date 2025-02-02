package com.taco.taifex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.taco.taifex.domain.data.BwibbuAllRs
import kotlinx.parcelize.Parcelize

@Parcelize
class BwibbuAllModel (private val response: List<BwibbuAllRs>) : Parcelable {

    private var _bwibbuList: List<BwibbuData>? = null
    val bwibbuList: List<BwibbuData>
        get() {
            if (_bwibbuList == null) {
                _bwibbuList = response.map { responseToData(it) }
            }
            return _bwibbuList!!
        }

    @Parcelize
    data class BwibbuData(
        val code: String,
        val name: String,
        val pEratio: String,
        val dividendYield: String,
        val pBratio: String,
    ): Parcelable

    private fun responseToData(response: BwibbuAllRs): BwibbuData {
        return BwibbuData(
            code = response.code,
            name = response.name,
            pEratio = response.pEratio,
            dividendYield = response.dividendYield,
            pBratio = response.pBratio,
        )
    }
}