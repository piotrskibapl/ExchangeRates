package pl.piotrskiba.exchangerates.model

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.domain.currency.model.RateModel
import pl.piotrskiba.exchangerates.domain.currency.model.RateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.model.Table
import pl.piotrskiba.exchangerates.ratelist.model.toRates

class RateTest {

    @Test
    fun `SHOULD midText have correct String representation of mid value`() {
        val tested = Rate(
            table = Table.A,
            currency = "Polish Zloty",
            code = "PLN",
            mid = 0.00000123456789,
        )

        tested.midText shouldBeEqualTo "0.00000123456789"
    }

    @Test
    fun `SHOULD map list of rate table models to rates`() {
        val tested = listOf(
            RateTableModel(
                table = TableModel.A,
                rates = listOf(
                    RateModel(currency = "Polish Zloty", code = "PLN", mid = 0.00000123456789),
                )
            ),
            RateTableModel(
                table = TableModel.B,
                rates = listOf(
                    RateModel(currency = "euro", code = "EUR", mid = 0.123),
                )
            ),
        )

        tested.toRates() shouldBeEqualTo listOf(
            Rate(
                table = Table.A,
                currency = "Polish Zloty",
                code = "PLN",
                mid = 0.00000123456789,
            ),
            Rate(
                table = Table.B,
                currency = "euro",
                code = "EUR",
                mid = 0.123,
            ),
        )
    }
}
