package com.cft.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cft.data.model.CardMetaData.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME)
data class CardMetaData(

    @PrimaryKey
    var bin: String = "",

    @Embedded(prefix = "number_")
    @SerializedName("number")
    var number: Number? = null,

    @SerializedName("scheme")
    var scheme: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("brand")
    var brand: String? = null,

    @SerializedName("prepaid")
    var prepaidBoolean: Boolean? = null,

    @Embedded(prefix = "country_")
    @SerializedName("country")
    var country: Country? = null,

    @Embedded(prefix = "bank_")
    @SerializedName("bank")
    var bank: Bank? = null,
) {

    val prepaid: String
        get() {
            prepaidBoolean?.let { value ->
                return if (value) "Yes" else "No"
            }
            return "?"
        }

    companion object {
        const val TABLE_NAME = "card_meta_data_table"
        const val BIN_COLUMN_NAME = "bin"
    }
}
