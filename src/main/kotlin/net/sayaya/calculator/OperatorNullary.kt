package net.sayaya.calculator

import java.util.function.Supplier

open class OperatorNullary<R>(keyword: String, order: Int, private val func: Supplier<R>): Token(keyword, order), Operator<R> {
    override fun operandRequiredCount(): Int = 0
    override fun apply(vararg operands: Any): R = func.get()

    companion object {
        val PI  = OperatorNullary("PI", 0) { Math.PI }
        val E   = OperatorNullary("E", 0) { Math.E }
        val T   = OperatorNullary("T", 0) { true }
        val F   = OperatorNullary("F", 0) { false }
    }
}