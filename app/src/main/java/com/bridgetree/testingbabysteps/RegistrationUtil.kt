package com.bridgetree.testingbabysteps

object RegistrationUtil {

    private val existingUsers = listOf("Subind", "Suresh")

    /**
     * Invalid if...
     * ...userName or password is empty
     * ...userName is already taken
     * ...passwords do not match
     * ...length of password is less than 2
     */

    fun validateRegistrationInput(
            userName: String,
            password: String,
            confirmPassword: String
    ): Boolean{
        if(userName.isEmpty() || password.isEmpty()){
            return false
        }
        if(userName in existingUsers){
            return false
        }
        if(password != confirmPassword){
            return false
        }
        if(password.count { it.isDigit() } < 2){
            return false
        }

        return true
    }

}