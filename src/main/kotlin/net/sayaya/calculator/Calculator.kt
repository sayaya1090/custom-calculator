package net.sayaya.calculator

import java.util.*
import java.util.stream.Collectors

class Calculator(tokens: List<Token>) {
    private val tokens: List<Token>
    constructor(vararg tokens: Token): this(tokens.asList())
    init {
        this.tokens = tokens.sorted()
    }
    fun calculate(expression: String): Any? = calculate(infix2Postfix(tokenize(expression)))
    private fun tokenize(expression: String): List<Any> {
        var exp = expression.trim()
        var match: MatchResult?
        val tokenized: MutableList<Any> = LinkedList()
        do {
            val prev = tokenized.size
            for(token in tokens) {
                match = token.match(exp)
                if(match!=null) {
                    exp = if(token is Function<*>) {
                        val (params) = match.destructured
                        val split = Function.tokenize(params, token.operandRequiredCount())
                        tokenized.add(token)
                        split.first.stream().map(::calculate).filter(Objects::nonNull).forEach{ tokenized.add(it!!) }
                        exp.substring(match.value.length).trim() + split.second
                    } else {
                        tokenized.add(parse(token, match))
                        exp.substring(match.value.length).trim()
                    }
                    break
                }
            }
            if(prev==tokenized.size) throw FormulaFormatException("Unknown token found:[$exp]")
        } while(exp.isNotEmpty())
        return tokenized
    }
    private fun infix2Postfix(infix: List<Any>): List<Any> {
        val postfix = LinkedList<Any>()
        val stack = Stack<Token>()
        for(token in infix) when (token) {
            is Number, is Boolean, is String    -> postfix.add(token)
            is Token                            -> {
                while(stack.isNotEmpty() && stack.peek() <= token) postfix.add(stack.pop())
                stack.push(token)
            }
            Token.BRACKET_OPEN                  -> stack.push(Token.BRACKET_OPEN)
            Token.BRACKET_CLOSE                 -> {
                while(stack.peek()!=Token.BRACKET_OPEN) postfix.add(stack.pop())
                stack.pop()
            }
        }
        while(stack.isNotEmpty()) postfix.add(stack.pop())
        return postfix
    }
    private fun calculate(tokens: List<Any>): Any? {
        val stack = Stack<Any>()
        for (it in tokens) {
            when(it) {
            is Number, is Boolean, is String -> stack.add(it)
            is Operator<*>                   -> stack.add(it.calculate(stack))
        }}
        if(stack.isEmpty()) return null
        var result = stack.pop()!!
        if(result is String) result = result.removeSurrounding("\"")
        return result
    }
    private fun parse(token: Token, match: MatchResult): Any {
        val expression = match.value
        return when(token) {
            Token.NUMBER        -> expression.toDouble()
            Token.BOOLEAN       -> expression.toBoolean()
            Token.STRING        -> expression
            else                -> token
        }
    }
    private fun <R> Operator<R>.calculate(stack: Stack<Any>): R {
        val required = this.operandRequiredCount()
        val operands: Array<Any> = Array(required) {0}
        for(i in required-1 downTo 0) {
            if(stack.isEmpty()) break
            operands[i] = stack.pop()
        }
        return this.apply(*operands)
    }
    class FormulaFormatException internal constructor(msg: String) : RuntimeException(msg)
}