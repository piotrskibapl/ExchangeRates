package pl.piotrskiba.exchangerates.domain.currency.usecase

import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test
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
    fun `SHOULD get historical currency rates`() {
        val tableModel: TableModel = mockk()
        val historicalRateTableModel: HistoricalRateTableModel = mockk()
        every { currencyRepository.getHistoricalRateTable(tableModel, "PLN", historicalDate, currentDate) } returns Single.just(historicalRateTableModel)

        val result = tested.execute(tableModel, "PLN").test()

        result.assertValue(historicalRateTableModel)
    }
}
