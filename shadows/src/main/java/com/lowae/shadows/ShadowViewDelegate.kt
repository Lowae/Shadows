package com.lowae.shadows

import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

interface ShadowViewDelegate {

    val view: View

    fun initAttrs(attrs: AttributeSet?)

    fun dispatchDraw(canvas: Canvas)

    fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int)

    fun drawableStateChanged()
}