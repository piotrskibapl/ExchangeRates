package pl.piotrskiba.exchangerates.data.currency.model

import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel

data class HistoricalRateTableRemote(
    val rates: List<HistoricalRateRemote>,
)

fun HistoricalRateTableRemote.toDomain() =
    HistoricalRateTableModel(
        rates = rates.map { it.toDomain() },
    )
