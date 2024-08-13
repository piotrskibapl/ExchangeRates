package pl.piotrskiba.exchangerates.ratedetails.model

import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import java.text.SimpleDateFormat

data class HistoricalRate(
    val effectiveDate: String,
    val mid: String,
)

fun HistoricalRateTableModel.toHistoricalRates(dateFormat: SimpleDateFormat) =
    rates.map { it.toUi(dateFormat) }

private fun HistoricalRateModel.toUi(dateFormat: SimpleDateFormat) =
    HistoricalRate(
        effectiveDate = dateFormat.format(effectiveDate),
        mid = mid.toBigDecimal().toPlainString(),
    )
