package pl.piotrskiba.exchangerates.domain.currency.model

data class RateTableModel(
    val table: TableModel,
    val rates: List<RateModel>,
)
