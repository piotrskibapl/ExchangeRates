package pl.piotrskiba.exchangerates.data.currency.repository

import io.reactivex.rxjava3.core.Single
import pl.piotrskiba.exchangerates.data.currency.CurrencyApiService
import pl.piotrskiba.exchangerates.data.currency.model.toDomain
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.RateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.domain.currency.model.toRemote
import pl.piotrskiba.exchangerates.domain.currency.repository.CurrencyRepository
import java.text.SimpleDateFormat
import java.time.LocalDate

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApiService,
    private val dateFormat: SimpleDateFormat,
) : CurrencyRepository {

    override fun getRateTable(table: TableModel): Single<RateTableModel> =
        currencyApi
            .getRateTable(table.toRemote())
            .map { it.first().toDomain() }

    override fun getHistoricalRateTable(
        table: TableModel,
        code: String,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Single<HistoricalRateTableModel> =
        currencyApi
            .getHistoricalRateTable(
                table = table.toRemote(),
                code = code,
                startDate = dateFormat.format(startDate),
                endDate = dateFormat.format(endDate),
            )
            .map { it.toDomain() }
}
