package com.lowae.shadows

import android.content.res.Resources

private val resources = Resources.getSystem()

internal val Number.dp: Int
    get() = dpF.toInt()

internal val Number.dpF: Float
    get() = this.toFloat() / resources.displayMetrics.density


internal val Number.pxF: Float
    get() = this.toFloat() * resources.displayMetrics.density


internal val Number.px: Int
    get() = pxF.toInt()


/**
 * external/skia/src/core/SkBlurMask.cpp
 */
private const val BLUR_SIGMA_SCALE = 0.57735f

internal fun convertRadiusToSigma(radius: Float) =
    if (radius > 0) BLUR_SIGMA_SCALE * radius + 0.5f else 0.0f;

