package com.andy.picker.wheel;

/**
 * Created by Andy.Li on 2016/12/22.
 */

public interface WheelTimeType {

    // 年月日时分
    public final int ALL = 1;

    public final String ALL_S = "yyyy-MM-dd HH:mm";

    // 年月日
    public final int YEAR_MONTH_DAY = 2;

    public final String YEAR_MONTH_DAY_S = "yyyy-MM-dd";

    // 时分
    public final int HOURS_MINS = 3;

    public final String HOURS_MINS_S = "HH:mm";

    // 月日时分
    public final int MONTH_DAY_HOUR_MIN = 4;

    public final String MONTH_DAY_HOUR_MIN_S = "MM-dd HH:mm";

    // YEAR_MONTH
    public final int YEAR_MONTH = 5;

    public final String YEAR_MONTH_S = "yyyy-MM";


}
