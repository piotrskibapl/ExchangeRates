package pl.piotrskiba.exchangerates.viewmodel

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.base.rx.TestSchedulersFacade
import pl.piotrskiba.exchangerates.base.viewmodel.ViewModelState
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.domain.currency.usecase.GetHistoricalCurrencyRatesUseCase
import pl.piotrskiba.exchangerates.ratedetails.model.HistoricalRate
import pl.piotrskiba.exchangerates.ratedetails.model.toHistoricalRates
import pl.piotrskiba.exchangerates.ratedetails.viewmodel.RateDetailsViewModel
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.Table
import pl.piotrskiba.exchangerates.ratelist.model.toDomain
import java.text.SimpleDateFormat

class RateDetailsViewModelTest {

    val getHistoricalCurrencyRatesUseCase: GetHistoricalCurrencyRatesUseCase = mockk()
    val dateFormat: SimpleDateFormat = mockk()
    val tested = RateDetailsViewModel(
        getHistoricalCurrencyRatesUseCase = getHistoricalCurrencyRatesUseCase,
        facade = TestSchedulersFacade(),
        dateFormat = dateFormat,
    )

    @Test
    fun `SHOULD set state to Loading WHEN loadRateDetails called`() {
        every { getHistoricalCurrencyRatesUseCase.execute(any(), any()) } returns Single.never()
        tested.rate = mockk(relaxed = true)
        tested.state.value shouldNotBeEqualTo ViewModelState.LOADING

        tested.loadRateDetails()

        tested.state.value shouldBeEqualTo ViewModelState.LOADING
    }

    @Test
    fun `SHOULD set historicalRateList and update state WHEN loadRateDetails called AND loading succeeds`() {
        mockkStatic(HistoricalRateTableModel::toHistoricalRates, Table::toDomain) {
            val historicalRates: List<HistoricalRate> = listOf(mockk())
            val historicalRateTableModel: HistoricalRateTableModel = mockk {
                every { toHistoricalRates(dateFormat) } returns historicalRates
            }
            val tableModel: TableModel = mockk()
            val rate: Rate = mockk {
                every { table.toDomain() } returns tableModel
                every { code } returns "PLN"
            }
            tested.rate = rate
            every { getHistoricalCurrencyRatesUseCase.execute(tableModel, "PLN") } returns Single.just(historicalRateTableModel)

            tested.loadRateDetails()

            assertSoftly {
                tested.historicalRateList.value shouldBeEqualTo historicalRates
                tested.state.value shouldBeEqualTo ViewModelState.LOADED
            }
        }
    }

    @Test
    fun `SHOULD set historicalRateList and update state WHEN loadRateDetails called AND loading fails`() {
        mockkStatic(Table::toDomain) {
            val rate: Rate = mockk {
                every { table.toDomain() } returns mockk()
                every { code } returns "PLN"
            }
            tested.rate = rate
            every { getHistoricalCurrencyRatesUseCase.execute(any(), any()) } returns Single.error(Throwable())

            tested.loadRateDetails()

            assertSoftly {
                tested.historicalRateList.value shouldBeEqualTo emptyList()
                tested.state.value shouldBeEqualTo ViewModelState.LOADED
            }
        }
    }

    @Test
    fun `SHOULD call getHistoricalCurrencyRatesUseCase only once WHEN loadRateDetails called twice`() {
        mockkStatic(Table::toDomain) {
            val rate: Rate = mockk {
                every { table.toDomain() } returns mockk()
                every { code } returns "PLN"
            }
            tested.rate = rate
            every { getHistoricalCurrencyRatesUseCase.execute(any(), any()) } returns Single.never()

            tested.loadRateDetails()
            tested.loadRateDetails()

            verify(exactly = 1) { getHistoricalCurrencyRatesUseCase.execute(any(), any()) }
        }
    }
}
