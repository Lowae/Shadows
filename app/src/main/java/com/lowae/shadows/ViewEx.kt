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

