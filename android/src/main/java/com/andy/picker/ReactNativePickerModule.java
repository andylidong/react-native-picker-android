package com.andy.picker;

import android.text.TextUtils;
import android.util.Log;

import com.andy.picker.wheel.ItemSelectedEvent;
import com.andy.picker.wheel.WheelTimeType;
import com.andy.picker.wheel.WheelType;
import com.bigkoo.pickerview.TimePickerView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Andy.Li on 2016/12/20.
 */

public class ReactNativePickerModule extends SimpleViewManager<WheelType> {

    private static final String REACT_CLASS = "WheelPicker";

    private ReactContext reactContext = null;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected WheelType createViewInstance(ThemedReactContext context) {
        reactContext = context;
        return new WheelType(context);
    }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                ItemSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onValueChange")
        );
    }

    /***********************************时间选择器***********************************************/

    /**
     * 暴露给js层调用的属性，显示picker的时间类型
     */
    @ReactProp(name = "initTime")
    public void setInitTime(final WheelType wtPicker, final ReadableMap items) {
        Log.i(REACT_CLASS, "initTime ============" + items);
        if (reactContext == null) return;
        reactContext.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TimePickerView.Type type = TimePickerView.Type.ALL;
                // 判断picker的类型
                if (wtPicker != null) {
                    // 处理picker的类型
                    int typeTemp = items.hasKey("init") ? items.getInt("init") : 1;
                    String timeFormatter = "";
                    switch (typeTemp) {
                        case WheelTimeType.ALL:
                            type = TimePickerView.Type.ALL;
                            timeFormatter = WheelTimeType.ALL_S;
                            break;
                        case WheelTimeType.YEAR_MONTH_DAY:
                            type = TimePickerView.Type.YEAR_MONTH_DAY;
                            timeFormatter = WheelTimeType.YEAR_MONTH_DAY_S;
                            break;
                        case WheelTimeType.HOURS_MINS:
                            type = TimePickerView.Type.HOURS_MINS;
                            timeFormatter = WheelTimeType.HOURS_MINS_S;
                            break;
                        case WheelTimeType.MONTH_DAY_HOUR_MIN:
                            type = TimePickerView.Type.MONTH_DAY_HOUR_MIN;
                            timeFormatter = WheelTimeType.MONTH_DAY_HOUR_MIN_S;
                            break;
                        case WheelTimeType.YEAR_MONTH:
                            type = TimePickerView.Type.YEAR_MONTH;
                            timeFormatter = WheelTimeType.YEAR_MONTH_S;
                            break;
                    }
                    wtPicker.setTimeInit(type);
                    // 设置时间的开始年份和结束年份
                    String data = items.hasKey("data") ? items.getString("data") : "2015-2018";
                    String dataTemp[] = data.split("-");
                    wtPicker.setTimeData(Integer.parseInt(dataTemp[0]), Integer.parseInt(dataTemp[1]));
                    // 设置picker弹出之后选中的时间
                    SimpleDateFormat formatter = new SimpleDateFormat(timeFormatter);
                    Date time = null;
                    try {
                        time = items.hasKey("time") ? formatter.parse(items.getString("time")) : new Date();
                    } catch (ParseException e) {
                        time = new Date();
                    }
                    wtPicker.setTimeFormatter(timeFormatter);
                    wtPicker.setTime(time);
                    // 设置数据是否循环
                    wtPicker.setTimeCyclic(false);
                    // 设置点击色区域是否显示
                    wtPicker.setTimeCancelable(true);
                    // 设置picker的选中值
                    int isShow = items.hasKey("selectedTime") ? items.getInt("selectedTime") : 0;
                    if (isShow == 0) {
                        return;
                    }
                    // 判断是否打开了选择器
                    if (wtPicker.isTimeShowing()) {
                        wtPicker.dismissTime();
                    } else {
                        wtPicker.showTime();
                    }
                }
            }
        });
    }

    /***********************************选项选择器***********************************************/

    /**
     * 暴露给js层调用的属性，显示picker的选项类型
     */
    @ReactProp(name = "initOption")
    public void setInitOption(final WheelType wtPicker, final ReadableMap items) {
        Log.i(REACT_CLASS, "initOption ============" + items);
        if (reactContext == null) return;
        reactContext.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (wtPicker != null) {
                    // 设置初始化信息
                    ReadableArray optionsItem = items.hasKey("data") ? items.getArray("data") : null;
                    // 没有数据则不显示
                    if (optionsItem == null) {
                        return;
                    }
                    ArrayList<String> options = new ArrayList<String>();
                    for (int i = 0; i < optionsItem.size(); i++) {
                        options.add(optionsItem.getString(i));
                    }
                    wtPicker.setOptionInit(options);
                    // 设置picker的数据
                    wtPicker.setPicker(options);
                    // 设置picker的选中值
                    String optionsSelected = items.hasKey("options") ? items.getString("options") : options.get(0);
                    wtPicker.setOptions(options, optionsSelected);
                    // 设置数据是否循环
                    wtPicker.setOptionCyclic(false);
                    // 设置点击色区域是否显示
                    wtPicker.setOptionCancelable(true);
                    // 设置picker的选中值
                    int isShow = items.hasKey("selectedOption") ? items.getInt("selectedOption") : 0;
                    if (isShow == 0) {
                       return;
                    }
                    // 判断是否打开了选择器
                    if (wtPicker.isOptionShowing()) {
                        wtPicker.dismissOption();
                    } else {
                        wtPicker.showOption();
                    }
                }
            }
        });
    }

}

