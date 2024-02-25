package exercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductParamsDTO {
    private String titleCont;
    private Long categoryId;
    private Integer priceLt;
    private Integer priceGt;
    private Double ratingGt;

    public Long getCategoryId() {
        return categoryId;
    }

    public Integer getPriceGt() {
        return priceGt;
    }

    public Integer getPriceLt() {
        return priceLt;
    }

    public Double getRatingGt() {
        return ratingGt;
    }

    public String getTitleCont() {
        return titleCont;
    }
}
