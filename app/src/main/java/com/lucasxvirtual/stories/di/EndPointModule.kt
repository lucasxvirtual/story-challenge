package com.lucasxvirtual.stories.di

import com.lucasxvirtual.stories.data.api.StoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Dependency injection for remote data source classes.
 */

@InstallIn(ApplicationComponent::class)
@Module
class EndPointModule {

    @Singleton
    @Provides
    fun provideStoryService(): StoryService {
        return StoryService.create()
    }

}