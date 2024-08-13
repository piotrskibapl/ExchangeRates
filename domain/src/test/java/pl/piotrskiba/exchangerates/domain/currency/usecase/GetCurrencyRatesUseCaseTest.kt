package pl.piotrskiba.exchangerates.domain.currency.usecase

import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.RateModel
import pl.piotrskiba.exchangerates.domain.currency.model.RateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.domain.currency.repository.CurrencyRepository

class GetCurrencyRatesUseCaseTest {

    val currencyRepository: CurrencyRepository = mockk()
    val tested = GetCurrencyRatesUseCase(currencyRepository)

    @Test
    fun `SHOULD return combined data from tables A and B`() {
        val rateA: RateModel = mockk()
        val rateB: RateModel = mockk()
        val tableA: RateTableModel = mockk {
            every { rates } returns listOf(rateA)
        }
        val tableB: RateTableModel = mockk {
            every { rates } returns listOf(rateB)
        }
        every { currencyRepository.getRateTable(TableModel.A) } returns Single.just(tableA)
        every { currencyRepository.getRateTable(TableModel.B) } returns Single.just(tableB)

        val result = tested.execute().test()

        result.assertValue(listOf(rateA, rateB))
    }
}
