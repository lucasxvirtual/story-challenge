package com.lucasxvirtual.stories.presentation.util

import android.view.MotionEvent
import android.view.View

class MyTouchEvent(private val screenX : Int,
                   private val onEvent : (EventType) -> Unit) : View.OnTouchListener {

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        if(motionEvent.action == MotionEvent.ACTION_DOWN) {
            onEvent.invoke(EventType.DOWN)
        }
        if(motionEvent.action == MotionEvent.ACTION_UP){
            onEvent.invoke(EventType.UP)
            view.performClick()
            val downTime = (motionEvent.downTime / 1000).toInt()
            val upTime = (motionEvent.eventTime / 1000).toInt()
            if(upTime - downTime > 1) {
                return true
            }

            if(motionEvent.rawX > screenX * 0.75)
                onEvent.invoke(EventType.RIGHT)
            else if(motionEvent.rawX < screenX * 0.25)
                onEvent.invoke(EventType.LEFT)
        }
        return true
    }

    enum class EventType {
        RIGHT,
        LEFT,
        DOWN,
        UP
    }
}