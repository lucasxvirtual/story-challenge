package com.lucasxvirtual.stories.data.repository

import com.lucasxvirtual.stories.data.api.Resource
import com.lucasxvirtual.stories.data.api.ResponseHandler
import com.lucasxvirtual.stories.data.api.StoryService
import com.lucasxvirtual.stories.data.mapper.StoryMapper
import com.lucasxvirtual.stories.data.model.Story
import com.lucasxvirtual.stories.data.response.StoryResponse
import com.lucasxvirtual.stories.domain.repository.StoryRepository
import javax.inject.Inject


/**
 * Implementation of StoryRepository
 * @see com.lucasxvirtual.stories.domain.repository.StoryRepository
 */
class StoryRepositoryImpl @Inject constructor(
        private val storyService: StoryService,
        private val storyMapper: StoryMapper,
        private val responseHandler: ResponseHandler
): StoryRepository {

    override suspend fun getStories() : Resource<List<Story>> {
        val storyResponse : StoryResponse
        try {
            storyResponse = storyService.getStories()
        } catch (e: Exception) {
            return responseHandler.handleException(e)
        }
        val storyList = storyMapper.map(storyResponse)
        return responseHandler.handleSuccess(storyList)
    }

}
