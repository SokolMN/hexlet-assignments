package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Table(name = "products")
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"manufacture", "model"})
public class Product {

    public Product(){}
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private int price;

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }
}


// END
