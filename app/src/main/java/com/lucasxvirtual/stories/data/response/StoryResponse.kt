package com.lucasxvirtual.stories.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StoryObject(
    val id : Int,
    val url : String,
    @SerializedName("large_url") val largeUrl : String,
    @SerializedName("source_id") val sourceId : String
) : Serializable

data class StoryResponse(
    val images : ArrayList<StoryObject>
)