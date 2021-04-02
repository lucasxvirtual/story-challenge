package com.lucasxvirtual.stories

import com.lucasxvirtual.stories.data.mapper.StoryMapper
import com.lucasxvirtual.stories.data.response.StoryObject
import com.lucasxvirtual.stories.data.response.StoryResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StoryMapperUnitTest {

    private val response = StoryResponse(
            arrayListOf(
                    StoryObject(
                            1,
                            "",
                            "",
                            ""
                    )
            )
    )

    private val mapper = StoryMapper()

    @Test
    fun map() {
        val stories = mapper.map(response)

        assertEquals(stories.size, 1)
        assertEquals(stories.firstOrNull()?.id, response.images.first().id)
    }
}