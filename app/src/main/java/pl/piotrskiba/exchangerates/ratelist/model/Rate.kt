package pl.piotrskiba.exchangerates.ratelist.model

import pl.piotrskiba.exchangerates.domain.currency.model.RateModel

data class Rate(
    val currency: String,
    val code: String,
    val mid: String,
)

fun RateModel.toUi() =
    Rate(
        currency = currency,
        code = code,
        mid = mid.toBigDecimal().toPlainString(),
    )

fun List<RateModel>.toUi() =
    map { it.toUi() }
