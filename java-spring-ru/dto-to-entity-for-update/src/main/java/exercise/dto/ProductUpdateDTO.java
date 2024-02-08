package exercise.dto;

import lombok.Getter;
import lombok.Setter;

// BEGIN
public class ProductUpdateDTO {
    private long id;
    private int price;
    private String title;

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }
}
// END
