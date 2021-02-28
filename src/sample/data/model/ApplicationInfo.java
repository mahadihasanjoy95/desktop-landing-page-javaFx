package sample.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationInfo {

    private String applicationName;
    private String version;
    private String asset;
    private boolean status;
    private String webUrl;

    public ApplicationInfo(Long id) {
        this.id = id;
    }

    private boolean isAppStore;
    private boolean isPlayStore;
    private String packageNameAndroid;
    private String packageNameiOS;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationInfo that = (ApplicationInfo) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    private Long id;
    private List<CategoryInfo> categories = new ArrayList<>();
    private String details;
}
