package pl.piotrskiba.exchangerates.data.currency.model

import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel

data class HistoricalRateRemote(
    val mid: Double,
)

fun HistoricalRateRemote.toDomain() =
    HistoricalRateModel(mid = mid)
