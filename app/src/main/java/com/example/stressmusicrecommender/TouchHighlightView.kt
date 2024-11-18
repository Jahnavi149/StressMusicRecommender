package com.example.stressmusicrecommender

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchHighlightView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private var touchX: Float = -1f
    private var touchY: Float = -1f
    private val paint: Paint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
            touchX = event.x
            touchY = event.y
            invalidate() // Redraw the view
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (touchX != -1f && touchY != -1f) {
            // Draw a circle at the touch point
            canvas.drawCircle(touchX, touchY, 20f, paint)
        }
    }

    fun resetHighlight() {
        touchX = -1f
        touchY = -1f
        invalidate()
    }
}
