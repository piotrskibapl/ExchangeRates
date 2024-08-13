package pl.piotrskiba.exchangerates.domain.currency.model

data class RateModel(
    val currency: String,
    val code: String,
    val mid: Double,
)
