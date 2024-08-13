package pl.piotrskiba.exchangerates.domain.currency.usecase

import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.domain.currency.repository.CurrencyRepository
import pl.piotrskiba.exchangerates.domain.date.DateProvider
import javax.inject.Inject

class GetHistoricalCurrencyRatesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val dateProvider: DateProvider,
) {

    fun execute(table: TableModel, code: String) =
        currencyRepository.getHistoricalRateTable(
            table = table,
            code = code,
            startDate = dateProvider.getHistoricalDate(14),
            endDate = dateProvider.getCurrentDate(),
        )
}
