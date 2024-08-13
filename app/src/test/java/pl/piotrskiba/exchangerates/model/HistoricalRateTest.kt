package pl.piotrskiba.exchangerates.model

import androidx.compose.ui.graphics.Color
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import pl.piotrskiba.exchangerates.ratedetails.model.HistoricalRate
import pl.piotrskiba.exchangerates.ratedetails.model.toHistoricalRates
import java.text.SimpleDateFormat
import java.util.Date

class HistoricalRateTest {

    companion object {

        @JvmStatic
        fun midTextColorParameters() = listOf(
            MidTextColorParameters(8.99, 10.0, Color.Red),
            MidTextColorParameters(9.00, 10.0, Color.Green),
            MidTextColorParameters(10.00, 10.0, Color.Green),
            MidTextColorParameters(11.00, 10.0, Color.Green),
            MidTextColorParameters(11.01, 10.0, Color.Red),
        )
    }

    data class MidTextColorParameters(
        val mid: Double,
        val referenceMid: Double,
        val expectedColor: Color,
    )

    @Test
    fun `SHOULD midText have correct String representation of mid value`() {
        val tested = HistoricalRate(
            effectiveDate = "2020-01-01",
            mid = 0.00000123456789,
        )

        tested.midText shouldBeEqualTo "0.00000123456789"
    }

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
                mid = 0.00000123456789,
            )
        )
    }

    @ParameterizedTest
    @MethodSource("midTextColorParameters")
    fun `SHOULD midTextColor return proper color`(param: MidTextColorParameters) {
        val tested = HistoricalRate(
            effectiveDate = "2020-01-01",
            mid = param.mid,
        )

        tested.midTextColor(param.referenceMid) shouldBeEqualTo param.expectedColor
    }
}
