package com.lowae.shadows

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.PaintDrawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Px
import kotlin.math.absoluteValue

class ShadowViewImpl(override val view: View) : ShadowViewDelegate, ShadowView {

    private var layerDrawable: LayerDrawable? = null

    private val shadowDrawable by lazy(LazyThreadSafetyMode.NONE) {
        ShadowDrawable(ShadowSpec()).apply {
            callback = object : Drawable.Callback {
                override fun invalidateDrawable(who: Drawable) {
                    view.invalidate()
                }

                override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {

                }

                override fun unscheduleDrawable(who: Drawable, what: Runnable) {

                }
            }
        }
    }
    private var includeShadowPadding = true

    override fun initAttrs(attrs: AttributeSet?) {
        val attr = view.context.obtainStyledAttributes(attrs, R.styleable.ShadowViews, 0, 0)
        try {
            val shadowRadius = attr.getDimension(R.styleable.ShadowViews_shadow_radius, 0f)
            val dX = attr.getDimension(R.styleable.ShadowViews_shadow_dx, 0f)
            val dY = attr.getDimension(R.styleable.ShadowViews_shadow_dy, 0f)
            val corners = attr.getDimension(R.styleable.ShadowViews_shadow_corner, 0f)
            val cornerTL = attr.getDimension(R.styleable.ShadowViews_shadow_corner_tl, 0f)
            val cornerTR = attr.getDimension(R.styleable.ShadowViews_shadow_corner_tr, 0f)
            val cornerBL = attr.getDimension(R.styleable.ShadowViews_shadow_corner_bl, 0f)
            val cornerBR = attr.getDimension(R.styleable.ShadowViews_shadow_corner_br, 0f)
            val inset = attr.getDimension(R.styleable.ShadowViews_shadow_inset, 0f)
            val shadowColor = attr.getColor(R.styleable.ShadowViews_shadow_color, Color.BLACK)
            val shadowBackground = attr.getDrawable(R.styleable.ShadowViews_shadow_background)
            includeShadowPadding =
                attr.getBoolean(R.styleable.ShadowViews_include_shadow_padding, true)

            shadowDrawable.update {
                this.shadowColor = shadowColor
                this.shadowRadius = shadowRadius
                this.shadowDX = dX
                this.shadowDY = dY
                this.cornerSize = corners
                this.cornerSizeTopLeft = cornerTL
                this.cornerSizeTopRight = cornerTR
                this.cornerSizeBottomLeft = cornerBL
                this.cornerSizeBottomRight = cornerBR
                this.inset = inset
            }
            updateShadowPadding(shadowDrawable.shadowSpec)

            layerDrawable = LayerDrawable(arrayOf(shadowDrawable, shadowBackground))
        } finally {
            attr.recycle()
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        layerDrawable?.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        layerDrawable?.setBounds(
            view.paddingLeft,
            view.paddingTop,
            view.width - view.paddingRight,
            view.height - view.paddingBottom
        )
    }

    override fun drawableStateChanged() {
        layerDrawable?.state = view.drawableState
    }

    private fun updateShadowPadding(shadowSpec: ShadowSpec) {
        if (includeShadowPadding) {
            val shadowPadding = (convertRadiusToSigma(shadowSpec.shadowRadius) * 3).toInt()
            val absDx = shadowSpec.shadowDX.absoluteValue.toInt()
            val absDY = shadowSpec.shadowDY.absoluteValue.toInt()
            view.setPadding(
                shadowPadding + absDx,
                shadowPadding + absDY,
                shadowPadding + absDx,
                shadowPadding + absDY,
            )
        }
    }

    override fun updateShadowColor(shadowColor: Int) {
        shadowDrawable.update {
            this.shadowColor = shadowColor
        }
        view.invalidate()
    }

    override fun updateShadowRadius(@Px shadowRadius: Float) {
        updateShadow {
            this.shadowRadius = shadowRadius
        }
    }

    override fun updateShadowCorners(@Px cornerSize: Float) {
        shadowDrawable.update {
            this.cornerSize = cornerSize
        }
        view.invalidate()
    }

    override fun updateShadowCorners(@Px tl: Float, @Px tr: Float, @Px bl: Float, @Px br: Float) {
        shadowDrawable.update {
            this.cornerSizeTopLeft = tl
            this.cornerSizeTopRight = tr
            this.cornerSizeBottomLeft = bl
            this.cornerSizeBottomRight = br
        }
        view.invalidate()
    }

    override fun updateShadowOffsetX(dx: Float) {
        updateShadow {
            this.shadowDX = dx
        }
    }

    override fun updateShadowOffsetY(dy: Float) {
        updateShadow {
            this.shadowDY = dy
        }
    }

    override fun updateShadowOffset(dx: Float, dy: Float) {
        updateShadow {
            this.shadowDX = dx
            this.shadowDY = dy
        }
    }

    override fun updateShadow(update: ShadowSpec.() -> Unit) {
        shadowDrawable.update(update)
        updateShadowPadding(shadowDrawable.shadowSpec)
        view.invalidate()
    }
}

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