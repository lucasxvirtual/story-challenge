package com.lucasxvirtual.stories.presentation.fragment.story

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.lucasxvirtual.stories.databinding.FragmentStoryBinding
import com.lucasxvirtual.stories.presentation.adapter.StoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoryFragment : Fragment() {

    private val viewModel : StoryViewModel by viewModels()
    private lateinit var storyAdapter : StoryAdapter

    private lateinit var binding : FragmentStoryBinding

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PAGE_PARAM, binding.viewPager.currentItem)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentStoryBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }

        viewModel.let {
            lifecycleScope.launch {
                it.fetchStories()
            }

            it.getError().observe(viewLifecycleOwner, Observer { error ->
                error?.let {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            })
        }

        setupViewPager(binding.viewPager, savedInstanceState)

        return binding.root
    }

    private fun setupViewPager(viewPager : ViewPager2, savedInstanceState: Bundle?){
        storyAdapter = StoryAdapter(
                arrayListOf(),
                getScreenWidth(),
                next = {
                    val currentItem = viewPager.currentItem
                    if(currentItem < storyAdapter.itemCount){
                        viewPager.currentItem = currentItem + 1
                    }
                },
                previous = {
                    val currentItem = viewPager.currentItem
                    if(currentItem > 0){
                        viewPager.currentItem = currentItem - 1
                    }
                }
        )
        viewPager.adapter = storyAdapter
        viewPager.isUserInputEnabled = false

        viewModel.getStories().observe(viewLifecycleOwner, Observer { stories ->
            storyAdapter.replaceItems(stories)
            val reload = savedInstanceState?.containsKey(PAGE_PARAM) ?: false
            if(reload) {
                viewPager.currentItem = savedInstanceState!![PAGE_PARAM] as Int
            }
        })
    }

    private fun getScreenWidth() : Int{
        val outMetrics = DisplayMetrics()

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = requireActivity().display
            display?.getRealMetrics(outMetrics)
        } else {
            @Suppress("DEPRECATION")
            val display = requireActivity().windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics)
        }
        return outMetrics.widthPixels
    }

    companion object {
        const val PAGE_PARAM = "PAGE"
    }

}