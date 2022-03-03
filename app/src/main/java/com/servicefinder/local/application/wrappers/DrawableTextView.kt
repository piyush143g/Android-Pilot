package com.servicefinder.local.application.wrappers

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.servicefinder.local.R


class DrawableTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    var topLeftRadius: Float = 0F
    var topRightRadius: Float = 0F
    var bottomRightRadius: Float = 0F
    var bottomLeftRadius: Float = 0F
    var cornerRadius: Float = 0F
    var strokeWidth: Float = 0F
    var strokeDashWidth: Float = 0F
    var strokeGapWidth: Float = 0F
    var strokeColor: Int = Color.TRANSPARENT
    var color: Int = Color.TRANSPARENT
    val shape = GradientDrawable()
    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.DrawableLayout, 0, 0).use {
            if (background != null) {
                return@use
            }
            color = it.getColor(R.styleable.DrawableLayout_background_color, Color.TRANSPARENT)
            strokeColor = it.getColor(R.styleable.DrawableLayout_stroke_color, Color.TRANSPARENT)
            topLeftRadius = it.getDimension(R.styleable.DrawableLayout_top_left_radius, 0F)
            topRightRadius = it.getDimension(R.styleable.DrawableLayout_top_right_radius, 0F)
            bottomRightRadius = it.getDimension(R.styleable.DrawableLayout_bottom_right_radius, 0F)
            bottomLeftRadius = it.getDimension(R.styleable.DrawableLayout_bottom_left_radius, 0F)
            cornerRadius = it.getDimension(R.styleable.DrawableLayout_corner_radius, 0F)
            strokeWidth = it.getDimension(R.styleable.DrawableLayout_stroke_width, 0F)
            strokeDashWidth = it.getDimension(R.styleable.DrawableLayout_stroke_dash_width, 0F)
            strokeGapWidth = it.getDimension(R.styleable.DrawableLayout_stroke_gap_width, 0F)

            shape.shape = GradientDrawable.RECTANGLE
            shape.setSize(height, width)
            if (cornerRadius == 0F) {
                shape.setCornerRadii(
                    floatArrayOf(
                        topLeftRadius, topLeftRadius, topRightRadius, topRightRadius,
                        bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius
                    )
                )
            } else {
                shape.cornerRadius = cornerRadius
            }
            shape.setStroke(strokeWidth.toInt(), strokeColor, strokeDashWidth, strokeGapWidth)
            shape.setColor(color)
            background = shape
        }
    }

    fun setBackGroundColor(color:Int){
        shape.setColor(color)
        background = shape
    }

    fun setStroke(
        color: Int = strokeColor,
        width: Int = strokeWidth.toInt(),
        strokeDashWidth: Float = this.strokeDashWidth,
        strokeGapWidth: Float = this.strokeGapWidth
    ) {
        shape.setStroke(width, color, strokeDashWidth, strokeGapWidth)
        background = shape
    }

    fun setRadius(cornerRadius: Float) {
        shape.cornerRadius = cornerRadius
        background = shape
    }

    fun setRadii(
        topLeftRadius: Float,
        topRightRadius: Float,
        bottomRightRadius: Float,
        bottomLeftRadius: Float
    ) {
        shape.cornerRadius = 0F
        shape.setCornerRadii(
            floatArrayOf(
                topLeftRadius, topLeftRadius, topRightRadius, topRightRadius,
                bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius
            )
        )
        background = shape
    }

}