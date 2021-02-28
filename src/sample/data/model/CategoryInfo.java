package sample.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryInfo {
    public Integer id;
    public String categoryName;
    public boolean status;
//    public int user;
}
