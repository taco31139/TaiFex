package com.taco.taifex.domain.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BwibbuAllRs(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("PEratio")
    val pEratio: String,
    @SerializedName("DividendYield")
    val dividendYield: String,
    @SerializedName("PBratio")
    val pBratio: String,
): Parcelable
