package pl.piotrskiba.exchangerates.model

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.ratelist.model.Table
import pl.piotrskiba.exchangerates.ratelist.model.toDomain
import pl.piotrskiba.exchangerates.ratelist.model.toUi

class TableTest {

    companion object {

        @JvmStatic
        fun parameters() = listOf(
            TableModel.A to Table.A,
            TableModel.B to Table.B,
        ).map { Arguments.of(it.first, it.second) }
    }

    @ParameterizedTest
    @MethodSource("parameters")
    fun `SHOULD map domain object to ui`(domainModel: TableModel, uiModel: Table) {
        domainModel.toUi() shouldBeEqualTo uiModel
    }

    @ParameterizedTest
    @MethodSource("parameters")
    fun `SHOULD map ui object to domain`(domainModel: TableModel, uiModel: Table) {
        uiModel.toDomain() shouldBeEqualTo domainModel
    }
}
