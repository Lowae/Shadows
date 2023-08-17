package com.lowae.shadows

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Px

/**
 * CSS style shadow params
 */
data class ShadowSpec(
    @ColorInt var shadowColor: Int = Color.BLACK,
    @Px var shadowRadius: Float = 0f,
    @Px var shadowDX: Float = 0f,
    @Px var shadowDY: Float = 0f,
    @Px var cornerSize: Float = 0f,
    @Px var cornerSizeTopLeft: Float = 0f,
    @Px var cornerSizeTopRight: Float = 0f,
    @Px var cornerSizeBottomLeft: Float = 0f,
    @Px var cornerSizeBottomRight: Float = 0f,
    @Px var inset: Float = 0f
)