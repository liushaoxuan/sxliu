package com.example.shadowtest.shadow;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shadowtest.R;

/**
 * ShadowImageView
 * 作者： 刘少轩
 * 创建时间： 2024/7/7
 * 描述：
 **/
public class ShadowImageView extends androidx.appcompat.widget.AppCompatImageView implements Shadow {

    // 重点就是利用下面的属性：
    // 拔高Z轴可以通过控制elevation和translationZ。区别：
    // elevation：一般是写在 xml 文件中做静态配置，单纯的控制Z轴；
    // translateZ：除了控制Z轴，还可以用来控制动画效果，比如我们点击按钮时希望它有一个弹起的效果。
    // 由于我们只需要实现阴影效果，所以我们只关注elevation即可。
    private float elevation = 0;
    private float translationZ = 0;
    private ColorStateList elevationShadowColor;
    // 表示光源的颜色
    private ColorStateList ambientShadowColor, spotShadowColor;
    public static final boolean IS_LOLLIPOP_OR_HIGHER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

    public static final boolean IS_PIE_OR_HIGHER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    public ShadowImageView(@NonNull Context context) {
        this(context, null);
    }

    public ShadowImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShadowImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    //获取自定义属性
    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ShadowImageView);
        // 获取xml设置的elevation属性的值，并设置给View
        elevation = a.getDimension(R.styleable.ShadowImageView_elevation, 0);
        // 获取xml设置的shadowColor属性的值，并设置给View
        elevationShadowColor = a.getColorStateList(R.styleable.ShadowImageView_elevationShadowColor);
        setElevationShadowColor(elevationShadowColor != null ? elevationShadowColor.withAlpha(255) : null);
        //如果有设置环境光源 ambientShadowColor，则设置给View
        if (a.hasValue(R.styleable.ShadowImageView_spotShadowColor)) {
            ambientShadowColor = a.getColorStateList(R.styleable.ShadowImageView_spotShadowColor);
            setOutlineAmbientShadowColor(ambientShadowColor != null ? ambientShadowColor.withAlpha(255) : null);
        }
        //如果有设置主光源spotShadowColor，则设置给View
        if (a.hasValue(R.styleable.ShadowImageView_ambientShadowColor)) {
            spotShadowColor = a.getColorStateList(R.styleable.ShadowImageView_ambientShadowColor);
            setOutlineSpotShadowColor(spotShadowColor != null ? spotShadowColor.withAlpha(255) : null);
        }
        a.recycle();
    }
    @Override
    public ColorStateList getElevationShadowColor() {
        return elevationShadowColor;
    }

    @Override
    public void setElevationShadowColor(ColorStateList color) {
        elevationShadowColor = color;
        ambientShadowColor = spotShadowColor = color;
        setElevation(elevation);
        setTranslationZ(translationZ);
    }

    @Override
    public void setElevationShadowColor(int color) {
        elevationShadowColor = ColorStateList.valueOf(color);
        ambientShadowColor = spotShadowColor = ColorStateList.valueOf(color);
        setElevation(elevation);
        setTranslationZ(translationZ);
    }

    @Override
    public void setOutlineAmbientShadowColor(ColorStateList color) {
        ambientShadowColor = color;
        if (IS_PIE_OR_HIGHER) {
            super.setOutlineAmbientShadowColor(color.getColorForState(getDrawableState(), color.getDefaultColor()));
        } else {
            setElevation(elevation);
            setTranslationZ(translationZ);
        }

    }

    @Override
    public void setOutlineSpotShadowColor(ColorStateList color) {
        setOutlineAmbientShadowColor(color);
        if (IS_PIE_OR_HIGHER) {
            super.setOutlineSpotShadowColor(color.getColorForState(getDrawableState(), color.getDefaultColor()));
        } else {
            setElevation(elevation);
            setTranslationZ(translationZ);
        }
    }

    @Override
    public float getElevation() {
        return elevation;
    }

    @Override
    public void setElevation(float elevation) {
        if (IS_PIE_OR_HIGHER) {
            Log.e("************************************","IS_PIE_OR_HIGHER   setTranslationZ(0)       " + translationZ);
            Log.e("************************************","IS_PIE_OR_HIGHER   setElevation(0)       " + elevation);
            super.setElevation(elevation);
            super.setTranslationZ(translationZ);
        } else if (IS_LOLLIPOP_OR_HIGHER) {
            if (ambientShadowColor == null || spotShadowColor == null) {
                Log.e("************************************","IS_LOLLIPOP_OR_HIGHER setTranslationZ(0)       " + translationZ);
                Log.e("************************************","IS_LOLLIPOP_OR_HIGHER setElevation(0)       " + elevation);
                super.setElevation(elevation);
                super.setTranslationZ(translationZ);
            } else {
                Log.e("************************************","IS_LOLLIPOP_OR_HIGHER setTranslationZ(0)       " + 0);
                Log.e("************************************","IS_LOLLIPOP_OR_HIGHER setElevation(0)       " + 0);
                super.setElevation(0);
                super.setTranslationZ(0);
            }
        } else if (elevation != this.elevation && getParent() != null) {
            ((View)getParent()).postInvalidate();
        }
        this.elevation = elevation;
    }

    public float getTranslationZ() {
        return translationZ;
    }

    public void setTranslationZ(float translationZ) {
        if (translationZ == this.translationZ)
            return;
        if (IS_PIE_OR_HIGHER) {
            Log.e("************************************","IS_PIE_OR_HIGHER setTranslationZ(translationZ)       " + translationZ);
            super.setTranslationZ(translationZ);
        } else if (IS_LOLLIPOP_OR_HIGHER) {
            if (ambientShadowColor == null || spotShadowColor == null) {
                Log.e("************************************","IS_LOLLIPOP_OR_HIGHER setTranslationZ(translationZ)       " + translationZ);
                super.setTranslationZ(translationZ);
            } else {
                Log.e("************************************","IS_LOLLIPOP_OR_HIGHER setTranslationZ(0)       " + 0);
                super.setTranslationZ(0);
            }
        } else if (translationZ != this.translationZ && getParent() != null) {
            ((View) getParent()).postInvalidate();
        }
        this.translationZ = translationZ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (IS_PIE_OR_HIGHER) {
            if (spotShadowColor != null)
                Log.e("************************************","onDraw   spotShadowColor != null");
                super.setOutlineSpotShadowColor(spotShadowColor.getColorForState(getDrawableState(), spotShadowColor.getDefaultColor()));
            if (ambientShadowColor != null)
                Log.e("************************************","onDraw   ambientShadowColor != null");
                super.setOutlineAmbientShadowColor(ambientShadowColor.getColorForState(getDrawableState(), ambientShadowColor.getDefaultColor()));
        }
        super.onDraw(canvas);
    }
}
