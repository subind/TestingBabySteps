package com.bridgetree.testingbabysteps

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class HomeWorkTest{

    /**
     * Fibonacci, test cases:
     * ...fib(0) returns 0
     * ...fib(1) returns 1
     * ...fib(4) returns 3
     * ...fib(5) returns 5
     * ...fib(8) returns 21
     */

    @Test
    fun `fib of 0 returns 0`(){
        var result = HomeWork.fib(0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `fib of 1 returns 1`(){
        var result = HomeWork.fib(1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `fib of 4 returns 3`(){
        var result = HomeWork.fib(4)
        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `fib of 5 returns 5`(){
        var result = HomeWork.fib(5)
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun `fib of 8 returns 21`(){
        var result = HomeWork.fib(8)
        assertThat(result).isEqualTo(21)
    }


    /**
     * Check braces, are invalid if...
     * ...1. particular bracket type doesn't have a pair
     * ...2. opening & then closing brackets are not in order its supposed to be ,i.e, ") a * b ("
     * ...3. balanced brackets {[()]}
     */

    @Test //1
    fun `"(a * b))" should return false`(){
        var result = HomeWork.checkBraces("(a * b))")
        assertThat(result).isFalse()
    }

    @Test //1
    fun `"{(a * b}" should return false`(){
        var result = HomeWork.checkBraces("{(a * b}")
        assertThat(result).isFalse()
    }

    @Test //2
    fun `") a * b (" should return false`(){
        var result = HomeWork.checkBraces(") a * b (")
        assertThat(result).isFalse()
    }

    @Test //3
    fun `"({a * b})" should return false`(){
        var result = HomeWork.checkBraces("({a * b})")
        assertThat(result).isFalse()
    }

    @Test //3
    fun `"{(a * b)}" should return true`(){
        var result = HomeWork.checkBraces("{(a * b)}")
        assertThat(result).isTrue()
    }
}