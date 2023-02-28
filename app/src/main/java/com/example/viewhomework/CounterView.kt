package com.example.viewhomework

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        isClickable = true
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CounterView,
            0, 0
        ).apply {
            try {
                counterMode = getBoolean(R.styleable.CounterView_counter_mode, false)
                counter = getInteger(R.styleable.CounterView_start_value, 0)
            } finally {
                recycle()
            }
            isClickable = true
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true
        if (counterMode) counter++ else counter--

        invalidate()
        return true
    }

    private var counter: Int
    private var counterMode: Boolean
    private var radius = 50.0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    fun switch() {
        counterMode = !counterMode
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when {
            counter < 10 -> {
                paint.color = Color.GREEN
                radius = 100F
            }
            counter in 10..19 -> {
                paint.color = Color.YELLOW
                radius = 200F
            }
            else -> {
                paint.color = Color.RED
                radius = 300F
            }
        }
        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            radius,
            paint
        )
        paint.color = Color.BLACK
        canvas.drawText(
            counter.toString(),
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            paint
        )
    }
}