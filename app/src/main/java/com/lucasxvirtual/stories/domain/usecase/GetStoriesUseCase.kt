package com.lucasxvirtual.stories.domain.usecase

import com.lucasxvirtual.stories.data.api.Resource
import com.lucasxvirtual.stories.data.model.Story
import com.lucasxvirtual.stories.domain.repository.StoryRepository
import javax.inject.Inject

/**
 * fetch stories.
 */
class GetStoriesUseCase @Inject constructor(private val storyRepository: StoryRepository) {

    suspend fun execute() : Resource<List<Story>> {
        return storyRepository.getStories()
    }

}