package pl.piotrskiba.exchangerates.domain.currency.usecase

import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.RateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.domain.currency.repository.CurrencyRepository

class GetCurrencyRateTablesUseCaseTest {

    val currencyRepository: CurrencyRepository = mockk()
    val tested = GetCurrencyRateTablesUseCase(currencyRepository)

    @Test
    fun `SHOULD return list of tables A and B`() {
        val tableA: RateTableModel = mockk()
        val tableB: RateTableModel = mockk()
        every { currencyRepository.getRateTable(TableModel.A) } returns Single.just(tableA)
        every { currencyRepository.getRateTable(TableModel.B) } returns Single.just(tableB)

        val result = tested.execute().test()

        result.assertValue(listOf(tableA, tableB))
    }
}
