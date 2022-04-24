package net.sayaya.calculator

import net.sayaya.calculator.Function.Companion.IF
import net.sayaya.calculator.Function.Companion.LOG10
import net.sayaya.calculator.OperatorBinary.Companion.PLUS
import net.sayaya.calculator.Token.Companion.BOOLEAN
import org.junit.jupiter.api.Test
import kotlin.math.log10
import kotlin.test.assertEquals

class FunctionTest {
    @Test
    fun log10() {
        val calculator = Calculator(Token.NUMBER, PLUS, LOG10)
        assertEquals(calculator.calculate("Log(10)"), log10(10.0))
        assertEquals(calculator.calculate("Log(10+10)"), log10(20.0))
    }
    @Test
    fun ifelse() {
        val calculator = Calculator(Token.NUMBER, BOOLEAN, IF, LOG10)
        //assertEquals(calculator.calculate("If(false, 10, 20)"), 20.0)
        assertEquals(calculator.calculate("If(false, 10, Log(20))"), log10(20.0))
    }
}