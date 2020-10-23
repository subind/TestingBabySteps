package com.bridgetree.testingbabysteps

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class RegistrationUtilTest{

    @Test
    fun `empty userName returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
                "",
                "123",
                "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Megna",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid userName and correctly repeated password returns true`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "bindu",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }


    @Test
    fun `userName already exists returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Subind",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `passwords do not match returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Megna",
            "123",
            "abc"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password length less than 2 digits returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Megna",
            "1",
            "1"
        )
        assertThat(result).isFalse()
    }


}