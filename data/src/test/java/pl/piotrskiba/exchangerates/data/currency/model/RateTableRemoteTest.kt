package pl.piotrskiba.exchangerates.data.currency.model

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.piotrskiba.exchangerates.domain.currency.model.RateModel
import pl.piotrskiba.exchangerates.domain.currency.model.RateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel

class RateTableRemoteTest {

    companion object {

        @JvmStatic
        fun parameters() =
            listOf(
                "A" to TableModel.A,
                "B" to TableModel.B,
            ).map { Arguments.of(it.first, it.second) }
    }

    @ParameterizedTest
    @MethodSource("parameters")
    fun `SHOULD map remote model to domain`(
        tableString: String,
        tableModel: TableModel,
    ) {
        mockkStatic(RateRemote::toDomain) {
            val rateModel: RateModel = mockk()
            val rateRemote: RateRemote = mockk {
                every { toDomain() } returns rateModel
            }
            val tested = RateTableRemote(
                table = tableString,
                rates = listOf(rateRemote),
            )

            val result = tested.toDomain()

            result shouldBeEqualTo RateTableModel(
                table = tableModel,
                rates = listOf(rateModel),
            )
        }
    }

    @Test
    fun `SHOULD throw error WHEN invalid table string provided`() {
        assertThrows<IllegalArgumentException> {
            mockkStatic(RateRemote::toDomain) {
                val tested = RateTableRemote(
                    table = "C",
                    rates = listOf(),
                )

                tested.toDomain()
            }
        }
    }
}
