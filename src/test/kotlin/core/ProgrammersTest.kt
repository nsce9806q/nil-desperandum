package core

import org.junit.jupiter.api.Assertions
import java.util.function.Consumer

open class ProgrammersTest<I,O>(private val testDataSet: List<TestData<I, O>>) {

    fun runTest(consumer: Consumer<TestData<I, O>>) {
        val errors = mutableListOf<String>()

        testDataSet.forEachIndexed { index, testData ->
            try {
                consumer.accept(testData)
            } catch (e: AssertionError) {
                errors.add("Test case ${index + 1} failed: ${e.message}")
            }
        }

        if (errors.isNotEmpty()) {
            Assertions.fail<String>("Test failed with the following errors:\n${errors.joinToString("\n")}")
        }
    }
}