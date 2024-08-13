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
        val domainRate1: HistoricalRateModel = mockk()
        val remoteRate1: HistoricalRateRemote = mockk {
            every { toDomain() } returns domainRate1
        }
        val tested = HistoricalRateTableRemote(rates = listOf(remoteRate1))

        tested.toDomain() shouldBeEqualTo HistoricalRateTableModel(rates = listOf(domainRate1))
    }
}
