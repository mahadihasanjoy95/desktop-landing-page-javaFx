package sample.utils;

import sample.data.model.ApplicationInfo;
import sample.data.model.Bookmarks;
import sample.data.model.UserDetails;

import java.util.List;

public class SuperApplication {

    private SuperApplication() {

    }

    private static SuperApplication INSTANCE = null;
    private List<ApplicationInfo> applicationInfoList;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    private UserDetails userDetails;

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
