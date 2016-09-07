package com.nitesh.spark.utils;

import android.content.Context;
import android.util.TypedValue;

public class SizeNDimensionUtils {

    /**
     * This will convert density independent pixels to pixels
     * @param context
     * @param dipValue
     * @return
     */
    public static float dpToPx(Context context, float dipValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
    }
    /**
     * This will convert Scale Independent Pixels to pixels
     * @param context
     * @param spValue
     * @return
     */
    public static float spToPx(Context context, float spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    /**
     * This will convert Pixels to density independent pixels
     * @param context
     * @param pixels
     * @return
     */
    public static float pxToDp(Context context, float pixels) {
        return (pixels / context.getResources().getDisplayMetrics().density);
    }

    /**
     * This will convert Pixels to Scale Independent Pixels
     * @param context
     * @param pixels
     * @return
     */
    public static float pxToSp(Context context, float pixels) {
        return pixels/context.getResources().getDisplayMetrics().scaledDensity;
    }
}