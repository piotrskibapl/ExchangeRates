package pl.piotrskiba.exchangerates.domain.currency.usecase

import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.domain.currency.repository.CurrencyRepository
import pl.piotrskiba.exchangerates.domain.date.TestDateProvider
import java.util.Date

class GetHistoricalCurrencyRatesUseCaseTest {

    val currentDate: Date = mockk()
    val historicalDate: Date = mockk()
    val currencyRepository: CurrencyRepository = mockk()
    val dateProvider = TestDateProvider(currentDate, historicalDate)
    val tested = GetHistoricalCurrencyRatesUseCase(currencyRepository, dateProvider)

    @Test
    fun `SHOULD get historical currency rates sorted descending by date`() {
        val rate1: HistoricalRateModel = mockk { every { effectiveDate } returns Date(500) }
        val rate2: HistoricalRateModel = mockk { every { effectiveDate } returns Date(400) }
        val rate3: HistoricalRateModel = mockk { every { effectiveDate } returns Date(600) }
        val tableModel: TableModel = mockk()
        val historicalRateTableModel = HistoricalRateTableModel(rates = listOf(rate1, rate2, rate3))
        every { currencyRepository.getHistoricalRateTable(tableModel, "PLN", historicalDate, currentDate) } returns Single.just(historicalRateTableModel)

        val result = tested.execute(tableModel, "PLN").test()

        result.assertValue(historicalRateTableModel.copy(rates = listOf(rate3, rate1, rate2)))
    }
}
