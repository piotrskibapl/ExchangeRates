package pl.piotrskiba.exchangerates.data.currency.model

import pl.piotrskiba.exchangerates.domain.currency.model.RateModel

data class RateRemote(
    val currency: String,
    val code: String,
    val mid: Double,
)

fun RateRemote.toDomain() =
    RateModel(
        currency = currency,
        code = code,
        mid = mid,
    )
