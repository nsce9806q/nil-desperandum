package kakao.winterinternship2024

import core.ProgrammersTest
import core.TestData
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

data class Input(
    val friends: Array<String>,
    val gifts: Array<String>
)

data class Output(
    val result: Int
)

class PG258712: ProgrammersTest<Input, Output>(listOf(
    TestData(
        Input(arrayOf("muzi", "ryan", "frodo", "neo"), arrayOf("muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi")),
        Output(2)
    ),
    TestData(
        Input(arrayOf("joy", "brad", "alessandro", "conan", "david"), arrayOf("alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david")),
        Output(4)
    ),
    TestData(
        Input(arrayOf("a", "b", "c"), arrayOf("a b", "b a", "c a", "a c", "a c", "c a")),
        Output(0)
    )
)) {

    private val solution = Solution()

    @Test
    fun test() {
        runTest {
            Assertions.assertEquals(it.output.result, solution.solution(it.input.friends, it.input.gifts))
        }
    }
}