package com.andy.picker.wheel;

import android.text.TextUtils;
import android.view.View;

import com.andylidong.pickerview.OptionsPickerView;
import com.andylidong.pickerview.TimePickerView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Andy.Li on 2016/12/23.
 */

public class WheelType extends View {

    private final EventDispatcher mEventDispatcher;

    // 选项选择器
    private OptionsPickerView opPicker;

    // 时间选择器
    private TimePickerView tpPicker;

    // 时间的格式
    private static String timeFormatter;

    public WheelType(ReactContext reactContext) {
        super(reactContext);
        mEventDispatcher = reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
    }

    /**
     * 设置时间picker显示的类型
     * @param type
     */
    public void setTimeInit(ReactContext reactContext,TimePickerView.Type type) {
        if (tpPicker != null) {
           tpPicker = null;
        }
        tpPicker = new TimePickerView(reactContext.getCurrentActivity(), type);
        // 设置标题的内容
        setTimeTitle("");
        // 获取选中的信息
        tpPicker.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                mEventDispatcher.dispatchEvent(
                        new ItemSelectedEvent(getId(), getTime(date)));
            }
        });
    }

    /**
     * 设置时间的开始年份和结束年份
     * @param startYear
     * @param endYear
     */
    public void setTimeData(int startYear, int endYear) {
        if (tpPicker == null) {
            return;
        }
        // 设置数据的范围 要在setTime 之前才有效果哦
        tpPicker.setRange(startYear, endYear);
    }

    /**
     * 设置picker弹出之后选中的时间
     * @param date
     */
    public void setTime(Date date) {
        if (tpPicker == null) {
            return;
        }
        // 设置选中的时间
        tpPicker.setTime(date, timeFormatter);
    }

    /**
     * 设置标题的显示信息
     * @param title
     */
    public void setTimeTitle(String title) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setTitle(title);
    }

    /**
     * 设置数据是否循环
     * @param isCyclic
     */
    public void setTimeCyclic(boolean isCyclic) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setCyclic(isCyclic);
    }


    /**
     * 设置点击色区域是否显示
     * @param isCancelable
     */
    public void setTimeCancelable(boolean isCancelable) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setCancelable(isCancelable);
    }

    /**
     * 设置确定的按钮信息
     * @param submitText
     */
    public void setTimeSubmit(String submitText) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setSubmit(submitText);
    }

    /**
     * 设置取消的按钮信息
     * @param cancelText
     */
    public void setTimeCancel(String cancelText) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setCancel(cancelText);
    }


    /**
     * 设置今天是否显示
     * @param isShowToday
     */
    public void setToday(boolean isShowToday) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setToday(isShowToday);
    }


    /**
     * 设置年份的信息
     * @param yearText
     */
    public void setYearText(String yearText) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setYearText(yearText);
    }

    /**
     * 设置月份的信息
     * @param monthText
     */
    public void setMonthText(String monthText) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setMonthText(monthText);
    }


    /**
     * 设置日期的信息
     * @param dayText
     */
    public void setDayText(String dayText) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setDayText(dayText);
    }


    /**
     * 设置小时的信息
     * @param hourText
     */
    public void setHourText(String hourText) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setHourText(hourText);
    }


    /**
     * 设置分钟的信息
     * @param minuteText
     */
    public void setMinuteText(String minuteText) {
        if (tpPicker == null) {
            return;
        }
        tpPicker.setMinuteText(minuteText);
    }


    /**
     * 判断picker是否显示
     */
    public boolean isTimeShowing() {
        if (tpPicker == null) {
            return false;
        }
        return tpPicker.isShowing();
    }

    /**
     * 隐藏picker
     */
    public void dismissTime () {
        if (tpPicker != null) {
            tpPicker.dismiss();
        }
    }

    /**
     * 显示picker
     */
    public void showTime() {
        if (tpPicker != null) {
            tpPicker.show();
        }
    }

    /**
     * 设置显示时间的格式
     * @param timeFormatter
     */
    public void setTimeFormatter(String timeFormatter) {
        this.timeFormatter = timeFormatter;
    }

    /**
     * 获取想要的时间格式
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormatter);
        return format.format(date);
    }

    /**-------------------------显示选项选择器-----------------------------------------*/

    /**
     * 设置选项picker显示的初始化
     */
    public void setOptionInit(ReactContext reactContext, final ArrayList<String> options) {
        //选项选择器
        if (opPicker != null) {
            opPicker = null;
        }
        opPicker = new OptionsPickerView(reactContext.getCurrentActivity());
        // 设置选择的标题
        setOptionTitle("");
        opPicker.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                mEventDispatcher.dispatchEvent(
                        new ItemSelectedEvent(getId(), options.get(options1)));
            }
        });
    }


    /**
     * 设置选项选择器的数据
     * @param options
     */
    public void setPicker(ArrayList<String> options) {
        if (opPicker == null) {
            return;
        }
        opPicker.setPicker(options);
    }

    /**
     * 设置选项选择器的选中值
     * @param options 选项选择器的数据
     * @param info 查询的信息
     */
    public void setOptions(ArrayList<String> options, String info) {
        if (opPicker == null) {
            return;
        }
        try {
            int index = options.indexOf(info);
            opPicker.setSelectOptions(index);
        }catch (Exception e) {
            opPicker.setSelectOptions(0);
        }
    }

    /**
     * 设置picker的标题
     * @param title
     */
    public void setOptionTitle(String title) {
        if (opPicker == null) {
            return;
        }
        opPicker.setTitle(title);
    }

    /**
     * 设置数据是否循环
     * @param isCyclic
     */
    public void setOptionCyclic(boolean isCyclic) {
        if (opPicker == null) {
            return;
        }
        opPicker.setCyclic(isCyclic);
    }

    /**
     * 设置点击色区域是否显示
     * @param isCancelable
     */
    public void  setOptionCancelable(boolean isCancelable) {
        if (opPicker == null) {
            return;
        }
        opPicker.setCancelable(isCancelable);
    }

    /**
     * 设置确定的按钮信息
     * @param submitText
     */
    public void setOptionSubmit(String submitText) {
        if (opPicker == null) {
            return;
        }
        opPicker.setSubmit(submitText);
    }

    /**
     * 设置取消的按钮信息
     * @param cancelText
     */
    public void setOptionCancel(String cancelText) {
        if (opPicker == null) {
            return;
        }
        opPicker.setCancel(cancelText);
    }


    /**
     * 判断picker是否显示
     */
    public boolean isOptionShowing() {
        if (opPicker == null) {
            return false;
        }
        return opPicker.isShowing();
    }

    /**
     * 隐藏picker
     */
    public void dismissOption () {
        if (opPicker != null) {
            opPicker.dismiss();
        }
    }

    /**
     * 显示picker
     */
    public void showOption() {
        if (opPicker != null) {
            opPicker.show();
        }
    }
}
