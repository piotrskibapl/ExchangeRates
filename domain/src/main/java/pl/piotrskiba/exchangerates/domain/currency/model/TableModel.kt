package pl.piotrskiba.exchangerates.domain.currency.model

enum class TableModel {
    A,
    B,
}

fun TableModel.toRemote() = toString()
