package com.poa.tempscanner.model;

import androidx.lifecycle.MutableLiveData;

public class WebServerData {
    private static WebServerData instance;

    private MutableLiveData<String> mutableLiveData = new MutableLiveData();

    public static WebServerData getInstance() {
        if (instance == null)
            instance = new WebServerData();
        return instance;
    }

    public MutableLiveData<String> getMutableLiveData() {
        return this.mutableLiveData;
    }
}
