package com.pandaveloper.transformersdemo.presentation.base

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pandaveloper.transformersdemo.util.DebounceClickListener

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    removeObservers(liveData)
    liveData.observe(this, Observer(body))
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.removeObservers(liveData: L) {
    liveData.removeObservers(this)
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.makeVisibleElseGone(makeVisible: Boolean) {
    visibility = if (makeVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.makeVisibleElseInvisible(makeVisible: Boolean) {
    visibility = if (makeVisible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.isGone() = visibility == View.GONE

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.setDebounceOnClickListener(doClick: (View) -> Unit) = setOnClickListener(DebounceClickListener(doClick = doClick))