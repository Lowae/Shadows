package com.lowae.shadows.widgets

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.FrameLayout
import com.lowae.shadows.ShadowAbility
import com.lowae.shadows.ShadowAbilityImpl
import com.lowae.shadows.ShadowSpec

class ShadowFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes), ShadowAbility {

    private val shadowImpl: ShadowAbilityImpl = ShadowAbilityImpl(this)

    init {
        shadowImpl.initAttrs(attrs)
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        shadowImpl.drawableStateChanged()
    }

    override fun dispatchDraw(canvas: Canvas) {
        shadowImpl.dispatchDraw(canvas)
        super.dispatchDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        shadowImpl.onSizeChanged(w, h, oldw, oldh)
    }

    override val shadowSpec: ShadowSpec = shadowImpl.shadowSpec

    override fun updateShadowColor(shadowColor: Int) {
        shadowImpl.updateShadowColor(shadowColor)
    }

    override fun updateShadowRadius(shadowRadius: Float) {
        shadowImpl.updateShadowRadius(shadowRadius)
    }

    override fun updateShadowCorners(cornerSize: Float) {
        shadowImpl.updateShadowCorners(cornerSize)
    }

    override fun updateShadowCorners(tl: Float, tr: Float, bl: Float, br: Float) {
        shadowImpl.updateShadowCorners(tl, tr, bl, br)
    }

    override fun updateShadowOffsetX(dx: Float) {
        shadowImpl.updateShadowOffsetX(dx)
    }

    override fun updateShadowOffsetY(dy: Float) {
        shadowImpl.updateShadowOffsetY(dy)
    }

    override fun updateShadowOffset(dx: Float, dy: Float) {
        shadowImpl.updateShadowOffset(dx, dy)
    }

    override fun updateShadow(update: ShadowSpec.() -> Unit) {
        shadowImpl.updateShadow(update)
    }
}