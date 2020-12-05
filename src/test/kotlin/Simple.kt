import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import strikt.api.*
import strikt.assertions.*

object SimpleTest : Spek({
    describe("Test Simple") {
        it("1 + 2 = 3") {
            val sum = 1 + 2
            expectThat(sum)
                    .isA<Int>()
                    .isEqualTo(3)
        }
    }
})
