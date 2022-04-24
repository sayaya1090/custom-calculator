package net.sayaya.calculator

import java.util.*
import kotlin.math.log10

abstract class Function<R>(
    private val name: String,
    private val paramRequired: Int
): Token("$name\\s*[(](.+)[)]", 0), Operator<R> {
    override fun operandRequiredCount(): Int = paramRequired
    override fun toString(): String = name

    companion object {
        private val regexString = Regex("(^\".+?\")\\s*[,)]*\\s*")
        private val regexBracket = Regex("(^[(].+?[)])\\s*[,)]*\\s*")
        private val regexParam = Regex("^([^,]+\\s*)\\s*[,)]*\\s*")
        fun tokenize(expression: String, paramRequired: Int): Pair<List<String>, String> {
            val params = LinkedList<String>()
            var exp = expression
            var match: MatchResult?
            do {
                match = regexString.find(exp)
                if(match==null) match = regexBracket.find(exp)
                if(match==null) match = regexParam.find(exp)
                if(match!=null) {
                    val (param) = match.destructured
                    params.add(param.trim())
                    exp = exp.substring(match.value.length).trim()
                    if(match.value.endsWith(")")) break
                } else {
                    params.add(exp.trim())
                    exp = ""
                }
            } while(exp.isNotEmpty() && params.size<paramRequired)
            return Pair(params, exp)
        }

        val LOG10 = object : Function<Double>("Log", 1) {
            override fun apply(vararg args: Any): Double {
                return log10(args[0] as Double)
            }
        }
        val IF = object : Function<Any>("If", 3) {
            override fun apply(vararg args: Any): Any {
                return if(args[0] as Boolean) args[1] else args[2]
            }
        }
    }
}