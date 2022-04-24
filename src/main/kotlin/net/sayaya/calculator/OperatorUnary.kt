package net.sayaya.calculator

open class OperatorUnary<R, I>(keyword: String, order: Int, private val func: java.util.function.Function<I, R>): Token(keyword, order), Operator<R> {
    override fun operandRequiredCount(): Int = 1
    override fun apply(vararg operands: Any): R = func.apply(operands[0] as I)

    companion object {
        val KILO    = OperatorUnary<Double, Double>("[Kk]", 2) { d -> d*1000 }
        val MEGA    = OperatorUnary<Double, Double>("[Mm]", 2) { d -> d*1000000 }
        val GIGA    = OperatorUnary<Double, Double>("[Gg]", 2) { d -> d*1000000000 }
    }
}