package com.lucasxvirtual.stories.domain.repository

import com.lucasxvirtual.stories.data.api.Resource
import com.lucasxvirtual.stories.data.model.Story


interface StoryRepository {

    suspend fun getStories() : Resource<List<Story>>

}