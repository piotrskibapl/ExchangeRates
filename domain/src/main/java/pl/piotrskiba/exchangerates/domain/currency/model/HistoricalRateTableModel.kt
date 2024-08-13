package pl.piotrskiba.exchangerates.domain.currency.model

data class HistoricalRateTableModel(
    val rates: List<HistoricalRateModel>,
)
