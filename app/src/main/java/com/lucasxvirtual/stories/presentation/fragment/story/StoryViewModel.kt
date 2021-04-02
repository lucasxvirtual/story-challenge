package com.lucasxvirtual.stories.presentation.fragment.story

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucasxvirtual.stories.data.api.Status
import com.lucasxvirtual.stories.data.model.Story
import com.lucasxvirtual.stories.domain.usecase.GetStoriesUseCase

class StoryViewModel @ViewModelInject internal constructor(
    private val getStoriesUseCase: GetStoriesUseCase
) : ViewModel()  {

    private val stories = MutableLiveData<List<Story>>().apply { value = arrayListOf() }
    private val error = MutableLiveData<String?>().apply { value = null }

    fun getStories() : LiveData<List<Story>> = stories
    fun getError() : LiveData<String?> = error

    suspend fun fetchStories() {
        if(stories.value!!.isEmpty()) {
            val storiesRequest = getStoriesUseCase.execute()
            when (storiesRequest.status) {
                Status.SUCCESS -> handleSuccess(storiesRequest.data!!)
                Status.ERROR -> handleError(storiesRequest.message!!)
                else -> {
                }
            }
        }
    }

    private fun handleSuccess(stories : List<Story>) {
        this.stories.postValue(stories)
    }

    private fun handleError(message : String) {
        error.postValue(message)
    }

}