package pl.piotrskiba.exchangerates.data.currency.model

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import java.text.SimpleDateFormat

class HistoricalRateTableRemoteTest {

    @BeforeEach
    fun setUp() {
        mockkStatic(HistoricalRateRemote::toDomain)
    }

    @AfterEach
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `SHOULD map remote object to domain`() {
        val dateFormat: SimpleDateFormat = mockk()
        val domainRate: HistoricalRateModel = mockk()
        val remoteRate: HistoricalRateRemote = mockk {
            every { toDomain(dateFormat) } returns domainRate
        }
        val tested = HistoricalRateTableRemote(rates = listOf(remoteRate))

        tested.toDomain(dateFormat) shouldBeEqualTo HistoricalRateTableModel(rates = listOf(domainRate))
    }
}
