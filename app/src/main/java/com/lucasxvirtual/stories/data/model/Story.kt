package com.lucasxvirtual.stories.data.model

import java.io.Serializable

data class Story(
    val id : Int,
    val url : String,
    val largeUrl : String,
    val sourceId : String?
) : Serializable