package org.uwcchina.vr_experience_android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class LiquidView extends View {
    private Color backgroundColor, textColor, lineColor;

    public LiquidView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.LiquidView,
                0, 0);

        try {
            backgroundColor = Color.valueOf(
                    a.getColor(R.styleable.LiquidView_backgroundColor, Color.WHITE)
            );
            textColor = Color.valueOf(
                    a.getColor(R.styleable.LiquidView_textColor, Color.WHITE)
            );
            lineColor = Color.valueOf(
                    a.getColor(R.styleable.LiquidView_lineColor, Color.WHITE)
            );
        } finally {
            a.recycle();
        }
    }


    private int measureDimension(int desiredSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = desiredSize;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        if (result < desiredSize){
            Log.e("LiquidView", "The view is too small, the content might get cut");
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.v("Chart onMeasure w", MeasureSpec.toString(widthMeasureSpec));
        Log.v("Chart onMeasure h", MeasureSpec.toString(heightMeasureSpec));

        int desiredWidth = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
        int desiredHeight = getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(measureDimension(desiredWidth, widthMeasureSpec),
                measureDimension(desiredHeight, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v("LiquidView onDraw", "Called.");
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        canvas.drawPaint(paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(16);
        canvas.drawText("Hello", 0, 0, paint);
    }
}
