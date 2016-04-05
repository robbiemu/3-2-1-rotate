package xyz.selfenrichment.robertotomas.three_two_one_rotate.lib;

/**
 * Created by RobertoTomás on 0004, 4, 4, 2016.
 */

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Utility functions for programmatic layout
 */
public class LayoutUtil {
    /* Singleton Pattern */
    private final String LOG_TAG = LayoutUtil.class.getSimpleName();

    private static LayoutUtil mInstance = null;

    private LayoutUtil(){
        mInstance = new LayoutUtil();
    }

    public static LayoutUtil getInstance() {
        if (mInstance == null) {
            Class clazz = LayoutUtil.class;
            synchronized (clazz) {
                mInstance = new LayoutUtil();
            }
        }
        return mInstance;
    }
    /* /endSingleton Pattern */

    /**
     * A handy getter for display metric width and height.
     * @param c
     * @return float[] of dpWidth, dpHeight
     */
    public static float[] getScreen(Context c){
        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();

        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        return new float[] {dpWidth, dpHeight};
        //Log.d(LayoutUtil.getInstance().LOG_TAG, String.format("%f x %f ", dpWidth, dpHeight));
    }

    public static Boolean twoPanes(Context c){
        Configuration conf = c.getResources().getConfiguration();
        return (Configuration.ORIENTATION_LANDSCAPE == conf.orientation) &&
                (600 <= conf.smallestScreenWidthDp);
    }

    /**
     * A helper function returning the pixels-per-display-pixel
     * @param dp
     * @param context
     * @return int px
     */
    public static int convertDPtoPixels(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (dp * (metrics.densityDpi / 160f));
        return px;
    }

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    public boolean isMultiPane(Context c) {
        return isXLargeTablet(c);
    }

}