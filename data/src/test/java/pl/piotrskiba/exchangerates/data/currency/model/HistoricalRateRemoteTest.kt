package pl.piotrskiba.exchangerates.data.currency.model

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateModel

class HistoricalRateRemoteTest {

    @Test
    fun `SHOULD map remote object to domain`() {
        val tested = HistoricalRateRemote(mid = 1.2345)

        tested.toDomain() shouldBeEqualTo HistoricalRateModel(mid = 1.2345)
    }
}
