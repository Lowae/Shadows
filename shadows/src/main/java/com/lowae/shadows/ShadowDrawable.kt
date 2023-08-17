package com.lowae.shadows

import android.graphics.Rect
import android.graphics.drawable.PaintDrawable

class ShadowDrawable(val shadowSpec: ShadowSpec) : PaintDrawable() {

    private val cornerRadii = FloatArray(8)

    override fun onBoundsChange(bounds: Rect) {
        bounds.inset(shadowSpec.inset.toInt(), shadowSpec.inset.toInt())
        super.onBoundsChange(bounds)
    }

    fun update(update: ShadowSpec.() -> Unit) {
        update(shadowSpec)
        paint.setShadowLayer(
            shadowSpec.shadowRadius,
            shadowSpec.shadowDX,
            shadowSpec.shadowDY,
            shadowSpec.shadowColor
        )
        if (shadowSpec.cornerSize != 0f) {
            setCornerRadius(shadowSpec.cornerSize)
        } else {
            cornerRadii[0] = shadowSpec.cornerSizeTopLeft
            cornerRadii[1] = cornerRadii[0]
            cornerRadii[2] = shadowSpec.cornerSizeTopRight
            cornerRadii[3] = cornerRadii[2]
            cornerRadii[4] = shadowSpec.cornerSizeBottomLeft
            cornerRadii[5] = cornerRadii[4]
            cornerRadii[6] = shadowSpec.cornerSizeBottomRight
            cornerRadii[7] = cornerRadii[6]
            setCornerRadii(cornerRadii)
        }
    }

}