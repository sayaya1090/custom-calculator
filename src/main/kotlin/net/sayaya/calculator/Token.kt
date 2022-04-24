package net.sayaya.calculator

open class Token (
    private val keyword: String,
    private val order: Int,
    private val exp: Regex = Regex("^$keyword")
): Comparable<Token> {
    fun match(expression: String): MatchResult? = exp.find(expression)
    override fun compareTo(other: Token): Int = order.compareTo(other.order)
    override fun toString(): String = keyword

    companion object {
        val BRACKET_OPEN = Token("[(]", Int.MAX_VALUE)
        val BRACKET_CLOSE = Token("[)]", Int.MAX_VALUE)
        var NUMBER = Token("[-+]*[0-9]+[.]*[0-9]*", Int.MAX_VALUE)
        var BOOLEAN = Token("true|false", Int.MAX_VALUE)
        var STRING = Token("\".*?\"", Int.MAX_VALUE)
    }
}
