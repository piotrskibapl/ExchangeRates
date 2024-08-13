package pl.piotrskiba.exchangerates.model

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import pl.piotrskiba.exchangerates.ratedetails.model.HistoricalRate
import pl.piotrskiba.exchangerates.ratedetails.model.toHistoricalRates
import java.text.SimpleDateFormat
import java.util.Date

class HistoricalRateTest {

    @Test
    fun `SHOULD map historical rate table model to historical rates`() {
        val date: Date = mockk()
        val dateFormat: SimpleDateFormat = mockk {
            every { format(date) } returns "2020-01-01"
        }
        val tested = HistoricalRateTableModel(
            rates = listOf(
                HistoricalRateModel(
                    effectiveDate = date,
                    mid = 0.00000123456789,
                )
            )
        )

        tested.toHistoricalRates(dateFormat) shouldBeEqualTo listOf(
            HistoricalRate(
                effectiveDate = "2020-01-01",
                mid = "0.00000123456789",
            )
        )
    }
}
