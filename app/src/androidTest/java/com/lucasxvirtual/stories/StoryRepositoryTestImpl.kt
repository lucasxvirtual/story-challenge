package com.lucasxvirtual.stories

import com.lucasxvirtual.stories.data.api.Resource
import com.lucasxvirtual.stories.data.api.ResponseHandler
import com.lucasxvirtual.stories.data.api.StoryService
import com.lucasxvirtual.stories.data.mapper.StoryMapper
import com.lucasxvirtual.stories.data.model.Story
import com.lucasxvirtual.stories.data.response.StoryResponse
import com.lucasxvirtual.stories.domain.repository.StoryRepository
import javax.inject.Inject

class StoryRepositoryTestImpl @Inject constructor(
        private val responseHandler: ResponseHandler
): StoryRepository {

    override suspend fun getStories() : Resource<List<Story>> {
        val storyList = arrayListOf(
                Story(
                        1,
                        "https://splashbase.s3.amazonaws.com/newoldstock/regular/tumblr_ph8vgdJV2r1sfie3io1_1280.jpg",
                        "https://splashbase.s3.amazonaws.com/newoldstock/large/tumblr_ph8vgdJV2r1sfie3io1_1280.jpg",
                        ""
                ),
                Story(
                        1,
                        "https://splashbase.s3.amazonaws.com/newoldstock/regular/tumblr_ph8vgdJV2r1sfie3io1_1280.jpg",
                        "https://splashbase.s3.amazonaws.com/newoldstock/large/tumblr_ph8vgdJV2r1sfie3io1_1280.jpg",
                        ""
                )
        )
        return responseHandler.handleSuccess(storyList)
    }

    companion object {
        const val STORY_NUMBER = 2
    }

}
