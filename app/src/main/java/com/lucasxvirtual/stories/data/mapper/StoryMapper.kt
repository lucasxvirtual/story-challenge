package com.lucasxvirtual.stories.data.mapper

import com.lucasxvirtual.stories.data.model.Story
import com.lucasxvirtual.stories.data.response.StoryResponse
import javax.inject.Inject

/**
 * Convert remote story data story object.
 */
class StoryMapper @Inject constructor() {

    fun map(response : StoryResponse) : List<Story> {
        return response.images.map {
            Story(
                id = it.id,
                url = it.url,
                largeUrl = it.largeUrl,
                sourceId = it.sourceId
            )
        }
    }

}