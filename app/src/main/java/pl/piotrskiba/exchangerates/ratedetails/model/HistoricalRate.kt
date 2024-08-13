package pl.piotrskiba.exchangerates.ratedetails.model

import androidx.compose.ui.graphics.Color
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import java.text.SimpleDateFormat
import kotlin.math.abs

data class HistoricalRate(
    val effectiveDate: String,
    val mid: Double,
) {

    val midText = mid.toBigDecimal().toPlainString()

    fun midTextColor(referenceRateMid: Double) =
        if (abs(referenceRateMid - mid) > referenceRateMid * 0.1) {
            Color.Red
        } else {
            Color.Green
        }
}

fun HistoricalRateTableModel.toHistoricalRates(dateFormat: SimpleDateFormat) =
    rates.map { it.toUi(dateFormat) }

private fun HistoricalRateModel.toUi(dateFormat: SimpleDateFormat) =
    HistoricalRate(
        effectiveDate = dateFormat.format(effectiveDate),
        mid = mid,
    )
