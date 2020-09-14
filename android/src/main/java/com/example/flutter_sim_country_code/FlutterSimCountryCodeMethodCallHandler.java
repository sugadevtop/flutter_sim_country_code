package com.example.flutter_sim_country_code;

import android.content.Context;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class FlutterSimCountryCodeMethodCallHandler implements MethodChannel.MethodCallHandler {

    private final Context mContext;

    public FlutterSimCountryCodeMethodCallHandler(Context context) {
        this.mContext = context;
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call,@NonNull MethodChannel.Result result) {

        if (call.method.equals("getSimCountryCode")) {
            getSimCountryCode(result);
        } else {
            result.notImplemented();
        }
    }

    private void getSimCountryCode(MethodChannel.Result result) {
        TelephonyManager manager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            String countryId = manager.getSimCountryIso();
            if (countryId != null) {
                result.success(countryId.toUpperCase());
                return;
            }
        }
        result.error("SIM_COUNTRY_CODE_ERROR", null, null);
    }
}
