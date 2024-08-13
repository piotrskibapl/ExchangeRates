package pl.piotrskiba.exchangerates.data.currency.model

import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import java.text.SimpleDateFormat

data class HistoricalRateRemote(
    val effectiveDate: String,
    val mid: Double,
)

fun HistoricalRateRemote.toDomain(dateFormat: SimpleDateFormat) =
    HistoricalRateModel(
        effectiveDate = dateFormat.parse(effectiveDate)!!,
        mid = mid,
    )
