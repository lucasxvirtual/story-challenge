package com.lucasxvirtual.stories.di

import com.lucasxvirtual.stories.data.api.ResponseHandler
import com.lucasxvirtual.stories.data.api.StoryService
import com.lucasxvirtual.stories.data.mapper.StoryMapper
import com.lucasxvirtual.stories.data.repository.StoryRepositoryImpl
import com.lucasxvirtual.stories.domain.repository.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Dependency injection for repository classes.
 */

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideStoryRepository(storyService: StoryService, storyMapper: StoryMapper, responseHandler: ResponseHandler): StoryRepository {
        return StoryRepositoryImpl(storyService, storyMapper, responseHandler)
    }
}