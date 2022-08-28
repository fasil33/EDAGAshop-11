package dto;

import java.util.List;

import lombok.Data;

@Data
public class ShoeSearchFilterDto {
    List<Integer> prices;
    List<String> shoes;
    List<String> genders;
    String shoeGender;
    String shoe;
}
