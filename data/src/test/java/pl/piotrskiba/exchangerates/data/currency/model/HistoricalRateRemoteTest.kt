package pl.piotrskiba.exchangerates.data.currency.model

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import java.text.SimpleDateFormat
import java.util.Date

class HistoricalRateRemoteTest {

    companion object {

        @JvmStatic
        fun parameters() = listOf(
            HistoricalRateRemoteParameters(8.99, 10.0, true),
            HistoricalRateRemoteParameters(9.00, 10.0, false),
            HistoricalRateRemoteParameters(10.00, 10.0, false),
            HistoricalRateRemoteParameters(11.00, 10.0, false),
            HistoricalRateRemoteParameters(11.01, 10.0, true),
        )
    }

    data class HistoricalRateRemoteParameters(
        val mid: Double,
        val reference: Double,
        val expectedAnomalous: Boolean,
    )

    @ParameterizedTest
    @MethodSource("parameters")
    fun `SHOULD map remote object to domain`(parameters: HistoricalRateRemoteParameters) {
        val date: Date = mockk()
        val dateFormat: SimpleDateFormat = mockk()
        val tested = HistoricalRateRemote(
            effectiveDate = "2020-01-01",
            mid = parameters.mid,
        )
        every { dateFormat.parse("2020-01-01") } returns date

        tested.toDomain(dateFormat, parameters.reference) shouldBeEqualTo HistoricalRateModel(
            effectiveDate = date,
            mid = parameters.mid,
            isAnomalous = parameters.expectedAnomalous
        )
    }
}
