package pl.piotrskiba.exchangerates.ratedetails.model

import androidx.compose.ui.graphics.Color
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import java.text.SimpleDateFormat

data class HistoricalRate(
    val effectiveDate: String,
    val mid: String,
    val midColor: Color,
)

fun HistoricalRateTableModel.toHistoricalRates(dateFormat: SimpleDateFormat) =
    rates.map { it.toUi(dateFormat) }

private fun HistoricalRateModel.toUi(dateFormat: SimpleDateFormat) =
    HistoricalRate(
        effectiveDate = dateFormat.format(effectiveDate),
        mid = mid.toBigDecimal().toPlainString(),
        midColor = if (isAnomalous) Color.Red else Color.Green
    )
