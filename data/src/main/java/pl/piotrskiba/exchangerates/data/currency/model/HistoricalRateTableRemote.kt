package pl.piotrskiba.exchangerates.data.currency.model

import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import java.text.SimpleDateFormat

data class HistoricalRateTableRemote(
    val rates: List<HistoricalRateRemote>,
)

fun HistoricalRateTableRemote.toDomain(dateFormat: SimpleDateFormat) =
    HistoricalRateTableModel(
        rates = rates.map { it.toDomain(dateFormat) },
    )
