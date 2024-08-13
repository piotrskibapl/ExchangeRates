package pl.piotrskiba.exchangerates.ratedetails.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pl.piotrskiba.exchangerates.R
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.Table
import pl.piotrskiba.exchangerates.ratelist.view.RateListElement
import pl.piotrskiba.exchangerates.ui.theme.ExchangeRatesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RateDetailsView(rate: Rate) {
    ExchangeRatesTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(title = { Text(stringResource(R.string.title_activity_rate_details)) })
            RateListElement(rate = rate, onClick = null)
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
            mid = "1.23456789",
        )
    )
}
