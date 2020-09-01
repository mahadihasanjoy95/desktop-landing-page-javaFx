package sample.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationListDto {
    public String listType;
    public String categoryId;
    public String deviceResulation;
}
