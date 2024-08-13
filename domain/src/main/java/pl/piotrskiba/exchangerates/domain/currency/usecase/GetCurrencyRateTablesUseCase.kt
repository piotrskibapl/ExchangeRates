package pl.piotrskiba.exchangerates.domain.currency.usecase

import io.reactivex.rxjava3.core.Single
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.domain.currency.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyRateTablesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
) {

    fun execute() =
        Single.zip(
            currencyRepository.getRateTable(TableModel.A),
            currencyRepository.getRateTable(TableModel.B),
        ) { tableA, tableB ->
            listOf(tableA, tableB)
        }
}
