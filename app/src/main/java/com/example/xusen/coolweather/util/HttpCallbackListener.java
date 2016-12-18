package com.example.xusen.coolweather.util;

/**
 * Created by xusen on 2016/12/18.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
