package com.skyworth.androidwithh5;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class jsinterface {

    private static final String TAG =  jsinterface.class.getSimpleName();

    private JsBridge jsBridge;

    public jsinterface(JsBridge jsBridge) {
        this.jsBridge = jsBridge;
    }

    @JavascriptInterface
    public void setValue(String value){
        Log.d(TAG, "setValue: "+value);
        jsBridge.setTextViewValue(value);
    }
}
