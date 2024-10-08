package pl.piotrskiba.exchangerates.ratelist.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.Table

@Composable
fun RateListElement(rate: Rate, onClick: ((Rate) -> Unit)?) {
    Surface {
        Column(modifier = Modifier
            .padding(horizontal = 8.dp)
            .let { modifier -> onClick?.let { modifier.clickable { it(rate) } } ?: modifier }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = rate.currency,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = rate.code,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Text(
                    text = rate.midText,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RateListElementPreview() {
    RateListElement(
        rate = Rate(
            table = Table.A,
            currency = "Polish Zloty",
            code = "PLN",
            mid = 0.123456789,
        ),
        onClick = {},
    )
}
