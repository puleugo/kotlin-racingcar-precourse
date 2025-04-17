package racingcar

import camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest
import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ApplicationTest : NsTest() {
    @Test
    fun `Should print prints the Collect winner`() {
        assertRandomNumberInRangeTest(
            {
                run("pobi,woni", "1")
                assertThat(output()).contains("pobi : -", "woni : ", "Winners : pobi")
            },
            MOVING_FORWARD,
            STOP,
        )
    }

    @Test
    fun `Correctly prints multiple winners`() {
        assertRandomNumberInRangeTest(
            {
                run("pobi,woni", "1")
                assertThat(output()).contains("pobi : -", "woni : -", "Winners : pobi, woni")
            },
            MOVING_FORWARD,
            MOVING_FORWARD,
        )
    }


    @Test
    fun `Should throw exception when car names is empty`() {
        assertSimpleTest() {
            assertThrows<IllegalArgumentException> {
                run("", "1")
            }
        }
    }

    @Test
    fun `Should throw exception when car name is over 5 characters`() {
        assertSimpleTest() {
            assertThrows<IllegalArgumentException> {
                run("123456", "1")
            }
        }
    }

    @Test
    fun `Should throw exception when car name is duplicated`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("pobi,pobi", "1") }
        }
    }

    @Test
    fun `Car name cannot contain spaces`() {
        assertSimpleTest() {
            assertThrows<IllegalArgumentException> { runException("p obi", "1") }
        }
    }

    @Test
    fun `Whitespace between car names (comma-separated) is allowed`() {
        assertSimpleTest() {
            run("pobi, woni", "1")
            assertThat(output()).contains("pobi", "woni")
        }
    }

    @Test
    fun `Should throws if number of rounds is not a number`() {
        assertSimpleTest() {
            assertThrows<IllegalArgumentException> {
                run("pobi,javaji", "a")
            }
        }
    }

    @Test
    fun `Should throws if number of rounds is not a positive number`() {
        assertSimpleTest() {
            assertThrows<IllegalArgumentException> {
                run("pobi,javaji", "-1")
            }
        }
    }


    override fun runMain() {
        main()
    }

    companion object {
        private const val MOVING_FORWARD: Int = 4
        private const val STOP: Int = 3
    }
}
