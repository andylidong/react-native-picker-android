package com.andy.picker.wheel;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/**
 * Created by Andy.Li on 2016/12/21.
 */

public class ItemSelectedEvent extends Event<ItemSelectedEvent> {

    public static final String EVENT_NAME = "WheelPickerSelected";

    private final String mValue;

    protected ItemSelectedEvent(int viewTag, String value) {
        super(viewTag);
        mValue = value;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putString("data", mValue);
        return eventData;
    }
}
