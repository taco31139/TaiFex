package com.taco.taifex.domain.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StockDayAvgRs(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("ClosingPrice")
    val closingPrice: String,
    @SerializedName("MonthlyAveragePrice")
    val monthlyAveragePrice: String,
): Parcelable