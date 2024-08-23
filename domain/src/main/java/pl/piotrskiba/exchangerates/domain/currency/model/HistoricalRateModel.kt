package pl.piotrskiba.exchangerates.domain.currency.model

import java.util.Date

data class HistoricalRateModel(
    val effectiveDate: Date,
    val mid: Double,
    val isAnomalous: Boolean,
)
