package com.lucasxvirtual.stories

import android.view.InputDevice
import android.view.MotionEvent
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.viewpager2.widget.ViewPager2
import com.lucasxvirtual.stories.data.api.ResponseHandler
import com.lucasxvirtual.stories.di.RepositoryModule
import com.lucasxvirtual.stories.domain.repository.StoryRepository
import com.lucasxvirtual.stories.presentation.activity.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Singleton


@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
class StoryFragmentTest {

    @Module
    @InstallIn(ApplicationComponent::class)
    class TestModule {

        @Provides
        @Singleton
        fun provideStoryRepository(responseHandler: ResponseHandler): StoryRepository {
            return StoryRepositoryTestImpl(responseHandler)
        }
    }

    private val hiltRule = HiltAndroidRule(this)
    private val activityScenario : ActivityScenarioRule<MainActivity> = ActivityScenarioRule(
            MainActivity::class.java)

    @get:Rule
    val chain: RuleChain = RuleChain.outerRule(hiltRule).around(activityScenario)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testFillContent() {
        Espresso.onView(withId(R.id.view_pager)).perform(clickPercent(0.80f, 0.50f))
        activityScenario.scenario.onActivity {
            val viewPager = it.findViewById<ViewPager2>(R.id.view_pager)
            Assert.assertNotNull(viewPager)
            Assert.assertNotNull(viewPager.adapter)
            Assert.assertTrue(StoryRepositoryTestImpl.STORY_NUMBER == viewPager.adapter?.itemCount)
            Assert.assertTrue(viewPager.currentItem == 1)
        }
    }

    private fun clickPercent(pctX: Float, pctY: Float): ViewAction? {
        return GeneralClickAction(
                Tap.SINGLE,
                CoordinatesProvider { view ->
                    val screenPos = IntArray(2)
                    view.getLocationOnScreen(screenPos)
                    val w = view.width
                    val h = view.height
                    val x = w * pctX
                    val y = h * pctY
                    val screenX = screenPos[0] + x
                    val screenY = screenPos[1] + y
                    floatArrayOf(screenX, screenY)
                },
                Press.FINGER,
                InputDevice.SOURCE_MOUSE,
                MotionEvent.BUTTON_PRIMARY)
    }
}