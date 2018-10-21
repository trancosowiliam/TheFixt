package br.com.jwk.thefixt.data.model.dto

import com.google.gson.annotations.SerializedName

open class OMDbData(
        @SerializedName("Response") val response: Boolean,
        @SerializedName("Error") val error: String?
)