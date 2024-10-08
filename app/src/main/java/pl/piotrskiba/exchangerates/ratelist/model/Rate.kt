package pl.piotrskiba.exchangerates.ratelist.model

import pl.piotrskiba.exchangerates.domain.currency.model.RateModel
import pl.piotrskiba.exchangerates.domain.currency.model.RateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import java.io.Serializable

data class Rate(
    val table: Table,
    val currency: String,
    val code: String,
    val mid: Double,
) : Serializable {

    val midText: String = mid.toBigDecimal().toPlainString()
}

fun List<RateTableModel>.toRates() =
    map { it.toRates() }.flatten()

private fun RateTableModel.toRates() =
    rates.map { it.toUi(table) }

private fun RateModel.toUi(tableModel: TableModel) =
    Rate(
        table = tableModel.toUi(),
        currency = currency,
        code = code,
        mid = mid,
    )
