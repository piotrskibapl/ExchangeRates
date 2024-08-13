package pl.piotrskiba.exchangerates.ratelist.view

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
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.Table
import pl.piotrskiba.exchangerates.ui.theme.ExchangeRatesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RateListView(
    ratesStateFlow: StateFlow<List<Rate>>,
    stateStateFlow: StateFlow<ViewModelState>,
    onClick: (Rate) -> Unit,
) {
    val rates by ratesStateFlow.collectAsState()
    val state by stateStateFlow.collectAsState()
    ExchangeRatesTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(title = { Text(stringResource(R.string.title_activity_main)) })
            LoadingIndicator(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .align(Alignment.CenterHorizontally),
                state = state
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items = rates) {
                        RateListElement(rate = it, onClick = onClick)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RateListViewPreview() {
    RateListView(
        ratesStateFlow = MutableStateFlow(
            listOf(
                Rate(table = Table.A, currency = "Polish Zloty", code = "PLN", mid = "0.123"),
                Rate(table = Table.A, currency = "Euro", code = "EUR", mid = "0.456"),
                Rate(table = Table.A, currency = "Canadian dollar", code = "CAD", mid = "0.789"),
                Rate(table = Table.A, currency = "Lao kip", code = "LAK", mid = "0.123456789"),
            )
        ),
        stateStateFlow = MutableStateFlow(ViewModelState.LOADED),
        onClick = {}
    )
}
