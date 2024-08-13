package pl.piotrskiba.exchangerates.ratelist.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.piotrskiba.exchangerates.base.rx.SchedulersProvider
import pl.piotrskiba.exchangerates.base.viewmodel.ViewModelState
import pl.piotrskiba.exchangerates.domain.currency.usecase.GetCurrencyRatesUseCase
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.toRates
import pl.piotrskiba.exchangerates.ratelist.nav.RateListNavigator
import javax.inject.Inject

@HiltViewModel
class RateListViewModel @Inject constructor(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val facade: SchedulersProvider,
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _rateList: MutableStateFlow<List<Rate>> = MutableStateFlow(emptyList())
    private val _state: MutableStateFlow<ViewModelState> = MutableStateFlow(ViewModelState.NOT_INITIALIZED)
    val rateList: StateFlow<List<Rate>> = _rateList.asStateFlow()
    val state: StateFlow<ViewModelState> = _state.asStateFlow()
    lateinit var navigator: RateListNavigator

    fun loadRateList() {
        if (_state.value == ViewModelState.NOT_INITIALIZED) {
            _state.value = ViewModelState.LOADING
            disposables.add(
                getCurrencyRatesUseCase.execute()
                    .subscribeOn(facade.io())
                    .observeOn(facade.ui())
                    .subscribe(
                        {
                            _rateList.value = it.toRates()
                            _state.value = ViewModelState.LOADED
                        },
                        {
                            _rateList.value = emptyList()
                            _state.value = ViewModelState.LOADED
                        },
                    )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun onRateClick(rate: Rate) {
        navigator.navigateToCurrencyDetails(rate)
    }
}
