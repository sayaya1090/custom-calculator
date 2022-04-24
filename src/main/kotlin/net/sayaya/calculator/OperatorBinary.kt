package net.sayaya.calculator

import java.util.function.BiFunction

open class OperatorBinary<R, I1, I2>(keyword: String, order: Int, private val func: BiFunction<I1, I2, R>): Token(keyword, order), Operator<R> {
    override fun operandRequiredCount(): Int = 2
    @Suppress("UNCHECKED_CAST")
    override fun apply(vararg operands: Any): R = func.apply(operands[0] as I1, operands[1] as I2)

    companion object {
        val PLUS    = OperatorBinary<Double, Double, Double>("[+]", 9) { a, b -> a + b }
        val MINUS   = OperatorBinary<Double, Double, Double>("[-]", 9) { a, b -> a - b }
        val MULTIPLE= OperatorBinary<Double, Double, Double>("[*]", 5) { a, b -> a * b }

        val LESS    = OperatorBinary<Boolean, Double, Double>("[<]", 11) { a, b -> a < b }
        val LARGER    = OperatorBinary<Boolean, Double, Double>("[>]", 11) { a, b -> a > b }

        val AND     = OperatorBinary<Boolean, Boolean, Boolean>("[&]", 15) { a, b -> a && b }
        val OR      = OperatorBinary<Boolean, Boolean, Boolean>("[|]", 15) { a, b -> a || b }
    }
}