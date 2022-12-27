package com.cft.data.model

import com.google.gson.annotations.SerializedName

data class Number(
    @SerializedName("length")
    var length: Int?,

    @SerializedName("luhn")
    var luhnBoolean: Boolean?,
) {
    val luhn: String
        get() {
            luhnBoolean?.let { value ->
                return if (value) "Yes" else "No"
            }
            return "?"
        }
}
