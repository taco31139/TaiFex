package com.taco.taifex.domain.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StockDayRs(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("TradeVolume")
    val tradeVolume: String,
    @SerializedName("TradeValue")
    val tradeValue: String,
    @SerializedName("OpeningPrice")
    val openingPrice: String,
    @SerializedName("HighestPrice")
    val highestPrice: String,
    @SerializedName("LowestPrice")
    val lowestPrice: String,
    @SerializedName("ClosingPrice")
    val closingPrice: String,
    @SerializedName("Change")
    val change: String,
    @SerializedName("Transaction")
    val transaction: String,
): Parcelable