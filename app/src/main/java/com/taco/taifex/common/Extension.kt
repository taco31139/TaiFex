package com.taco.taifex.common

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.taco.taifex.R
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun String.toTWDFormat(): String {
    val amount = this.toDoubleOrNull() ?: return this
    val format = NumberFormat.getCurrencyInstance(Locale.TAIWAN)
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("TWD")
    return format.format(amount).replace("NT$", "").replace("$", "").trim()
}

fun String.toCurrencyAmount(): Double? {
    return this.toDoubleOrNull()
}

fun TextView.setTextColorRed() {
    val color = ContextCompat.getColor(context, R.color.red)
    setTextColor(color)
}

fun TextView.setTextColorGreen() {
    val color = ContextCompat.getColor(context, R.color.green)
    setTextColor(color)
}