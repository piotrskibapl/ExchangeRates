package pl.piotrskiba.exchangerates.data.currency.model

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import java.text.SimpleDateFormat
import java.util.Date

class HistoricalRateRemoteTest {

    @Test
    fun `SHOULD map remote object to domain`() {
        val date: Date = mockk()
        val dateFormat: SimpleDateFormat = mockk()
        val tested = HistoricalRateRemote(
            effectiveDate = "2020-01-01",
            mid = 1.2345,
        )
        every { dateFormat.parse("2020-01-01") } returns date

        tested.toDomain(dateFormat) shouldBeEqualTo HistoricalRateModel(effectiveDate = date, mid = 1.2345)
    }
}
