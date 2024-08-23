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
        val domainRate1: HistoricalRateModel = mockk()
        val domainRate2: HistoricalRateModel = mockk()
        val domainRates = listOf(domainRate1, domainRate2)
        val remoteRate1: HistoricalRateRemote = mockk {
            every { mid } returns 1.0
            every { toDomain(dateFormat, 1.0) } returns domainRate1
        }
        val remoteRate2: HistoricalRateRemote = mockk {
            every { mid } returns 2.0
            every { toDomain(dateFormat, 1.0) } returns domainRate2
        }
        val remoteRates = listOf(remoteRate1, remoteRate2)
        val tested = HistoricalRateTableRemote(remoteRates)

        tested.toDomain(dateFormat) shouldBeEqualTo HistoricalRateTableModel(rates = domainRates)
    }
}
