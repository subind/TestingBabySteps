package com.bridgetree.testingbabysteps.other

/**
 * Generic wrapper class that wraps around our object & emits the values that we want it to emit,
 * useful for success status, error status & loading status*/

/**
 * @Keyword out
 * Denotes that all classes of this type are accepted
 * lets say - 'out Number' accepts integers, floating, decimal, etc,.
 *
 * @param data
 * Lets say the Resource class is wrapped around a list of songs that were loaded, the the 'data' param
 * will be the list of songs, so that we have access to it in our fragment & activities
 *
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}