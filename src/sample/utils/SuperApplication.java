package sample.utils;

import sample.data.model.ApplicationInfo;

import java.util.List;

public class SuperApplication {

    private SuperApplication() {

    }

    private static SuperApplication INSTANCE = null;
    private List<ApplicationInfo> applicationInfoList;

    public List<ApplicationInfo> getApplicationInfoList() {
        return applicationInfoList;
    }

    public void setApplicationInfoList(List<ApplicationInfo> applicationInfoList) {
        this.applicationInfoList = applicationInfoList;
    }

    public static synchronized SuperApplication getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SuperApplication();
        }
        return INSTANCE;
    }
}
