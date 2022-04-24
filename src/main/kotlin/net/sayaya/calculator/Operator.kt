package net.sayaya.calculator

interface Operator<R> {
    fun operandRequiredCount(): Int
    fun apply(vararg operands: Any): R
}