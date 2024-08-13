package pl.piotrskiba.exchangerates.data.currency.model

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.RateModel

class RateRemoteTest {

    @Test
    fun `SHOULD map remote object to domain`() {
        val tested = RateRemote(
            currency = "Polish Zloty",
            code = "PLN",
            mid = 1.2345,
        )

        tested.toDomain() shouldBeEqualTo RateModel(
            currency = "Polish Zloty",
            code = "PLN",
            mid = 1.2345,
        )
    }
}
