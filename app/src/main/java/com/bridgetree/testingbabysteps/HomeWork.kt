package com.bridgetree.testingbabysteps

object HomeWork {

    /**
     * Returns the n-th fibonacci number
     * They are defined like this:
     * fib(0) = 0
     * fib(1) = 1
     * fib(n) = fib(n-2) + fib(n-1)
     */
    fun fib(n: Int): Long {
        if (n == 0 || n == 1) {
            return n.toLong()
        }
        var a = 0L
        var b = 1L
        var c = 1L
        (1..n - 1).forEach { i ->
            c = a + b
            a = b
            b = c
        }
        return c
    }

    /**
     * Checks if the braces are set correctly
     * e.g. "(a * b))" should return false
     */
    fun checkBraces(string: String): Boolean {
        var a = string.indexOf('(')
        var b = string.indexOf(')')
        var openCloseOrder = a < b

        var countBalance = string.count { it == '(' } == string.count { it == ')' }

        var x = string.indexOf('{')
        var y = string.indexOf('[')
        var z = string.indexOf('(')
        var balancedBrackets = false
        if(x.equals(-1)){
            balancedBrackets = y < z
        }else if(y.equals(-1)){
            balancedBrackets = x < z
        }else if(z.equals(-1)){
            balancedBrackets = x < y
        }

        return openCloseOrder && countBalance && balancedBrackets
    }

}