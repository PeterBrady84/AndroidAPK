package com.peter.androidapk;

/*
 * Created by Peter Brady on 06/04/2017.
 * X00115070
 */

class AndroidAPI {
    private String name;
    private String platformVersion;
    private int apiLevel;
    private String year;

    // constructor
    AndroidAPI(String name, String platformVersion, int apiLevel, String year) {
        this.name = name;
        this.platformVersion = platformVersion;
        this.apiLevel = apiLevel;
        this.year = year;
    }

    String getName() {
        return name;
    }

    String getPlatformVersion() {
        return platformVersion;
    }

    int getApiLevel() {
        return apiLevel;
    }

    String getYear() {
        return year;
    }
}
