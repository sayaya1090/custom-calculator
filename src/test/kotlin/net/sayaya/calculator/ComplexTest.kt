package net.sayaya.calculator

import net.sayaya.calculator.Function.Companion.IF
import net.sayaya.calculator.Function.Companion.LOG10
import net.sayaya.calculator.OperatorBinary.Companion.LARGER
import net.sayaya.calculator.OperatorBinary.Companion.LESS
import net.sayaya.calculator.OperatorBinary.Companion.MINUS
import net.sayaya.calculator.OperatorBinary.Companion.MULTIPLE
import net.sayaya.calculator.OperatorBinary.Companion.PLUS
import net.sayaya.calculator.OperatorNullary.Companion.E
import net.sayaya.calculator.OperatorNullary.Companion.PI
import net.sayaya.calculator.OperatorUnary.Companion.GIGA
import net.sayaya.calculator.OperatorUnary.Companion.KILO
import net.sayaya.calculator.Token.Companion.NUMBER
import org.junit.jupiter.api.Test
import kotlin.math.log10
import kotlin.test.assertEquals

class ComplexTest {
    val calculator = Calculator(NUMBER, PLUS, MINUS, MULTIPLE, KILO, GIGA, PI, E, LESS, LARGER, LOG10, IF)
    @Test
    fun complex() {
        val calculated = calculator.calculate("If( 2*PI +E-3 > 5, Log(1K +1K)+4, Log(2G) ) +1")
        assertEquals(calculated, log10(1000+1000.0)+4+1)
    }
}