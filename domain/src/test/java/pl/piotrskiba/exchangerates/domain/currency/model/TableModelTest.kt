package pl.piotrskiba.exchangerates.domain.currency.model

import org.amshove.kluent.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TableModelTest {

    companion object {

        @JvmStatic
        fun parameters() = listOf(
            TableModel.A to "A",
            TableModel.B to "B",
        ).map { Arguments.of(it.first, it.second) }
    }

    @ParameterizedTest
    @MethodSource("parameters")
    fun `SHOULD map domain model to remote`(domainModel: TableModel, remoteModel: String) {
        domainModel.toRemote() shouldBe remoteModel
    }
}
