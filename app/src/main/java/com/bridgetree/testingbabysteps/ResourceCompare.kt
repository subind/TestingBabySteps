package com.bridgetree.testingbabysteps

import android.content.Context

class ResourceCompare {

    fun isEqual(context: Context, resId: Int, str: String): Boolean{
        return context.getString(resId) == str
    }

}