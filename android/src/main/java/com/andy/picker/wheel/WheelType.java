package com.andy.picker.wheel;

import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
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

    private ReactContext reactContext;

    // 选项选择器
    private OptionsPickerView opPicker;

    // 时间选择器
    private TimePickerView wtPicker;

    // 时间的格式
    private static String timeFormatter;

    public WheelType(ReactContext reactContext) {
        super(reactContext);
        mEventDispatcher = reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        if (reactContext.getCurrentActivity() != null) {
            this.reactContext = reactContext;
        }
    }

    /**
     * 设置时间picker显示的类型
     * @param type
     */
    public void setTimeInit(TimePickerView.Type type) {
        if (wtPicker != null) {
            wtPicker = null;
        }
        wtPicker = new TimePickerView(reactContext.getCurrentActivity(), type);
        // 设置标题的内容
        setTimeTitle("");
        // 获取选中的信息
        wtPicker.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
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
        if (wtPicker == null) {
            return;
        }
        // 设置数据的范围 要在setTime 之前才有效果哦
        wtPicker.setRange(startYear, endYear);
    }

    /**
     * 设置picker弹出之后选中的时间
     * @param date
     */
    public void setTime(Date date) {
        if (wtPicker == null) {
            return;
        }
        // 设置选中的时间
        wtPicker.setTime(date, timeFormatter);
    }

    /**
     * 设置标题的显示信息
     * @param title
     */
    public void setTimeTitle(String title) {
        if (wtPicker == null) {
            return;
        }
        wtPicker.setTitle(title);
    }

    /**
     * 设置数据是否循环
     * @param isCyclic
     */
    public void setTimeCyclic(boolean isCyclic) {
        if (wtPicker == null) {
            return;
        }
        wtPicker.setCyclic(isCyclic);
    }


    /**
     * 设置点击色区域是否显示
     * @param isCancelable
     */
    public void setTimeCancelable(boolean isCancelable) {
        if (wtPicker == null) {
            return;
        }
        wtPicker.setCancelable(isCancelable);
    }


    /**
     * 判断picker是否显示
     */
    public boolean isTimeShowing() {
        if (wtPicker == null) {
            return false;
        }
        return wtPicker.isShowing();
    }

    /**
     * 隐藏picker
     */
    public void dismissTime () {
        if (wtPicker != null) {
            wtPicker.dismiss();
        }
    }

    /**
     * 显示picker
     */
    public void showTime() {
        if (wtPicker != null) {
            wtPicker.show();
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
    public void setOptionInit(final ArrayList<String> options) {
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
