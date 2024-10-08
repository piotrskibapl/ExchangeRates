package pl.piotrskiba.exchangerates.domain.currency.repository

import io.reactivex.rxjava3.core.Single
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.RateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import java.util.Date

interface CurrencyRepository {

    fun getRateTable(table: TableModel): Single<RateTableModel>

    fun getHistoricalRateTable(
        table: TableModel,
        code: String,
        startDate: Date,
        endDate: Date,
    ): Single<HistoricalRateTableModel>
}
