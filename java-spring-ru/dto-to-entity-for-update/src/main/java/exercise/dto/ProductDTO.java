package exercise.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProductDTO {
    private long id;
    private String title;
    private int price;
    private long vendorCode;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public long getVendorCode() {
        return vendorCode;
    }

    public int getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setVendorCode(long vendorCode) {
        this.vendorCode = vendorCode;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }
}
