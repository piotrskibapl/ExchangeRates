package pl.piotrskiba.exchangerates.ratedetails.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.piotrskiba.exchangerates.base.rx.SchedulersProvider
import pl.piotrskiba.exchangerates.base.viewmodel.ViewModelState
import pl.piotrskiba.exchangerates.domain.currency.usecase.GetHistoricalCurrencyRatesUseCase
import pl.piotrskiba.exchangerates.ratedetails.model.HistoricalRate
import pl.piotrskiba.exchangerates.ratedetails.model.toHistoricalRates
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.toDomain
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class RateDetailsViewModel @Inject constructor(
    private val getHistoricalCurrencyRatesUseCase: GetHistoricalCurrencyRatesUseCase,
    private val facade: SchedulersProvider,
    private val dateFormat: SimpleDateFormat,
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _historicalRateList: MutableStateFlow<List<HistoricalRate>> = MutableStateFlow(emptyList())
    private val _state: MutableStateFlow<ViewModelState> = MutableStateFlow(ViewModelState.NOT_INITIALIZED)
    val historicalRateList: StateFlow<List<HistoricalRate>> = _historicalRateList.asStateFlow()
    val state: StateFlow<ViewModelState> = _state.asStateFlow()
    lateinit var rate: Rate

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun loadRateDetails() {
        if (_state.value == ViewModelState.NOT_INITIALIZED) {
            _state.value = ViewModelState.LOADING
            disposables.add(
                getHistoricalCurrencyRatesUseCase.execute(table = rate.table.toDomain(), code = rate.code)
                    .subscribeOn(facade.io())
                    .observeOn(facade.ui())
                    .subscribe(
                        {
                            _historicalRateList.value = it.toHistoricalRates(dateFormat)
                            _state.value = ViewModelState.LOADED
                        },
                        {
                            _historicalRateList.value = emptyList()
                            _state.value = ViewModelState.LOADED
                        }
                    )
            )
        }
    }
}
