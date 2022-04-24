

    class ComplexTest {
        val calculator = Calculator(NUMBER, PLUS, MINUS, MULTIPLE, KILO, GIGA, PI, E, LESS, LARGER, LOG10, IF)
        @Test
        fun complex() {
            val calculated = calculator.calculate("If( 2*PI +E-3 > 5, Log(1K +1K)+4, Log(2G) ) +1")
            assertEquals(calculated, log10(1000+1000.0)+4+1)
        }
    }
