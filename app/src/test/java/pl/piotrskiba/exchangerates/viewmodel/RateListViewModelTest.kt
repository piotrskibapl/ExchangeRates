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
import pl.piotrskiba.exchangerates.domain.currency.model.RateModel
import pl.piotrskiba.exchangerates.domain.currency.usecase.GetCurrencyRatesUseCase
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.toUi
import pl.piotrskiba.exchangerates.ratelist.viewmodel.RateListViewModel

class RateListViewModelTest {

    val getCurrencyRatesUseCase: GetCurrencyRatesUseCase = mockk()
    val tested = RateListViewModel(
        getCurrencyRatesUseCase = getCurrencyRatesUseCase,
        facade = TestSchedulersFacade(),
    )

    @Test
    fun `SHOULD set state to Loading WHEN loadRateList called`() {
        every { getCurrencyRatesUseCase.execute() } returns Single.never()
        tested.state.value shouldNotBeEqualTo ViewModelState.LOADING

        tested.loadRateList()

        tested.state.value shouldBeEqualTo ViewModelState.LOADING
    }

    @Test
    fun `SHOULD set rateList and update state WHEN loadRateList called AND loading succeeds`() {
        mockkStatic(List<RateModel>::toUi) {
            val rates: List<Rate> = mockk(relaxed = true)
            val rateModels: List<RateModel> = mockk {
                every { toUi() } returns rates
            }
            every { getCurrencyRatesUseCase.execute() } returns Single.just(rateModels)

            tested.loadRateList()

            assertSoftly {
                tested.rateList.value shouldBeEqualTo rates
                tested.state.value shouldBeEqualTo ViewModelState.LOADED
            }
        }
    }

    @Test
    fun `SHOULD set rateList and update state WHEN loadRateList called AND loading fails`() {
        every { getCurrencyRatesUseCase.execute() } returns Single.error(Throwable())

        tested.loadRateList()

        assertSoftly {
            tested.rateList.value shouldBeEqualTo emptyList()
            tested.state.value shouldBeEqualTo ViewModelState.LOADED
        }
    }

    @Test
    fun `SHOULD call getCurrencyRatesUseCas only once WHEN loadRateList called twice`() {
        every { getCurrencyRatesUseCase.execute() } returns Single.never()

        tested.loadRateList()
        tested.loadRateList()

        verify(exactly = 1) { getCurrencyRatesUseCase.execute() }
    }
}
