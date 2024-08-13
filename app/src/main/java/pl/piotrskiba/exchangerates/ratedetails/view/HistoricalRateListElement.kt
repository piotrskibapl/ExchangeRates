package pl.piotrskiba.exchangerates.ratedetails.view

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.piotrskiba.exchangerates.ratedetails.model.HistoricalRate

@Composable
fun HistoricalRateListElement(historicalRate: HistoricalRate, referenceRateMid: Double) {
    Surface {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = historicalRate.effectiveDate,
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = historicalRate.midText,
                    style = MaterialTheme.typography.bodySmall,
                    color = historicalRate.midTextColor(referenceRateMid),
                )
            }
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoricalRateListElementPreview() {
    HistoricalRateListElement(
        historicalRate = HistoricalRate(
            effectiveDate = "2020-01-01",
            mid = 0.123456789,
        ),
        referenceRateMid = 0.123456789,
    )
}
