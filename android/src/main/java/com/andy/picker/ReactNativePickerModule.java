package com.artsvgdemo;

import android.util.Log;

import com.bigkoo.pickerview.TimePickerView;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Andy.Li on 2016/12/20.
 */

public class ReactNativePickerModule extends ReactContextBaseJavaModule {

    private static final String REACT_CLASS = "WheelPicker";

    private TimePickerView pvTime;

    public ReactNativePickerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Log.i(REACT_CLASS, "ReactNativePickerModule == " + reactContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }


    @ReactMethod
    public void toggle(){
        Log.i(REACT_CLASS, "toggle");
        if (getCurrentActivity() != null) {
            getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //时间选择器
                    pvTime = new TimePickerView(getCurrentActivity(), TimePickerView.Type.ALL);
                    // 控制时间范围
                    Calendar calendar = Calendar.getInstance();
                    pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
                    pvTime.setTime(new Date());
                    pvTime.setCyclic(false);
                    pvTime.setCancelable(true);
                    // 判断是否打开了选择器
                    if (pvTime.isShowing()) {
                        pvTime.dismiss();
                    } else {
                        pvTime.show();
                    }
                }
            });
        }
    }

    @ReactMethod
    public void getResult(final Callback callback) {
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                callback.invoke(getTime(date));
            }
        });
    }


    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
