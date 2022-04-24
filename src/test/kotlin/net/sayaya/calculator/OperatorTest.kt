package net.sayaya.calculator

import net.sayaya.calculator.OperatorBinary.Companion.AND
import net.sayaya.calculator.OperatorBinary.Companion.LESS
import net.sayaya.calculator.OperatorBinary.Companion.MINUS
import net.sayaya.calculator.OperatorBinary.Companion.MULTIPLE
import net.sayaya.calculator.OperatorBinary.Companion.OR
import net.sayaya.calculator.OperatorBinary.Companion.PLUS
import net.sayaya.calculator.OperatorNullary.Companion.E
import net.sayaya.calculator.OperatorNullary.Companion.PI
import net.sayaya.calculator.OperatorUnary.Companion.GIGA
import net.sayaya.calculator.OperatorUnary.Companion.KILO
import net.sayaya.calculator.OperatorUnary.Companion.MEGA
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class OperatorTest {
    @Test
    fun nullary() {
        val calculator = Calculator(PLUS, PI, E)
        assertEquals(calculator.calculate("PI+E"), Math.PI+Math.E)
    }
    @Test
    fun unary() {
        val calculator = Calculator(Token.NUMBER, PLUS, KILO, MEGA, GIGA)
        assertEquals(calculator.calculate("1k+1M+1g"), 1000.0+1000000+1000000000)
    }
    @Test
    fun plus() {
        val calculator = Calculator(Token.NUMBER, PLUS)
        assertEquals(calculator.calculate("1+2+4+0.2"), 1+2+4+0.2)
    }
    @Test
    fun minus() {
        val calculator = Calculator(Token.NUMBER, MINUS)
        assertEquals(calculator.calculate("1-2-4-0.2"), 1-2-4-0.2)
    }
    @Test
    fun multiple() {
        val calculator = Calculator(Token.NUMBER, MULTIPLE)
        assertEquals(calculator.calculate("1*2*4*0.2"), 1*2*4*0.2)
    }
    @Test
    fun less() {
        val calculator = Calculator(Token.NUMBER, LESS)
        assertEquals(calculator.calculate("1<2"), 1<2)
    }
    @Test
    fun and() {
        val calculator = Calculator(Token.BOOLEAN, AND)
        assertEquals(calculator.calculate("true&false"), true&&false)
    }
    @Test
    fun or() {
        val calculator = Calculator(Token.BOOLEAN, OR)
        assertEquals(calculator.calculate("true|false"), true||false)
    }
    @Test
    fun complex() {
        val calculator = Calculator(Token.NUMBER, PLUS, MINUS, MULTIPLE, LESS, PI, E)
        assertEquals(calculator.calculate("4+PI-E*3 < 1"), (4+Math.PI-Math.E*3) < 1)
    }
}