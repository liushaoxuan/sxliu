package com.example.shadowtest.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * CustomImageView
 * 作者： 刘少轩
 * 创建时间： 2024/7/7
 * 描述：
 **/
public class CustomImageView extends AppCompatImageView {

    private float cornerRadius = 20.0f;

    private Paint paint;
    private Path path;

    public CustomImageView(Context context) {
        super(context);
        init();
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Create a rounded rectangle path
        path.reset();
        RectF rect = new RectF(0, 0, getWidth(), getHeight());
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW);

        // Draw the shadow
        canvas.drawPath(path, paint);

        // Clip the canvas to the rounded rectangle path
        canvas.clipPath(path);

        // Draw the image
        super.onDraw(canvas);
    }

    // Setter for corner radius
    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        invalidate();
    }

}
