package com.lowae.shadows

import androidx.annotation.ColorInt
import androidx.annotation.Px

/**
 * Provides the ability to control the parameters of the shadow effect.
 */
interface ShadowAbility {

    val shadowSpec: ShadowSpec

    fun updateShadowColor(@ColorInt shadowColor: Int)

    fun updateShadowRadius(@Px shadowRadius: Float)

    fun updateShadowCorners(@Px cornerSize: Float)

    fun updateShadowCorners(@Px tl: Float, @Px tr: Float, @Px bl: Float, @Px br: Float)

    fun updateShadowOffsetX(@Px dx: Float)

    fun updateShadowOffsetY(@Px dy: Float)

    fun updateShadowOffset(@Px dx: Float, @Px dy: Float)

    fun updateShadow(update: ShadowSpec.() -> Unit)

}