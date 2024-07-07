package com.example.shadowtest.shadow;

import android.content.res.ColorStateList;

/**
 * Shadow
 * 作者： 刘少轩
 * 创建时间： 2024/7/7
 * 描述：
 **/
public interface Shadow {
    /**
     * The elevation value. There are useful values of elevation defined in xml as
     * carbon_elevationFlat, carbon_elevationLow, carbon_elevationMedium, carbon_elevationHigh,
     * carbon_elevationMax.
     */
    float getElevation();

    void setElevation(float elevation);

    float getTranslationZ();

    void setTranslationZ(float translationZ);

    ColorStateList getElevationShadowColor();

    void setElevationShadowColor(ColorStateList color);

    void setElevationShadowColor(int color);

    int getOutlineAmbientShadowColor();

    void setOutlineAmbientShadowColor(int color);

    void setOutlineAmbientShadowColor(ColorStateList color);

    int getOutlineSpotShadowColor();

    void setOutlineSpotShadowColor(int color);

    void setOutlineSpotShadowColor(ColorStateList color);
}
