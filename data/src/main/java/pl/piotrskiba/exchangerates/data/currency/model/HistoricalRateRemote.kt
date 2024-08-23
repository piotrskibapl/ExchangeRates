package pl.piotrskiba.exchangerates.data.currency.model

import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import java.text.SimpleDateFormat
import kotlin.math.abs

data class HistoricalRateRemote(
    val effectiveDate: String,
    val mid: Double,
)

fun HistoricalRateRemote.toDomain(dateFormat: SimpleDateFormat, referenceRate: Double) =
    HistoricalRateModel(
        effectiveDate = dateFormat.parse(effectiveDate)!!,
        mid = mid,
        isAnomalous = abs(referenceRate - mid) > referenceRate * 0.1,
    )
