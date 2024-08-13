package pl.piotrskiba.exchangerates.ratelist.model

import pl.piotrskiba.exchangerates.domain.currency.model.TableModel

enum class Table {
    A, B,
}

fun TableModel.toUi() = when (this) {
    TableModel.A -> Table.A
    TableModel.B -> Table.B
}
