package sample.interfaces;


import sample.data.model.ApplicationInfo;

import java.util.List;

public interface ApplicationListListener {
    void applicationListCompleted(List<ApplicationInfo> applicationInfoList);

    void applicationListFailed(String message);
}
