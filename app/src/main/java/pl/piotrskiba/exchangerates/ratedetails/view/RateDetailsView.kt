package pl.piotrskiba.exchangerates.ratedetails.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.piotrskiba.exchangerates.R
import pl.piotrskiba.exchangerates.base.view.LoadingIndicator
import pl.piotrskiba.exchangerates.base.viewmodel.ViewModelState
import pl.piotrskiba.exchangerates.ratedetails.model.HistoricalRate
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.Table
import pl.piotrskiba.exchangerates.ratelist.view.RateListElement
import pl.piotrskiba.exchangerates.ui.theme.ExchangeRatesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RateDetailsView(
    rate: Rate,
    historicalRatesStateFlow: StateFlow<List<HistoricalRate>>,
    stateStateFlow: StateFlow<ViewModelState>,
) {
    val historicalRates by historicalRatesStateFlow.collectAsState()
    val state by stateStateFlow.collectAsState()
    ExchangeRatesTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(title = { Text(stringResource(R.string.title_activity_rate_details)) })
            RateListElement(rate = rate, onClick = null)
            LoadingIndicator(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .align(Alignment.CenterHorizontally),
                state = state,
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items = historicalRates) {
                        HistoricalRateListElement(historicalRate = it, referenceRateMid = rate.mid)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RateDetailsViewPreview() {
    RateDetailsView(
        rate = Rate(
            table = Table.A,
            currency = "Polish Zloty",
            code = "PLN",
            mid = 1.23456789,
        ),
        historicalRatesStateFlow = MutableStateFlow(
            listOf(
                HistoricalRate(effectiveDate = "2020-01-01", mid = 0.123),
                HistoricalRate(effectiveDate = "2020-01-02", mid = 0.124),
                HistoricalRate(effectiveDate = "2020-01-03", mid = 0.13),
                HistoricalRate(effectiveDate = "2020-01-04", mid = 0.13524),
                HistoricalRate(effectiveDate = "2020-01-05", mid = 0.135),
            )
        ),
        stateStateFlow = MutableStateFlow(ViewModelState.LOADED),
    )
}
