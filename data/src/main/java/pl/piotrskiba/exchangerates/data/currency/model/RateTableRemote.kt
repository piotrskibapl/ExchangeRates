package pl.piotrskiba.exchangerates.data.currency.model

import pl.piotrskiba.exchangerates.domain.currency.model.RateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel

data class RateTableRemote(
    val table: String,
    val rates: List<RateRemote>,
)

fun RateTableRemote.toDomain() =
    RateTableModel(
        table = table.toTable(),
        rates = rates.map { it.toDomain() },
    )

private fun String.toTable() = TableModel.valueOf(this)
