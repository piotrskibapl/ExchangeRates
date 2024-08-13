package pl.piotrskiba.exchangerates.model

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.RateModel
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.toUi

class RateTest {

    @Test
    fun `SHOULD map domain model to ui`() {
        val tested = RateModel(
            currency = "Polish Zloty",
            code = "PLN",
            mid = 0.00000123456789,
        )

        tested.toUi() shouldBeEqualTo Rate(
            currency = "Polish Zloty",
            code = "PLN",
            mid = "0.00000123456789",
        )
    }

    @Test
    fun `SHOULD map list of domain models to ui`() {
        mockkStatic(RateModel::toUi) {
            val rate: Rate = mockk()
            val rateModel: RateModel = mockk {
                every { toUi() } returns rate
            }

            listOf(rateModel).toUi() shouldBeEqualTo listOf(rate)
        }
    }
}
